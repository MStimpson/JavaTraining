package com.media;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

/**
 * Simple Swing Application
 * 
 * @author Jesse, ECSS, 3/28/2017
 *
 */
public class App {

	static Color colors[] = { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE,Color.MAGENTA };
	private JFrame frame;

	public App() {
		initialize();
	}

	private void initialize() {
		// Setup initial app window ..
		frame = new JFrame("Media Inventory");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setupComponents() {
		// Set basic/default layout ..
		frame.setLayout(null);

		
		
		// Add label for title ..
		JLabel titleField = new JLabel();
		titleField.setBounds(50, 10, 300, 30);// x, y, width, height - (pixels)
		titleField.setText("Simple Swing Application");
		titleField.setFont(new Font("Arial", Font.BOLD, 24));
		titleField.setForeground(Color.red);
		titleField.setVisible(true);
		frame.add(titleField);

		
		
		
		// Add label for output message ..
		JLabel textField = new JLabel();
		textField.setBounds(50, 200, 300, 30);
		textField.setVisible(true);
		frame.add(textField);

		// Add a YES button ..
		JButton yesButton = new JButton("Yes");
		yesButton.setBounds(50, 150, 90, 30);
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When clicked, set lower label text ..
				textField.setText(((JButton) e.getSource()).getText() + " button was clicked.");
			}
		});
		frame.add(yesButton);

		// Add a NO button ..
		JButton noButton = new JButton("No");
		noButton.setBounds(150, 150, 90, 30);
		noButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// When clicked, set lower label text ..
				textField.setText(((JButton) e.getSource()).getText() + " button was clicked.");
			}
		});
		frame.add(noButton);
		
		

	}

	public static void main(String[] args) {
		try {
			App app = new App();
			app.setupComponents();
			app.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
