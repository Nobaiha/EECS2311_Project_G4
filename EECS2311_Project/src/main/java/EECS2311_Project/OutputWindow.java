package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;

public class OutputWindow extends JFrame {

	private JPanel outputOptionsLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OutputWindow frame = new OutputWindow();
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
	public OutputWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		outputOptionsLabel = new JPanel();
		outputOptionsLabel.setBackground(new Color(248, 248, 255));
		outputOptionsLabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(outputOptionsLabel);
		outputOptionsLabel.setLayout(null);
		
		JLabel fileOutputTitle = new JLabel("This is your file!");
		fileOutputTitle.setFont(new Font("Calibri", Font.BOLD, 80));
		fileOutputTitle.setBounds(165, 45, 598, 100);
		outputOptionsLabel.add(fileOutputTitle);
		
		JLabel instructionsOnOutput = new JLabel("Click download to view your file");
		instructionsOnOutput.setFont(new Font("Calibri", Font.PLAIN, 28));
		instructionsOnOutput.setBounds(258, 284, 373, 42);
		outputOptionsLabel.add(instructionsOnOutput);
		
		JButton downloadButton = new JButton("Download");
		downloadButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		downloadButton.setBackground(SystemColor.activeCaption);
		downloadButton.setBounds(317, 471, 248, 75);
		outputOptionsLabel.add(downloadButton);
	}

}
