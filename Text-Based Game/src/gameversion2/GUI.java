package gameversion2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
	
	private int buttonPress;
	private boolean isButtonPressed = false;
	private JFrame window = new JFrame("Dungeon Quest 420");
	private JTextArea displayText = new JTextArea();
	private JScrollPane scroll = new JScrollPane(displayText);
	private JButton one = new JButton("1");
	private JButton two = new JButton("2");
	private JButton three = new JButton("3");
	private JPanel buttonPanel = new JPanel();
	
	public void startGUI() {

		// Text area
		displayText.setBackground(Color.GRAY);
		displayText.setEditable(false);
		
		// Buttons
		one.setBackground(Color.CYAN);
		one.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
			    buttonPress = 1;
			    isButtonPressed = true;
			}  
		});
		two.setBackground(Color.CYAN);
		two.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				buttonPress = 2;
			    isButtonPressed = true;
			}  
		});
		three.setBackground(Color.CYAN);
		three.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				buttonPress = 3;
			    isButtonPressed = true;
			}  
		});
		
		// Panel to put buttons on
		buttonPanel.add(one);
		buttonPanel.add(two);
		buttonPanel.add(three);
		
		// Adding components to the window
		window.getContentPane().add(scroll);		
		window.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		// The window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	// Method to update the JTextArea displayText
	public void appendText(String t) {
		displayText.append(t + "\n");
		displayText.setCaretPosition(displayText.getDocument().getLength());
	}
	
	public int getButtonPress() {
		while(isButtonPressed == false){
		    try {
		       Thread.sleep(200);
		    } 
		    catch(Exception e) {
		    	System.out.println(e);
		    }
		}
		isButtonPressed = false;
		return buttonPress;
	}
}