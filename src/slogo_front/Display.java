package slogo_front;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * currently includes methods to move turtle
 * NOTE: API (public) methods except constructor
 * take x & y coordinates with (0,0) in the middle
 * of the screen
 * 
 * API Methods:
 * 	- Display(int xDimension, int yDimension,
			Canvas myCanvas)
 *  - clearScreen() (not yet completed)
 *  - setPen(Turtle t, boolean leaveTrail)
 *  - showTurtle(Turtle t, boolean showTurtle)
 *  - rotateLeft(Turtle t,double degrees)
 *  - rotateRight(Turtle t,double degrees)
 *  - setHeading(Turtle t, double degrees)
 *  - setXY(Turtle t, int x, int y)
 *  - moveForward(Turtle t, int pixels)
 *  - moveBackward(Turtle t, int pixels)
 *  - moveTowards(Turtle t,int x, int y)
 *  
 * to be added:
 * 	- painting implementation (painting lines on canvas)
 *  - turtle image updates 
 *
 */
//TODO flexible canvas size
public class Display {
	//Display Background (a vbox wrapper)
//	private VBox displayBackground;
	//Canvas dimensions
	private int xCanvas;
	private int yCanvas;
	//References the middle of the canvas
	private int xOrigin;
	private int yOrigin;
	//Outer bounds of the display
	private int maxCanvasWidth;
	private int minCanvasWidth = 0;
	private int maxCanvasHeight;
	private int minCanvasHeight = 0;
	//instances of cancas and graphics context
	private Canvas canvas;
	private GraphicsContext graphics;
	private static final int LINE_WIDTH = 1;
	
	/**
	 * basic constructor
	 * @param xDimension
	 * @param yDimension
	 * @param myCanvas
	 */
	public Display(int canvasWidth, int canvasHeight){
		canvas = new Canvas (canvasWidth,canvasHeight);
		xOrigin = canvasWidth/2;
		yOrigin = canvasHeight/2;
		maxCanvasWidth = canvasWidth;
		maxCanvasHeight = canvasHeight;
		graphics = canvas.getGraphicsContext2D();
		Turtle turtle = new Turtle(xOrigin,yOrigin, 0,0, Color.BLUE, "", true,true );
//		graphics.setStroke(Color.BLUE);
//		graphics.setLineWidth(LINE_WIDTH);
//		graphics.strokeLine(xOrigin, yOrigin, xOrigin, yOrigin);
		turtle.setHeading(45);
		moveForward(turtle, 500);
		
		
	}
	
	public Node getDisplay(){
		return canvas;
	}
	
	/*
	 * methods for canvas properties
	 */
	
	/*
	 * CS erases turtle's trails and sends it to the home position
	 * returns the distance turtle moved
	 */
	public double clearScreen(Turtle turtle){
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
	public void setPen(Turtle turtle, boolean leaveTrail){
		turtle.setPenDown(leaveTrail);
	}
	
	/**
	 * TODO: implement turtle visualization
	 * shows (true) or hides (false) turtle image on gui
	 * @param turtle
	 * @param showTurtle
	 */
	public void showTurtle(Turtle turtle, boolean showTurtle){
		turtle.setShowTurtle(showTurtle);
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
	public void rotateLeft(Turtle turtle,double degrees){
		int newHeading  = (int) (turtle.getHeading() + degrees);
		if(newHeading > 360){
			newHeading -= 360;
		}
		turtle.setHeading(newHeading);
	}
	
	/**
	 * changes heading to rotate right
	 * @param turtle
	 * @param degrees
	 */
	public void rotateRight(Turtle turtle,double degrees){
		int newHeading  = (int) (turtle.getHeading() - degrees);
		if(newHeading < 0){
			newHeading+= 360;
		}
		turtle.setHeading(newHeading);
	}
	
	/**
	 * sets turtle heading absolutely (between 0-360)
	 * adjusts for out of bounds to reduce to between 0-360
	 * @param turtle
	 * @param degrees
	 */
	public void setHeading(Turtle turtle, double degrees){
		double newHeading = degrees;
		if(degrees > 360){
			newHeading = degrees - 360*(degrees/360) - degrees%360;
		}else if(degrees < 0){
			newHeading = degrees + 360*(degrees/360) + 360;
		}
		turtle.setHeading(newHeading);
	}
	
	/**
	 * sets turtle position
	 * @param turtle
	 * @param x
	 * @param y
	 */
	public double setXY(Turtle turtle, int x, int y){
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),x,y);
		turtle.setXPosition(x+xOrigin);
		turtle.setXPosition(y+yOrigin);
		return distance;
	}
	
	/**
	 * set turtle back to origin (and default heading)
	 * @param turtle
	 */
	public double home(Turtle turtle){
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
	public void moveForward(Turtle turtle, int pixels){
//		moveStraight(turtle,pixels,turtle.getPenDown());
		move(turtle,pixels,turtle.getPenDown());
	}

	/**
	 * moves turtle toward coordinate & sets heading accordingly
	 * @param turtle
	 * @param x
	 * @param y
	 */
	public void moveTowards(Turtle turtle,int x, int y){
		int actualX = x + xOrigin;
		int actualY = y + yOrigin;
		double newHeading = calculateDirection(x,y);
		turtle.setHeading(newHeading);
		int distance = (int) getDistance(turtle.getXloc(),turtle.getYloc(),actualX,actualY);
		moveStraight(turtle, distance, turtle.getPenDown());
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
	
//	private void moveStraight(Turtle turtle, int pixels, boolean leaveTrail){
//		int curX = turtle.getXloc();
//		int curY = turtle.getYloc();
//		double heading = turtle.getHeading();
//		System.out.println("heading: " + heading);
//		System.out.println(curX + " " + curY);
//		int xDistance = (int) getXDistance(heading,pixels);
//		int yDistance = (int) getYDistance(heading,pixels);
//		int finalX = curX + xDistance;
//		int finalY = curY - yDistance;
//		System.out.println(finalX + " " + finalY);
//		turtle.setXPosition(finalX);
//		turtle.setYPosition(finalY);
//		// paint line (first line if hits edge)
//		if(leaveTrail){
//			paintLine(turtle,curX, curY, finalX, finalY);	
//		}
//	}
	// replacements for previous long methods
	private void move(Turtle turtle, int pixels, boolean leaveTrail){
		//starting turtle position
		int curX = turtle.getXloc();
		int curY = turtle.getYloc();
		//direction of the turtle
		double heading = turtle.getHeading();
		
		System.out.println("heading: " + heading);
		System.out.println("current position"+ curX + " " + curY);
		
		int xDistance = (int) getXDistance(heading,pixels);
		int yDistance = (int) getYDistance(heading,pixels);
		System.out.println("x,y vectors " +xDistance+ " " + yDistance);
		
		int finalX = curX + xDistance;
		int finalY = curY - yDistance;
		
		moveTurtle(turtle, curX, curY, finalX, finalY,leaveTrail);
	}
	
	// TODO debug this boi
	private void moveTurtle(Turtle turtle, int x1, int y1, int x2, int y2,boolean leaveTrail){
		int tempX = x2;
		int tempY = y2;
		turtle.setXPosition(x2);
		turtle.setYPosition(y2);
		System.out.println("initial: " + x1 + " " + y1 + " " + x2 + " " + y2);
		//Edge check might have height and width checks?
		System.out.println(getOffScreen(x2,y2));
		if(getOffScreen(x2,y2)){ // TODO ERROR IS IN LOGIC HERE
			if(x2 > maxCanvasWidth){
				tempX = x2 - maxCanvasWidth; // remaining X
				x2 = minCanvasWidth;
			}else if(x2 < minCanvasWidth){
				tempX = Math.abs(x2);
				x2 = maxCanvasWidth;
			}
			if(y2 > maxCanvasHeight){
				tempY = y2 - maxCanvasHeight; // remaining Y
				y2 = minCanvasHeight;
			}else if(y2 < minCanvasHeight){
				tempY = minCanvasHeight;
				y2 = maxCanvasHeight;
			}
			turtle.setXPosition(tempX);
			turtle.setYPosition(tempY);
			if(leaveTrail){
				System.out.println("temp paint: " + x1 + " " + y1 + " " + tempX + " " + tempY);
				paintLine(turtle,x1,y1,tempX,tempY);
			}
			System.out.println("recursive paint: " + tempX + " " + tempY + " " + (x2-tempX) + " " + (y2-tempY));
			moveTurtle(turtle,tempX,tempY,x2-tempX,y2-tempY,leaveTrail);	
		}else{
			turtle.setXPosition(x2);
			turtle.setYPosition(y2);
			if(leaveTrail){
				paintLine(turtle, x1, y1, x2, y2);
			}
		}
	}
	
	private boolean getOffScreen(int x, int y){
		return ((x > maxCanvasWidth) | (x < minCanvasWidth) | (y > maxCanvasHeight) | (y < minCanvasHeight));
	}
	
	
	/**
	 * given turtle and distance, moves turtle accordingly
	 * @param turtle
	 * @param pixels
	 * @param leaveTrail
	 */
	private void moveStraight(Turtle turtle, int pixels, boolean leaveTrail){
		boolean atTop = false;
		boolean atBottom = false;
		boolean atLeft = false;
		boolean atRight = false;
		
		int curX = turtle.getXloc();
		int curY = turtle.getYloc();
//		System.out.println(curX);
//		System.out.println(curY);
//		System.out.println(turtle.getHeading());
		double heading = turtle.getHeading();
		
		double xDistance = getXDistance(heading,pixels);
		double yDistance = getYDistance(heading,pixels);
		//michael changed this to reflect direction
		double xTemp = curX + xDistance;
		double yTemp = curY - yDistance;
		
		int finalX;
		int finalY;
		
		// check if line hits edge in x direction
		if(checkEdgeHorizontal(xTemp)){
			finalX = (int) xTemp;
		}else{
			if(xTemp > maxCanvasWidth){
				atRight = true;
				finalX = maxCanvasWidth;
				xDistance = xDistance - maxCanvasWidth;
			}else{
				atLeft = true;
				finalX = minCanvasWidth;
				xDistance = xDistance + minCanvasWidth; 
			}
		}
		
		// check if line hits edge in y direction
		if(checkEdgeVertical(yTemp)){ // y loc is in bounds
			finalY = (int) yTemp;
		}else{
			if(yTemp > maxCanvasHeight){
				atTop = true;
				finalY = maxCanvasHeight;
				yDistance = yDistance - maxCanvasHeight;
			}else{
				atBottom = true;
				finalY = minCanvasHeight;
				yDistance = yDistance + minCanvasHeight; 
			}
		}
		
		// update turtle location properties
		turtle.setXPosition(finalX);
		turtle.setYPosition(finalY);
		System.out.println("x1: " + curX + " y1: " + curY + " x2: " + finalX + " y2: " + finalY);
		System.out.println("heading: " + heading + " xDistance: " + xDistance + " yDistance: " + yDistance);
		// paint line (first line if hits edge)
		if(leaveTrail){
			paintLine(turtle,curX, curY, finalX, finalY);	
		}
		
		// set new locations if at edge
		if(atRight){
			turtle.setXPosition(minCanvasWidth);
		}else if(atLeft){
			turtle.setXPosition(maxCanvasWidth);
		}
		if(atTop){
			turtle.setYPosition(minCanvasHeight);
		}else if(atBottom){
			turtle.setYPosition(maxCanvasHeight);
		}
		
		// if turtle is at edge, continue moving turtle
		if(atRight|atLeft|atTop|atBottom){
			int newDistance = (int) Math.sqrt(xDistance*xDistance + // update distance 
					yDistance*yDistance);
			System.out.println("newDistance: " + newDistance);
			System.out.println("newXDistance: " + getXDistance(turtle.getXloc(), newDistance));
			System.out.println("newXDistance: " + getYDistance(turtle.getYloc(), newDistance));
			System.out.println("angle: " + Math.toDegrees(Math.atan(getYDistance(turtle.getYloc(), newDistance)/getXDistance(turtle.getXloc(), newDistance))));
			moveStraight(turtle,newDistance,leaveTrail); // call recursively on remaining distance
		}
		
	}
	
	private boolean checkEdgeHorizontal(double x){
		return ((x <= maxCanvasWidth) && (x >= minCanvasWidth));
	}
	
	private boolean checkEdgeVertical(double y){
		return ((y <= maxCanvasHeight) && (y >= minCanvasHeight));
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
	private void paintLine(Turtle t, int x1, int y1, int x2, int y2){ 
		// TODO: move turtle image as well, take canvas
		graphics.setStroke(t.getPenColor());
		graphics.setLineWidth(LINE_WIDTH);
		graphics.strokeLine(x1, y1, x2, y2);
		// move image
	}
	
	/*
	 * helper methods for geometry
	 */
	
	private double getDistance(int x1,int y1, int x2, int y2){
		int xDiff = Math.abs(x2-x1);
		int yDiff = Math.abs(y2-y1);
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
