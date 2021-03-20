package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Font;
import java.awt.Label;

import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class SaveFile extends JFrame {

	protected Component parent;
	private static String xml;
	private static String title;
	private static String composer;
	private static String content;

	private JPanel saveFile;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public SaveFile(String title, String composer, String content, String xml) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		saveFile = new JPanel();
		saveFile.setBackground(new Color(248, 248, 255));
		saveFile.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(saveFile);
		saveFile.setLayout(null);
		
		Label label_4 = new Label("TM");
		label_4.setBounds(372, 10, 28, 21);
		saveFile.add(label_4);
		
		Label label_3 = new Label("TAB-2-MusicXML");
		label_3.setForeground(new Color(0, 51, 153));
		label_3.setFont(new Font("Arial Black", Font.BOLD, 35));
		label_3.setBounds(10, 10, 372, 49);
		saveFile.add(label_3);
		
		
		/*JLabel saveLabel = new JLabel("in your computer or would you like to exit?");
		saveLabel.setBounds(10, 179, 885, 49);
		saveLabel.setHorizontalAlignment(SwingConstants.LEFT);
		saveLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		saveLabel.setBackground(new Color(248, 248, 255));
		saveFile.add(saveLabel);*/
		/*Label instructionsLabel1 = new Label("Your music XML file is ready!");
		instructionsLabel1.setFont(new Font("Calibri", Font.PLAIN, 25));
		instructionsLabel1.setBounds(10, 73, 885, 65);
		saveFile.add(instructionsLabel1);*/

		JTextArea tabDisplayTextArea = new JTextArea();
		tabDisplayTextArea.setFont(new Font("Courier New", Font.PLAIN, 22));
		JScrollPane sp = new JScrollPane(tabDisplayTextArea);
		sp.setBounds(10, 159, 885, 459);
		saveFile.add(sp);
		tabDisplayTextArea.setText(xml);
		tabDisplayTextArea.setCaretPosition(0);
		
		Button yesButton = new Button("Download");
		//yesButton.setBounds(151, 268, 249, 69);
		yesButton.setBounds(339, 638, 224, 41);
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setSelectedFile(new File("guitarTab.musicxml"));
			        fileChooser.setFileFilter(new FileNameExtensionFilter("musicxml file","musicxml"));
			        int userSelection = fileChooser.showSaveDialog(SaveFile.this);
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File file = fileChooser.getSelectedFile();
			            Main.saveFile(file, tabDisplayTextArea.getText());
			          //new GuiUploadWindow("","","").setVisible(true);

				}
			}
		});
		yesButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		saveFile.add(yesButton);
		
		Button noButton = new Button("Exit");
		noButton.setBounds(739, 638, 150, 41);
		noButton.setFont(new Font("Calibri", Font.PLAIN, 25));
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
				new GuiUploadWindow(title,composer,content).setVisible(true);
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 25));
		button.setBounds(10, 638, 150, 41);
		saveFile.add(button);
	}
}
