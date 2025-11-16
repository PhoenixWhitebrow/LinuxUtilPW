public class OBS extends BashScript {

    public static void enableVirtualCamDevice(String sudoPass) throws Exception {
        BashCommand enableCam = new BashCommand("modprobe",
                "v4l2loopback", "exclusive_caps=1", "card_label='OBS Virtual Webcam'");
        BashCommand startObs = new BashCommand("obs", "&>", "/dev/null", "&");
        BashCommand virtualCamStart = new BashCommand("obs-cmd",
                "--websocket", "obsws://localhost:4455/spTcRg2KzZPAiqGS",
                "virtual-camera", "start");

        BashScript.executeSudoCommand(enableCam, sudoPass);
        BashScript.executeCommand(startObs);
        Thread.sleep(4000);
        BashScript.executeCommand(virtualCamStart);
    }

    public static void disableVirtualCamera(String sudoPass) throws Exception {
        BashCommand virtualCamStop = new BashCommand("modprobe", "-r", "v4l2loopback");

        BashScript.executeSudoCommand(virtualCamStop, sudoPass);
    }

    public static boolean virialCamStatus() throws Exception {
        BashCommand checkModule = new BashCommand("lsmod");
        String grep = "v4l2loopback";

        return BashScript.executeGrepCommand(checkModule, grep);
    }
}
