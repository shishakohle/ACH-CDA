package at.ach.CDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CDA
{
	public static void main(String[] args)
	{
		System.out.println("hello world");
		
		//String cdaDirectory = "./resources/cda/";
		
		// This is the improvised path (applying to Dahn's machine)
		//String cdaDirectory = "/Users/dahnkim/git/ACH-CDA/src/main/resources/cda/";
		
		// This is the improvised path (applying to Ingo's machine)
		String cdaDirectory = "/home/ingo/git/ACH-CDA/src/main/resources/cda/";
		
		File cdafile = new File(cdaDirectory+"labreport0.cda");
		
		Scanner sc=null;
		
		try
		{
			sc = new Scanner(cdafile);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		List<String> patientSegment = new ArrayList<String>();
		
		while ( sc.hasNextLine() )
		{
			String line = sc.nextLine();
			
			if ( line.contains("<patient>") )
			{
				// put all lines in a list, until line contains the closing tag
				while ( sc.hasNextLine() && !line.contains("</patient>") )
				{
					patientSegment.add(line);
					line = sc.nextLine();
				}
				
				patientSegment.add(line);
			}
		}
		
		// just to check: print all lines in the list
		for(String s : patientSegment)
		{
			System.out.println(s);
		}
		
	}
}

	


