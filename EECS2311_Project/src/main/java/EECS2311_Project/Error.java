package EECS2311_Project;

import javax.swing.*;

/**
 * A class that deals with input error by user.
 * 
 * @author Team 4 EECS2311 Winter 2021
 */
public class Error {

	/**
	 * Displays an error message to the user, along with other information.
	 *
	 * @param error    the error message to be displayed.
	 * @param title    the title of the tablature.
	 * @param composer the composer of the tablature.
	 * @param content  the content of the tablature.
	 */
	public Error(String error, String title, String composer, String content) {
		int input = JOptionPane.showOptionDialog(null, error, "Error", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);
		if (input == JOptionPane.OK_OPTION) {
			JOptionPane.getRootFrame().dispose();
			new GuiUploadWindow(title, composer, content).setVisible(true);
		} else {
			System.exit(0);
		}
	}

	/**
	 * Displays an error message to the user.
	 */
	public Error(String error) {
		int input = JOptionPane.showOptionDialog(null, error, "Error", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, null, null);
		System.exit(0);
	}

}
