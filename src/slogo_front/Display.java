package slogo_front;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * currently includes methods to move turtle
 * NOTE: API (protected) methods except constructor
 * take x & y coordinates with (0,0) in the middle
 * of the screen
 *  
 * to be added:
 *  - turtle image updates 
 *
 */
protected class Display {
	//References the middle of the canvas
	private int xOrigin;
	private int yOrigin;
	//Outer bounds of the display
	private int maxCanvasWidth;
	private int minCanvasWidth = 0;
	private int maxCanvasHeight;
	private int minCanvasHeight = 0;
	//instances of canvas and graphics context
	private Canvas canvas;
	private GraphicsContext graphics;
	private static final int LINE_WIDTH = 1;
	
	/**
	 * basic constructor
	 * @param xDimension
	 * @param yDimension
	 * @param myCanvas
	 */
	protected Display(int canvasWidth, int canvasHeight){
		canvas = new Canvas (canvasWidth,canvasHeight);
		xOrigin = canvasWidth/2;
		yOrigin = canvasHeight/2;
		maxCanvasWidth = canvasWidth;
		maxCanvasHeight = canvasHeight;
		graphics = canvas.getGraphicsContext2D();
		Turtle turtle = new Turtle(xOrigin,yOrigin, 0,0, Color.BLUE, "", true,true );
		// for testing
		turtle.setHeading(170);
		moveForward(turtle, 1000);
	}
	
	protected Node getDisplay(){
		return canvas;
	}
	
	/*
	 * methods for canvas properties
	 */

	/**
	 * CS erases turtle's trails and sends it to the home position
	 * returns the distance turtle moved
	 * @param turtle
	 * @return
	 */
	protected double clearScreen(Turtle turtle){
		//TODO fix this
		graphics.clearRect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),xOrigin,yOrigin);
		home(turtle);
		return distance;
	}
	
	/*
	 * methods for turtle properties
	 */
	
	/**
	 * sets pen up (false) or down (true)
	 * @param turtle
	 * @param leaveTrail
	 */
	protected double setPen(Turtle turtle, boolean leaveTrail){
		turtle.setPenDown(leaveTrail);
		if(leaveTrail){
			return 1;
		}else{
			return 0;
		}
	}
	
	/**
	 * TODO: implement turtle visualization
	 * shows (true) or hides (false) turtle image on gui
	 * @param turtle
	 * @param showTurtle
	 */
	protected double showTurtle(Turtle turtle, boolean showTurtle){
		turtle.setShowTurtle(showTurtle);
		if(showTurtle){
			return 1;
		}else{
			return 0;
		}
		//TODO: reflect this change on GUI	
	}
	
	/*
	 * methods for turtle movement
	 * input: turtle, parameters
	 */
	
	/**
	 * changes heading to rotate left
	 * @param turtle
	 * @param degrees
	 */
	protected double rotateLeft(Turtle turtle,double degrees){
		int newHeading  = (int) (turtle.getHeading() + degrees);
		if(newHeading > 360){
			newHeading -= 360;
		}
		turtle.setHeading(newHeading);
		return degrees;
	}
	
	/**
	 * changes heading to rotate right
	 * @param turtle
	 * @param degrees
	 */
	protected double rotateRight(Turtle turtle,double degrees){
		int newHeading  = (int) (turtle.getHeading() - degrees);
		if(newHeading < 0){
			newHeading+= 360;
		}
		turtle.setHeading(newHeading);
		return degrees;
	}
	
	/**
	 * sets turtle heading absolutely (between 0-360)
	 * adjusts for out of bounds to reduce to between 0-360
	 * @param turtle
	 * @param degrees
	 */
	protected double setHeading(Turtle turtle, double degrees){
		double newHeading = degrees;
		double oldHeading = turtle.getHeading();
		if(degrees > 360){
			newHeading = degrees - 360*(degrees/360) - degrees%360;
		}else if(degrees < 0){
			newHeading = degrees + 360*(degrees/360) + 360;
		}
		turtle.setHeading(newHeading);
		return Math.abs(oldHeading-newHeading);
	}
	
	/**
	 * sets turtle position
	 * @param turtle
	 * @param x
	 * @param y
	 */
	protected double setXY(Turtle turtle, int x, int y){
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),x,y);
		turtle.setXPosition(x+xOrigin);
		turtle.setXPosition(y+yOrigin);
		return distance;
	}
	
	/**
	 * set turtle back to origin (and default heading)
	 * @param turtle
	 */
	protected double home(Turtle turtle){
		double distance = setXY(turtle,xOrigin,yOrigin);
		setHeading(turtle,0);
		//TODO (emre) incorporate default direction
		return distance;
	}
	
	/**
	 * calls moveTowards method to move turtle forward/backward
	 * negative pixel number moves backwards
	 * @param turtle
	 * @param pixels
	 */
	protected double moveForward(Turtle turtle, int pixels){
		moveTurtle(turtle,pixels,turtle.getPenDown());
		return pixels;
	}

	/**
	 * moves turtle toward coordinate & sets heading accordingly
	 * @param turtle
	 * @param x
	 * @param y
	 * TODO change this to match
	 */
	protected double moveTowards(Turtle turtle,int x, int y){
		int actualX = x + xOrigin;
		int actualY = y + yOrigin;
		double oldHeading = turtle.getHeading();
		double newHeading = calculateDirection(x,y);
		turtle.setHeading(newHeading);
		int distance = (int) getDistance(turtle.getXloc(),turtle.getYloc(),actualX,actualY);
		moveTurtle(turtle, distance, turtle.getPenDown());
		return Math.abs(oldHeading - newHeading);
	}
	
	/**
	 * given point, calculates angle from "origin" (0,0)
	 * adjusts for inverse tangent angle calculation
	 * @param x
	 * @param y
	 * @return
	 */
	private double calculateDirection(int x, int y){
		double rawAngle = Math.toDegrees(Math.atan(y/x));
		if(x < 0){
			rawAngle+=180; // second & third quadrant
		}
		if(y < 0){
			rawAngle+= 360; // fourth quadrant
		}
		return rawAngle;
	}

	// TODO update turtle image
	private void moveTurtle(Turtle turtle, int pixels, boolean leaveTrail){
		double heading = turtle.getHeading();
		if(pixels == 0){ // base case
			return;
		}else{
			double xDistance = getXDistance(heading,1);
			double yDistance = getYDistance(heading,1);
			double x = turtle.getXloc();
			double y = turtle.getYloc();
			double x2 = x + xDistance;
			double y2 = y - yDistance;
			if(getOffScreen(x2,y2)){ // next pixel is off screen
				x = (x2+maxCanvasWidth) % maxCanvasWidth;
				y = (y2+maxCanvasHeight) % maxCanvasHeight;
				turtle.setXPosition(x);
				turtle.setYPosition(y);
				moveTurtle(turtle,pixels,leaveTrail);
			}else{ // move turtle if next pixel is on screen
				if(leaveTrail){
					paintLine(turtle,x,y,x2,y2);
				}
				turtle.setXPosition(x2);
				turtle.setYPosition(y2);
				pixels--;
				System.out.println(pixels);
				moveTurtle(turtle,pixels,leaveTrail);
			}
		}
	}
	
	private boolean getOffScreen(double x, double y){
		return ((x > maxCanvasWidth) | (x < minCanvasWidth) | (y > maxCanvasHeight) | (y < minCanvasHeight));
	}
	
	/*
	 * painting methods
	 */
	// TODO implement this method
	/**
	 * draws line on canvas from point A to point B (points guaranteed to be in bounds)
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	private void paintLine(Turtle t, double x1, double y1, double x2, double y2){ 
		// TODO: move turtle image as well, take canvas
		graphics.setStroke(t.getPenColor());
		graphics.setLineWidth(LINE_WIDTH);
		graphics.strokeLine(x1, y1, x2, y2);
	}
	
	/*
	 * helper methods for geometry
	 */
	
	private double getDistance(double x1,double y1, double x2, double y2){
		double xDiff = Math.abs(x2-x1);
		double yDiff = Math.abs(y2-y1);
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	//given r and theta
	private double getXDistance(double heading, int pixels){
		double headingInRadians = degreesToRadians(heading);
//		System.out.println(pixels*Math.cos(headingInRadians));
		return pixels*Math.cos(getMinAngle(headingInRadians));
	}
	
	private double getYDistance(double heading, int pixels){
		double headingInRadians = degreesToRadians(heading);
//		System.out.println(pixels*Math.sin(headingInRadians));
		return pixels*Math.sin(getMinAngle(headingInRadians));
	}
	
	private double degreesToRadians (double degrees){
		return Math.toRadians(degrees);
	}
	
	private double getMinAngle(double degrees){
		if(degrees <= 90){
			return degrees;
		}else if(degrees <= 180){
			return 180-degrees;
		}else if(degrees <= 270){
			return 270-degrees;
		}else if(degrees <= 360){
			return 360 - degrees;
		}
		return 0; // returns zero if error exists
	}

}
