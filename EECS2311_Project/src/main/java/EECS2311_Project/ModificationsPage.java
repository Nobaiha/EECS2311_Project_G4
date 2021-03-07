package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.Button;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModificationsPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldComposer;
	private JTextField textFieldTitle;

	private static String composer;
	private static String title;
	private static String content;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificationsPage frame = new ModificationsPage(title,composer, content);
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
	public ModificationsPage(String title, String composer, String content) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label standardHeaderLabel = new Label("TAB-2-MusicXML");
		standardHeaderLabel.setForeground(new Color(0, 51, 153));
		standardHeaderLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		standardHeaderLabel.setBounds(10, 10, 372, 49);
		contentPane.add(standardHeaderLabel);
		
		Label tmLabel = new Label("TM");
		tmLabel.setBounds(388, 10, 28, 21);
		contentPane.add(tmLabel);
		
		JLabel instructionLabel1 = new JLabel("This page will allow you to add small modifications to your tab. You will be able\r\n\r\n");
		instructionLabel1.setFont(new Font("Calibri", Font.PLAIN, 20));
		instructionLabel1.setBounds(10, 103, 875, 49);
		contentPane.add(instructionLabel1);
		
		JLabel instructionLabel2 = new JLabel("to enter the composer and title of your tab!");
		instructionLabel2.setFont(new Font("Calibri", Font.PLAIN, 20));
		instructionLabel2.setBounds(10, 150, 865, 49);
		contentPane.add(instructionLabel2);
		
		JLabel composerLabel = new JLabel("Composer:");
		composerLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		composerLabel.setBounds(56, 262, 177, 31);
		contentPane.add(composerLabel);
		
		textFieldComposer = new JTextField();
		textFieldComposer.setFont(new Font("Calibri", Font.PLAIN, 22));
		textFieldComposer.setBounds(243, 252, 463, 49);
		contentPane.add(textFieldComposer);
		textFieldComposer.setColumns(10);
		textFieldComposer.setText(composer);
		
		JLabel titleLabel = new JLabel("Title of Tab:");
		titleLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		titleLabel.setBounds(52, 363, 166, 31);
		contentPane.add(titleLabel);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setFont(new Font("Calibri", Font.PLAIN, 22));
		textFieldTitle.setColumns(10);
		textFieldTitle.setBounds(243, 354, 463, 49);
		contentPane.add(textFieldTitle);
		textFieldTitle.setText(title);
		
		Button enterButton = new Button("Confirm");
		enterButton.setForeground(Color.BLACK);
		enterButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		enterButton.setBackground(SystemColor.menu);
		enterButton.setBounds(340, 597, 224, 41);
		contentPane.add(enterButton);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUploadWindow guiUploadWindow = new GuiUploadWindow(textFieldTitle.getText(), textFieldComposer.getText(),content);
				setVisible(false);
				dispose();
				guiUploadWindow.setVisible(true);
			}
		});
	}
}
