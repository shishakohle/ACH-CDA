/* An application that juggles with CDA labreports.
 * 
 *  Written by Ingo Weigel and Daeun Kim.
 *  January 14th, 2020
 *  
 *  Find this project on GitHub: https://github.com/shishakohle/ACH-CDA
 *  
 *  This project was carried out in the "Applications for Crowdsourced Healthcare"
 *  course at University of Applied Sciences Technikum Wien (Vienna, Austria)
 *  under the guidance of lecturer Matthias Frohner, PhD, MSc.
 */

package at.ach.CDA;

import java.awt.EventQueue;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* This "Main" Class contains the main()
 * to be executed to run the application.
 */
public class Main
{
	// This is the improvised path (applying to Dahn's machine)
//	private static String cdaDirectory = "/Users/dahnkim/git/ACH-CDA/src/main/resources/cda/";
	
	// This is the improvised path (applying to Ingo's machine)
	private static String cdaDirectory = "/home/ingo/git/ACH-CDA/src/main/resources/cda/";
	
	public static void main(String[] args)
	{
		System.out.println("ACH-CDA: An application that juggles with CDA labreports.");
		
		// extract all Labreports
		
		List<Labreport> labreports = new ArrayList<Labreport>();
		
		File cdaDir = new File(cdaDirectory);
		File[] files = cdaDir.listFiles();
		
		if (files != null)
		{
			for (File file : files)
			{
				if (file.isFile())
				{
					// extract Labreport
					Labreport labreport = new Labreport(file.getAbsolutePath());
					if ( labreport.hasPatient() )
					{
						System.out.println("Parsed labreport for patient " + labreport.getPatient().getFamilyName() + " with " + labreport.getObservations().size() + " observations.");
						labreports.add(labreport);
					}
					else
					{
						System.out.println( "Could not parse file:" + file.getAbsolutePath() );
					}
					
				}
			}
			
			System.out.println("Extracted a total of " + labreports.size() + " CDA labreports.");
		}
		else
		{
		    System.out.println("Could not find directory: " + cdaDirectory);
		}
		
		// create list of Patients and associate their labreports with em
		
		// ... TODO ...
		List<Patient> patients = new ArrayList<Patient>();
		for (Labreport labreport : labreports)
		{
			// TODO: check whether this Patient already has been added to the list
			patients.add( labreport.getPatient() );
		}
		
		// start the GUI and hand over the Patients
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					GUI frame = new GUI(patients);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		System.out.println("Quit application. Bye.");
	}

}
