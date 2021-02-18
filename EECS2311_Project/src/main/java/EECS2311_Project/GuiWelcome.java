package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
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
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label tmLabel = new Label("TM");
		tmLabel.setBounds(372, 10, 28, 21);
		contentPane.add(tmLabel);
		
		Label standardHeaderLabel = new Label("TAB-2-MusicXML");
		standardHeaderLabel.setForeground(new Color(0, 51, 153));
		standardHeaderLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		standardHeaderLabel.setBounds(10, 10, 372, 49);
		contentPane.add(standardHeaderLabel);
		
		GuiUploadWindow m = new GuiUploadWindow();
		Button browseButton = new Button("Browse Tab");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
                m.setVisible(true);
			}
		});
		browseButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		browseButton.setBounds(151, 555, 249, 69);
		contentPane.add(browseButton);
		
		paste p = new paste();
		Button pasteButton = new Button("Paste Tab");
		pasteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
                p.setVisible(true);
			}
		});
		pasteButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		pasteButton.setBounds(533, 555, 249, 69);
		contentPane.add(pasteButton);
		
		Label instructionLabel2 = new Label("Pick which method you would like to use to upload your tab.");
		instructionLabel2.setFont(new Font("Calibri", Font.PLAIN, 25));
		instructionLabel2.setBounds(10, 466, 885, 49);
		contentPane.add(instructionLabel2);
		
		Label instructionLabel1 = new Label("Welcome to your personal tablature to music XML converter,");
		instructionLabel1.setFont(new Font("Calibri", Font.PLAIN, 25));
		instructionLabel1.setBounds(10, 78, 874, 49);
		contentPane.add(instructionLabel1);
		
		Label updateUserLabel = new Label("");
		updateUserLabel.setText(System.getProperty("user.name"));
		updateUserLabel.setFont(new Font("Calibri", Font.PLAIN, 25));
		updateUserLabel.setBounds(10, 133, 874, 49);
		contentPane.add(updateUserLabel);
		
		JLabel lblNewLabel = new JLabel("The TAB-2-MusicXML\u2122 (T.2.M) converter takes TABs of guitar (and ");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 215, 838, 49);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("eventually percussion) music, and converts them into a musicXML");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(10, 262, 838, 49);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("file. This user manual will go over how to install  the current version");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 30));
		lblNewLabel_2.setBounds(10, 321, 864, 38);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("of the T.2.M and how to use it. Enjoy!");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(10, 369, 826, 38);
		contentPane.add(lblNewLabel_3);
		
		/*JLabel logoImageLabel = new JLabel("");
		logoImageLabel.setIcon(new ImageIcon(GuiWelcome.class.getResource("/EECS2311_Project/imgs/TAB-2-MusicXML.png")));
		logoImageLabel.setBounds(180, 198, 500, 262);
		contentPane.add(logoImageLabel);*/
	}
}
