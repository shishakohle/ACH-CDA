package at.ach.CDA.discarded;

import java.util.ArrayList;
import java.util.List;

public class Extractor
{
	public static List<String> extract (List<String> source, String xmlTag)
	{
		List<String> result = new ArrayList<String>();
		
		String startpattern = "<"  + xmlTag + ">";
		String endpattern   = "</" + xmlTag + ">";
		
		boolean putLineInResultList = false;
		
		
		for(String line : source)
		{
			if ( line.contains(startpattern) )
				putLineInResultList = true;
			
			if (putLineInResultList)
				result.add(line);
			
			if ( line.contains(endpattern) )
				putLineInResultList = false;
		}
		
		return result;
		
	}
	
	
	
	public static String extractByTag (List<String> source, String xmlTag) 
	{
		String extractedData = "";
		
		String startTag = "<" + xmlTag + ">";
		String endTag = "</" + xmlTag + ">";
		
		for (String line : source)
		{
			if (line.contains(startTag) && line.contains(endTag))
			{
				extractedData = line.replaceAll(startTag, "").replaceAll(endTag,"").replaceAll("\t","");
				return extractedData;
			}
		}
		
		return "";
		
	}
	
	public static String extracted (List<String> source, String xmlTag)
	{
		List<String> result = new ArrayList<String>();
		result = Extractor.extract(source, xmlTag);
		String outcome = "";
		outcome = Extractor.extractByTag(result, xmlTag);
		return outcome;
		
	}
	
}
