package slogo_front;

/**
 * currently includes methods to move turtle
 * NOTE: API (public) methods except constructor
 * take x & y coordinates with (0,0) in the middle
 * of the screen
 * 
 * API Methods:
 * 	- Display(int xCenter, int yCenter, int xHigh, int xLow,
			int yHigh, int yLow)
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
 * @author emresonmez
 *
 */
public class Display {
	private int xOrigin;
	private int yOrigin;
	private int xBoundHigh;
	private int xBoundLow;
	private int yBoundHigh;
	private int yBoundLow;
	
	/**
	 * basic constructor
	 * @param xCenter
	 * @param yCenter
	 * @param xHigh
	 * @param xLow
	 * @param yHigh
	 * @param yLow
	 */
	public Display(int xCenter, int yCenter, int xHigh, int xLow,
			int yHigh, int yLow){
		xOrigin = xCenter;
		yOrigin = yCenter;
		xBoundHigh = xHigh;
		xBoundLow = xLow;
		yBoundHigh = yHigh;
		yBoundLow = yLow;
	}
	
	/*
	 * methods for canvas properties
	 */
	
	//TODO: implement this method
	public void clearScreen(){

		
	}
	
	/*
	 * methods for turtle properties
	 */
	
	/**
	 * sets pen up (false) or down (true)
	 * @param t
	 * @param leaveTrail
	 */
	public void setPen(Turtle t, boolean leaveTrail){
		t.setPenDown(leaveTrail);
	}
	
	/**
	 * TODO: implement turtle visualization
	 * shows (true) or hides (false) turtle image on gui
	 * @param t
	 * @param showTurtle
	 */
	public void showTurtle(Turtle t, boolean showTurtle){
		t.setShowTurtle(showTurtle);
		//TODO: reflect this change on GUI
		
	}
	
	/*
	 * methods for turtle movement
	 * input: turtle, parameters
	 */
	
	/**
	 * changes heading to rotate left
	 * @param t
	 * @param degrees
	 */
	public void rotateLeft(Turtle t,double degrees){
		int newHeading  = (int) (t.getHeading() + degrees);
		if(newHeading > 360){
			newHeading -= 360;
		}
		t.setHeading(newHeading);
	}
	
	/**
	 * changes heading to rotate right
	 * @param t
	 * @param degrees
	 */
	public void rotateRight(Turtle t,double degrees){
		int newHeading  = (int) (t.getHeading() - degrees);
		if(newHeading < 0){
			newHeading+= 360;
		}
		t.setHeading(newHeading);
	}
	
	/**
	 * sets turtle heading absolutely (between 0-360)
	 * adjusts for out of bounds to reduce to between 0-360
	 * @param t
	 * @param degrees
	 */
	public void setHeading(Turtle t, double degrees){
		double newHeading = degrees;
		if(degrees > 360){
			newHeading = degrees - 360*(degrees/360) - degrees%360;
		}else if(degrees < 0){
			newHeading = degrees + 360*(degrees/360) + 360;
		}
		t.setHeading(newHeading);
	}
	
	/**
	 * sets turtle position
	 * @param t
	 * @param x
	 * @param y
	 */
	public void setXY(Turtle t, int x, int y){
		t.setXLoc(x+xOrigin);
		t.setXLoc(y+yOrigin);
	}
	
	/**
	 * set turtle back to origin (and default heading)
	 * @param t
	 */
	public void moveHome(Turtle t){
		setXY(t,xOrigin,yOrigin);
		setHeading(t,0);
		//TODO (emre) incorporate default direction
	}
	
	/**
	 * calls moveTowards method to move turtle forward
	 * @param t
	 * @param pixels
	 */
	public void moveForward(Turtle t, int pixels){
		moveStraight(t,pixels,t.getPenDown(),true);
	}

	/**
	 * calls moveTowards method to move turtle backward
	 * @param t
	 * @param pixels
	 */
	public void moveBackward(Turtle t, int pixels){
		moveStraight(t,pixels,t.getPenDown(),false);
	}
	
	/**
	 * moves turtle toward coordinate & sets heading accordingly
	 * @param t
	 * @param x
	 * @param y
	 */
	public void moveTowards(Turtle t,int x, int y){
		int actualX = x + xOrigin;
		int actualY = y + yOrigin;
		double newHeading = calculateDirection(x,y);
		t.setHeading(newHeading);
		int distance = (int) getDistance(t.getXloc(),t.getYloc(),actualX,actualY);
		moveStraight(t, distance, t.getPenDown(),true);
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
	
	/**
	 * given turtle and distance, moves turtle accordingly
	 * @param t
	 * @param pixels
	 * @param leaveTrail
	 */
	private void moveStraight(Turtle t, int pixels, boolean leaveTrail, boolean forward){
		boolean atTop = false;
		boolean atBottom = false;
		boolean atLeft = false;
		boolean atRight = false;
		
		int curX = t.getXloc();
		int curY = t.getYloc();
		double heading = t.getHeading();
		
		double xDistance = getXDistance(heading,pixels);
		double yDistance = getYDistance(heading,pixels);
		
		double xTemp = curX;
		double yTemp = curY;
		
		if(forward){
			xTemp += xDistance;
			yTemp += yDistance;
		}else{
			xTemp -= xDistance;
			yTemp -= yDistance;
		}
		
		int finalX;
		int finalY;
		
		// check if line hits edge in x direction
		if(checkEdgeHorizontal(xTemp)){
			finalX = (int) xTemp;
		}else{
			if(xTemp > xBoundHigh){
				atRight = true;
				finalX = xBoundHigh;
				xDistance = xDistance - xBoundHigh;
			}else{
				atLeft = true;
				finalX = xBoundLow;
				xDistance = xDistance - xBoundLow; 
			}
		}
		
		// check if line hits edge in y direction
		if(checkEdgeVertical(yTemp)){ // y loc is in bounds
			finalY = (int) yTemp;
		}else{
			if(yTemp > yBoundHigh){
				atTop = true;
				finalY = yBoundHigh;
				yDistance = yDistance - yBoundHigh;
			}else{
				atBottom = true;
				finalY = yBoundLow;
				yDistance = yDistance - yBoundLow; 
			}
		}
		
		// update turtle location properties
		t.setXLoc(finalX);
		t.setYLoc(finalY);
		// paint line (first line if hits edge)
		if(leaveTrail){
			paintLine(curX, curY, finalX, finalY);	
		}
		
		// set new locations if at edge
		if(atRight){
			t.setXLoc(xBoundLow);
		}else if(atLeft){
			t.setXLoc(xBoundHigh);
		}
		if(atTop){
			t.setYLoc(yBoundLow);
		}else if(atBottom){
			t.setYLoc(yBoundHigh);
		}
		
		// if turtle is at edge, continue moving turtle
		if(atRight|atLeft|atTop|atBottom){
			int newDistance = (int) Math.sqrt(xDistance*xDistance + // update distance 
					yDistance*yDistance);
			moveStraight(t,newDistance,leaveTrail,forward); // call recursively on remaining distance
		}
		
	}
	
	private boolean checkEdgeHorizontal(double x){
		return ((x <= xBoundHigh) && (x >= xBoundLow));
	}
	
	private boolean checkEdgeVertical(double y){
		return ((y <= yBoundHigh) && (y >= yBoundLow));
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
	private void paintLine(int x1, int x2, int y1, int y2){ 

		// TODO: move turtle image as well
		
	}
	
	/*
	 * helper methods for geometry
	 */
	
	private double getDistance(int x1,int y1, int x2, int y2){
		int xDiff = x2-x1;
		int yDiff = y2-y1;
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	
	private double getXDistance(double heading, int pixels){
		double headingInRadians = degreesToRadians(heading);
		return pixels*Math.cos(headingInRadians);
	}
	
	private double getYDistance(double heading, int pixels){
		double headingInRadians = degreesToRadians(heading);
		return pixels*Math.sin(headingInRadians);
	}
	
	private double degreesToRadians (double degrees){
		return Math.toRadians(degrees);
	}
}
