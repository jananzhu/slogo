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
	private String defaultURL;
	private HashMap<String,String> params;
	private HashMap<String,String> defaults;
	
	/**
	 * basic constructor
	 * @param defaultURLloc
	 */
	protected ReadXML(String defaultURLloc){
		defaultURL = defaultURLloc;
		params = new HashMap<>();
		defaults = new HashMap<>();
	}
	
	/**
	 * returns hashmap of parameter:value (strings)
	 * @param fileLoc
	 * @return
	 */
	protected HashMap<String, String> readXML(String fileLoc){
		readFile(fileLoc);
		Node head = myDoc.getFirstChild();
		Node head2 = defaultDoc.getFirstChild();
		getElements(head,"properties",params);
		getElements(head2,"properties",defaults);
		checkParams();
		return params;
	}
	
	private void checkParams(){
		for(String k : params.keySet()){
			if(params.get(k).equals("")){
				if(defaults.containsKey(k)){
					params.put(k,defaults.get(k));
				}
			}
		}
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
	 * recursive method to populate parameters HashMap
	 * derived from stackoverflow
	 * @param node
	 */
	private void getElements(Node node, String moduleName, HashMap<String,String> map){
		if((!node.getNodeName().equals("module")) && 
				(!node.getNodeName().equals("setup"))){
			map.put(node.getNodeName() , node.getTextContent());
			System.out.println(node.getNodeName() + ": " + node.getTextContent());
		}
		NodeList nodeList = node.getChildNodes();
		for (int i = 0 ; i < nodeList.getLength(); i++){
			Node currentNode = nodeList.item(i);
			if(currentNode.getNodeType() == Node.ELEMENT_NODE){
				getElements(currentNode,moduleName,map);
			}
		}
	}
	
	// for testing
//	public static void main(String argv[]) {
//		try{
//			ReadXML myXML = new ReadXML("src/xml/default.xml");
//			myXML.readXML("src/xml/test.xml");
//			System.out.println("\nparams...\n");
//			for(String k:myXML.params.keySet()){
//				System.out.println(k+": " + myXML.params.get(k));
//			}
//			System.out.println("\ndefaults...\n");
//			for(String k:myXML.defaults.keySet()){
//				System.out.println(k+": " + myXML.defaults.get(k));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
