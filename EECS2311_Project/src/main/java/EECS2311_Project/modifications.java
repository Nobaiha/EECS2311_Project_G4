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

public class modifications extends JFrame {

	private JPanel modificationBody;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					modifications frame = new modifications();
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
	public modifications() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		modificationBody = new JPanel();
		modificationBody.setBackground(new Color(248, 248, 255));
		modificationBody.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(modificationBody);
		modificationBody.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Time Signature Change: ");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setBounds(52, 105, 302, 73);
		modificationBody.add(lblNewLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(366, 122, 57, 45);
		modificationBody.add(spinner);
		
		Label label = new Label("Start:");
		label.setFont(new Font("Calibri", Font.BOLD, 30));
		label.setBounds(136, 240, 93, 63);
		modificationBody.add(label);
		
		Label label_1 = new Label("Time Fraction Value:");
		label_1.setFont(new Font("Calibri", Font.BOLD, 30));
		label_1.setBounds(52, 378, 302, 38);
		modificationBody.add(label_1);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(366, 254, 57, 45);
		modificationBody.add(spinner_1);
		
		textField = new JTextField();
		textField.setBounds(366, 378, 185, 45);
		modificationBody.add(textField);
		textField.setColumns(10);
		
		Button button = new Button("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		button.setFont(new Font("Calibri", Font.BOLD, 25));
		button.setBounds(292, 556, 166, 95);
		modificationBody.add(button);
	}
}
