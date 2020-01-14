package at.ach.CDA;

import javax.swing.*;
import java.util.Vector;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

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
	private JList<Patient> list2 = new JList<Patient>();
	private JTable table;
	private JTextField textField;
	//private String tests[]= {"Leukozyten","Thrombozyten", "Erythrozyten", "Hämoglobin", "Hämatokrit", "MCH", "Leukozyten","Thrombozyten", "Erythrozyten", "Hämoglobin", "Hämatokrit", "MCH", "Leukozyten","Thrombozyten", "Erythrozyten", "Hämoglobin", "Hämatokrit", "MCH", "Leukozyten","Thrombozyten", "Erythrozyten", "Hämoglobin", "Hämatokrit", "MCH"};
	private List<CodedLabparameter> tests = new ArrayList<CodedLabparameter>();
	private JPanel panel_7;
	private ChartPanel chartPanel;
	private List<CodedLabparameter> SelectedList = new ArrayList<CodedLabparameter>();
	private Map<JCheckBox,CodedLabparameter> checkBoxMap = new HashMap<JCheckBox, CodedLabparameter>();
	private Map<JComboBox,CodedLabparameter> comboBoxMap = new HashMap<JComboBox, CodedLabparameter>();
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI(new ArrayList<Patient>());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.validate();
		layeredPane.repaint();
		layeredPane.revalidate();
		
		System.out.println("GUI switched to another page. " + (
				SelectedList.isEmpty() ?
				"No checkboxes were checked recently." :
				"These checkboxes were checked recently:" )
				);
		
		for (CodedLabparameter checkbox : SelectedList)
		{
			System.out.println("\t" + checkbox.getCodeSystemName() + " : " + checkbox.getDisplayName());
		}
	}
	
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			/*putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");*/
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	

	/**
	 * Create the frame.
	 */
	public GUI(Map<String,Patient> mappedPatients, Map<String,List<Labreport>> mappedLabreports) {
		// start GUI config
		setTitle("Ingo & Dahn Healthcare co");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 667, 740);
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

		
		/*---- : added to the main panel #1--- */
		
		/*Scrolled list of Patients*/
		JScrollPane Patient_scrollPane = new JScrollPane();
		Patient_scrollPane.setBounds(16, 48, 139, 164);
		
		/*-- BUTTON --*/
		
		JButton b1 = new JButton("Show graph");
		//b1.setAction(action);
		b1.setEnabled(false);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//switchPanels(BigPanel2);
				switchPanels(page2(mappedLabreports));
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
		
		DefaultListModel<Patient> model = new DefaultListModel<>();
		list2.setModel(model);
		for (Patient patient : mappedPatients.values())
		{
			model.addElement(patient);
		}
		
		
		
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
		CheckBoxPanel.setLayout(new GridLayout(0, 2, 0, 0));
		List<JCheckBox> CheckBoxList = new ArrayList<JCheckBox>();
		
		// read CodedLabparameters from catalogue file
		tests = CDALabreportParser.extractCodedLabparameters("/home/ingo/git/ACH-CDA/src/main/resources/cda/labreport0.cda");
		//tests = CDALabreportParser.extractCodedLabparameters("/Users/dahnkim/git/ACH-CDA/src/main/resources/cda/labreport0.cda");
		//tests = CDALabreportParser.extractCodedLabparameters("U:\\git\\ACH-CDA\\src\\main\\resources\\cda\\labreport0.cda");
		System.out.println("The GUI extracted " + tests.size() + " CodedLabparameters from catalogue file.");
		
		for(CodedLabparameter test : tests)
		{	
			JCheckBox myCheckBox = new JCheckBox(test.getDisplayName());
			CheckBoxPanel.add(myCheckBox);
			CheckBoxList.add(myCheckBox);
			checkBoxMap.put(myCheckBox,test);
		}
			
		for(JCheckBox myCheckBox : CheckBoxList)
		{
			myCheckBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 if (myCheckBox.isSelected())
							 {
						 System.out.println("This checkbox was selected: " + myCheckBox.getText());
						 SelectedList.add( checkBoxMap.get(myCheckBox) ); // TODO: possible null pointer exception here!
							 }
					 else {
						 System.out.println("This checkbox was deselected: " + myCheckBox.getText());
						 SelectedList.remove( checkBoxMap.get(myCheckBox) ); // TODO: possible null pointer exception here!
					 }

				}
			});  
			
	
		}
			
		
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
		

		for(CodedLabparameter test : tests)
		{	
			comboBox.addItem(test.getDisplayName());
			comboBoxMap.put(comboBox,test);
		
		}

		
		
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
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(436, 390, 118, 27);
		BigPanel3.add(comboBox_1);
		
		//TODO: once you set value unit in 'CDALabreportParser', can you please enable this
		
		
		/*for (CodedLabparameter test: tests) {
			comboBox_1.addItem(test.getValueUnit());
			comboBoxMap.put(comboBox_1, test);
		}*/
		
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
		
		table.getModel().addTableModelListener(new TableModelListener(){
			
			public void tableChanged(TableModelEvent e) {
				
				for (int i = 0; i<table.getRowCount(); i++){
					for (int j = 0; j<table.getColumnCount(); j++){
						ArrayList testing = new ArrayList();
						testing.add((String)table.getModel().getValueAt(i, j));
						System.out.println(testing);
					}}}});

		
		BigPanel3.add(btnAdd);	

	}
	

	
	private JPanel page2 (Map<String,List<Labreport>> mappedLabreports)
	{
		BigPanel2 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(102, 204, 255));
		panel_2.setBounds(6, 224, 651, 34);
		
		JLabel LabParameter = new JLabel("Lab Parameters");
		LabParameter.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		LabParameter.setBounds(6, 6, 200, 22);
		panel_2.add(LabParameter);
		LabParameter.setForeground(Color.WHITE);
		BigPanel2.setBackground(new Color(255, 255, 255));
		layeredPane.add(BigPanel2, "name_99374934333812");
		BigPanel2.setLayout(null);
	
	
		JTextArea textArea_2 = new JTextArea();
		/*list2.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		    	Patient p = list2.getSelectedValue();
		    	textArea_2.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
						"DOB: " + p.getBirthdate() + System.lineSeparator()
						+ "Gender: " + p.getGender() + System.lineSeparator()
						+ "Social Insurance: " + p.getSocialInsuranceNumber());
		    }
		});*/
		Patient p = list2.getSelectedValue();
    	textArea_2.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
				"DOB: " + p.getBirthdate() + System.lineSeparator()
				+ "Gender: " + p.getGender() + System.lineSeparator()
				+ "Social Insurance: " + p.getSocialInsuranceNumber());
		

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
		lblReportOverview.setBounds(6, 12, 250, 22);
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
		
		/*------Graphs-----*/
		
		
		JPanel panel_7 = new JPanel();
	
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		JScrollPane scroll2 = new JScrollPane(panel_7);
		scroll2.setBounds(10, 148, 628, 400);
		BigPanel2.add(scroll2);
		
		
		/*JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 432, 628, 282);
		BigPanel2.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));*/
		
		// Plot the selected parameters
		
		/*panel_7.add( getPlotFromLabreports(new ArrayList<Labreport>(), "Erythrozyten") );
		panel_7.add( getPlotFromLabreports(new ArrayList<Labreport>(), "abcde") );
		panel_8.add( getPlotFromLabreports(new ArrayList<Labreport>(), "Erythrozyten") );
		panel_8.add( getPlotFromLabreports(new ArrayList<Labreport>(), "abcde") );*/
		
		// find out which Patient was selected
		
		Patient selectedPatient = list2.getSelectedValue();
		// System.out.println("The selected Patient is: " + selectedPatient.getFamilyName());
		
		// SelectedList contains all the selected lab parameters.
		// Unfortunately, atm, only 4 parameters can be plotted, see:
		// Improvised for now: Shorten SelectedList to a maximum of 4 ListItems.
		
		List<CodedLabparameter> parametersToPlot = new ArrayList<CodedLabparameter>();
		
		/*//for(int i=0; i<4; i++)
		for (int i=0; i < SelectedList.size(); i++)
		{
			if (i<SelectedList.size())
			{
				parametersToPlot.add( SelectedList.get(i) );
			}
		}
		
		// as for now (see TODO above), hardcoded number of plots is four*/
		
		List<Labreport> thePatientsLabreports = mappedLabreports.get( selectedPatient.getSocialInsuranceNumber() );
		
		/*for (int i=0; i < parametersToPlot.size(); i++)
		{
			if ( !parametersToPlot.isEmpty())	
			{
				panel_7.add( getPlotFromLabreports(thePatientsLabreports, parametersToPlot.remove(0))); 
				
			}
		}*/
		
		for (CodedLabparameter parameter : SelectedList)
		{
			panel_7.add( getPlotFromLabreports(thePatientsLabreports,parameter) );
		}
		
		return BigPanel2;
		
		/*if ( !parametersToPlot.isEmpty() )
			panel_7.add( getPlotFromLabreports(thePatientsLabreports,parametersToPlot.remove(0)) );
		if ( !parametersToPlot.isEmpty() )
			panel_7.add( getPlotFromLabreports(thePatientsLabreports, parametersToPlot.remove(0)) );
		if ( !parametersToPlot.isEmpty() )
			panel_7.add( getPlotFromLabreports(thePatientsLabreports, parametersToPlot.remove(0)) );
		if ( !parametersToPlot.isEmpty() )
			panel_7.add( getPlotFromLabreports(thePatientsLabreports, parametersToPlot.remove(0)) );*
		return BigPanel2;*/
		
		
	}
	
	
	private ChartPanel getPlotFromLabreports (List<Labreport> inputLabreports, CodedLabparameter parameter)
	{
		XYSeries series = new XYSeries(parameter.getDisplayName());
		
		String unit = "";
		
		for(Labreport labreport : inputLabreports)
		{
			for(Observation observation : labreport.getObservations() )
			{
				if(observation.getCodeSystem().equals(parameter.getCodeSystem()) && observation.getCodeCode().equals(parameter.getParameterCode()))
				{
					String strTime = observation.getEffectiveTimeValue();
					//long unixtime;
					String strValue = observation.getValueValue();
					unit = observation.getValueUnit();
					
					/* e.g. 20121201063400+0100
				            yyyymmddHHMMSSzzzzz
				            0123456789*/
					/*
				Date now = new Date();
				long ut3 = now.getTime() / 1000L;
				System.out.println(ut3);
					 */
					
					/*SimpleDateFormat ft = new SimpleDateFormat ("yyyymmddHHMMSSzzzzz");
					Date time = null;
					try
					{
						//time = ft.parse(strTime);
						//unixtime = time.getTime() / 1000L;
						//unixtime = time.getDate() * 1 +
						//		   time.getMonth() * 100
						System.out.println("Calculated unixtime: " + unixtime);
					}
					catch (ParseException e)
					{
						System.out.println("could not parse effective time");
						unixtime = 0;
					}*/
					
					
					//int unixtime = Integer.parseInt("121201");
					char[] chars = new char[6];
					
					for(int i=2; i<=7;i++)
					{
						chars[i-2] = strTime.charAt(i);
					}
					
					strTime = new String(chars);
					
					int unixtime = Integer.parseInt(strTime);
					
					series.add(unixtime, Double.parseDouble(strValue));
				}
			}
		}
		
		XYSeriesCollection data = new XYSeriesCollection(series);
		JFreeChart chart = ChartFactory.createXYLineChart(
				parameter.getDisplayName(),
				"Date", 
				unit, 
				data,
				PlotOrientation.VERTICAL,
				false,
				false,
				false);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(300, 270));
		
		return chartPanel;
	}

	
}
