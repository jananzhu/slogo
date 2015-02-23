package slogo_front;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * class for Turtle object + properties
 * @author emresonmez
 *
 */
public class Turtle {
	protected int myID;
	private int xLoc;
	private int yLoc;
	private double heading; // from 0-360
	private ImageView image; 
	private Color penColor;
	
	// getters
	
	public int getXloc() {
		return xLoc;
	}

	public int getYloc() {
		return yLoc;
	}
	
	public double getHeading(){
		return heading;
	}
	
	public ImageView getImage(){
		return image;
	}
	
	public Color getPenColor(){
		return penColor;
	}
	
	// setters
	
	public void setXLoc(int x){
		xLoc = x;
	}
	public void setYLoc(int y){
		yLoc = y;
	}
	
	public void setHeading(double direction){
		heading = direction;
	}
	
	public void setImage(String imageURL){
		image = getImageFromURL(imageURL);
	}
	
	public void setPenColor(Color color){
		penColor = color;
	}
	
	/**
	 * basic constructor
	 * @param x
	 * @param y
	 * @param heading
	 * @param penColor
	 * @param imageURL
	 */
	public Turtle(int x, int y, float direction, int turtleID, Color color, String imageURL){
		xLoc = x;
		yLoc = y;
		myID = turtleID;
		penColor = color;
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
