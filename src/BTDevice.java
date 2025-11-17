public class BTDevice extends Object{
    private String mac = "";
    private String name = "";

    BTDevice(String mac, String name) {
        this.mac = mac;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMac() {
        return mac;
    }
}
