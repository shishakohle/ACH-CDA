package at.ach.CDA;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;


public class GUI extends JFrame implements ActionListener {
	
	JFrame frame; JLabel title; JButton b1; JButton b2;
	
	GUI(String s){
		//frame =  new JFrame("Patient chart");
		
		super(s);
		title = new JLabel("Patient Chart");
		title.setFont(new Font("Margem", Font.BOLD, 40));
		title.setBounds(50, 20, 500, 40);
		
		b1 = new JButton("Search");
		b1.setBounds(50, 100, 200, 30);
		
		b2 = new JButton("Overview");
		b2.setBounds(300,100, 200, 30);
		b1.addActionListener(this);  
        b2.addActionListener(this);  
		add(title);add(b1);add(b2);
		setSize(600, 800);
		setLayout(null);setVisible(true);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			//TO DO
		}
		else if(e.getSource()==b2) {
			//TO DO
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	new GUI("Patient chart");
	}

}
