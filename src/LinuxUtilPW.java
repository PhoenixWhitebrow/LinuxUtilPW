import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LinuxUtilPW extends JFrame{
    private JPanel mainPanel;
    private JLabel sudoPassLabel;
    private JPasswordField sudoPassField;
    private JLabel wgLabel;
    private JLabel wgConfigLabel;
    private JTextField wgConfigField;
    private JButton wgUpButton;
    private JButton wgDownButton;
    private JLabel wgInternetconnectionLabel;
    private JTextField wgInternetconnectionField;
    private JLabel wgStatus;
    private JLabel secondMonLabel;
    private JLabel firstMonOutLabel;
    private JLabel secMonOutLabel;
    private JTextField firstMonOutField;
    private JTextField secMonOutField;
    private JTextField firstMonResField;
    private JTextField secMonResField;
    private JButton secondMonOnButton;
    private JButton secondMonOffButton;
    private JLabel resolutionLabel;
    private JLabel secondMonStatus;
    private JButton btConnectButton;
    private JLabel btConnectLabel;
    private JTextField btMacField;
    private JLabel btMacLabel;
    private JLabel btConnectStatus;

    Color uiGreen = new Color(0, 137, 50);
    Color uiRed = new Color(223, 21, 45);

    static String version = "1.3";
    String sudoPass = "";
    String internetConnection = "";
    String wgConfig = "";
    String mon1out = "";
    String mon2out = "";
    String mon1res = "";
    String mon2res = "";
    String btMac = "";


    public LinuxUtilPW(String title) throws Exception {

        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        // default icon replacement
        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png"));
        this.setIconImage(image);

        // initial wg status check
        if (Wireguard.wgInitStatus(wgConfigField.getText())) {
            wgStatus.setText("UP");
            wgStatus.setForeground(uiGreen);
        } else {
            wgStatus.setText("DOWN");
            wgStatus.setForeground(uiRed);
        }

        wgUpButton.addActionListener(e -> {
            // retrieving data from forms
            wgConfig = wgConfigField.getText();
            sudoPass = sudoPassField.getText();

            try {
                Wireguard.wgUp(wgConfig, sudoPass);
                if (Wireguard.wgStatus(wgConfig, sudoPass)) {
                    wgStatus.setText("UP");
                    wgStatus.setForeground(uiGreen);
                } else {
                    wgStatus.setText("FAILED (pass?)");
                    wgStatus.setForeground(uiRed);
                }
            } catch (Exception ex) {
                wgStatus.setText("FAILED (pass?)");
                wgStatus.setForeground(uiRed);
                throw new RuntimeException(ex);
            }
        });

        wgDownButton.addActionListener(e -> {
            // retrieving data from forms
            wgConfig = wgConfigField.getText();
            internetConnection = "'" + wgInternetconnectionField.getText() + "'";
            sudoPass = sudoPassField.getText();

            try {
                Wireguard.wgDown(wgConfig,internetConnection, sudoPass);
                if (!Wireguard.wgStatus(wgConfig, sudoPass)) {
                    wgStatus.setText("DOWN");
                    wgStatus.setForeground(uiRed);
                } else {
                    wgStatus.setText("FAILED (pass?)");
                    wgStatus.setForeground(uiRed);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        // initial second monitor status check
        if (SecondMonitor.secondMonitorStatus(secMonOutField.getText(), secMonResField.getText())) {
            secondMonStatus.setText("ON");
            secondMonStatus.setForeground(uiGreen);
        } else {
            secondMonStatus.setText("OFF");
            secondMonStatus.setForeground(uiRed);
        }

        secondMonOnButton.addActionListener(e -> {
            // retrieving data from forms
            mon1out = firstMonOutField.getText();
            mon2out = secMonOutField.getText();
            mon2res = secMonResField.getText();

            try {
                SecondMonitor.activateSecondMonitor(mon1out, mon2out, mon2res);
            if (SecondMonitor.secondMonitorStatus(mon2out,mon2res)) {
                secondMonStatus.setText("ON");
                secondMonStatus.setForeground(uiGreen);
            } else {
                secondMonStatus.setText("OFF");
                secondMonStatus.setForeground(uiRed);
            }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        secondMonOffButton.addActionListener(e -> {
            // retrieving data from forms
            mon1out = firstMonOutField.getText();
            mon2out = secMonOutField.getText();
            mon1res = firstMonResField.getText();

            try {
                SecondMonitor.deactivateSecondMonitor(mon1out, mon2out, mon1res);
                if (SecondMonitor.secondMonitorStatus(mon2out,mon2res)) {
                    secondMonStatus.setText("ON");
                    secondMonStatus.setForeground(uiGreen);
                } else {
                    secondMonStatus.setText("OFF");
                    secondMonStatus.setForeground(uiRed);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        btConnectButton.addActionListener(e -> {
            // retrieving data from forms
            btMac = btMacField.getText();

            try {
                if (Bluetooth.connectBtDevice(btMac) == true) {
                    btConnectStatus.setText("OK");
                    btConnectStatus.setForeground(uiGreen);
                } else {
                    btConnectStatus.setText("FAIL");
                    btConnectStatus.setForeground(uiRed);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private static void setTheme() {
        try {
            UIManager.put("control", new Color(242, 242, 247));
            UIManager.put("Button.background", new Color(209, 209, 214));
            UIManager.put("Button.foreground", new Color(174, 174, 178));
            UIManager.put("info", new Color(255, 255, 200));
            UIManager.put("nimbusBase", new Color(142, 142, 147));
            UIManager.put("nimbusAlertYellow", new Color(255, 214, 0));
            UIManager.put("nimbusDisabledText", new Color(58, 58, 60));
            UIManager.put("nimbusFocus", new Color(0, 137, 50));
            UIManager.put("nimbusGreen", new Color(0, 137, 50));
            UIManager.put("nimbusInfoBlue", new Color(0, 136, 255));
            UIManager.put("nimbusLightBackground", new Color(255, 255, 255));
            UIManager.put("nimbusOrange", new Color(255, 141, 40));
            UIManager.put("nimbusRed", new Color(223, 41, 45));
            UIManager.put("nimbusSelectedText", new Color(255, 255, 255));
            UIManager.put("nimbusSelectionBackground", new Color(0, 137, 50));
            UIManager.put("text", new Color(28, 28, 30));
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Handle exceptions
            System.err.println("Exception caught:" + e);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        setTheme();
        JFrame frame = new LinuxUtilPW("Phoenix's Linux Util v" + version);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
