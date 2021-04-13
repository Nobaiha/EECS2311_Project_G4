package EECS2311_Project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

/**
 * The class that operates the modifications window of the GUI.
 *
 * @author Team 4 EECS2311 Winter 2021
 */
public class ModificationsPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldComposer;
	private JTextField textFieldTitle;

	private static String composer;
	private static String title;
	private static String content;

	/**
	 * Launches the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificationsPage frame = new ModificationsPage(title, composer, content);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the frame for the modification window where user can enter some input
	 * to change specific components.
	 * 
	 * @param title    tablature's title entered by user
	 * @param composer tablature's composer entered by user
	 * @param content  tablature content from conversion
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
		enterButton.setFont(new Font("Calibri", Font.PLAIN, 23));
		enterButton.setBackground(Color.WHITE);
		enterButton.setBounds(387, 597, 178, 43);
		contentPane.add(enterButton);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblKey.setBounds(56, 404, 166, 31);
		contentPane.add(lblKey);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "C major", "G major", "D major", "A major", "E major",
				"B major", "F major", "B flat major", "E flat major", "A flat major", "D flat major", "G flat major",
				"C flat major" }));
		comboBox.setBounds(243, 395, 321, 49);
		contentPane.add(comboBox);

		comboBox.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			int keySignature = cb.getSelectedIndex() + 1;
			if (keySignature > 6) {
				keySignature = keySignature - 13;
			}
			Main.keySignature = keySignature;
		});

		JLabel lblTimeSignature = new JLabel("Time Signature:");
		lblTimeSignature.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		lblTimeSignature.setBounds(56, 463, 166, 31);
		contentPane.add(lblTimeSignature);

		JComboBox keyComboBox_1 = new JComboBox();
		keyComboBox_1.setBounds(243, 398, 288, 41);
		contentPane.add(keyComboBox_1);

		JComboBox timeSigNumComboBox_2 = new JComboBox();
		timeSigNumComboBox_2.setModel(new DefaultComboBoxModel(new String[] { "4", "2", "3", "6", "9", "12" }));
		timeSigNumComboBox_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		timeSigNumComboBox_2.setBounds(243, 461, 70, 34);
		contentPane.add(timeSigNumComboBox_2);

		JComboBox timeSigDenomboBox_3 = new JComboBox();
		timeSigDenomboBox_3.setModel(new DefaultComboBoxModel(new String[] { "4", "1", "2", "8", "16" }));
		timeSigDenomboBox_3.setFont(new Font("Calibri", Font.PLAIN, 14));
		timeSigDenomboBox_3.setBounds(334, 461, 70, 34);
		contentPane.add(timeSigDenomboBox_3);

		timeSigNumComboBox_2.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			String timeSig1 = (String) cb.getSelectedItem();
			Main.timeSig1 = Integer.parseInt(timeSig1);
		});

		timeSigDenomboBox_3.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			String timeSig1 = (String) cb.getSelectedItem();
			Main.timeSig2 = Integer.parseInt(timeSig1);
		});

		String[] comboBox1 = new String[] { "4", "2", "3", "6", "9", "12" };
		String[] comboBox2 = new String[] { "4", "1", "2", "8", "16" };
		String[] keySigs = new String[] { "C major", "G major", "D major", "A major", "E major", "B major", "F major",
				"B flat major", "E flat major", "A flat major", "D flat major", "G flat major", "C flat major" };

		int timeSig1Selector = 0;
		int timeSig2Selector = 0;
		String mainTimeSig1 = Integer.toString(Main.timeSig1);
		String mainTimeSig2 = Integer.toString(Main.timeSig2);

		for (int i = 0; i < comboBox1.length; i++) {
			if (comboBox1[i].equals(mainTimeSig1)) {
				timeSig1Selector = i;
			}
		}
		for (int i = 0; i < comboBox2.length; i++) {
			if (comboBox2[i].equals(mainTimeSig2)) {
				timeSig2Selector = i;
			}
		}
		int keySelector = Main.keySignature - 1;
		if (keySelector < 0) {
			keySelector = keySelector + 13;
		}

		comboBox.setSelectedIndex(keySelector);
		timeSigNumComboBox_2.setSelectedIndex(timeSig1Selector);
		timeSigDenomboBox_3.setSelectedIndex(timeSig2Selector);

		JLabel lblKey_1 = new JLabel("/");
		lblKey_1.setFont(new Font("Calibri", Font.BOLD, 28));
		lblKey_1.setBounds(307, 454, 28, 57);
		contentPane.add(lblKey_1);

		JLabel lblNewLabel = new JLabel(" TAB - 2 - MusicXML\u2122 Modifications");
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

		Label label_2 = new Label("finished with this page, press \"Confirm\".");
		label_2.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		label_2.setBounds(10, 188, 885, 34);
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
				GuiUploadWindow guiUploadWindow = new GuiUploadWindow(textFieldTitle.getText(),
						textFieldComposer.getText(), content);
				setVisible(false);
				dispose();
				guiUploadWindow.setVisible(true);
			}
		});
	}
}
