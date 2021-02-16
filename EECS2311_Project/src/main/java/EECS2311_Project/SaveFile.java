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
import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class SaveFile extends JFrame {
	
	protected Component parent;
	

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
		
		JLabel saveLabel = new JLabel("Would you like to save the file?");
		saveLabel.setBounds(120, 315, 668, 71);
		saveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		saveLabel.setFont(new Font("Calibri", Font.BOLD, 35));
		saveLabel.setBackground(new Color(248, 248, 255));
		saveFile.add(saveLabel);
		
		Button Yesbutton = new Button("YES");
		Yesbutton.setBounds(159, 496, 148, 97);
		Yesbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fileChooser = new JFileChooser();
			        fileChooser.setSelectedFile(new File("guitarTab.musicxml"));
			        fileChooser.setFileFilter(new FileNameExtensionFilter("musicxml file","musicxml"));
			        int userSelection = fileChooser.showSaveDialog(SaveFile.this);
			        File file = null;
			        if (userSelection == JFileChooser.APPROVE_OPTION) {
			            file = fileChooser.getSelectedFile();
			            setVisible(false);
			      
				}
			}
		});
		Yesbutton.setFont(new Font("Calibri", Font.PLAIN, 20));
		saveFile.add(Yesbutton);
		
		Button NObutton = new Button("NO");
		NObutton.setBounds(604, 496, 148, 97);
		NObutton.setFont(new Font("Calibri", Font.PLAIN, 20));
		NObutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		saveFile.add(NObutton);
		
		JLabel filereadyLabel = new JLabel("YOUR FILE IS READY!");
		filereadyLabel.setFont(new Font("Calibri", Font.BOLD, 68));
		filereadyLabel.setBounds(174, 88, 694, 92);
		saveFile.add(filereadyLabel);
	}
}
