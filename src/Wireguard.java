public class Wireguard extends BashScript{

    public static void wgUp(String config, String sudoPass) throws Exception {
        BashCommand resolvconf = new BashCommand("resolvconf", "-u");
        BashCommand wgUp = new BashCommand("wg-quick", "up", config);

        executeSudoCommand(resolvconf, sudoPass);
        executeSudoCommand(wgUp, sudoPass);
    }

    public static void wgDown(String config, String internetConnection, String sudoPass) throws Exception {
        BashCommand wgDown = new BashCommand("wg-quick", "down", config);
        BashCommand connUp = new BashCommand("nmcli", "connection", "up", internetConnection);
        BashCommand connDown = new BashCommand("nmcli", "connection", "down", internetConnection);

        executeSudoCommand(wgDown, sudoPass);
        executeCommand(connDown);
        executeCommand(connUp);
    }

    public static Boolean wgStatus(String wgConn,String sudoPass) throws Exception {
        BashCommand wgStat = new BashCommand("wg", "show");

        return executeSudoGrepCommand(wgStat, sudoPass, wgConn);
    }

    public static Boolean wgInitStatus(String wgConn) throws Exception {
        BashCommand wgStat = new BashCommand("wg", "show");

        return executeGrepCommand(wgStat, wgConn);
    }
}
