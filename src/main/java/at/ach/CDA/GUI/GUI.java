package at.ach.CDA.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import at.ach.CDA.converter.CDALabreportParser;
import at.ach.CDA.model.CodedLabparameter;
import at.ach.CDA.model.Labreport;
import at.ach.CDA.model.Observation;
import at.ach.CDA.model.Patient;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLayeredPane layeredPane;
	private JPanel page1;
	private JPanel page2;
	private JPanel page3;
	private JTextArea patientInfoDisplayPage1;
	private JList<Patient> patientList = new JList<Patient>();
	private JTextField labValueField;
	private List<CodedLabparameter> parameterList = new ArrayList<CodedLabparameter>();
	private List<CodedLabparameter> selectedParameters = new ArrayList<CodedLabparameter>();
	private Map<JCheckBox,CodedLabparameter> checkBoxMap = new HashMap<JCheckBox, CodedLabparameter>();
	private Map<JComboBox<String>,CodedLabparameter> comboBoxMap = new HashMap<JComboBox<String>, CodedLabparameter>();

	public void switchPanels(JPanel panel)
	{
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.validate();
		layeredPane.repaint();
		layeredPane.revalidate();
		
		System.out.println("GUI switched to another page. " + (
				selectedParameters.isEmpty() ?
				"No checkboxes were checked recently." :
				"These checkboxes were checked recently:" )
				);
		
		for (CodedLabparameter checkbox : selectedParameters)
		{
			System.out.println("\t" + checkbox.getCodeSystemName() + " : " + checkbox.getDisplayName());
		}
	}
	
	public GUI (Map<String,Patient> mappedPatients, Map<String,List<Labreport>> mappedLabreports)
	{
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
		
		/*---- Page 1 Layout ----*/	
		
		page1 = new JPanel();
		page1.setBounds(100, 100, 720, 700);
		page1.setBackground(Color.WHITE);
		page1.setBorder(null);
		page1.setAlignmentX(Component.LEFT_ALIGNMENT);
		page1.setLayout(null);
		layeredPane.add(page1);
		
		/*---- : added to the main panel #1--- */
		
		/*Scrolled list of Patients*/
		JScrollPane Patient_scrollPane = new JScrollPane();
		Patient_scrollPane.setBounds(16, 48, 139, 164);
		
		/*-- BUTTON --*/
		
		JButton showGraphButton = new JButton("Show graph");
		showGraphButton.setEnabled(false);
		showGraphButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				switchPanels( page2(mappedLabreports) );
			}		
		});
		showGraphButton.setBounds(445, 70, 126, 40);
		page1.add(showGraphButton);
		showGraphButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JButton addLabReportButton = new JButton("Add Lab report");
		addLabReportButton.setEnabled(false);
		addLabReportButton.setForeground(Color.BLACK);
		addLabReportButton.setBackground(new Color(102, 204, 255));
		addLabReportButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				switchPanels(page3);	
			}
			
		});
		
		
		addLabReportButton.setBounds(445, 122, 126, 40);
		page1.add(addLabReportButton);
		addLabReportButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
	
		/*---- Patient List from extracted patient profiles / CDA  ----*/
		
		DefaultListModel<Patient> patientListModel = new DefaultListModel<>();
		patientList.setModel(patientListModel);
		for (Patient patient : mappedPatients.values())
		{
			patientListModel.addElement(patient);
		}
		
		Patient_scrollPane.getViewport().setView(patientList);
		page1.add(Patient_scrollPane);
		
		/*--Patient Info--*/
		
		patientInfoDisplayPage1 = new JTextArea();
		patientInfoDisplayPage1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		patientInfoDisplayPage1.setText("Choose the patient");
		patientInfoDisplayPage1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		patientInfoDisplayPage1.setBackground(SystemColor.window);
		patientInfoDisplayPage1.setBounds(210, 48, 162, 163);
		patientInfoDisplayPage1.setLineWrap(true);
		page1.add(patientInfoDisplayPage1);
		
		/*- chosen list displays the detailed in next coloumn-*/
		
		patientList.addListSelectionListener(new ListSelectionListener()
		{	
			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				Patient p = patientList.getSelectedValue();
				if (!arg0.getValueIsAdjusting())
				{
					patientInfoDisplayPage1.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
									"DOB: " + p.getBirthdate() + System.lineSeparator()
									+ "Gender: " + p.getGender() + System.lineSeparator()
									+ "Social Insurance: " + p.getSocialInsuranceNumber());
					showGraphButton.setEnabled(true);
					addLabReportButton.setEnabled(true);
				}
			}
		});
		
		/*--CheckBox: Lab Parameter view selection --*/
		
		JPanel checkBoxPanel = new JPanel();
		checkBoxPanel.setBackground(Color.WHITE);
		checkBoxPanel.setBounds(16, 270, 623, 500);

		JScrollPane checkBoxPanelScroll = new JScrollPane(checkBoxPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		checkBoxPanelScroll.setBounds(16, 270, 623, 361);
		page1.add(checkBoxPanelScroll);
		checkBoxPanel.setLayout(new GridLayout(0, 2, 0, 0));
		List<JCheckBox> CheckBoxList = new ArrayList<JCheckBox>();
		
		// read CodedLabparameters from catalogue file
		
		parameterList = CDALabreportParser.extractCodedLabparameters("/home/ingo/git/ACH-CDA/src/main/resources/cda/labreport0.cda");
//		parameterList = CDALabreportParser.extractCodedLabparameters("/Users/dahnkim/git/ACH-CDA/src/main/resources/cda/labreport0.cda");
//		parameterList = CDALabreportParser.extractCodedLabparameters("U:\\git\\ACH-CDA\\src\\main\\resources\\cda\\labreport0.cda");
		
		System.out.println("The GUI extracted " + parameterList.size() + " CodedLabparameters from catalogue file.");
		
		for(CodedLabparameter test : parameterList)
		{	
			JCheckBox myCheckBox = new JCheckBox(test.getDisplayName());
			checkBoxPanel.add(myCheckBox);
			CheckBoxList.add(myCheckBox);
			checkBoxMap.put(myCheckBox,test);
		}
			
		for(JCheckBox myCheckBox : CheckBoxList)
		{
			myCheckBox.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					if (myCheckBox.isSelected())
					{
						System.out.println("This checkbox was selected: " + myCheckBox.getText());
						selectedParameters.add( checkBoxMap.get(myCheckBox) ); // TODO: possible null pointer exception here!
					}
					else
					{
						System.out.println("This checkbox was deselected: " + myCheckBox.getText());
						selectedParameters.remove( checkBoxMap.get(myCheckBox) ); // TODO: possible null pointer exception here!
					}
				}
			});  
		}
			
		/*-- Contienued: Page 1 Layout  --*/
		
		JPanel page1TitleBox1 = new JPanel();
		page1TitleBox1.setBackground(new Color(102, 204, 255));
		page1TitleBox1.setBounds(6, 6, 651, 34);
		page1.add(page1TitleBox1);
		page1TitleBox1.setLayout(null);
		
		JLabel patientInfoLabel = new JLabel("Patient Info");
		patientInfoLabel.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		patientInfoLabel.setBounds(204, 9, 160, 22);
		page1TitleBox1.add(patientInfoLabel);
		patientInfoLabel.setForeground(Color.WHITE);
		JLabel patientListLabel = new JLabel("Patient");
		patientListLabel.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		patientListLabel.setBounds(6, 6, 130, 28);
		page1TitleBox1.add(patientListLabel);
		patientListLabel.setForeground(Color.WHITE);
		
		JPanel page1TitleBox2 = new JPanel();
		page1TitleBox2.setLayout(null);
		page1TitleBox2.setBackground(new Color(102, 204, 255));
		page1TitleBox2.setBounds(6, 224, 651, 34);
		page1.add(page1TitleBox2);
		
		JLabel labParameterLabel = new JLabel("Lab Parameters");
		labParameterLabel.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		labParameterLabel.setBounds(6, 10, 200, 22);
		page1TitleBox2.add(labParameterLabel);
		labParameterLabel.setForeground(Color.WHITE);
	    
		/*-- Page 3 Layout --*/
		
		page3 = new JPanel();
		page3.setBackground(Color.WHITE);
		layeredPane.add(page3, "name_100020660159536");
		page3.setLayout(null);
		
		JTextArea patientInfoDisplayPage3 = new JTextArea();
		patientList.addListSelectionListener(new ListSelectionListener()
		{
		    @Override
		    public void valueChanged(ListSelectionEvent e)
		    {
		    	Patient p = patientList.getSelectedValue();
		    	patientInfoDisplayPage3.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
						"DOB: " + p.getBirthdate() + System.lineSeparator()
						+ "Gender: " + p.getGender() + System.lineSeparator()
						+ "Social Insurance: " + p.getSocialInsuranceNumber());
		    }
		});
		patientInfoDisplayPage3.setLineWrap(true);
		patientInfoDisplayPage3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		patientInfoDisplayPage3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		patientInfoDisplayPage3.setBackground(SystemColor.window);
		
		JScrollPane patientInfoScroll2 = new JScrollPane(patientInfoDisplayPage3);
		patientInfoScroll2.setBounds(10, 46, 453, 53);
		
		page3.add(patientInfoScroll2);
		
		JPanel page3TitleBox1 = new JPanel();
		page3TitleBox1.setLayout(null);
		page3TitleBox1.setBackground(new Color(102, 204, 255));
		page3TitleBox1.setBounds(0, 0, 651, 34);
		page3.add(page3TitleBox1);
		
		JLabel page3Label1 = new JLabel("Patient Info");
		page3Label1.setForeground(Color.WHITE);
		page3Label1.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		page3Label1.setBounds(6, 12, 160, 22);
		page3TitleBox1.add(page3Label1);
		
		JPanel page3TitleBox2 = new JPanel();
		page3TitleBox2.setLayout(null);
		page3TitleBox2.setBackground(new Color(102, 204, 255));
		page3TitleBox2.setBounds(0, 111, 651, 34);
		page3.add(page3TitleBox2);
		
		JLabel page3Label2 = new JLabel("Lab Parameter");
		page3Label2.setForeground(Color.WHITE);
		page3Label2.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		page3Label2.setBounds(6, 6, 164, 22);
		page3TitleBox2.add(page3Label2);
		
		JPanel page3ButtonBox = new JPanel();
		page3ButtonBox.setLayout(null);
		page3ButtonBox.setBackground(new Color(102, 204, 255));
		page3ButtonBox.setBounds(0, 598, 651, 40);
		page3.add(page3ButtonBox);
		
		JButton btnNewSave = new JButton("Save");
		btnNewSave.setBounds(519, 0, 126, 40);
		page3ButtonBox.add(btnNewSave);
		btnNewSave.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		JButton btnNewBack = new JButton("Cancel");
		btnNewBack.setBounds(6, 0, 126, 40);
		page3ButtonBox.add(btnNewBack);
		btnNewBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				switchPanels(page1);	
			}		
		});
		btnNewBack.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		// table setup
		
		JTable page3Table = new JTable();
				
		// a table model creation 
		
		String[] tableColumns = {"ID", "Value", "unit"};
		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(tableColumns);
				
		// set the model to the table
		
		page3Table.setModel(tableModel);
				
		// table visual setup
		
		page3Table.setBackground(Color.LIGHT_GRAY);
		page3Table.setForeground(Color.BLACK);
		Font font = new Font("", 1,22);
		page3Table.setFont(font);
		page3Table.setRowHeight(30);
				
		// create JScrollPane
		
		JScrollPane tableScroll = new JScrollPane(page3Table);
		tableScroll.setBounds(10, 216, 400, 300);
		page3.add(tableScroll);
		
		JLabel labelParameter = new JLabel("Parameter");
		labelParameter.setBounds(436, 228, 128, 16);
		page3.add(labelParameter);
		
		JComboBox<String> paraSelection = new JComboBox<String>();
		paraSelection.setBounds(433, 256, 200, 27);
		page3.add(paraSelection);

		for(CodedLabparameter test : parameterList)
		{	
			paraSelection.addItem(test.getDisplayName());
			comboBoxMap.put(paraSelection,test);
		}
		
		JLabel lblValue = new JLabel("Value");
		lblValue.setBounds(436, 295, 61, 16);
		page3.add(lblValue);
		
		labValueField = new JTextField();
		labValueField.setBounds(434, 324, 130, 26);
		page3.add(labValueField);
		labValueField.setColumns(10);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setBounds(436, 362, 61, 16);
		page3.add(lblUnit);
		
		JComboBox<String> valueUnitSelection = new JComboBox<String>();
		valueUnitSelection.setBounds(436, 390, 118, 27);
		page3.add(valueUnitSelection);
		
		valueUnitSelection.addItem("G/l");
		valueUnitSelection.addItem("T/l");
		valueUnitSelection.addItem("g/dl");
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(434, 443, 117, 40);
		btnAdd.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String[] row = new String[3];
				
				row[0] = paraSelection.getSelectedItem().toString();
				row[1] = labValueField.getText();
				row[2] = valueUnitSelection.getSelectedItem().toString();

				tableModel.addRow(row);
			}
		});
		
		page3.add(btnAdd);	
	}
	
	///----------page 2--------////
	
	private JPanel page2 (Map<String,List<Labreport>> mappedLabreports)
	{
		page2 = new JPanel();
		
		page2.setBackground(new Color(255, 255, 255));
		layeredPane.add(page2, "name_99374934333812");
		page2.setLayout(null);
	
		JTextArea page2PatientInfo = new JTextArea();
		Patient p = patientList.getSelectedValue();
    	page2PatientInfo.setText("Name: " + p.getGivenName() + " "+p.getFamilyName() + System.lineSeparator() + 
				"DOB: " + p.getBirthdate() + System.lineSeparator()
				+ "Gender: " + p.getGender() + System.lineSeparator()
				+ "Social Insurance: " + p.getSocialInsuranceNumber());

		page2PatientInfo.setLineWrap(true);
		page2PatientInfo.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		page2PatientInfo.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		page2PatientInfo.setBackground(SystemColor.window);
		
		JScrollPane page2PatientScroll = new JScrollPane(page2PatientInfo);
		page2PatientScroll.setBounds(10, 46, 453, 53);
		page2.add(page2PatientScroll);
		
		JPanel page2TitleBox1 = new JPanel();
		page2TitleBox1.setLayout(null);
		page2TitleBox1.setBackground(new Color(102, 204, 255));
		page2TitleBox1.setBounds(0, 0, 651, 34);
		page2.add(page2TitleBox1);
		
		JLabel page2Title1 = new JLabel("Patient Info");
		page2Title1.setForeground(Color.WHITE);
		page2Title1.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		page2Title1.setBounds(6, 12, 160, 22);
		page2TitleBox1.add(page2Title1);
		
		
		JPanel page2TitleBox2 = new JPanel();
		page2TitleBox2.setLayout(null);
		page2TitleBox2.setBackground(new Color(102, 204, 255));
		page2TitleBox2.setBounds(0, 111, 651, 34);
		page2.add(page2TitleBox2);
		
		JLabel lblReportOverview = new JLabel("Report Overview");
		lblReportOverview.setForeground(Color.WHITE);
		lblReportOverview.setFont(new Font("Myriad Pro", Font.BOLD, 20));
		lblReportOverview.setBounds(6, 12, 250, 22);
		page2TitleBox2.add(lblReportOverview);
		
		JButton page2BackButton = new JButton("Back");
		page2BackButton.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		page2BackButton.setBounds(504, 46, 126, 40);
		page2BackButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				switchPanels(page1);	
			}		
		});
		page2.add(page2BackButton);
		
		/*------Graphs-----*/
		
		JPanel graphContainer = new JPanel();
	
		graphContainer.setLayout(new GridLayout(0, 2, 0, 0));
		JScrollPane graphScroll = new JScrollPane(graphContainer);
		graphScroll.setBounds(10, 148, 628, 400);
		page2.add(graphScroll);
		
		Patient selectedPatient = patientList.getSelectedValue();
		List<Labreport> thePatientsLabreports = mappedLabreports.get( selectedPatient.getSocialInsuranceNumber() );

		for (CodedLabparameter parameter : selectedParameters)
		{
			graphContainer.add( getPlotFromLabreports(thePatientsLabreports, parameter) );
		}
		
		return page2;
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
					String strValue = observation.getValueValue();
					unit = observation.getValueUnit();
					
					char[] chars = new char[6];
					
					for(int i=2; i<=7;i++)
					{
						chars[i-2] = strTime.charAt(i);
					}
					
					strTime = new String(chars);
					
					int weirdTime = Integer.parseInt(strTime);
					
					series.add( weirdTime, Double.parseDouble(strValue) );
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
				false );
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(300, 270));
		
		return chartPanel;
	}
}
