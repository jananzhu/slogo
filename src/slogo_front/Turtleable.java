package slogo_front;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Turtleable {
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
//	void Turtle(double x, double y, float direction, int id, int display, Color color, double lineWidth,
//			String imageURL, boolean leaveTrail, boolean showTurtleImage);
	
	// getters
	protected abstract int getID();
	
	protected abstract double getXloc();

	protected abstract double getYloc();
	
//	protected abstract double getHeading();
//	
//	protected abstract double getAdjustedHeading();
	
	protected abstract Color getPenColor();
	
	protected abstract double getPenWidth();
	
	protected abstract boolean getPenDown();
	
	protected abstract boolean getShowTurtle();
	
	protected abstract ImageView getTurtleImage();
	
	// setters
//	protected abstract void setXPosition(double x);
//	
//	protected abstract void setYPosition(double y);
	
//	protected abstract void setDisplayID(int id);
	
	protected abstract double setHeading(double direction);
	
	protected abstract void setPenColor(Color color);
	
	protected abstract void setPenWidth(double width);
	
	protected abstract void setPenDown(Boolean leaveTrail);
	
	protected abstract void setShowTurtle(Boolean showTurtleImage);
	
	protected abstract void setImage(String imageURL);
	
	//movement
	
//	protected abstract void forward(double pixel);
//	
//	protected abstract void back(double pixel);
	
	protected abstract void moveTurtle(double pixel);
	
	protected abstract double home();
	
	protected abstract double setXY(double x, double y);
	
	protected abstract double left(double degree);
	
	protected abstract double right(double degree);
	
	protected abstract double towards(double x, double y);
	
}
