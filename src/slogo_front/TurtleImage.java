package slogo_front;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * class for Turtle Image + properties 
 */

public class TurtleImage {
	private double xLoc;
	private double yLoc;
	private double heading; // from 0-360
	private ImageView image; 
	
	// getters
	
	public double getXloc() {
		return xLoc;
	}

	public double getYloc() {
		return yLoc;
	}
	
	public double getHeading(){
		return heading;
	}
	
	public ImageView getImage(){
		return image;
	}
	
	// setters
	
	public void setXLoc(double x){
		xLoc = x;
	}
	public void setYLoc(double y){
		yLoc = y;
	}
	
	public void setHeading(double direction){
		heading = direction;
	}
	
	public void setImage(String imageURL){
		image = getImageFromURL(imageURL);
	}
	
	/**
	 * basic constructor for TurtleImage object
	 * @param x
	 * @param y
	 * @param direction
	 * @param imageURL
	 */
	public TurtleImage(double x, double y, float direction, String imageURL){
		xLoc = x;
		yLoc = y;
		heading = direction;
		image = getImageFromURL(imageURL);
	}
	
	/**
	 * takes location of image, retrieves and converts to imageview
	 * @param imageURL
	 * @return
	 */
	private ImageView getImageFromURL(String imageURL){
		ImageView i = new ImageView(new Image(getClass()
				.getResourceAsStream(imageURL)));
		return i;
	}
	
}
