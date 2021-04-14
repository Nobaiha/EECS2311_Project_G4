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
/**
 * @author suhas
 *
 */
/**
 * @author suhas
 *
 */
public class ModificationsPage extends JFrame {

	private JPanel contentPane;
	private JTextField composerTextField;
	private JTextField titleTextField;

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

		composerLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		composerLabel.setBounds(56, 282, 177, 31);

		contentPane.add(composerLabel);

		composerTextField = new JTextField();
		composerTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
		composerTextField.setBounds(243, 273, 463, 49);
		contentPane.add(composerTextField);
		composerTextField.setColumns(10);
		composerTextField.setText(composer);

		JLabel titleOfTabLabel = new JLabel("Title of Tab:");

		titleOfTabLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		titleOfTabLabel.setBounds(56, 346, 166, 31);

		contentPane.add(titleOfTabLabel);

		titleTextField = new JTextField();
		titleTextField.setFont(new Font("Calibri", Font.PLAIN, 22));
		titleTextField.setColumns(10);

		titleTextField.setBounds(243, 337, 463, 49);

		contentPane.add(titleTextField);
		titleTextField.setText(title);

		Button confirmButton = new Button("Confirm");

		confirmButton.setForeground(Color.BLACK);
		confirmButton.setFont(new Font("Calibri", Font.PLAIN, 23));
		confirmButton.setBackground(Color.WHITE);
		confirmButton.setBounds(387, 597, 178, 43);
		contentPane.add(confirmButton);

		JLabel keyLabel = new JLabel("Key:");
		keyLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		keyLabel.setBounds(56, 417, 166, 31);
		contentPane.add(keyLabel);

		JComboBox keyComboBox = new JComboBox();
		keyComboBox.setFont(new Font("Calibri", Font.PLAIN, 22));
		keyComboBox.setModel(new DefaultComboBoxModel(new String[] { "C major", "G major", "D major", "A major",
				"E major", "B major", "F major", "B flat major", "E flat major", "A flat major", "D flat major",
				"G flat major", "C flat major" }));
		keyComboBox.setBounds(243, 408, 321, 49);
		contentPane.add(keyComboBox);

		keyComboBox.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			int keySignature = cb.getSelectedIndex() + 1;
			if (keySignature > 6) {
				keySignature = keySignature - 13;
			}
			Main.keySignature = keySignature;
		});

		JLabel timeSignatureLabel = new JLabel("Time Signature:");
		timeSignatureLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		timeSignatureLabel.setBounds(56, 480, 166, 31);
		contentPane.add(timeSignatureLabel);

		JComboBox timeSigNumComboBox1 = new JComboBox();
		timeSigNumComboBox1.setModel(new DefaultComboBoxModel(new String[] { "4", "2", "3", "6", "9", "12" }));
		timeSigNumComboBox1.setFont(new Font("Calibri", Font.PLAIN, 22));
		timeSigNumComboBox1.setBounds(243, 479, 70, 34);
		contentPane.add(timeSigNumComboBox1);

		JComboBox timeSigDenomboBox2 = new JComboBox();
		timeSigDenomboBox2.setModel(new DefaultComboBoxModel(new String[] { "4", "1", "2", "8", "16" }));
		timeSigDenomboBox2.setFont(new Font("Calibri", Font.PLAIN, 22));
		timeSigDenomboBox2.setBounds(370, 479, 70, 34);
		contentPane.add(timeSigDenomboBox2);

		timeSigNumComboBox1.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			String timeSig1 = (String) cb.getSelectedItem();
			Main.timeSig1 = Integer.parseInt(timeSig1);
		});

		timeSigDenomboBox2.addActionListener(e -> {
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

		keyComboBox.setSelectedIndex(keySelector);
		timeSigNumComboBox1.setSelectedIndex(timeSig1Selector);
		timeSigDenomboBox2.setSelectedIndex(timeSig2Selector);

		JLabel slashLabel = new JLabel("/");
		slashLabel.setFont(new Font("Calibri", Font.BOLD, 28));
		slashLabel.setBounds(332, 466, 28, 67);
		contentPane.add(slashLabel);

		JLabel headerLabel = new JLabel(" TAB - 2 - MusicXML\u2122 Modifications");
		headerLabel.setOpaque(true);
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		headerLabel.setBackground(new Color(153, 153, 204));
		headerLabel.setBounds(0, 0, 905, 67);
		contentPane.add(headerLabel);

		Label instructionLabel1 = new Label(
				"This page will allow you to add small modifications to your tab. You will be");
		instructionLabel1.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		instructionLabel1.setBounds(10, 87, 885, 41);
		contentPane.add(instructionLabel1);

		Label instructionLabel2 = new Label(
				"able to enter the composer, title of your tab, key and time signature. Once ");
		instructionLabel2.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		instructionLabel2.setBounds(10, 134, 885, 41);
		contentPane.add(instructionLabel2);

		Label instructionLabel3 = new Label("finished with this page, press \"Confirm\".");
		instructionLabel3.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		instructionLabel3.setBounds(10, 188, 885, 34);
		contentPane.add(instructionLabel3);

		JLabel footerLabel = new JLabel("");
		footerLabel.setOpaque(true);
		footerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		footerLabel.setForeground(Color.WHITE);
		footerLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		footerLabel.setBackground(new Color(153, 153, 204));
		footerLabel.setBounds(0, 701, 905, 67);
		contentPane.add(footerLabel);

		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUploadWindow guiUploadWindow = new GuiUploadWindow(titleTextField.getText(),
						composerTextField.getText(), content);
				setVisible(false);
				dispose();
				guiUploadWindow.setVisible(true);
			}
		});
	}

	/**
	 * Following code fragment for testing purpose setting bounds as width and
	 * height of confirm button
	 */
	private int width = 387;
	private int height = 597;

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}
}
