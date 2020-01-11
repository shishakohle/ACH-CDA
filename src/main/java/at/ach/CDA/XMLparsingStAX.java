package at.ach.CDA;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
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

	public static void main(String[] args)
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
}
