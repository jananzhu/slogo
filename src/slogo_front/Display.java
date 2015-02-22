package slogo_front;

public class Display {
	private int xOrigin;
	private int yOrigin;
	private int xBoundHigh;
	private int xBoundLow;
	private int yBoundHigh;
	private int yBoundLow;
	
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
	 * command helper methods
	 * input: turtle, parameters
	 */
	
	/**
	 * given turtle and distance, moves turtle forward accordingly
	 * @param t
	 * @param pixels
	 * @param leaveTrail
	 */
	public void moveForward(Turtle t,int pixels,boolean leaveTrail){
		boolean atTop = false;
		boolean atBottom = false;
		boolean atLeft = false;
		boolean atRight = false;
		
		int curX = t.getXloc();
		int curY = t.getYloc();
		double heading = t.getHeading();
		
		double xDistance = getXDistance(heading,pixels);
		double yDistance = getYDistance(heading,pixels);
		
		double xTemp = curX + xDistance;
		double yTemp = curY + yDistance;
		
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
			moveForward(t,newDistance,leaveTrail); // call recursively on remaining distance
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
	private void paintLine(int x1, int x2, int y1, int y2){ // TODO implement this
		
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
