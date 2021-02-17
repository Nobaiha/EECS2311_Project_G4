package EECS2311_Project;

import javax.swing.*;

public class Error extends JFrame {
    public Error() {
        int input = JOptionPane.showOptionDialog(null, "We had an issue processing your tab, please retry.", "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (input == JOptionPane.OK_OPTION) {
            new GuiWelcome().setVisible(true);
        } else {
            System.exit(0);
        }
    }
}
