package EECS2311_Project;

import javax.swing.*;

public class Error {
    public Error(String error, String title, String composer,String content) {
        int input = JOptionPane.showOptionDialog(null, error, "Error", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
        if (input == JOptionPane.OK_OPTION) {
            JOptionPane.getRootFrame().dispose();
            new GuiUploadWindow(title,composer,content).setVisible(true);
        } else {
            System.exit(0);
        }
    }
}
