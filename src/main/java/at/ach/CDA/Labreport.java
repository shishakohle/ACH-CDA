package at.ach.CDA;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Labreport
{
	Patient patient;
	List<Observation> observations;
	
	Labreport (Scanner cdaScanner)
	{
		// TODO: validate cdaScanner (is the null approach applying here?)
		
		// get all Lines of CDA file in a List
		
		List<String> cdaFileContent = new ArrayList<String>();
		
		while(cdaScanner.hasNext())
		{
			cdaFileContent.add( cdaScanner.nextLine() );
		}
		
		// extract the Patient from cdaFileContent
		
		// extract all Observations from cdaFileContent
	}
}
