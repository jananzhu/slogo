package slogo_xml;

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
	
	public void readXML(String fileLocation){
		ReadXML reader = new ReadXML(defaultURL);
		reader.readXML(fileLocation);
		
	}
	
}
