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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

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
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel composerLabel = new JLabel("Composer:");

		composerLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		composerLabel.setBounds(56, 262, 177, 31);

		contentPane.add(composerLabel);
		
		textFieldComposer = new JTextField();
		textFieldComposer.setFont(new Font("Calibri", Font.PLAIN, 22));
		textFieldComposer.setBounds(243, 253, 463, 49);
		contentPane.add(textFieldComposer);
		textFieldComposer.setColumns(10);
		textFieldComposer.setText(composer);
		
		JLabel titleLabel = new JLabel("Title of Tab:");

		titleLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		titleLabel.setBounds(56, 331, 166, 31);

		contentPane.add(titleLabel);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setFont(new Font("Calibri", Font.PLAIN, 22));
		textFieldTitle.setColumns(10);

		textFieldTitle.setBounds(243, 322, 463, 49);

		contentPane.add(textFieldTitle);
		textFieldTitle.setText(title);
		

		Button enterButton = new Button("Confirm");

		enterButton.setForeground(Color.BLACK);
		enterButton.setFont(new Font("Calibri Light", Font.PLAIN, 23));
		enterButton.setBackground(Color.WHITE);
		enterButton.setBounds(387, 597, 162, 41);
		contentPane.add(enterButton);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblKey.setBounds(56, 404, 166, 31);
		contentPane.add(lblKey);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"C major A minor (DEFAULT)", "C# major A# minor", "F# major D# minor", "B major G# minor", "E major C# minor", "A major F# minor", "D major B minor", "G major E minor", "F major D minor", "B FLAT major G minor", "E FLAT major C minor", "A FLAT major F minor", "D FLAT major B FLAT minor", "G FLAT major E FLAT minor", "C FLAT major A FLAT minor"}));
		comboBox.setBounds(243, 395, 321, 49);
		contentPane.add(comboBox);
		
		JLabel lblTimeSignature = new JLabel("Time Signature:");
		lblTimeSignature.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblTimeSignature.setBounds(56, 463, 166, 31);
		contentPane.add(lblTimeSignature);
		
		JComboBox keyComboBox_1 = new JComboBox();
		keyComboBox_1.setBounds(243, 398, 288, 41);
		contentPane.add(keyComboBox_1);
		
		JComboBox timeSigNumComboBox_2 = new JComboBox();
		timeSigNumComboBox_2.setModel(new DefaultComboBoxModel(new String[] {"4", "2", "3", "6", "9", "12"}));
		timeSigNumComboBox_2.setFont(new Font("Calibri", Font.PLAIN, 22));
		timeSigNumComboBox_2.setBounds(243, 461, 54, 34);
		contentPane.add(timeSigNumComboBox_2);
		
		JComboBox timeSigDenomboBox_3 = new JComboBox();
		timeSigDenomboBox_3.setModel(new DefaultComboBoxModel(new String[] {"4", "1", "2", "8", "16"}));
		timeSigDenomboBox_3.setFont(new Font("Calibri", Font.PLAIN, 22));
		timeSigDenomboBox_3.setBounds(334, 461, 54, 34);
		contentPane.add(timeSigDenomboBox_3);
		
		JLabel lblKey_1 = new JLabel("/");
		lblKey_1.setFont(new Font("Calibri", Font.BOLD, 28));
		lblKey_1.setBounds(307, 454, 28, 57);
		contentPane.add(lblKey_1);
		
		JLabel lblNewLabel = new JLabel(" TAB - 2 - MusicXML\u2122 ");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		lblNewLabel.setBackground(new Color(153, 153, 204));
		lblNewLabel.setBounds(0, 0, 905, 67);
		contentPane.add(lblNewLabel);
		
		Label label = new Label("This page will allow you to add small modifications to your tab. You will be");
		label.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		label.setBounds(10, 87, 885, 41);
		contentPane.add(label);
		
		Label label_1 = new Label("able to enter the composer, title of your tab, key and time signature. Once ");
		label_1.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		label_1.setBounds(10, 134, 885, 41);
		contentPane.add(label_1);
		
		Label label_2 = new Label("finished, press \"Confirm\".");
		label_2.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		label_2.setBounds(10, 188, 325, 34);
		contentPane.add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Poor Richard", Font.BOLD, 40));
		lblNewLabel_1.setBackground(new Color(153, 153, 204));
		lblNewLabel_1.setBounds(0, 701, 905, 67);
		contentPane.add(lblNewLabel_1);

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
