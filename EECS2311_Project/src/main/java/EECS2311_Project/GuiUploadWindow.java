package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

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
		
		Label instructionsLabel1 = new Label("Pick a file from your file explorer of type \".txt\" that contains your");
		instructionsLabel1.setFont(new Font("Calibri", Font.PLAIN, 25));
		instructionsLabel1.setBounds(10, 73, 885, 65);
		contentPane.add(instructionsLabel1);
		
		Label instructionsLabel2 = new Label("tablature music.");
		instructionsLabel2.setFont(new Font("Calibri", Font.PLAIN, 25));
		instructionsLabel2.setBounds(10, 114, 885, 65);
		contentPane.add(instructionsLabel2);
		
		JLabel fileSelectedLabel = new JLabel("No file currently selected");
		fileSelectedLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		fileSelectedLabel.setForeground(new Color(192, 192, 192));
		fileSelectedLabel.setOpaque(true);
		fileSelectedLabel.setBackground(new Color(255, 255, 255));
		fileSelectedLabel.setBounds(10, 196, 692, 41);
		contentPane.add(fileSelectedLabel);
		
		JTextArea tabDisplayTextArea = new JTextArea();
		JScrollPane sp = new JScrollPane(tabDisplayTextArea);
		sp.setBounds(10, 259, 885, 429);
		contentPane.add(sp);
		
		Button browseButton = new Button("Browse");
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choosingFile = new JFileChooser();
				choosingFile.showOpenDialog(null);
				File fileSelected = choosingFile.getSelectedFile();
				String fileName= fileSelected.getAbsolutePath();
				try {
					FileReader fileReader = new FileReader(fileName);
					BufferedReader bufferReader = new BufferedReader(fileReader);
					tabDisplayTextArea.read(bufferReader, null);
					bufferReader.close();
					tabDisplayTextArea.requestFocus();
					fileSelectedLabel.setText(fileName.toString());
				}
				catch(Exception exception) {
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		browseButton.setFont(new Font("Calibri", Font.BOLD, 15));
		browseButton.setBounds(730, 195, 134, 41);
		contentPane.add(browseButton);
		
		Label tmLabel = new Label("TM");
		tmLabel.setBounds(372, 10, 28, 21);
		contentPane.add(tmLabel);
		
		Label standardHeaderLabel = new Label("TAB-2-MusicXML");
		standardHeaderLabel.setForeground(new Color(0, 51, 153));
		standardHeaderLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		standardHeaderLabel.setBounds(10, 10, 372, 49);
		contentPane.add(standardHeaderLabel);
		
		
		
		Button enterButton = new Button("Enter");
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pastedTab = tabDisplayTextArea.getText();
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
		enterButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		enterButton.setBounds(340, 702, 224, 41);
		contentPane.add(enterButton);
		
		
		
		
		
		
	}
}
