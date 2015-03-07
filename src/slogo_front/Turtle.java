package slogo_front;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * class for Turtle object + properties
 */
public class Turtle{
	private int myID;
	private int displayID;
	private double xPosition;
	private double yPosition;
	private double heading; // from 0-360
	private Color penColor;
	private double penWidth;
	private boolean penDown;
	private boolean showTurtle;
	private ImageView turtleImage;
	
	// getters
	protected int getID(){
		return myID;
	}
	
	protected int getDisplayID(){
		return displayID;
	}
	
	protected double getXloc() {
		return xPosition;
	}

	protected double getYloc() {
		return yPosition;
	}
	
	protected double getHeading(){
		return heading;
	}
	
	protected double getAdjustedHeading(){
		// heading for image
		return 360-heading;
	}
	
	protected Color getPenColor(){
		return penColor;
	}
	
	protected double getPenWidth(){
		return penWidth;
	}
	
	protected boolean getPenDown(){
		return penDown;
	}
	
	protected boolean getShowTurtle(){
		return showTurtle;
	}
	
	protected ImageView getTurtleImage(){
		return turtleImage;
	}
	
	// setters
	protected void setXPosition(double x){
		xPosition = x;
	}
	protected void setYPosition(double y){
		yPosition = y;
	}
	
	protected void setDisplayID(int id){
		displayID = id;
	}
	
	protected void setHeading(double direction){
		heading = direction;
	}
	
	public void setPenColor(Color color){
		penColor = color;
	}
	
	protected void setPenWidth(double width){
		penWidth = width;
	}
	
	protected void setPenDown(Boolean leaveTrail){
		penDown = leaveTrail;
	}
	
	protected void setShowTurtle(Boolean showTurtleImage){
		showTurtle = showTurtleImage;
	}
	
	public void setImage(String imageURL){
		turtleImage = getImageFromURL(imageURL);
	}
	
	/**
	 * basic constructor for Turtle object
	 * @param x
	 * @param y
	 * @param direction
	 * @param turtleID, display
	 * @param color
	 * @param imageURL
	 * @param leaveTrail
	 * @param showTurtleImage
	 */
	protected Turtle(double x, double y, float direction, int id, Color color, double lineWidth,
			String imageURL, boolean leaveTrail, boolean showTurtleImage){
		xPosition = x;
		yPosition = y;
		myID = id;
//		displayID = display; 
		penColor = color;
		penWidth = lineWidth;
		heading = direction;
		penDown = leaveTrail;
		showTurtle = showTurtleImage;
		turtleImage = getImageFromURL(imageURL);
		
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
