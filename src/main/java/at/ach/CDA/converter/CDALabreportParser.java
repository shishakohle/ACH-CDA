package at.ach.CDA.converter;

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

import at.ach.CDA.model.CodedLabparameter;
import at.ach.CDA.model.Labreport;
import at.ach.CDA.model.Observation;
import at.ach.CDA.model.Patient;

public class CDALabreportParser
{
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
			System.out.println("Could not parse Patient from file: " + cdaFilepath);
		}
		
		return patient;
	}

	public static List<Observation> extractObservations(String cdaFilepath)
	{
		List<Observation> resultList = new ArrayList<Observation>();
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
									case "code":
										Attribute code =           startElement.getAttributeByName(new QName("code"));
										Attribute codeSystem =     startElement.getAttributeByName(new QName("codeSystem"));
										Attribute codeSystemName = startElement.getAttributeByName(new QName("codeSystemName"));
										Attribute displayName =    startElement.getAttributeByName(new QName("displayName"));
										Attribute codeNullFlavor = startElement.getAttributeByName(new QName("nullFlavor"));
										
										if (codeNullFlavor != null)
										{
											boolean nullFlavorCodeIncomplete = true;
											
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
										
										if (code != null)             strCode           = code.getValue();
										if (codeSystem != null)       strCodeSystem     = codeSystem.getValue();
										if (codeSystemName != null)   strCodeSystemName = codeSystemName.getValue();
										if (displayName != null)      strDisplayName    = displayName.getValue();
										
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
			System.out.println("Could not parse Observations from file: " + cdaFilepath);
		}
		
		return resultList;
	}
	
	public static List<CodedLabparameter> extractCodedLabparameters(String catalogueFilepath)
	{
		List<CodedLabparameter> labparameters = new ArrayList<CodedLabparameter>();
		
		List<Observation> observations = extractObservations(catalogueFilepath);
		
		for(Observation observation : observations)
		{
			// TODO check for null here before using setters of CodedLabparameter!
			
			CodedLabparameter labparameter = new CodedLabparameter();
			labparameter.setCodeSystem( observation.getCodeSystem() );
			labparameter.setCodeSystemName( observation.getCodeSystemName() );
			labparameter.setParameterCode( observation.getCodeCode() );
			labparameter.setDisplayName( observation.getDisplayName() );
			//labparameter.setValueUnit(  );
			
			labparameters.add(labparameter);
		}
		
		return labparameters;
	}
	
	public static Labreport extractLabreport(String cdaFilepath)
	{
		return new Labreport( extractPatient(cdaFilepath), extractObservations(cdaFilepath) );
	}
}
