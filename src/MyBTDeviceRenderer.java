import javax.swing.*;
import java.awt.*;

public class MyBTDeviceRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value instanceof BTDevice) {
            BTDevice dev = (BTDevice) value;
            if (index == -1) { // When rendering the selected item in the JComboBox itself
                setText(dev.getName() + "| " + dev.getMac());
            } else { // When rendering items in the dropdown list
                setText(dev.getName() + "| " + dev.getMac());
            }
        }
        return this;
    }
}
