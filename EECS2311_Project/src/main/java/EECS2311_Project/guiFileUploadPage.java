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
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import java.awt.TextArea;

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
		//SaveFile s = new SaveFile();
		
		JLabel messageLabel = new JLabel("No file currently selected");
		messageLabel.setBackground(new Color(255, 255, 255));
		messageLabel.setFont(new Font("Calibri", Font.ITALIC, 20));
		messageLabel.setOpaque(true);
		messageLabel.setForeground(Color.LIGHT_GRAY);
		messageLabel.setBounds(47, 148, 602, 42);
		uploadFileWindow.add(messageLabel);
		
		JTextArea textArea1 = new JTextArea();
		textArea1.setBounds(47, 215, 823, 404);
		uploadFileWindow.add(textArea1);
		
		JButton browseButton = new JButton("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String fileName= f.getAbsolutePath();
				try {
					FileReader reader = new FileReader(fileName);
					BufferedReader br = new BufferedReader(reader);
					textArea1.read(br, null);
					br.close();
					textArea1.requestFocus();
					messageLabel.setText(fileName.toString());
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(null, e);
				}
			} 
		});
		browseButton.setBackground(SystemColor.activeCaption);
		browseButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		browseButton.setBounds(688, 148, 171, 42);
		uploadFileWindow.add(browseButton);
		
		JLabel instructionLabel1 = new JLabel("Pick a file from your file explorer of type .txt that contains your");
		instructionLabel1.setToolTipText("");
		instructionLabel1.setFont(new Font("Calibri", Font.PLAIN, 30));
		instructionLabel1.setBounds(47, 10, 787, 85);
		uploadFileWindow.add(instructionLabel1);
		
		JLabel instructionLabel2 = new JLabel("tablature music.");
		instructionLabel2.setFont(new Font("Calibri", Font.PLAIN, 30));
		instructionLabel2.setBounds(47, 75, 787, 63);
		uploadFileWindow.add(instructionLabel2);
		
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				modifications f = new modifications(filePath);
//				f.setVisible(true);
				String pastedTab = textArea1.getText();
				//System.out.println(pastedTab);
				File file = new File("temp.txt");
				try {
					FileWriter fw = new FileWriter(file);
					fw.write(pastedTab);
					fw.close();
					Main.start(file.toString(), "","");
					file.delete();
					//s.setVisible(true);
				} catch (Exception exception) {
					//throw error screen here.
				}
				setVisible(false);
				//s.setVisible(true);
			}
		});
		enterButton.setBackground(SystemColor.activeCaption);
		enterButton.setFont(new Font("Calibri", Font.BOLD, 30));
		enterButton.setBounds(345, 673, 261, 85);
		uploadFileWindow.add(enterButton);
		
		
	}
}
