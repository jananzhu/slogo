package slogo_xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

 /**
  * Writes configuration of modules to an XML file.
  * 
  * @author emresonmez
  *
  */
public class WriteXML {
	private Element rootElement;
	private Document doc;
	private String[] propertiesLabels = {"imageURL","backgroundcolor",
			"language","turtlecount","pendown","linestyle"};
	
	// API method
	public void writeXML(String[] properties, String fileLocation){
		initialize();
		configureProperties(properties);
		closeFile(fileLocation);
	}
	
	/**
	 * initializes document builder & root
	 * sets rootElement and doc variables
	 */
	private void initialize(){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			doc = docBuilder.newDocument();
			rootElement = doc.createElement("setup");
			doc.appendChild(rootElement);
		}  catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}
		
	}
	
	/**
	 * closes file by using transformer & transformer factory
	 * @param fileLocation
	 */
	private void closeFile(String fileLocation){
		try{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(fileLocation));
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
		transformer.setOutputProperty(OutputKeys.INDENT,"yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","2");
		transformer.transform(source, result);
		System.out.println("File saved!");
		}catch (TransformerException tfe) {
			tfe.printStackTrace();
		 }
	}
	
	/**
	 * writes properties module
	 * imageURL, background color, language, turtle count
	 * @param params
	 */
	private void configureProperties(String[] params){
		writeElements("module","properties",propertiesLabels,params);
	}
	
	
	/**
	 * given labels and text, writes to XML
	 * @param elemName
	 * @param id
	 * @param labels
	 * @param params
	 */
	private void writeElements(String elemName, String id,
			String[] labels, String[] params){
		Element module = doc.createElement(elemName);
		initializeModule(module,"id",id);
		for(int i = 0 ; i < labels.length; i++){
			if(i < params.length){
				addChild(labels[i],params[i],module);
			}
		}
	}
	
	/**
	 * helper method to initialize root element
	 * @param myElement
	 * @param attribute
	 * @param value
	 */
	private void initializeModule(Element myElement,String attribute, String value){
		rootElement.appendChild(myElement);
		Attr attr = doc.createAttribute(attribute);
		attr.setValue(value);
		myElement.setAttributeNode(attr);
	}
	
	/**
	 * adds child to current element
	 * @param elementName
	 * @param text
	 * @param node
	 */
	private void addChild(String elementName, String text, Element node){
		Element myElement = doc.createElement(elementName);
		myElement.appendChild(doc.createTextNode(text));
		node.appendChild(myElement);
	}
	
	// for testing
//	public static void main(String argv[]) {
//		WriteXML xml = new WriteXML();
//		xml.writeXML(new String[] {"src/xml/test1.xml","blue","french","10","lineeee" }, 
//				"src/xml/test1.xml");
//	}
}