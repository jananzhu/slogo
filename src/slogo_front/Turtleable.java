package slogo_front;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public interface Turtleable {
	//TODO unsure how to implement this
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
	 * @return 
	 */
	void Turtle(double x, double y, float direction, int id, int display, Color color, double lineWidth,
			String imageURL, boolean leaveTrail, boolean showTurtleImage);
	
	// getters
	int getID();
	
	double getXloc();

	double getYloc();
	
	double getHeading();
	
	double getAdjustedHeading();
	
	Color getPenColor();
	
	double getPenWidth();
	
	boolean getPenDown();
	
	boolean getShowTurtle();
	
	ImageView getTurtleImage();
	
	// setters
	void setXPosition(double x);
	
	void setYPosition(double y);
	
	void setDisplayID(int id);
	
	void setHeading(double direction);
	
	void setPenColor(Color color);
	
	void setPenWidth(double width);
	
	void setPenDown(Boolean leaveTrail);
	
	void setShowTurtle(Boolean showTurtleImage);
	
	void setImage(String imageURL);
	/**
	 * takes location of image, retrieves and converts to imageview
	 * @param imageURL
	 * @return
	 */

}
