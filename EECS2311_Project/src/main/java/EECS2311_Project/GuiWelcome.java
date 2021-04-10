package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import javax.swing.border.EtchedBorder;
import java.awt.Canvas;

public class GuiWelcome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public GuiWelcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		contentPane.setBackground(new Color(248, 248, 255));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//ModificationsPage modificationsPage = new ModificationsPage();
		GuiUploadWindow guiUploadWindow = new GuiUploadWindow("","","");
		Button browseButton = new Button("Start");
		browseButton.setBackground(new Color(255, 255, 255));
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				guiUploadWindow.setVisible(true);
			}
		});
		browseButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		browseButton.setBounds(358, 575, 178, 43);
		contentPane.add(browseButton);

		JLabel lblNewLabel = new JLabel("Welcome to TAB-2-MusicXML\u2122 (T.2.M) converter that takes TABs of guitar \r\n");
		lblNewLabel.setForeground(new Color(102, 102, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel.setBounds(33, 300, 838, 49);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("and bass music converts them into a musicXML. Enjoy!\r\n");
		lblNewLabel_1.setForeground(new Color(102, 102, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 24));
		lblNewLabel_1.setBounds(33, 359, 838, 49);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Poor Richard", Font.BOLD, 50));
		lblNewLabel_2.setBackground(new Color(153, 153, 204));
		lblNewLabel_2.setBounds(0, 0, 905, 67);
		lblNewLabel_2.setOpaque(true);
		contentPane.add(lblNewLabel_2);

		JSeparator separator = new JSeparator();
		separator.setBounds(52, 438, 801, 11);
		contentPane.add(separator);

		JButton btnNewButton = new JButton("Learn More");
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void openWebPage(String url){
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				}
				catch (java.io.IOException e) {
					System.out.println(e.getMessage());
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				openWebPage("https://docs.google.com/document/d/1657gNM07LNx6sL1kakaTqn_BVKaRHH7KuUWIAG7X0DE/edit?usp=sharing");
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(309, 728, 109, 30);
		contentPane.add(btnNewButton);

		JButton btnTutorial = new JButton("Tutorial");
		btnTutorial.addMouseListener(new MouseAdapter() {
			public void openWebPage(String url){
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
				}
				catch (java.io.IOException e) {
					System.out.println(e.getMessage());
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				openWebPage("https://docs.google.com/document/d/1657gNM07LNx6sL1kakaTqn_BVKaRHH7KuUWIAG7X0DE/edit?usp=sharing");
			}
		});
		btnTutorial.setFont(new Font("Calibri", Font.PLAIN, 15));
		btnTutorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnTutorial.setBackground(new Color(255, 255, 255));
		btnTutorial.setBounds(562, 728, 109, 30);
		contentPane.add(btnTutorial);

		JLabel lblNewLabel_3 = new JLabel("User manual:\r\n");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_3.setBounds(189, 727, 109, 30);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Live demo:\r\n");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4.setBounds(470, 731, 93, 23);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_2_2 = new JLabel("");
		lblNewLabel_2_2.setOpaque(true);
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setFont(new Font("Poor Richard", Font.BOLD, 50));
		lblNewLabel_2_2.setBackground(new Color(153, 153, 204));
		lblNewLabel_2_2.setBounds(0, 701, 905, 67);
		contentPane.add(lblNewLabel_2_2);


		Label label = new Label("Click the button below to upload your tablature.");
		label.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		label.setBounds(229, 490, 537, 43);
		contentPane.add(label);

		JLabel lblNewLabel_5 = new JLabel("");
		//lblNewLabel_5.setIcon(new ImageIcon(GuiWelcome.class.getResource("/EECS2311_Project/imgs/Tab-2-MusicXML_Logo_V7.png")));
		lblNewLabel_5.setBounds(91, 54, 702, 220);
		contentPane.add(lblNewLabel_5);
		
		/*JLabel logoImageLabel = new JLabel("");
		logoImageLabel.setIcon(new ImageIcon(GuiWelcome.class.getResource("/EECS2311_Project/imgs/TAB-2-MusicXML.png")));
		logoImageLabel.setBounds(180, 198, 500, 262);
		contentPane.add(logoImageLabel);*/
	}
}
