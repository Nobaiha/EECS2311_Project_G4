package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Label;

import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SaveFile extends JFrame {
	
	protected Component parent;
	private String xml = "";

	private JPanel saveFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveFile frame = new SaveFile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	/**
	 * Create the frame.
	 */
	public SaveFile() {
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
		
		
		JLabel saveLabel = new JLabel("in your computer or would you like to exit?");
		saveLabel.setBounds(10, 179, 885, 49);
		saveLabel.setHorizontalAlignment(SwingConstants.LEFT);
		saveLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		saveLabel.setBackground(new Color(248, 248, 255));
		saveFile.add(saveLabel);
		
		Button Yesbutton = new Button("Save");
		Yesbutton.setBounds(151, 268, 249, 69);
		Yesbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setSelectedFile(new File("guitarTab.musicxml"));
			        fileChooser.setFileFilter(new FileNameExtensionFilter("musicxml file","musicxml"));
			        int userSelection = fileChooser.showSaveDialog(SaveFile.this);
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            File file = fileChooser.getSelectedFile();
			            Main.saveFile(file, xml);
			            setVisible(false);
				}
			}
		});
		Yesbutton.setFont(new Font("Calibri", Font.PLAIN, 20));
		saveFile.add(Yesbutton);
		
		Button Nobutton = new Button("Exit");
		Nobutton.setBounds(526, 268, 249, 69);
		Nobutton.setFont(new Font("Calibri", Font.PLAIN, 20));
		Nobutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				System.exit(0);
			}
		});
		saveFile.add(Nobutton);
		
		JLabel filereadyLabel = new JLabel("Your musicXML file is ready! Would you like to save the file");
		filereadyLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		filereadyLabel.setBounds(10, 139, 895, 49);
		saveFile.add(filereadyLabel);
		
		Button button = new Button("Home");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GuiWelcome().setVisible(true);
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 20));
		button.setBounds(10, 689, 249, 69);
		saveFile.add(button);
	}
}
