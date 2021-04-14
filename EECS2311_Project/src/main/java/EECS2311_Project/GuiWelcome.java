package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import javax.swing.border.EtchedBorder;

/**
 * The class that operates the welcome screen of the GUI.
 *
 * @author Team 4 EECS2311 Winter 2021
 */
public class GuiWelcome extends JFrame {

	private JPanel contentPane;
	JLabel label = new JLabel("Button wasn't clicked!");

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiWelcome frame = new GuiWelcome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame for the welcome window which the user will see upon first
	 * running the application.
	 */
	public GuiWelcome() {

		label.setName("myLabel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.setBackground(new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		GuiUploadWindow guiUploadWindow = new GuiUploadWindow("", "", "");
		Button startButton = new Button("Start");
		startButton.setBackground(new Color(255, 255, 255));
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				label.setText("Button was clicked!");
				setVisible(false);
				dispose();
				guiUploadWindow.setVisible(true);
			}
		});

		startButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		startButton.setBounds(358, 575, 178, 43);
		contentPane.add(startButton);

		JLabel introLabel1 = new JLabel(
				"Welcome to TAB-2-MusicXML\u2122 (T.2.M) converter that takes TABs of guitar, \r\n");
		introLabel1.setForeground(new Color(102, 102, 255));
		introLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		introLabel1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 24));
		introLabel1.setBounds(33, 300, 838, 49);
		contentPane.add(introLabel1);

		JLabel introLabel2 = new JLabel("bass, and drum music tablature converts them into a musicXML. Enjoy!\r\n");
		introLabel2.setForeground(new Color(102, 102, 255));
		introLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		introLabel2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 24));
		introLabel2.setBounds(33, 359, 838, 49);
		contentPane.add(introLabel2);

		JLabel headerLabel = new JLabel("");
		headerLabel.setForeground(new Color(255, 255, 255));
		headerLabel.setFont(new Font("Poor Richard", Font.BOLD, 50));
		headerLabel.setBackground(new Color(153, 153, 204));
		headerLabel.setBounds(0, 0, 905, 67);
		headerLabel.setOpaque(true);
		contentPane.add(headerLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(52, 438, 801, 11);
		contentPane.add(separator);

		JButton learnMoreButton = new JButton("Learn More");
		learnMoreButton.addMouseListener(new MouseAdapter() {
			public void openWebPage(String url) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e) {
					System.out.println(e.getMessage());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				openWebPage(
						"https://docs.google.com/document/d/1657gNM07LNx6sL1kakaTqn_BVKaRHH7KuUWIAG7X0DE/edit?usp=sharing");
			}
		});
		learnMoreButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		learnMoreButton.setBackground(new Color(255, 255, 255));
		learnMoreButton.setBounds(309, 728, 109, 30);
		contentPane.add(learnMoreButton);

		JButton tutorialButton = new JButton("Tutorial");
		tutorialButton.addMouseListener(new MouseAdapter() {
			public void openWebPage(String url) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				} catch (java.io.IOException e) {
					System.out.println(e.getMessage());
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				openWebPage("https://www.youtube.com/watch?v=BxA4vHd6Ghk&ab_channel=Superepicherp");
			}
		});
		tutorialButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		tutorialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		tutorialButton.setBackground(new Color(255, 255, 255));
		tutorialButton.setBounds(562, 728, 109, 30);
		contentPane.add(tutorialButton);

		JLabel userManualLabel = new JLabel("User manual:\r\n");
		userManualLabel.setForeground(new Color(255, 255, 255));
		userManualLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		userManualLabel.setBounds(189, 727, 109, 30);
		contentPane.add(userManualLabel);

		JLabel liveDemoLabel = new JLabel("Live demo:\r\n");
		liveDemoLabel.setForeground(new Color(255, 255, 255));
		liveDemoLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		liveDemoLabel.setBounds(470, 731, 93, 23);
		contentPane.add(liveDemoLabel);

		JLabel footerLabel = new JLabel("");
		footerLabel.setOpaque(true);
		footerLabel.setForeground(Color.WHITE);
		footerLabel.setFont(new Font("Poor Richard", Font.BOLD, 50));
		footerLabel.setBackground(new Color(153, 153, 204));
		footerLabel.setBounds(0, 701, 905, 67);
		contentPane.add(footerLabel);

		Label instructionLabel = new Label("Click the button below to upload your tablature.");
		instructionLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		instructionLabel.setBounds(229, 490, 537, 43);
		contentPane.add(instructionLabel);

		JLabel titleLabel = new JLabel(" TAB - 2 - MusicXML\u2122\r\n");
		titleLabel.setForeground(new Color(51, 0, 153));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Poor Richard", Font.BOLD, 92));
		titleLabel.setBounds(31, 98, 843, 175);
		contentPane.add(titleLabel);

	}

	/**
	 * Following code fragment for testing purpose setting bounds as width and
	 * height of Start button
	 */
	private int width = 358;
	private int height = 575;

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

}
