package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Label;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import javax.swing.JEditorPane;

public class GuiUploadWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiUploadWindow frame = new GuiUploadWindow();
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
	public GuiUploadWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("Pick a file from your file explorer of type \".txt\" that contains your");
		label.setFont(new Font("Calibri", Font.PLAIN, 25));
		label.setBounds(10, 73, 885, 65);
		contentPane.add(label);
		
		Label label_1 = new Label("tablature music.");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		label_1.setBounds(10, 114, 885, 65);
		contentPane.add(label_1);
		
		Label label_2 = new Label("No file currently selected");
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 20));
		label_2.setBackground(Color.WHITE);
		label_2.setBounds(10, 195, 700, 41);
		contentPane.add(label_2);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(10, 268, 854, 367);
		contentPane.add(editorPane);
		
		Button button = new Button("Browse");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choosingFile = new JFileChooser();
				choosingFile.showOpenDialog(null);
				File fileSelected = choosingFile.getSelectedFile();
				String fileName= fileSelected.getAbsolutePath();
				try {
					FileReader fileReader = new FileReader(fileName);
					BufferedReader bufferReader = new BufferedReader(fileReader);
					editorPane.read(bufferReader, null);
					bufferReader.close();
					editorPane.requestFocus();
					label_2.setText(fileName.toString());
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		button.setFont(new Font("Calibri", Font.BOLD, 15));
		button.setBounds(730, 195, 134, 41);
		contentPane.add(button);
		
		Label label_4 = new Label("TM");
		label_4.setBounds(372, 10, 28, 21);
		contentPane.add(label_4);
		
		Label label_3 = new Label("TAB-2-MusicXML");
		label_3.setForeground(new Color(0, 51, 153));
		label_3.setFont(new Font("Arial Black", Font.BOLD, 35));
		label_3.setBounds(10, 10, 372, 49);
		contentPane.add(label_3);
		
		Button button_1 = new Button("Enter");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pastedTab = editorPane.getText();
				File file = new File("temp.txt");
				try {
					FileWriter fw = new FileWriter(file);
					fw.write(pastedTab);
					fw.close();
					Main.start(file.toString());
					file.delete();
				} catch (Exception exception) {
					//throw error screen here.
				}
				setVisible(false);
			}
		});
		button_1.setFont(new Font("Calibri", Font.PLAIN, 25));
		button_1.setBounds(354, 694, 224, 41);
		contentPane.add(button_1);
		
		
	}
}
