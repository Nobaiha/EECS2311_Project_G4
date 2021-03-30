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
import java.awt.Window.Type;
import java.awt.SystemColor;

public class GuiUploadWindow extends JFrame {

	private static String composer;
	private static String title;
	private static String content;
	private static String lastDir;
	private static String filePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiUploadWindow frame = new GuiUploadWindow(title, composer, content);
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
	public GuiUploadWindow(String title, String composer, String content) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label instructionsLabel1 = new Label("Pick a file from your file explorer of type \".txt\" that contains your");
		instructionsLabel1.setFont(new Font("Calibri", Font.PLAIN, 25));
		instructionsLabel1.setBounds(10, 73, 885, 65);
		contentPane.add(instructionsLabel1);
		
		Label instructionsLabel2 = new Label("tablature music or paste it in the window below.");
		instructionsLabel2.setFont(new Font("Calibri", Font.PLAIN, 25));
		instructionsLabel2.setBounds(10, 114, 885, 65);
		contentPane.add(instructionsLabel2);
		
		JLabel fileSelectedLabel = new JLabel("No file currently selected");
		fileSelectedLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 25));
		fileSelectedLabel.setForeground(new Color(192, 192, 192));
		fileSelectedLabel.setOpaque(true);
		fileSelectedLabel.setBackground(new Color(255, 255, 255));
		fileSelectedLabel.setBounds(10, 196, 450, 41);
		contentPane.add(fileSelectedLabel);
		
		JTextArea tabDisplayTextArea = new JTextArea();

		tabDisplayTextArea.setFont(new Font("Courier New", Font.PLAIN, 22));
		tabDisplayTextArea.setText(content);

		JScrollPane sp = new JScrollPane(tabDisplayTextArea);
		sp.setBounds(10, 259, 885, 359);
		contentPane.add(sp);

		if(filePath != null && !tabDisplayTextArea.getText().equals("")){
			fileSelectedLabel.setText(filePath);
		}

		JComboBox intrsutmentDropDown = new JComboBox();
		intrsutmentDropDown.setEditable(true);
		intrsutmentDropDown.setBackground(Color.WHITE);
		intrsutmentDropDown.setFont(new Font("Calibri", Font.PLAIN, 22));
		intrsutmentDropDown.setModel(new DefaultComboBoxModel(new String[] {"Guitar","Bass"}));
		intrsutmentDropDown.setBounds(657, 197, 127, 40);
		contentPane.add(intrsutmentDropDown);

		intrsutmentDropDown.addActionListener(e->{
				JComboBox cb  = (JComboBox) e.getSource();
				String instrument = (String)cb.getSelectedItem();
				if(instrument.equals("Bass")){
					Main.guitar = 1;
				}else if(instrument.equals("Guitar")){
					Main.guitar = 0;
				}
		});
		
		Button browseButton = new Button("Browse");
		browseButton.setBackground(Color.WHITE);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choosingFile;
				if(lastDir != null) {
					choosingFile = new JFileChooser(lastDir);
				}else{
					choosingFile = new JFileChooser();
				}
				choosingFile.showOpenDialog(null);
				File fileSelected = choosingFile.getSelectedFile();
				String fileName= fileSelected.getAbsolutePath();
				filePath = fileName;
				lastDir = fileSelected.getParent();
				try {
					if(Main.fileChecker(fileName)) {
						FileReader fileReader = new FileReader(fileName);
						BufferedReader bufferReader = new BufferedReader(fileReader);
						tabDisplayTextArea.read(bufferReader, null);
						bufferReader.close();
						tabDisplayTextArea.requestFocus();
						fileSelectedLabel.setText(filePath);
					}else{
						setVisible(false);
						dispose();
						new Error("File type not supported, please ensure file is a .txt", title, composer,tabDisplayTextArea.getText());
					}
				}
				catch(Exception exception) {
					setVisible(false);
					dispose();
					new Error("File type not supported, please ensure file is a .txt", title, composer,tabDisplayTextArea.getText());
				}
			}
		});
		browseButton.setFont(new Font("Calibri", Font.BOLD, 15));
		browseButton.setBounds(470, 195, 134, 41);
		contentPane.add(browseButton);

		Button homeButton = new Button("Back");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GuiWelcome().setVisible(true);
			}
		});

		homeButton.setBackground(Color.WHITE);
		homeButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		homeButton.setForeground(new Color(0, 0, 0));
		homeButton.setBounds(31, 686, 150, 41);
		contentPane.add(homeButton);
		
		
		
		Button enterButton = new Button("Convert");
		enterButton.setBackground(Color.WHITE);
		enterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pastedTab = tabDisplayTextArea.getText();
				File file = new File("temp.txt");
				try {
					FileWriter fw = new FileWriter(file);
					fw.write(pastedTab);
					fw.close();
					Main.start(file.toString(), title, composer);
					file.delete();
					setVisible(false);
					dispose();
				} catch (Exception exception) {
					setVisible(false);
					dispose();
					new Error("Error parsing, please ensure tab is in correct format.", title, composer,tabDisplayTextArea.getText());
				}
				//setVisible(false);
			}
		});
		enterButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		enterButton.setBounds(292, 686, 224, 41);
		contentPane.add(enterButton);

		Button modButton = new Button("Modifications");
		modButton.setBackground(Color.WHITE);
		modButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificationsPage modificationsPage = new ModificationsPage(title,composer,tabDisplayTextArea.getText());
				setVisible(false);
				modificationsPage.setVisible(true);
			}
		});

		modButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		modButton.setBounds(645, 686, 224, 41);
		contentPane.add(modButton);
		
		JLabel lblNewLabel = new JLabel(" TAB - 2 - MusicXML\u2122 ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		lblNewLabel.setBackground(new Color(153, 153, 204));
		lblNewLabel.setBounds(0, 0, 905, 67);
		lblNewLabel.setOpaque(true);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(153, 153, 204));
		lblNewLabel_1.setBounds(0, 642, 905, 126);
		lblNewLabel_1.setOpaque(true);
		contentPane.add(lblNewLabel_1);

		
		
		
		
		
		
	}

	public static void setTabTitle(String title) {
		GuiUploadWindow.title = title;
	}

	public static void setContent(String content) {
		GuiUploadWindow.content = content;
	}

	public static void setComposer(String composer) {
		GuiUploadWindow.composer = composer;

	}
}
