public class SecondMonitor extends BashScript{

    public static void activateSecondMonitor(String mon1, String mon2, String mon2res) throws Exception {
        BashCommand mon1resolution = new BashCommand("xrandr", "--output", mon1, "--mode", mon2res);
        BashCommand mon2up = new BashCommand("xrandr", "--output", mon2, "--auto", "--same-as", mon1);

        executeCommand(mon1resolution);
        executeCommand(mon2up);
    }

    public static void deactivateSecondMonitor(String mon1, String mon2, String mon1res) throws Exception {
        BashCommand mon1restore = new BashCommand("xrandr", "--output", mon1, "--mode", mon1res);
        BashCommand mon2down = new BashCommand("xrandr", "--output", mon2, "--off");

        executeCommand(mon2down);
        executeCommand(mon1restore);
    }

    public static Boolean secondMonitorStatus(String mon2out, String mon2res) throws Exception {
        BashCommand mon2stat = new BashCommand("xrandr");
        String grep = String.format("%s connected %s", mon2out, mon2res);

        return executeGrepCommand(mon2stat, grep);
    }
}
