public class OBS extends BashScript {

    public static void enableVirtualCamDevice(String sudoPass, Integer obsPort, String obsPass) throws Exception {
        BashCommand enableCam = new BashCommand("modprobe",
                "v4l2loopback", "exclusive_caps=1", "card_label='OBS Virtual Webcam'");
        BashCommand startObs = new BashCommand("obs", "&>", "/dev/null", "&");
        BashCommand virtualCamStart = new BashCommand("obs-cmd",
                "--websocket", String.format("obsws://localhost:%s/%s", obsPort, obsPass),
                "virtual-camera", "start");

        executeSudoCommand(enableCam, sudoPass);
        executeCommand(startObs);
        Thread.sleep(4000);
        executeCommand(virtualCamStart);
    }

    public static void disableVirtualCamera(String sudoPass) throws Exception {
        BashCommand virtualCamStop = new BashCommand("modprobe", "-r", "v4l2loopback");

        executeSudoCommand(virtualCamStop, sudoPass);
    }

    public static boolean virialCamStatus() throws Exception {
        BashCommand checkModule = new BashCommand("lsmod");
        String grep = "v4l2loopback";

        return executeGrepCommand(checkModule, grep);
    }
}
