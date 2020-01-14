package at.ach.CDA.discarded;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

@Deprecated
@SuppressWarnings("unused")
public class XMLparsingJDOM
{
	
	private static Document getSAXParsedDocument(final String fileName) 
	{
	    SAXBuilder builder = new SAXBuilder(); 
	    Document document = null;
	    try
	    {
	        document = builder.build(fileName);
	    } 
	    catch (JDOMException | IOException e) 
	    {
	        e.printStackTrace();
	    }
	    return document;
	}
	
	public static void main(String[] args)
	{
		String filepath = "M:\\git\\ACH-CDA\\src\\main\\resources\\cda\\labreport1.cda";
		//String filepath = "M:\\git\\ACH-CDA\\src\\main\\resources\\cda\\ingosxml.xml";
		
		Document document = getSAXParsedDocument(filepath);
		
		Element rootNode = document.getRootElement();
		
		System.out.println( "Root Element: " + rootNode.getName() );
		
		List<Element> childNodes = rootNode.getChildren("realmCode");
		//List<Element> childNodes = rootNode.getChildren("employee");
		
		System.out.println(childNodes.size());
		
		for (Element child : childNodes)
		{
			System.out.println("***"+child.getText());
		}
		
		List<Attribute> attributes = rootNode.getAttributes();
		System.out.println(attributes.size());
		
		rootNode.getChildren("realmCode").forEach( XMLparsingJDOM::readEmployeeNode );
	}
	
	private static void readEmployeeNode(Element employeeNode) 
	{
	    //Employee Id
	    System.out.println("Id : " + employeeNode.getName());
	}
}
