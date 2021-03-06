package EECS2311_Project;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;

/**
 * The class that operates the upload window of the GUI.
 *
 * @author Team 4 EECS2311 Winter 2021
 */
/**
 * @author suhas
 *
 */
public class GuiUploadWindow extends JFrame {

	private static String composer;
	private static String title;
	private static String content;
	private static String lastDir;
	private static String filePath;

	/**
	 * Launches the application.
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
	 * Creates the frame.
	 */
	/**
	 * Creates the frame that will allow the user to upload their tablature and
	 * select what the instrument they will be converting for
	 * 
	 * @param title    tablature's title entered by user
	 * @param composer tablature's composer entered by user
	 * @param content  tablature content from conversion
	 */
	public GuiUploadWindow(String title, String composer, String content) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 919, 805);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Label instructionsLabel1 = new Label(
				"Pick a file from your file explorer of type \".txt\" that contains your tablature music or ");
		instructionsLabel1.setFont(new Font("Calibri Light", Font.PLAIN, 20));
		instructionsLabel1.setBounds(10, 73, 885, 65);
		contentPane.add(instructionsLabel1);

		Label instructionsLabel2 = new Label("or paste it in the window below.");
		instructionsLabel2.setFont(new Font("Calibri Light", Font.PLAIN, 20));
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

		JScrollPane inputScroller = new JScrollPane(tabDisplayTextArea);
		inputScroller.setBounds(10, 259, 885, 359);
		contentPane.add(inputScroller);

		if (filePath != null && !tabDisplayTextArea.getText().equals("")) {
			fileSelectedLabel.setText(filePath);
		}

		JComboBox instrumentDropDown = new JComboBox();
		instrumentDropDown.setEditable(true);
		instrumentDropDown.setBackground(Color.WHITE);
		instrumentDropDown.setFont(new Font("Calibri", Font.PLAIN, 23));
		instrumentDropDown.setModel(new DefaultComboBoxModel(new String[] { "Guitar", "Bass", "Drums" }));
		instrumentDropDown.setBounds(657, 201, 127, 30);
		contentPane.add(instrumentDropDown);

		instrumentDropDown.addActionListener(e -> {
			JComboBox cb = (JComboBox) e.getSource();
			String instrument = (String) cb.getSelectedItem();
			if (instrument.equals("Bass")) {
				Main.guitar = 1;
			} else if (instrument.equals("Guitar")) {
				Main.guitar = 0;
			} else if (instrument.equals("Drums")) {
				Main.guitar = 2;
			}
		});

		instrumentDropDown.setSelectedIndex(Main.guitar);
		instrumentDropDown.setEditable(false);

		Button browseButton = new Button("Browse");
		browseButton.setBackground(Color.WHITE);
		browseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser choosingFile;
				if (lastDir != null) {
					choosingFile = new JFileChooser(lastDir);
				} else {
					choosingFile = new JFileChooser();
				}
				choosingFile.showOpenDialog(null);
				File fileSelected = choosingFile.getSelectedFile();
				String fileName = fileSelected.getAbsolutePath();
				filePath = fileName;
				lastDir = fileSelected.getParent();
				try {
					if (Main.fileChecker(fileName)) {
						FileReader fileReader = new FileReader(fileName);
						BufferedReader bufferReader = new BufferedReader(fileReader);
						tabDisplayTextArea.read(bufferReader, null);
						bufferReader.close();
						tabDisplayTextArea.requestFocus();
						fileSelectedLabel.setText(filePath);
					} else {
						setVisible(false);
						dispose();
						new Error("File type not supported, please ensure file is a .txt", title, composer,
								tabDisplayTextArea.getText());
					}
				} catch (Exception exception) {
					setVisible(false);
					dispose();
					new Error("File type not supported, please ensure file is a .txt", title, composer,
							tabDisplayTextArea.getText());
				}
			}
		});
		browseButton.setFont(new Font("Calibri Light", Font.BOLD, 15));
		browseButton.setBounds(470, 195, 134, 41);
		contentPane.add(browseButton);

		Button backButton = new Button("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				new GuiWelcome().setVisible(true);
			}
		});

		backButton.setBackground(Color.WHITE);
		backButton.setFont(new Font("Calibri", Font.PLAIN, 15));
		backButton.setForeground(new Color(0, 0, 0));
		backButton.setBounds(10, 728, 109, 30);
		contentPane.add(backButton);

		Button convertButton = new Button("Convert");
		convertButton.setBackground(Color.WHITE);
		convertButton.addActionListener(new ActionListener() {
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

				}

			}
		});
		convertButton.setFont(new Font("Calibri Light", Font.PLAIN, 23));
		convertButton.setBounds(254, 639, 162, 41);
		contentPane.add(convertButton);

		Button modButton = new Button("Modifications");
		modButton.setBackground(Color.WHITE);
		modButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificationsPage modificationsPage = new ModificationsPage(title, composer,
						tabDisplayTextArea.getText());
				setVisible(false);
				modificationsPage.setVisible(true);
			}
		});

		modButton.setFont(new Font("Calibri Light", Font.PLAIN, 23));
		modButton.setBounds(480, 639, 162, 41);
		contentPane.add(modButton);

		JLabel headerLabel = new JLabel(" TAB - 2 - MusicXML\u2122 Upload");
		headerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		headerLabel.setForeground(Color.WHITE);
		headerLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		headerLabel.setBackground(new Color(153, 153, 204));
		headerLabel.setBounds(0, 0, 905, 67);
		headerLabel.setOpaque(true);
		contentPane.add(headerLabel);

		JLabel footerLabel = new JLabel("");
		footerLabel.setOpaque(true);
		footerLabel.setHorizontalAlignment(SwingConstants.LEFT);
		footerLabel.setForeground(Color.WHITE);
		footerLabel.setFont(new Font("Poor Richard", Font.BOLD, 40));
		footerLabel.setBackground(new Color(153, 153, 204));
		footerLabel.setBounds(0, 701, 905, 67);
		contentPane.add(footerLabel);

	}

	/**
	 * Sets the title of the tablature.
	 * 
	 * @param title tablature's title entered by user
	 */
	public static void setTabTitle(String title) {
		GuiUploadWindow.title = title;
	}

	/**
	 * Sets the content of the tablature.
	 * 
	 * @param content tablature content from conversion
	 */
	public static void setContent(String content) {
		GuiUploadWindow.content = content;
	}

	/**
	 * Sets the composer of the tablature.
	 * 
	 * @param composer tablature's composer entered by user
	 */
	public static void setComposer(String composer) {
		GuiUploadWindow.composer = composer;

	}

	/**
	 * Following code fragment for testing purpose setting bounds as width and
	 * height of convert button
	 */
	private int widthConvert = 254;
	private int heightConvert = 639;

	public int getWidthConvert() {
		return this.widthConvert;
	}

	public int getHeightConvert() {
		return this.heightConvert;
	}

	/**
	 * Following code fragment for testing purpose setting bounds as width and
	 * height of modification button
	 */
	private int widthMod = 480;
	private int heightMod = 639;

	public int getWidthMod() {
		return this.widthMod;
	}

	public int getHeightMod() {
		return this.heightMod;
	}
}
