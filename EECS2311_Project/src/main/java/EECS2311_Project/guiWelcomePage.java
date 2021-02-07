package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class guiWelcomePage extends JFrame {

	private JPanel openingWindow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					guiWelcomePage frame = new guiWelcomePage();
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
	public guiWelcomePage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		openingWindow = new JPanel();
		openingWindow.setBackground(new Color(248, 248, 255));
		openingWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(openingWindow);
		openingWindow.setLayout(null);
		
		JLabel companyNameLabel = new JLabel("TAB2MusicXML");
		companyNameLabel.setBounds(5, 5, 895, 106);
		companyNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyNameLabel.setFont(new Font("Calibri", Font.BOLD, 86));
		openingWindow.add(companyNameLabel);
		
		JButton pasteWindowButton = new JButton("Paste Tab");
		pasteWindowButton.setFont(new Font("Calibri", Font.BOLD, 30));
		pasteWindowButton.setBackground(SystemColor.activeCaption);
		pasteWindowButton.setBounds(534, 304, 277, 75);
		openingWindow.add(pasteWindowButton);
		guiFileUploadPage m=new guiFileUploadPage();
		JButton btnUploadTab = new JButton("Upload Tab");
		btnUploadTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				m.setVisible(true);
			}
		});
		btnUploadTab.setFont(new Font("Calibri", Font.BOLD, 30));
		btnUploadTab.setBackground(SystemColor.activeCaption);
		btnUploadTab.setBounds(107, 304, 277, 75);
		openingWindow.add(btnUploadTab);
	}
}
