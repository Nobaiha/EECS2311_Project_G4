package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Choice;
import java.awt.List;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSpinner;
import java.awt.Label;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JRadioButton;

public class modifications extends JFrame {

	private JPanel modificationBody;
	private JTextField fracValue;
	private JTextField textFieldName;
	private JTextField textFieldComposure;

	/**
	 * Launch the application.
	 */

		/**
		 * Create the frame.
		 */
	public modifications(String filepath) {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 919, 805);
			modificationBody = new JPanel();
			modificationBody.setBackground(new Color(248, 248, 255));
			modificationBody.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(modificationBody);
			modificationBody.setLayout(null);

			JLabel TimeSignChange = new JLabel("Time Signature Change: ");
			TimeSignChange.setFont(new Font("Calibri", Font.BOLD, 30));
			TimeSignChange.setBounds(52, 161, 302, 73);
			modificationBody.add(TimeSignChange);

			JSpinner spinner1 = new JSpinner();
			spinner1.setBounds(366, 178, 57, 45);
			modificationBody.add(spinner1);

			Label Startlabel = new Label("Start:");
			Startlabel.setFont(new Font("Calibri", Font.BOLD, 30));
			Startlabel.setBounds(136, 240, 93, 63);
			modificationBody.add(Startlabel);

			Label TimeFracVal = new Label("Time Fraction Value:");
			TimeFracVal.setFont(new Font("Calibri", Font.BOLD, 30));
			TimeFracVal.setBounds(52, 331, 302, 38);
			modificationBody.add(TimeFracVal);

			JSpinner spinner2 = new JSpinner();
			spinner2.setBounds(366, 254, 57, 45);
			modificationBody.add(spinner2);

			fracValue = new JTextField();
			fracValue.setBounds(376, 324, 185, 45);
			modificationBody.add(fracValue);
			fracValue.setColumns(10);

			/*Button Submit = new Button("Submit");
			Submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					try {
						Main.start(filepath);
					} catch (FileNotFoundException fileNotFoundException) {
						fileNotFoundException.printStackTrace();
					}
				}
			});
			Submit.setFont(new Font("Calibri", Font.BOLD, 30));
			Submit.setBounds(292, 556, 214, 115);
			modificationBody.add(Submit);*/

			JLabel name = new JLabel("Name of the music piece:");
			name.setFont(new Font("Calibri", Font.BOLD, 30));
			name.setBounds(52, 47, 321, 45);
			modificationBody.add(name);

			textFieldName = new JTextField();
			textFieldName.setBounds(385, 61, 133, 31);
			modificationBody.add(textFieldName);
			textFieldName.setColumns(10);

			Label ComposureLabel = new Label("Composure:");
			ComposureLabel.setFont(new Font("Calibri", Font.BOLD, 30));
			ComposureLabel.setBounds(52, 124, 205, 31);
			modificationBody.add(ComposureLabel);

			textFieldComposure = new JTextField();
			textFieldComposure.setColumns(10);
			textFieldComposure.setBounds(261, 124, 257, 41);
			modificationBody.add(textFieldComposure);

			JRadioButton MusicXMLRadioButton = new JRadioButton("Music Sheet");
			MusicXMLRadioButton.setBackground(new Color(248, 248, 255));
			MusicXMLRadioButton.setFont(new Font("Calibri", Font.BOLD, 25));
			MusicXMLRadioButton.setBounds(98, 457, 203, 54);
			modificationBody.add(MusicXMLRadioButton);

			JRadioButton rdbtnTabSheet = new JRadioButton("Tab Sheet");
			rdbtnTabSheet.setBackground(new Color(248, 248, 255));
			rdbtnTabSheet.setFont(new Font("Calibri", Font.BOLD, 25));
			rdbtnTabSheet.setBounds(507, 457, 203, 54);
			modificationBody.add(rdbtnTabSheet);

			Label label = new Label("Choose the one you prefer");
			label.setFont(new Font("Calibri", Font.BOLD, 25));
			label.setBounds(226, 408, 321, 43);
			modificationBody.add(label);
		}
}
