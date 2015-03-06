package slogo_xml;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Field;
/**
 * converts string input from xml file into correct type output
 * @author emresonmez
 *
 */
public class TypeChecker {
	/**
	 * gets unique URL
	 * @param path
	 * @return
	 */
	public String getNewPath(){
		String baseURL = "src/xml/display";
		String end = ".xml";
		int count = 1;
		String currentURL = "";
		File f = new File(baseURL+end);
		if(!f.exists()) { 
			return baseURL+end;
		}else{
			while(f.exists()){
				currentURL = baseURL + count + end;
				f = new File(currentURL);
			}
			return currentURL;
		}
	}
	
	// booleans
	public boolean getAsBool(String s){
		if(s.equals("1") | s.toLowerCase().equals("yes")){
			return true;
		}else{
			return false;
		}
	}
	
	// doubles
	public double getAsDouble(String s){
		try { 
	        double dub = Double.parseDouble(s);
	        return dub;
	    } catch(NumberFormatException e) { 
	        return 0;
	    }
	}	        
	
	// integers
	public int getAsInt(String s){
		if(checkInt(s)){
			return stringToInt(s);
		}else{
			return 0;
		}
	}

	private boolean checkInt(String myString){
	    try { 
	        Integer.parseInt(myString); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    return true;
	}
	
	public int stringToInt(String s){
		return Integer.parseInt(s);
	}
	
	// colors
	
	public Color getAsColor(String color){
		if(checkColors(color.toLowerCase()) != null){
			return checkColors(color.toLowerCase());
		}else{
			return Color.white;
		}
	}

	private Color checkColors(String myString){
		try{
			Field field = Class.forName("java.awt.Color").getField(myString.toLowerCase());
			Color color = (Color)field.get(null);
			return color;
		}catch (Exception e){
			return null;
		}
	}
	
	
}
