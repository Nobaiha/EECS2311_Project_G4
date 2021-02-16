package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.DropMode;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class paste extends JFrame {

	private JPanel pasteBody;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					paste frame = new paste();
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
	public paste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		pasteBody = new JPanel();
		pasteBody.setBackground(new Color(248, 248, 255));
		pasteBody.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(pasteBody);
		pasteBody.setLayout(null);
		//modifications m=new modifications();
		
		JLabel lblNewLabel = new JLabel("PASTE");
		lblNewLabel.setBounds(0, 0, 891, 105);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 240, 245));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 86));
		pasteBody.add(lblNewLabel);
		
		TextArea pasteArea = new TextArea();
		pasteArea.setBackground(new Color(240, 248, 255));
		pasteArea.setBounds(38, 128, 821, 489);
		pasteBody.add(pasteArea);
		Button enterButton = new Button("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				//m.setVisible(true);
			}
		});
		enterButton.setBackground(new Color(240, 248, 255));
		enterButton.setFont(new Font("Calibri", Font.BOLD, 30));
		enterButton.setForeground(new Color(0, 0, 0));
		enterButton.setBounds(354, 645, 161, 67);
		pasteBody.add(enterButton);
		
	}
	
}
