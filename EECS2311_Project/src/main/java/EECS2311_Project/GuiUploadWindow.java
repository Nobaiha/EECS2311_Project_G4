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
		fileSelectedLabel.setBounds(10, 196, 692, 41);
		contentPane.add(fileSelectedLabel);
		
		JTextArea tabDisplayTextArea = new JTextArea();
		tabDisplayTextArea.setFont(new Font("Courier New", Font.PLAIN, 17));
		tabDisplayTextArea.setText(content);
		JScrollPane sp = new JScrollPane(tabDisplayTextArea);
		sp.setBounds(10, 259, 885, 359);
		contentPane.add(sp);

		if(filePath != null && !tabDisplayTextArea.getText().equals("")){
			fileSelectedLabel.setText(filePath);
		}
		
		Button browseButton = new Button("Browse");
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
		browseButton.setBounds(730, 195, 134, 41);
		contentPane.add(browseButton);

		Button homeButton = new Button("Back");
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GuiWelcome().setVisible(true);
			}
		});

		homeButton.setBackground(UIManager.getColor("Button.background"));
		homeButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		homeButton.setForeground(new Color(0, 0, 0));
		homeButton.setBounds(10, 638, 150, 41);
		contentPane.add(homeButton);
		
		Label tmLabel = new Label("TM");
		tmLabel.setBounds(372, 10, 28, 21);
		contentPane.add(tmLabel);
		
		Label standardHeaderLabel = new Label("TAB-2-MusicXML");
		standardHeaderLabel.setForeground(new Color(0, 51, 153));
		standardHeaderLabel.setFont(new Font("Arial Black", Font.BOLD, 35));
		standardHeaderLabel.setBounds(10, 10, 372, 49);
		contentPane.add(standardHeaderLabel);
		
		
		
		Button enterButton = new Button("Convert");
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
		enterButton.setBounds(339, 638, 224, 41);
		contentPane.add(enterButton);

		Button modButton = new Button("Modifications");
		modButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificationsPage modificationsPage = new ModificationsPage(title,composer,tabDisplayTextArea.getText());
				setVisible(false);
				modificationsPage.setVisible(true);
			}
		});

		modButton.setFont(new Font("Calibri", Font.PLAIN, 25));
		modButton.setBounds(671, 638, 224, 41);
		contentPane.add(modButton);

		
		
		
		
		
		
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
