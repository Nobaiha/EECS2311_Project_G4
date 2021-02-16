package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class guiFileUploadPage extends JFrame {

	private JPanel uploadFileWindow;
	protected Component parent;
	private String filePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiFileUploadPage frame = new guiFileUploadPage();
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
	public guiFileUploadPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		uploadFileWindow = new JPanel();
		uploadFileWindow.setBackground(new Color(248, 248, 255));
		uploadFileWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(uploadFileWindow);
		uploadFileWindow.setLayout(null);
		
		JLabel messageLabel = new JLabel("No file currently selected");
		messageLabel.setBackground(new Color(255, 255, 255));
		messageLabel.setFont(new Font("Calibri", Font.ITALIC, 20));
		messageLabel.setOpaque(true);
		messageLabel.setForeground(Color.LIGHT_GRAY);
		messageLabel.setBounds(47, 318, 602, 42);
		uploadFileWindow.add(messageLabel);
		
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser browseFile = new JFileChooser();
				browseFile.setCurrentDirectory(new File (System.getProperty("user.home")));
				int uploadFile = browseFile.showOpenDialog(parent);
				if (uploadFile == JFileChooser.APPROVE_OPTION) {
				    File chosenFile = browseFile.getSelectedFile();
				    messageLabel.setText(chosenFile.getAbsolutePath());
				    filePath = chosenFile.getAbsolutePath();
				}
				
				
			}
		});
		browseButton.setBackground(SystemColor.activeCaption);
		browseButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		browseButton.setBounds(650, 318, 171, 42);
		uploadFileWindow.add(browseButton);
		
		JLabel instructionLabel1 = new JLabel("Pick a file from your file explorer of type .txt that contains your");
		instructionLabel1.setToolTipText("");
		instructionLabel1.setFont(new Font("Calibri", Font.PLAIN, 30));
		instructionLabel1.setBounds(47, 70, 787, 85);
		uploadFileWindow.add(instructionLabel1);
		
		JLabel instructionLabel2 = new JLabel("tablature music.");
		instructionLabel2.setFont(new Font("Calibri", Font.PLAIN, 30));
		instructionLabel2.setBounds(47, 129, 787, 63);
		uploadFileWindow.add(instructionLabel2);
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifications f = new modifications(filePath);
				f.setVisible(true);
				setVisible(false);
			}
		});
		enterButton.setBackground(SystemColor.activeCaption);
		enterButton.setFont(new Font("Calibri", Font.BOLD, 30));
		enterButton.setBounds(343, 571, 261, 85);
		uploadFileWindow.add(enterButton);
	}
}
