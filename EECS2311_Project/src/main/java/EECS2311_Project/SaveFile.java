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
		JScrollPane outputScroller = new JScrollPane(tabDisplayTextArea);
		outputScroller.setBounds(10, 159, 885, 459);
		saveFile.add(outputScroller);
		tabDisplayTextArea.setText(xml);
		tabDisplayTextArea.setCaretPosition(0);

		Button downloadButton = new Button("Download");

		downloadButton.setBounds(363, 638, 178, 43);
		downloadButton.addActionListener(new ActionListener() {
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
		downloadButton.setFont(new Font("Calibri", Font.PLAIN, 23));
		saveFile.add(downloadButton);

		Button exitButton = new Button("Exit");
		exitButton.setBounds(717, 638, 178, 43);
		exitButton.setFont(new Font("Calibri", Font.PLAIN, 23));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				System.exit(0);
			}
		});
		saveFile.add(exitButton);

		JLabel fileReadyLabel = new JLabel("Your musicXML file is ready!");
		fileReadyLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		fileReadyLabel.setBounds(10, 100, 895, 49);
		saveFile.add(fileReadyLabel);

		Button editButton = new Button("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GuiUploadWindow(title, composer, content).setVisible(true);
			}
		});
		editButton.setFont(new Font("Calibri", Font.PLAIN, 23));
		editButton.setBounds(10, 638, 178, 43);
		saveFile.add(editButton);

		JLabel headerLabel = new JLabel(" TAB - 2 - MusicXML\u2122 Save File");
		headerLabel.setOpaque(true);
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		headerLabel.setBackground(new Color(153, 153, 204));
		headerLabel.setBounds(0, 0, 905, 67);
		saveFile.add(headerLabel);

		JLabel footerLabel = new JLabel("");
		footerLabel.setOpaque(true);
		footerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		footerLabel.setForeground(Color.WHITE);
		footerLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		footerLabel.setBackground(new Color(153, 153, 204));
		footerLabel.setBounds(0, 701, 905, 67);
		saveFile.add(footerLabel);
	}
}
