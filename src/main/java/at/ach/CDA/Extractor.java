package at.ach.CDA;

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
}
