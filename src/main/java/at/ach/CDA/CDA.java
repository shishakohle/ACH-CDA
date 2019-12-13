package at.ach.CDA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CDA {

	public static void main(String[] args)
	{
		System.out.println("hello world");
		
		//String cdaDirectory = "./resources/cda/";
		
		// This is the improvised path (applying to Dahn's machine)
		String cdaDirectory = "/*TODO*/";
		
		// This is the improvised path (applying to Ingo's machine)
		// String cdaDirectory = "/home/ingo/git/ACH-CDA/src/main/resources/cda/";
		
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
		
		for(int i=0; i<10; i++)
		{
			String line = sc.nextLine();
			System.out.println(line);
		}
	}

}
