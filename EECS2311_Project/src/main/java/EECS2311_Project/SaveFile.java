package EECS2311_Project;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

/**
 * The class that operates the save file window of the GUI.
 *
 * @author Team 4 EECS2311 Winter 2021
 */
public class SaveFile extends JFrame {

	protected Component parent;
	private static String xml;
	private static String title;
	private static String composer;
	private static String content;

	private JPanel saveFile;

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveFile frame = new SaveFile(title, composer, content, xml);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame for the saving page.
	 * 
	 * @param title    tablature's title entered by user
	 * @param composer tablature's composer entered by user
	 * @param content  tablature content from conversion
	 * @param xml      final output language of the tablature
	 */
	public SaveFile(String title, String composer, String content, String xml) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		saveFile = new JPanel();
		saveFile.setBackground(new Color(248, 248, 255));
		saveFile.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(saveFile);
		saveFile.setLayout(null);

		JTextArea tabDisplayTextArea = new JTextArea();
		tabDisplayTextArea.setFont(new Font("Courier New", Font.PLAIN, 22));
		JScrollPane sp = new JScrollPane(tabDisplayTextArea);
		sp.setBounds(10, 159, 885, 459);
		saveFile.add(sp);
		tabDisplayTextArea.setText(xml);
		tabDisplayTextArea.setCaretPosition(0);

		Button yesButton = new Button("Download");

		yesButton.setBounds(363, 638, 178, 43);
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setSelectedFile(new File("guitarTab.musicxml"));
				fileChooser.setFileFilter(new FileNameExtensionFilter("musicxml file", "musicxml"));
				int userSelection = fileChooser.showSaveDialog(SaveFile.this);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					Main.saveFile(file, tabDisplayTextArea.getText());

				}
			}
		});
		yesButton.setFont(new Font("Calibri", Font.PLAIN, 23));
		saveFile.add(yesButton);

		Button noButton = new Button("Exit");
		noButton.setBounds(717, 638, 178, 43);
		noButton.setFont(new Font("Calibri", Font.PLAIN, 23));
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				System.exit(0);
			}
		});
		saveFile.add(noButton);

		JLabel fileReadyLabel = new JLabel("Your musicXML file is ready!");
		fileReadyLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		fileReadyLabel.setBounds(10, 100, 895, 49);
		saveFile.add(fileReadyLabel);

		Button button = new Button("Edit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GuiUploadWindow(title, composer, content).setVisible(true);
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 23));
		button.setBounds(10, 638, 178, 43);
		saveFile.add(button);

		JLabel lblTab = new JLabel(" TAB - 2 - MusicXML\u2122 Save File");
		lblTab.setOpaque(true);
		lblTab.setHorizontalAlignment(SwingConstants.LEFT);
		lblTab.setForeground(Color.WHITE);
		lblTab.setFont(new Font("Poor Richard", Font.BOLD, 40));
		lblTab.setBackground(new Color(153, 153, 204));
		lblTab.setBounds(0, 0, 905, 67);
		saveFile.add(lblTab);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Poor Richard", Font.BOLD, 40));
		lblNewLabel_1.setBackground(new Color(153, 153, 204));
		lblNewLabel_1.setBounds(0, 701, 905, 67);
		saveFile.add(lblNewLabel_1);
	}
}
