package at.ach.CDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CDA
{
	public static void main(String[] args)
	{
		System.out.println("hello world");
		
		//String cdaDirectory = "./resources/cda/";
		
		// This is the improvised path (applying to Dahn's machine)
		String cdaDirectory = "/Users/dahnkim/git/ACH-CDA/src/main/resources/cda/";
		
		// This is the improvised path (applying to Ingo's machine)
		//String cdaDirectory = "/home/ingo/git/ACH-CDA/src/main/resources/cda/";
		
		File cdafile = new File(cdaDirectory+"labreport2.cda");
		
		Scanner sc=null;
		
		try
		{
			sc = new Scanner(cdafile);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		// get all Lines of CDA file in a list
		List<String> cdaFileContent = new ArrayList<String>();
		
		while(sc.hasNext())
		{
			cdaFileContent.add( sc.nextLine() );
		}
		
		// extract the patient segment out of the CDA content into a new list
		List<String> patientSegment;
		patientSegment = Extractor.extract(cdaFileContent, "patientRole");
		
		
		// Dahn's manual attempt to extract patient-related segments and add them to an object "patient"
		// sorry for being super messy 
		
		// extract the name segment out of the patient segment into a new list
		List<String> givenSegment;
		givenSegment = Extractor.extract(patientSegment, "given");
		
		// removing second given name in the CDA report
		givenSegment.remove(1);
	
		// converting string array to string and remove xml tags
		String givenName = givenSegment.toString().replaceAll("<.*?>", "");
		
		// extract the name segment out of the patient segment into a new list
		List<String> familySegment;
		familySegment = Extractor.extract(patientSegment, "family");
		String familyName = familySegment.toString().replaceAll("<.*?>", "");
		
		
		List<String> genderSegment;
		genderSegment = Extractor.extract(patientSegment, "administrativeGenderCode code");
		String gender = genderSegment.toString().replaceAll("<.*?>", "");
		
		
		List<String> DOBSegment;
		DOBSegment = Extractor.extract(patientSegment, "birthTime value");
		String birthdate = DOBSegment.toString().replaceAll("<.*?>", "");
		
		
		List<String> insuranceSegment;
		insuranceSegment = Extractor.extract(patientSegment, "Sozialversicherungsnummer des Patienten");
		String insurance = insuranceSegment.toString().replaceAll("<.*?>", "");
	
		
		Patient patient = new Patient(insurance, givenName, familyName, gender, birthdate);
		patient.getGivenName();
		
	
		/*String name = givenSegment.toString();
		System.out.println(name);*/
		
		/*
		// just to check: print all lines in the list
		for(String e : patientSegment)
		{
			patientSegment.add(e);
		}*/
	
		
	}
}

	


