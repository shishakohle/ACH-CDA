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

public class CDALabreportParser
{
	public static void main(String[] args)
	{
		String cdaDirectory      = "/home/ingo/git/ACH-CDA/src/main/resources/cda/";
		String labreportFilename = "labreport1.cda";
		String cdaFilepath       = cdaDirectory + labreportFilename;
		
		Patient extractedPatient = extractPatient(cdaFilepath);
		
		System.out.println("Social ins.no.: " + extractedPatient.getSocialInsuranceNumber());
		System.out.println("Given name: " + extractedPatient.getGivenName());
		System.out.println("Family Name: " + extractedPatient.getFamilyName());
		System.out.println("Gender: " + extractedPatient.getGender());
		System.out.println("Date of birth: " + extractedPatient.getBirthdate());
		
		List<Observation> extractedObservations = extractObservations(cdaFilepath);
		
		System.out.println("Extracted observations: " + extractedObservations.size());
		
		for(Observation o : extractedObservations)
		{
			System.out.println();
			System.out.println("Observation Code: " + o.getCodeCode());
			System.out.println("Observation Code System: " + o.getCodeSystem());
			System.out.println("Observation Code System Name: " + o.getCodeSystemName());
			System.out.println("Observation display name: " + o.getDisplayName());
			System.out.println("Observation effective time: " + o.getEffectiveTimeValue());
			System.out.println("Observation value: " + o.getValueValue());
			System.out.println("Observation unit: " + o.getValueUnit());
		}
	}
	
	public static void OLDmain(String[] args)
	{
		try
		{
			/* "Needless to say, the first step to parse an XML is to read it. We need an
			 *  XMLInputFactory to create an XMLEventReader for reading our file: "
			 */
			
			String path = "/home/ingo/git/ACH-CDA/src/main/resources/cda/" + "ingosxml.xml";
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
	
	public static Patient extractPatient(String cdaFilepath)
	{
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
					StartElement startElement = xmlEvent.asStartElement();
					String tagname = startElement.getName().getLocalPart();
					
					if(tagname.contentEquals("patientRole"))
					{
						patient = new Patient();
						boolean objectIncomplete = true;
						
						while(objectIncomplete && reader.hasNext())
						{
							xmlEvent = reader.nextEvent();
							if ( xmlEvent.isStartElement() )
							{
								startElement = xmlEvent.asStartElement();
								tagname = startElement.getName().getLocalPart();
								
							switch (tagname)
							{
								case "id":
									Attribute root = startElement.getAttributeByName(new QName("root"));
									if (root != null && root.getValue().equals("1.2.40.0.10.1.4.3.1"))
									{
										patient.setSocialInsuranceNumber( startElement.getAttributeByName(new QName("extension")).getValue() );
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
					}
					
				}
			}
		}
		catch (FileNotFoundException | XMLStreamException e)
		{
			e.printStackTrace();
		}
		
		return patient;
	}

	public static List<Observation> extractObservations(String cdaFilepath)
	{
		List<Observation> resultList = new ArrayList<Observation>(); // TODO: Consider alternatives for ArrayList
		Observation observation = null;
		
		try
		{
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(cdaFilepath));
			
			while ( reader.hasNext() )
			{
				XMLEvent xmlEvent = reader.nextEvent();
				if ( xmlEvent.isStartElement() )
				{
					StartElement startElement = xmlEvent.asStartElement();
					String tagname = startElement.getName().getLocalPart();
					
					if(tagname.contentEquals("observation"))
					{
						observation = new Observation();
						boolean objectIncomplete = true;
						
						while(objectIncomplete && reader.hasNext())
						{
							xmlEvent = reader.nextEvent();
							if ( xmlEvent.isStartElement() )
							{
								startElement = xmlEvent.asStartElement();
								tagname = startElement.getName().getLocalPart();
								
							switch (tagname)
							{
								/* private String codeCode;
								private String codeSystem;
								private String codeSystemName;
								private String displayName;
								private String effectiveTimeValue;
								private String valueUnit;
								private String valueValue; */
								
								case "code":
									Attribute code =           startElement.getAttributeByName(new QName("code"));
									Attribute codeSystem =     startElement.getAttributeByName(new QName("codeSystem"));
									Attribute codeSystemName = startElement.getAttributeByName(new QName("codeSystemName"));
									Attribute displayName =    startElement.getAttributeByName(new QName("displayName"));
									Attribute codeNullFlavor = startElement.getAttributeByName(new QName("nullFlavor"));
									
									/* old code (no check for nullFlavors, no check for LOINC:Annotation Comment
									if(code!=null)           observation.setCodeCode(code.getValue());
									if(codeSystem!=null)     observation.setCodeSystem(codeSystem.getValue());
									if(codeSystemName!=null) observation.setCodeSystemName(codeSystemName.getValue());
									if(displayName!=null)    observation.setDisplayName(displayName.getValue());
									*/
									
									if (codeNullFlavor != null)
									{
										boolean nullFlavorCodeIncomplete=true;
										
										while(reader.hasNext() && nullFlavorCodeIncomplete)
										{
											xmlEvent = reader.nextEvent();
											
											if (xmlEvent.isStartElement())
											{
												startElement = xmlEvent.asStartElement();
												if ( startElement.getName().getLocalPart().equals("translation") )
												{
													code =           startElement.getAttributeByName(new QName("code"));
													codeSystem =     startElement.getAttributeByName(new QName("codeSystem"));
													codeSystemName = startElement.getAttributeByName(new QName("codeSystemName"));
													displayName =    startElement.getAttributeByName(new QName("displayName")); 
												}
											}
											
											if (xmlEvent.isEndElement())
											{
												EndElement endElement = xmlEvent.asEndElement();
										        if ( endElement.getName().getLocalPart().equals("translation") )
										        {
										        	nullFlavorCodeIncomplete = false;
										        }
											}
										}
									}
									
									String strCode = null;
									String strCodeSystem=null;
									String strCodeSystemName = null;
									String strDisplayName = null;
									
									if(code!=null) strCode = code.getValue();
									if(codeSystem!=null) strCodeSystem = codeSystem.getValue();
									if(codeSystemName!=null) strCodeSystemName = codeSystemName.getValue();
									if(displayName!=null) strDisplayName = displayName.getValue();
									
									// if codepage is "LOINC" and code is "Annotation Comment"
									if ( strCodeSystem!=null
											&& strCode!=null
											&& strCodeSystem.equals(/*LOINC*/"2.16.840.1.113883.6.1")
											&& strCode.equals(/*Annotation Comment*/"48767-8") )
									{
										// Do not add any of these data to observation.
									}
									else
									{
										if(strCode!=null)           observation.setCodeCode(strCode);
										if(strCodeSystem!=null)     observation.setCodeSystem(strCodeSystem);
										if(strCodeSystemName!=null) observation.setCodeSystemName(strCodeSystemName);
										if(strDisplayName!=null)    observation.setDisplayName(strDisplayName);
									}
									
								break;
								
								case "effectiveTime":
									Attribute effectiveTime = startElement.getAttributeByName(new QName("value"));
									if (effectiveTime!=null) observation.setEffectiveTimeValue(effectiveTime.getValue());
								break;
								
								case "value":
									Attribute unit = startElement.getAttributeByName(new QName("unit"));
									Attribute value = startElement.getAttributeByName(new QName("value"));
									if (unit!=null) observation.setValueUnit(unit.getValue());
									if (value!=null) observation.setValueValue(value.getValue());
								break;
							}
							}
							
							if ( xmlEvent.isEndElement() )
							{
								EndElement endElement = xmlEvent.asEndElement();
								if ( endElement.getName().getLocalPart().equals("observation") )
								{
						        		objectIncomplete = false;
						        }
							}
						}
						
						resultList.add(observation);
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
	
	public static Labreport extractLabreport(String cdaFilepath)
	{
		return new Labreport( extractPatient(cdaFilepath), extractObservations(cdaFilepath) );
	}
}
