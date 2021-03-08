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

public class ModificationsPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldComposer;
	private JTextField textFieldTitle;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificationsPage frame = new ModificationsPage();
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
	public ModificationsPage() {
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
		
		JLabel instructionLabel2 = new JLabel("to enter the composer and title of your tab! (Optional)");
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
		
		JLabel titleLabel = new JLabel("Title of Tab:");
		titleLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		titleLabel.setBounds(56, 331, 166, 31);
		contentPane.add(titleLabel);
		
		textFieldTitle = new JTextField();
		textFieldTitle.setFont(new Font("Calibri", Font.PLAIN, 22));
		textFieldTitle.setColumns(10);
		textFieldTitle.setBounds(243, 322, 463, 49);
		contentPane.add(textFieldTitle);
		
		Button enterButton = new Button("Next");
		enterButton.setForeground(Color.BLACK);
		enterButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		enterButton.setBackground(SystemColor.menu);
		enterButton.setBounds(340, 597, 224, 41);
		contentPane.add(enterButton);
		
		JLabel lblKey = new JLabel("Key:");
		lblKey.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblKey.setBounds(56, 404, 166, 31);
		contentPane.add(lblKey);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Calibri", Font.PLAIN, 22));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"C major A minor (DEFAULT)", "C# major A# minor", "F# major D# minor", "B major G# minor", "E major C# minor", "A major F# minor", "D major B minor", "G major E minor", "F major D minor", "B FLAT major G minor", "E FLAT major C minor", "A FLAT major F minor", "D FLAT major B FLAT minor", "G FLAT major E FLAT minor", "C FLAT major A FLAT minor"}));
		comboBox.setBounds(243, 395, 321, 49);
		contentPane.add(comboBox);
		
		JLabel lblTimeSignature = new JLabel("Time Signature:");
		lblTimeSignature.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblTimeSignature.setBounds(56, 463, 166, 31);
		contentPane.add(lblTimeSignature);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(243, 398, 288, 41);
		contentPane.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"4", "2", "3", "6", "9", "12"}));
		comboBox_2.setFont(new Font("Calibri", Font.PLAIN, 22));
		comboBox_2.setBounds(243, 461, 54, 34);
		contentPane.add(comboBox_2);
		
		JComboBox comboBox_2_1 = new JComboBox();
		comboBox_2_1.setModel(new DefaultComboBoxModel(new String[] {"4", "1", "2", "8", "16"}));
		comboBox_2_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		comboBox_2_1.setBounds(334, 461, 54, 34);
		contentPane.add(comboBox_2_1);
		
		JLabel lblKey_1 = new JLabel("/");
		lblKey_1.setFont(new Font("Calibri", Font.BOLD, 28));
		lblKey_1.setBounds(307, 454, 28, 57);
		contentPane.add(lblKey_1);

		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUploadWindow guiUploadWindow = new GuiUploadWindow(textFieldTitle.getText(), textFieldComposer.getText());
				setVisible(false);
				dispose();
				guiUploadWindow.setVisible(true);
			}
		});
	}
}
