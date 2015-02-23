package slogo_front;

import javafx.scene.paint.Color;

/**
 * class for Turtle object + properties
 */
public class Turtle {
	protected int myID;
	private int xLoc;
	private int yLoc;
	private double heading; // from 0-360
	private Color penColor;
	private boolean penDown;
	private boolean showTurtle;
	private TurtleImage turtleImage;


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
	
	public Color getPenColor(){
		return penColor;
	}
	
	public boolean getPenDown(){
		return penDown;
	}
	
	public boolean getShowTurtle(){
		return showTurtle;
	}
	
	public TurtleImage getTurtleImage(){
		return turtleImage;
	}
	
	// setters
	
	public void setXLoc(int x){
		xLoc = x;
		turtleImage.setXLoc(x);
	}
	public void setYLoc(int y){
		yLoc = y;
		turtleImage.setYLoc(y);
	}
	
	public void setHeading(double direction){
		heading = direction;
		turtleImage.setHeading(direction);
	}
	
	public void setImage(String imageURL){
		turtleImage.setImage(imageURL);
	}
	
	public void setPenColor(Color color){
		penColor = color;
	}
	
	public void setPenDown(Boolean leaveTrail){
		penDown = leaveTrail;
	}
	
	public void setShowTurtle(Boolean showTurtleImage){
		showTurtle = showTurtleImage;
	}
	
	/**
	 * basic constructor for Turtle object
	 * @param x
	 * @param y
	 * @param direction
	 * @param turtleID
	 * @param color
	 * @param imageURL
	 * @param leaveTrail
	 * @param showTurtleImage
	 */
	public Turtle(int x, int y, float direction, int turtleID, Color color, 
			String imageURL, boolean leaveTrail, boolean showTurtleImage){
		xLoc = x;
		yLoc = y;
		myID = turtleID;
		penColor = color;
		heading = direction;
		penDown = leaveTrail;
		showTurtle = showTurtleImage;
		turtleImage = new TurtleImage(x, y, direction, imageURL);
		
	}

}
