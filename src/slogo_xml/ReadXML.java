package slogo_xml;

import java.lang.reflect.Field;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Parses XML file and stores variables.
 * 
 * @author emresonmez
 *
 */
public class ReadXML {
	// general variables
	private Document myDoc;
	private Document defaultDoc;
	private String defaultURL = "src/xml/default.xml";
	private HashMap<String,String> propertiesMap = new HashMap<>();
	
	public HashMap<String,String> getProperties(String fileLoc){
		readFile(fileLoc);
		
		return propertiesMap;
		
	}
	
	/**
	 * takes in file location and initializes/cleans document
	 * @param fileLoc
	 */
	private void readFile(String fileLoc) {
		try {
			System.out.println("Reading file");
			File xml = new File(fileLoc);
			DocumentBuilderFactory dbFac = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFac.newDocumentBuilder();
			myDoc = dBuilder.parse(xml);
			myDoc.getDocumentElement().normalize();
			
			System.out.println("Reading default file");
			File xmlDefault = new File(defaultURL);
			DocumentBuilderFactory dbFacDefault = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilderDefault = dbFacDefault.newDocumentBuilder();
			defaultDoc = dBuilderDefault.parse(xmlDefault);
			defaultDoc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * returns string in element by tag name
	 * @param myElement
	 * @param tagName
	 * @return
	 */
	private String getElement(Element myElement, String tagName) {
		return myElement.getElementsByTagName(tagName).item(0).getTextContent();
	}
	
	/**
	 * returns arrayList of modules
	 * @return
	 */
	protected ArrayList<String> getModules(){
		NodeList nodeList = myDoc.getElementsByTagName("module");
		ArrayList<String> modules = new ArrayList<>();
		boolean foundSetup = false;
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node myNode = nodeList.item(i);
			if (myNode.getNodeType() == Node.ELEMENT_NODE) {
				Element myElement = (Element) myNode;
				String id = myElement.getAttribute("id");
				modules.add(id);
				if(id.toLowerCase().equals("properties")){
					foundSetup = true;
				}
				System.out.println(id);
			}
		}
		if((foundSetup) && (modules.size()>=1)){
			return modules;
		}else{
			if(!foundSetup){
				System.out.println("No properties configuration found.");
			}else{
				if(modules.size()<=1){
					System.out.println("No module found."); //TODO throw error
				}
			}
			
		}
		return modules;
	}
	
	/**
	 * iterates over tree to return parameter from XML
	 * @param id
	 * @param label
	 * @param root
	 * @param useDefault
	 * @return
	 */
	private String getNode(String id, String label,String root,boolean useDefault){
		NodeList nodeList;
		if(!useDefault){ 
			nodeList = myDoc.getElementsByTagName(root);
		}else{
			nodeList = defaultDoc.getElementsByTagName(root);
		}
		String val = "";
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node myNode = nodeList.item(i);
			if (myNode.getNodeType() == Node.ELEMENT_NODE) {
				Element myElement = (Element) myNode;
				if(myElement.getAttribute("id").equals(id)){
					try{
						val = getElement(myElement,label);
						if(val.equals("")){
							System.out.println("Empty value. Getting default");
							val = getNode(id,label,root,true);
						}
						return val;
					}catch(Exception e){
					}
				}
			}
		}
		return "Error";
	}
			
	/*
	 * String checking 
	 */
	
	protected String getAsString(String id, String label, String root){
		String val = getNode(id,label,root,false);
		if(val.isEmpty()){
			return getNode(id,label,root,true);
		}
		return val;
	}
	
	
	/*
	 * getting integer elements (with error checking & defaults)
	 */
	
	/**
	 * gets node value as int (default if input invalid)
	 * @param id
	 * @param label
	 * @param root
	 * @param elementID
	 * @return
	 */
	protected int getAsInt(String id, String label, String root){
		String val = getNode(id,label,root,false);
		if(checkInt(val)){
			return stringToInt(val);
		}else{
			String def = getNode(id,label,root,true);
			return stringToInt(def);
		}
	}

	private int stringToInt(String s){
		return Integer.parseInt(s);
	}
	
	/**
	 * checks if integer input is integer
	 * derived from stack overflow
	 * @param myString
	 * @return
	 */
	private boolean checkInt(String myString){
	    try { 
	        Integer.parseInt(myString); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}

	/*
	 * getting boolean elements (with error checking & defaults)
	 */
	
	/**
	 * gets node value as boolean (default if input invalid)
	 * @param id
	 * @param label
	 * @param root
	 * @param elementID
	 * @return
	 */
	protected boolean getAsBool(String id, String label, String root){
		String val = getNode(id,label,root,false);
		if(checkYesNo(val)){
			return stringToBool(val);
		}else{
			System.out.println("getting default boolean for " + label);
			String def = getNode(id,label,root,true);
			return stringToBool(def);
		}
	}
	
	// checks if input is 0 or 1
	private boolean checkYesNo(String myString){
		if(myString.equals("0") | myString.equals("1")){
			return true;
		}
		return false;
	}
	
	// converts yes or no to booleans
	private boolean stringToBool(String s){
		if(s.equals("1")){
			return true;
		}
		return false;
	}
	
	/*
	 * getting colors (with error checking & defaults)
	 */
	
	public Color getAsColor(){
		String color = getNode("Setup","colors","module",false);
		if(getColors(color)!=null){
			return getColors(color);
		}else{
			String colorsDef = getNode("Setup","colors","module",true);
			return getColors(colorsDef);
		}
	}
	
	/**
	 * returns array list of colors (null if colors are not matched)
	 * @param colorString
	 * @return
	 */
	private Color getColors(String colorString){
		Color color = checkColors(colorString);
		if(color!=null){
			return color;
		}else{
			System.out.println("Color " + colorString + "does not exist.");
			return null;
		}
	}

	/**
	 * returns list of colors if colors exist (null otherwise)
	 * @param colorString
	 * @return
	 */
	private Color checkColors(String myString){
		try{
			Field field = Class.forName("java.awt.Color").getField(myString.toLowerCase());
			Color color = (Color)field.get(null);
			return color;
		}catch (Exception e){
			return null;
		}
	}
	
	// for testing
//	public static void main(String argv[]) {
//		try{
//			ReadXML myXML = new ReadXML();
//			myXML.readFile("src/xml/default.xml");
//			myXML.getModules();
//			String color = myXML.getAsString("properties","backgroundcolor","module");
//			Boolean penDown = myXML.getAsBool("0", "pendown", "module");
//			int turtleCount = myXML.getAsInt("properties", "turtlecount", "module");
//			System.out.println("testing XML\n");
//			System.out.println("color: " + color);
//			System.out.println(penDown);
//			System.out.println(turtleCount);
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
