package at.ach.CDA;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
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
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		contentPane.add(layeredPane, "name_92778199354778");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.LEFT_ALIGNMENT);
		layeredPane.add(panel, "name_93565557549433");
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 6, 139, 218);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setBounds(6, 6, 61, 16);
		panel_1.add(lblPatient);
		
		
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Patient 1", "Patient 2", "Patient 3", "Patient 4", "Patient 5", 
											"Patient 6", "Patient 7", "Patient 8", "Patient 9", "Patient 10", 
											"Patient 11", "Patient 12"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(6, 34, 128, 162);
		list.getAutoscrolls(); list.setAutoscrolls(true);
		panel_1.add(list); 
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(148, 6, 137, 218);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_1 = new JLabel("Patient Info");
		label_1.setBounds(6, 6, 128, 16);
		panel_2.add(label_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(284, 6, 150, 217);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel label = new JLabel("Lab Parameter");
		label.setBounds(6, 5, 138, 16);
		panel_3.add(label);
		
		JCheckBox checkBox = new JCheckBox("para 1");
		checkBox.setBounds(6, 33, 128, 23);
		panel_3.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("para 2");
		checkBox_1.setBounds(6, 58, 128, 23);
		panel_3.add(checkBox_1);
		
		JCheckBox chckbxPara = new JCheckBox("para 3");
		chckbxPara.setBounds(6, 85, 128, 23);
		panel_3.add(chckbxPara);
		
		JCheckBox chckbxPara_1 = new JCheckBox("para 4");
		chckbxPara_1.setBounds(6, 111, 128, 23);
		panel_3.add(chckbxPara_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(0, 236, 434, 26);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JButton b1 = new JButton("Add Lab report");
		b1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		b1.setBounds(16, 6, 98, 14);
		panel_4.add(b1);
		
		JButton b2 = new JButton("Show graph");
		b2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		b2.setBounds(330, 6, 98, 14);
		panel_4.add(b2);
	}
}
