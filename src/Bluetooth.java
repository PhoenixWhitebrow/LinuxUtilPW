import java.io.IOException;
import java.util.ArrayList;

public class Bluetooth extends BashScript{

    public static boolean connectBtDevice(BTDevice btd) throws IOException, InterruptedException {

        System.out.println(btd.getMac());
        return executeExpectScript("connect-bt.exp", btd.getMac());
    }

    public static ArrayList<BTDevice> getBluetoothDevices() throws Exception {
        BashCommand listDevices = new BashCommand("bluetoothctl", "devices", "|", "sort", "-r");
        ArrayList<BTDevice> list = new ArrayList<>();

        String[] output = executeListCommand(listDevices);
        for (int i = 0; i < output.length; i++ ) {

            String[] arr = output[i].split("\s");

            StringBuilder sb = new StringBuilder();
            for (int j = 2; j < arr.length; j++ ) {
                sb.append(arr[j]).append("\s");
            }

            String mac = arr[1];
            String name = sb.toString();
            BTDevice dev = new BTDevice(mac, name);

            list.add(dev);
        }
        return list;
    }
}
