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
		
		Label label_4 = new Label("TM");
		label_4.setBounds(372, 10, 28, 21);
		contentPane.add(label_4);
		
		Label label_3 = new Label("TAB-2-MusicXML");
		label_3.setForeground(new Color(0, 51, 153));
		label_3.setFont(new Font("Arial Black", Font.BOLD, 35));
		label_3.setBounds(10, 10, 372, 49);
		contentPane.add(label_3);
		
		guiFileUploadPage m = new guiFileUploadPage();
		Button button = new Button("Browse Tab");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                m.setVisible(true);
			}
		});
		button.setFont(new Font("Calibri", Font.PLAIN, 25));
		button.setBounds(151, 555, 249, 69);
		contentPane.add(button);
		
		paste p = new paste();
		Button button_1 = new Button("Paste Tab");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
                p.setVisible(true);
			}
		});
		button_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		button_1.setBounds(533, 555, 249, 69);
		contentPane.add(button_1);
		
		Label label = new Label("Pick which method you would like to use to upload your tab.");
		label.setFont(new Font("Calibri", Font.PLAIN, 25));
		label.setBounds(10, 466, 885, 49);
		contentPane.add(label);
		
		Label label_1 = new Label("Welcome to your personal tablature to music XML converter,");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_1.setBounds(10, 78, 874, 49);
		contentPane.add(label_1);
		
		Label label_2 = new Label("");
		label_2.setText(System.getProperty("user.name"));
		label_2.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_2.setBounds(10, 133, 874, 49);
		contentPane.add(label_2);
	}

}
