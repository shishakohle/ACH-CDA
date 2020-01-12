package at.ach.CDA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/* "StAX is one of the several XML libraries in Java. It's a memory-efficient
 *  library included in the JDK since Java 6. StAX doesn't load the entire
 *  XML into memory. Instead, it pulls data from a stream in a forward-only
 *  fashion. The stream is read by an XMLEventReader object." [1]
 *  
 *  This and all other citations in this source file were taken from these
 *  sources:
 *   [1] https://www.baeldung.com/java-stax (2020-01-11)
 */

public class XMLparsingStAX
{
	private static String path = "/home/ingo/git/ACH-CDA/src/main/resources/cda/" + "ingosxml.xml";
	
	private static String cdaDirectory      = "/home/ingo/git/ACH-CDA/src/main/resources/cda/";
	private static String labreportFilename = "labreport1.cda";
	private static String cdaFilepath       = cdaDirectory + labreportFilename;
	
	public static void main(String[] args)
	{
		List<Patient> extractedPatients = extractPatients(cdaFilepath);
		
		System.out.println("Extracted patients: " + extractedPatients.size());
		
		for(Patient p : extractedPatients)
		{
			System.out.println("Social ins.no.: " + p.getSocialInsuranceNumber());
			System.out.println("Given name: " + p.getGivenName());
			System.out.println("Family Name: " + p.getFamilyName());
			System.out.println("Gender: " + p.getGender());
			System.out.println("Date of birth: " + p.getBirthdate());
		}
	}
	
	public static void OLDmain(String[] args)
	{
		try
		{
			/* "Needless to say, the first step to parse an XML is to read it. We need an
			 *  XMLInputFactory to create an XMLEventReader for reading our file: "
			 */
			
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(path));
			
			/* "Now that the XMLEventReader is ready, we move forward through the stream
			 *  with nextEvent(): "
			 */
			
			while ( reader.hasNext() )
			{
				XMLEvent nextEvent = reader.nextEvent();
				
				/* "Next, we need to find our desired start tag first: "
				 */
				
				if ( nextEvent.isStartElement() )
				{
					StartElement startElement = nextEvent.asStartElement();
					
					if (startElement.getName().getLocalPart().equals("tag"))
					{
						// System.out.println(startElement.toString());
						
						/* "Consequently, we can read the attributes and data: "
						 */
						
						// read attribute by its nmae
						String id = startElement.getAttributeByName(new QName("attr")).getValue();
						System.out.println(id);
						
						// read data
						String name = reader.nextEvent().asCharacters().getData();
						System.out.println(name);
					}
				}
				
				if (nextEvent.isEndElement())
				{
					@SuppressWarnings("unused")
					EndElement endElement = nextEvent.asEndElement();
				    // System.out.println(endElement.toString());
				}
			}
		}
		catch (FileNotFoundException | XMLStreamException e)
		{
			e.printStackTrace();
		}
	}
	
	public static List<Patient> extractPatients(String cdaFilepath)
	{
		List<Patient> resultList = new ArrayList<Patient>(); // TODO: Consider alternatives for ArrayList
		Patient patient = null;
		
		try
		{
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(cdaFilepath));
			
			while ( reader.hasNext() )
			{
				XMLEvent xmlEvent = reader.nextEvent();
				if ( xmlEvent.isStartElement() )
				{
					/* TODO */ //System.out.println(xmlEvent);
					StartElement startElement = xmlEvent.asStartElement();
					String tagname = startElement.getName().getLocalPart();
					
					if(tagname.contentEquals("patientRole"))
					{
						patient = new Patient();
						boolean objectIncomplete = true;
						
						while(objectIncomplete && reader.hasNext())
						{//System.out.println("PING");
						/* TODO */ //System.out.println(xmlEvent);
							xmlEvent = reader.nextEvent();
							if ( xmlEvent.isStartElement() )
							{
								startElement = xmlEvent.asStartElement();
								tagname = startElement.getName().getLocalPart();
								
							switch (tagname)
							{
								case "id":
									//System.out.println("ID Tag gefunden: \n" + startElement);
									Attribute root = startElement.getAttributeByName(new QName("root"));
									if (root != null && root.getValue().equals("1.2.40.0.10.1.4.3.1"))
									{
										patient.setSocialInsuranceNumber( startElement.getAttributeByName(new QName("extension")).getValue() );
										//System.out.println("Added insurance number to patient.");
									}
								break;
								
								case "family":
									Attribute familyQualifier = startElement.getAttributeByName(new QName("qualifier"));
									if (familyQualifier==null)
									{
										patient.setFamilyName( reader.nextEvent().asCharacters().getData() );
									}
								break;
								
								case "given":
									patient.setGivenName( reader.nextEvent().asCharacters().getData() );
								break;
								
								case "administrativeGenderCode":
									Attribute gendercode = startElement.getAttributeByName(new QName("code"));
									if(gendercode!=null)
									{
										patient.setGender( gendercode.getValue() );
									}
								break;
								
								case "birthTime":
									Attribute birthdate = startElement.getAttributeByName(new QName("value"));
									if(birthdate!=null)
									{
										patient.setBirthdate( birthdate.getValue() );
									}
								break;
							}
							}
							
							if ( xmlEvent.isEndElement() )
							{
								EndElement endElement = xmlEvent.asEndElement();
						        if ( endElement.getName().getLocalPart().equals("patientRole") )
						        {
						        	objectIncomplete = false;
						        }
							}
						}
						
						resultList.add(patient);
						//System.out.println("Added a Patient to resulting List.");
					}
					
				}
			}
		}
		catch (FileNotFoundException | XMLStreamException e)
		{
			e.printStackTrace();
		}
		
		return resultList;
	}
}
