package slogo_xml;

import java.util.HashMap;

public class XMLmanager {
	private String defaultURL = "src/xml/default.xml";
	private String[] propertiesLabels = {"imageURL","backgroundcolor",
			"language","turtlecount","pendown","linestyle"};

	/**
	 * API method to write to XML file
	 * @param properties
	 * @param fileLocation
	 */
	public void writeXML(String[] properties, String fileLocation){
		WriteXML writer = new WriteXML(propertiesLabels);
		writer.initialize();
		writer.configureProperties(properties);
		writer.closeFile(fileLocation);
	}
	
	/**
	 * API method to read XML file
	 * returns HashMap of parameter(string):value(string)
	 * @param fileLocation
	 * @return
	 */
	public HashMap<String,String> readXML(String fileLocation){
		ReadXML reader = new ReadXML(defaultURL);
		HashMap<String,String> paramsMap = reader.readXML(fileLocation);
		return paramsMap;
	}
	
}
