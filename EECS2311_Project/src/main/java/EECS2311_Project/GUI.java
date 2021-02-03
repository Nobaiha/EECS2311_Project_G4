package EECS2311_Project;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A class that creates the GUI for the user. It will prompt the user the input a file or to copy and paste text to be converted.
 * 
 * @author Team 4 EECS2311 Winter 2021
 * 
 */
//hi v1
//hello suha
public class GUI {
	public GUI() {
		JFrame frame = new JFrame();
		
		JButton button = new JButton("Click me");
		JLabel label = new JLabel("Number of clicks: 0");
		
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(button);
		panel.add(label);
		
		
		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Our GUI");
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUI();
	}

}
