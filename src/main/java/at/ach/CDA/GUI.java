package at.ach.CDA;

import javax.swing.*;
import java.util.Vector;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import javax.swing.border.BevelBorder;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel BigPanel1;
	private JPanel BigPanel2;
	private JPanel BigPanel3;
	private final Action action = new SwingAction();
	private JTextArea textArea;
	private JTextArea textArea2;
	private JTextArea textArea3;
	private JList list2;
	private JTable table;
	private JTextField textField;


	

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

	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.validate();
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			/*putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");*/
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	

	
	
	/*private class TextAreaTesting extends JList {
		public void TestAreaTesting(TextArea a) {
		Patient p = (Patient) list2.getSelectedValue();
		a.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
				"DOB: " + p.getBirthdate() + System.lineSeparator()
				+ "Gender: " + p.getGender() + System.lineSeparator()
				+ "Social Insurance: " + p.getSocialInsuranceNumber());}
	}*
	
	
	/*for patient info
	 * selected patient object from list 
	 * */
	
	/*
	 * 
	 * */
	

	/**
	 * Create the frame.
	 */
	public GUI() {
		setTitle("Ingo & Dahn Healthcare co");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 670); //(100, 100, 667, 459)
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		

		layeredPane = new JLayeredPane();
		
		layeredPane.setBackground(Color.WHITE);
		contentPane.add(layeredPane, "name_92778199354778");
		layeredPane.setLayout(new CardLayout(0, 0));
		
		BigPanel1 = new JPanel();
		BigPanel1.setBounds(100, 100, 720, 700);
		BigPanel1.setBackground(Color.WHITE);
		BigPanel1.setBorder(null);
		BigPanel1.setAlignmentX(Component.LEFT_ALIGNMENT);
		//layeredPane.add(BigPanel1, "name_93565557549433");
		BigPanel1.setLayout(null);
		layeredPane.add(BigPanel1);
		
		
		/*JScrollPane pane = new JScrollPane(BigPanel1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBounds(100, 100, 500, 500);
		pane.setAutoscrolls(true);
		//pane.setBounds(100, 100, 720, 700);
		//pane.getViewport().setView(BigPanel1);
		//pane.setVisible(true);
		layeredPane.add(pane);*/
		
		
		/*---- : added to the main panel #1--- */
		
		/*Scrolled list of Patients*/
		JScrollPane Patient_scrollPane = new JScrollPane();
		Patient_scrollPane.setBounds(16, 48, 139, 164);
		
		/*- ArrayList of Patients  -*/
		//ArrayList<Patient> PList = new ArrayList<Patient>();
		
		/*for (int i = 0; i < 13; i ++) {
			Patient p = new Patient();
			p.setFamlilyName("testing" + i);
			p.setGivenName("Kim" + i);
			//p.setGender("m");
			PList.add(p);
		}*/
		
		Patient patient1 = new Patient("23424234","Dahn","Kim","F","13101991");
		//PList.add(patient1);
		
		
		//JList list = new JList(PList.toArray());
		//list.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		
		/*for (int cnt = 0; cnt < PList.size(); cnt ++) {
		DefaultListModel<Patient> model = new DefaultListModel<>();
		for (Patient p : PList) {
			model.addElement(p.toString());
			}
		list.setModel(model);
		list.setSelectedIndex(0);
		}*/
		
		/*-- BUTTON --*/
		
		JButton b1 = new JButton("Show graph");
		//b1.setAction(action);
		b1.setEnabled(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(BigPanel2);	
			}		
		});
		b1.setBounds(445, 70, 126, 40);
		BigPanel1.add(b1);
		b1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JButton b2 = new JButton("Add Lab report");
		b2.setEnabled(false);
		b2.setForeground(Color.BLACK);
		b2.setBackground(new Color(102, 204, 255));
		//b1.setAction(action);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(BigPanel3);	
			}
			
		});
		
		b2.setBounds(445, 122, 126, 40);
		BigPanel1.add(b2);
		b2.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JList<Patient> list2 = new JList<>();
		DefaultListModel<Patient> model = new DefaultListModel<>();
		list2.setModel(model);
		model.addElement(new Patient("234","Dahn","SMith","f","sefe" ));
		model.addElement(new Patient("dfsf","Dahn","Kim2","g","wrewrw"));
		
		/*list2.getSelectionModel().addListSelectionListener(e -> {
			Patient p = list2.getSelectedValue();
			textArea.setText("Name: " + p.getGivenName() + p.getFamilyName() + System.lineSeparator() + 
									"DOB: " + p.getBirthdate() + System.lineSeparator()
									+ "Gender: " + p.getGender() + System.lineSeparator()
									+ "Social Insurance: " + p.getSocialInsuranceNumber() );
		});*/
		
		
		Patient_scrollPane.getViewport().setView(list2);
		BigPanel1.add(Patient_scrollPane);
		
		
		/*--Patient Info--*/
		textArea = new JTextArea();
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		textArea.setText("Choose the patient");
		textArea.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBackground(SystemColor.window);
		textArea.setBounds(210, 48, 162, 163);
		textArea.setLineWrap(true);
		BigPanel1.add(textArea);
		
		/*- chosen list displays the detailed in next coloumn-*/
		list2.addListSelectionListener(new ListSelectionListener() {	
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Patient p = list2.getSelectedValue();
				if (!arg0.getValueIsAdjusting()) {
					textArea.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
									"DOB: " + p.getBirthdate() + System.lineSeparator()
									+ "Gender: " + p.getGender() + System.lineSeparator()
									+ "Social Insurance: " + p.getSocialInsuranceNumber());
					b1.setEnabled(true);
					b2.setEnabled(true);
					
					//textArea.setText(list2.getSelectedValue().toString());
				}
			}
		});
		
		/*--CheckBox: Lab Parameter view selection --*/
		JPanel CheckBoxPanel = new JPanel();
		CheckBoxPanel.setBackground(Color.WHITE);
		CheckBoxPanel.setBounds(16, 270, 623, 500);

		JScrollPane pane = new JScrollPane(CheckBoxPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBounds(16, 270, 623, 361);
		//pane.setAutoscrolls(true);
		//pane.setBounds(100, 100, 720, 700);
		//pane.getViewport().setView(BigPanel1);
		//pane.setVisible(true);
		BigPanel1.add(pane);
		CheckBoxPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel CBpanel_3 = new JPanel();
		CheckBoxPanel.add(CBpanel_3);
		CBpanel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel Para1 = new JLabel("  Hämatologie - BlutBild ");
		Para1.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		CBpanel_3.add(Para1);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		CBpanel_3.add(horizontalStrut);
		
		JCheckBox checkBox_1 = new JCheckBox("Leukozyten");
		CBpanel_3.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("Thrombozyten");
		CBpanel_3.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("Erythrozyten");
		CBpanel_3.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("Hämoglobin");
		CBpanel_3.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("Hämatokrit");
		CBpanel_3.add(checkBox_5);
		
		JCheckBox checkBox_6 = new JCheckBox("MCH");
		CBpanel_3.add(checkBox_6);
		
		JCheckBox checkBox_7 = new JCheckBox("MCV");
		CBpanel_3.add(checkBox_7);
		
		JCheckBox checkBox_8 = new JCheckBox("MCHC");
		CBpanel_3.add(checkBox_8);
		
		JCheckBox checkBox_9 = new JCheckBox("Akt. Lymph, rel. mikr");
		CBpanel_3.add(checkBox_9);
		
		JLabel label = new JLabel("");
		CBpanel_3.add(label);
		

		
		JPanel CBpanel_4 = new JPanel();
		CheckBoxPanel.add(CBpanel_4);
		CBpanel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		CBpanel_4.add(horizontalStrut_1);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		CBpanel_4.add(horizontalStrut_2);
		
		JLabel Para2 = new JLabel("  Hämatologie - Knochenmark Morphologie ");
		Para2.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		CBpanel_4.add(Para2);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		CBpanel_4.add(horizontalStrut_3);
		
		JCheckBox checkBox_10 = new JCheckBox("Lymphozyten rel. /KM");
		CBpanel_4.add(checkBox_10);
		
		JCheckBox checkBox_11 = new JCheckBox("Blasten rel. /KM");
		CBpanel_4.add(checkBox_11);
		
		JCheckBox checkBox_12 = new JCheckBox("Eosinophile pro 100 Zellen /KM");
		CBpanel_4.add(checkBox_12);
		
		JPanel CBpanel_5 = new JPanel();
		CheckBoxPanel.add(CBpanel_5);
		CBpanel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		CBpanel_5.add(horizontalStrut_4);
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		CBpanel_5.add(horizontalStrut_5);
		
		JLabel Para3 = new JLabel("  Hämostaseologie - Hämostaseologie Globaltest ");
		Para3.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		CBpanel_5.add(Para3);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		CBpanel_5.add(horizontalStrut_6);
		
		JCheckBox checkBox_13 = new JCheckBox("PTZ(prothrombinz.)");
		CBpanel_5.add(checkBox_13);
		
		JCheckBox checkBox_14 = new JCheckBox("INR");
		CBpanel_5.add(checkBox_14);
		
		JCheckBox checkBox_15 = new JCheckBox("aPTT");
		CBpanel_5.add(checkBox_15);
		
		JCheckBox checkBox_16 = new JCheckBox("AT III Aktivität");
		CBpanel_5.add(checkBox_16);
		
		JCheckBox checkBox_17 = new JCheckBox("D-Dimer");
		CBpanel_5.add(checkBox_17);
		
		JPanel CBpanel_6 = new JPanel();
		CheckBoxPanel.add(CBpanel_6);
		CBpanel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		CBpanel_6.add(horizontalStrut_7);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		CBpanel_6.add(horizontalStrut_8);
		
		JLabel Para4 = new JLabel("  Hämostaseologie - Einzelfaktoranalysen");
		Para4.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		CBpanel_6.add(Para4);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		CBpanel_6.add(horizontalStrut_9);
		
		JCheckBox checkBox_18 = new JCheckBox("Faktor VII Akt.");
		CBpanel_6.add(checkBox_18);
		
		JCheckBox checkBox_19 = new JCheckBox("Faktor VIII Akt.");
		CBpanel_6.add(checkBox_19);
		
		JPanel CBpanel_7 = new JPanel();
		CheckBoxPanel.add(CBpanel_7);
		CBpanel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		CBpanel_7.add(horizontalStrut_10);
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		CBpanel_7.add(horizontalStrut_11);
		
		JLabel Para5 = new JLabel("  Hämostaseologie - Thromobophilie Tests");
		Para5.setFont(new Font("Lucida Grande", Font.BOLD, 12));
		CBpanel_7.add(Para5);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		CBpanel_7.add(horizontalStrut_12);
		
		JCheckBox checkBox_20 = new JCheckBox("Protein C Aktivität");
		CBpanel_7.add(checkBox_20);
		
		JCheckBox checkBox_21 = new JCheckBox("Protein S Aktivität");
		CBpanel_7.add(checkBox_21);
		
		
		/*-- Panels --*/
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(102, 204, 255));
		panel_1.setBounds(6, 6, 651, 34);
		BigPanel1.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel PatientInfo = new JLabel("Patient Info");
		PatientInfo.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		PatientInfo.setBounds(204, 9, 160, 22);
		panel_1.add(PatientInfo);
		PatientInfo.setForeground(Color.WHITE);
		JLabel PatientList = new JLabel("Patient");
		PatientList.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		PatientList.setBounds(6, 6, 130, 28);
		panel_1.add(PatientList);
		PatientList.setForeground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(102, 204, 255));
		panel_2.setBounds(6, 224, 651, 34);
		BigPanel1.add(panel_2);
		
		JLabel LabParameter = new JLabel("Lab Parameter");
		LabParameter.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		LabParameter.setBounds(6, 6, 164, 22);
		panel_2.add(LabParameter);
		LabParameter.setForeground(Color.WHITE);
		BigPanel2 = new JPanel();
		BigPanel2.setBackground(new Color(255, 255, 255));
		layeredPane.add(BigPanel2, "name_99374934333812");
		BigPanel2.setLayout(null);
	
	
		JTextArea textArea_2 = new JTextArea();
		list2.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		    	Patient p = list2.getSelectedValue();
		    	textArea_2.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
						"DOB: " + p.getBirthdate() + System.lineSeparator()
						+ "Gender: " + p.getGender() + System.lineSeparator()
						+ "Social Insurance: " + p.getSocialInsuranceNumber());
		    }
		});
		

		textArea_2.setLineWrap(true);
		textArea_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		textArea_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea_2.setBackground(SystemColor.window);
		
		JScrollPane pane2 = new JScrollPane(textArea_2);
		pane2.setBounds(10, 46, 453, 53);
		BigPanel2.add(pane2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(102, 204, 255));
		panel_3.setBounds(0, 0, 651, 34);
		BigPanel2.add(panel_3);
		
		JLabel label_1 = new JLabel("Patient Info");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		label_1.setBounds(6, 12, 160, 22);
		panel_3.add(label_1);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(102, 204, 255));
		panel.setBounds(0, 111, 651, 34);
		BigPanel2.add(panel);
		
		JLabel lblReportOverview = new JLabel("Report Overview");
		lblReportOverview.setForeground(Color.WHITE);
		lblReportOverview.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		lblReportOverview.setBounds(6, 12, 160, 22);
		panel.add(lblReportOverview);
		
		JButton button3 = new JButton("Back");
		button3.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		button3.setBounds(504, 46, 126, 40);
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(BigPanel1);	
			}		
		});
		BigPanel2.add(button3);
		
		/*-- Page 3 --*/
		
		BigPanel3 = new JPanel();
		BigPanel3.setBackground(Color.WHITE);
		layeredPane.add(BigPanel3, "name_100020660159536");
		BigPanel3.setLayout(null);
	
		
		JTextArea textArea_3 = new JTextArea();
		list2.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		    	Patient p = list2.getSelectedValue();
		    	textArea_3.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
						"DOB: " + p.getBirthdate() + System.lineSeparator()
						+ "Gender: " + p.getGender() + System.lineSeparator()
						+ "Social Insurance: " + p.getSocialInsuranceNumber());
		    }
		});
		textArea_3.setLineWrap(true);
		textArea_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		textArea_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea_3.setBackground(SystemColor.window);
		//pane3.setBounds(10, 46, 162, 163);
		
		JScrollPane pane3 = new JScrollPane(textArea_3);
		pane3.setBounds(10, 46, 453, 53);
		
		BigPanel3.add(pane3);
		
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(new Color(102, 204, 255));
		panel_4.setBounds(0, 0, 651, 34);
		BigPanel3.add(panel_4);
		
		JLabel label_2 = new JLabel("Patient Info");
		label_2.setForeground(Color.WHITE);
		label_2.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		label_2.setBounds(6, 12, 160, 22);
		panel_4.add(label_2);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(102, 204, 255));
		panel_5.setBounds(0, 111, 651, 34);
		BigPanel3.add(panel_5);
		
		JLabel label_3 = new JLabel("Lab Parameter");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		label_3.setBounds(6, 6, 164, 22);
		panel_5.add(label_3);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(102, 204, 255));
		panel_6.setBounds(0, 598, 651, 40);
		BigPanel3.add(panel_6);
		
		JButton btnNewSave = new JButton("Save");
		btnNewSave.setBounds(519, 0, 126, 40);
		panel_6.add(btnNewSave);
		btnNewSave.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JButton btnNewBack = new JButton("Cancel");
		btnNewBack.setBounds(6, 0, 126, 40);
		panel_6.add(btnNewBack);
		btnNewBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(BigPanel1);	
			}		
		});
		btnNewBack.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		//table setup
		JTable table = new JTable();
				
		//a table model creation 
		String[] columns = {"ID", "Value", "unit"};
		DefaultTableModel Tmodel = new DefaultTableModel();
		Tmodel.setColumnIdentifiers(columns);
				
		//set the model to the table
		table.setModel(Tmodel);
				
		//table visual setup
		table.setBackground(Color.LIGHT_GRAY);
		table.setForeground(Color.BLACK);
		Font font = new Font("", 1,22);
		table.setFont(font);
		table.setRowHeight(30);
				
		//create JScrollPane
		JScrollPane Spane = new JScrollPane(table);
		Spane.setBounds(10, 216, 400, 300);
		BigPanel3.add(Spane);

		
		JLabel lblNewLabel = new JLabel("Parameter");
		lblNewLabel.setBounds(436, 228, 128, 16);
		BigPanel3.add(lblNewLabel);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(433, 256, 200, 27);
		BigPanel3.add(comboBox);
		comboBox.addItem("Leukozyten");
		comboBox.addItem("Thrombozyten");
		
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(436, 295, 61, 16);
		BigPanel3.add(lblValue);
		
		textField = new JTextField();
		textField.setBounds(434, 324, 130, 26);
		BigPanel3.add(textField);
		textField.setColumns(10);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setBounds(436, 362, 61, 16);
		BigPanel3.add(lblUnit);
		
		JComboBox<String> comboBox_1 = new JComboBox();
		comboBox_1.setBounds(436, 390, 118, 27);
		BigPanel3.add(comboBox_1);
		
		comboBox_1.addItem("G/l");
		comboBox_1.addItem("T/l");
		comboBox_1.addItem("g/dl");
		

		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(434, 443, 117, 40);
		btnAdd.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{	String[] row = new String[3];
				row[0]=comboBox.getSelectedItem().toString();
				row[1]=textField.getText();
				row[2]=comboBox_1.getSelectedItem().toString();
				
				//add row to the model
				Tmodel.addRow(row);
			}
		});
		
		BigPanel3.add(btnAdd);
		
	
		

	}
}