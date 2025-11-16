import java.io.IOException;

public class Bluetooth extends BashScript{

    public static boolean connectBtDevice(String mac) throws IOException, InterruptedException {

        return BashScript.executeExpectScript("connect-bt.exp", mac);
    }
}
