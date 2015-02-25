package slogo_front;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
public class Display {
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
	private static final int defaultHeading = 0;
	
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
		Turtle turtle = new Turtle(xOrigin,yOrigin, 0,0, Color.BLUE, 
				"/images/turtle_small.png", true,true);
		show(turtle);
		graphics.rect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);

		// for testing
		
		turtle.setHeading(170);
		moveForward(turtle, 1000);
	}
	
	protected Node getDisplay(){
		return canvas;
	}
	
	protected void changeBackground(Color backgroundColor){
		graphics.setFill(backgroundColor);
		graphics.fillRect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
	}
	
	/**
	 * erases turtle's trails & sends it to home position
	 * returns distance turtle moved
	 * @param turtle
	 * @return
	 */
	protected double clearScreen(Turtle turtle){
		graphics.clearRect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),xOrigin,yOrigin);
		home(turtle);
		return distance;
	}
	
	/**
	 * puts pen down
	 * @param turtle
	 * @return
	 */
	protected double penDown(Turtle turtle){
		return setPen(turtle,true);
	}
	
	/**
	 * puts pen up
	 * @param turtle
	 * @return
	 */
	protected double penUp(Turtle turtle){
		return setPen(turtle,false);
	}
	
	private double setPen(Turtle turtle, boolean leaveTrail){
		turtle.setPenDown(leaveTrail);
		if(leaveTrail){
			return 1;
		}else{
			return 0;
		}
	}
	
	protected double show(Turtle turtle){
		return showTurtle(turtle,true);
	}
	
	protected double hide(Turtle turtle){
		return showTurtle(turtle,false);
	}
	
	// TODO implement turtle visualization
	private double showTurtle(Turtle turtle, boolean showTurtle){
		turtle.setShowTurtle(showTurtle);
		drawTurtle(turtle,0,0);
		if(showTurtle){
			return 1;
		}else{
			return 0;
		}
	}
	
	private void drawTurtle(Turtle turtle,int x, int y){
		Image image = turtle.getTurtleImage().getImage();
		double widthAdj = image.getWidth()/2;
		double heightAdj= image.getHeight()/2;
		graphics.drawImage(turtle.getTurtleImage().getImage(), xOrigin-widthAdj, yOrigin-heightAdj);
	}
	
	private void setImageXY(Turtle turtle,double x, double y){
		ImageView image = turtle.getTurtleImage();
		double widthAdj = image.getImage().getWidth()/2;
		double heightAdj= image.getImage().getHeight()/2;
		image.setTranslateX(x - widthAdj);
		image.setTranslateY(y - heightAdj);
	}
	
	
	/**
	 * turns turtle counterclockwise
	 * returns value of degrees
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double left(Turtle turtle,double degrees){
		int newHeading  = (int) (turtle.getHeading() + degrees);
		if(newHeading > 360){
			newHeading -= 360;
		}
		turtle.setHeading(newHeading);
		return degrees;
	}
	
	/**
	 * turns turtle clockwise
	 * returns value of degrees
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double right(Turtle turtle,double degrees){
		int newHeading  = (int) (turtle.getHeading() - degrees);
		if(newHeading < 0){
			newHeading+= 360;
		}
		turtle.setHeading(newHeading);
		return degrees;
	}
	
	/**
	 * turns turtle to an absolute heading
	 * returns number of degrees moved
	 * @param turtle
	 * @param degrees
	 * @return
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
	 * moves turtle to absolute screen position
	 * returns distance turtle moved
	 * @param turtle
	 * @param x
	 * @param y
	 * @return
	 */
	protected double setXY(Turtle turtle, int x, int y){
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),x,y);
		turtle.setXPosition(x+xOrigin);
		turtle.setXPosition(y+yOrigin);
		setImageXY(turtle,x,y);
		return distance;
	}
	
	/**
	 * moves turtle to center of screen
	 * returns distance turtle moved
	 * @param turtle
	 * @return
	 */
	protected double home(Turtle turtle){
		double distance = setXY(turtle,xOrigin,yOrigin);
		setHeading(turtle,defaultHeading);
		setImageXY(turtle,xOrigin,yOrigin);
		return distance;
	}
	
	/**
	 * moves turtle forward in its current heading
	 * returns value of pixels
	 * @param turtle
	 * @param pixels
	 * @return
	 */
	protected double forward(Turtle turtle, int pixels){
		return moveForward(turtle, pixels);
	}
	
	/**
	 * moves turtle backward in its current heading
	 * returns value of pixels
	 * @param turtle
	 * @param pixels
	 * @return
	 */
	protected double back(Turtle turtle, int pixels){
		return moveForward(turtle,pixels*-1);
	}
	
	private double moveForward(Turtle turtle, int pixels){
		moveTurtle(turtle,pixels,turtle.getPenDown());
		return pixels;
	}

	/**
	 * turns turtle to face the point (x,y)
	 * returns number of degrees turtle turned
	 * @param turtle
	 * @param x
	 * @param y
	 * @return
	 */
	protected double towards(Turtle turtle,int x, int y){
		double oldHeading = turtle.getHeading();
		double newHeading = calculateDirection(x,y);
		turtle.setHeading(newHeading);
		return Math.abs(oldHeading - newHeading);
	}
	
	/**
	 * calculates direction of turtle (adjusts for arctan values)
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
	 * moves turtle recursively given number of pixels
	 * @param turtle
	 * @param pixels
	 * @param leaveTrail
	 */
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
				setImageXY(turtle,x,y);
				moveTurtle(turtle,pixels,leaveTrail);
			}else{ // move turtle if next pixel is on screen
				if(leaveTrail){
					paintLine(turtle,x,y,x2,y2);
				}
				turtle.setXPosition(x2);
				turtle.setYPosition(y2);
				setImageXY(turtle,x2,y2);
				pixels--;
				moveTurtle(turtle,pixels,leaveTrail);
			}
		}
	}
	
	private boolean getOffScreen(double x, double y){
		return ((x > maxCanvasWidth) | (x < minCanvasWidth) | (y > maxCanvasHeight) | (y < minCanvasHeight));
	}
	
	private void paintLine(Turtle t, double x1, double y1, double x2, double y2){ 
		graphics.setStroke(t.getPenColor());
		graphics.setLineWidth(LINE_WIDTH);
		graphics.strokeLine(x1, y1, x2, y2);
		paintImage(t.getTurtleImage(),x2,y2,t.getHeading());
	}
	
	// paint image
	private void paintImage(ImageView image,double x, double y,double heading){
		image.setRotate(heading - image.getRotate());
		image.setTranslateX(x);
		image.setTranslateY(y);
	}
	
	// helper methods for geometry calculations
	
	/**
	 * gets distance between 2 points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	private double getDistance(double x1,double y1, double x2, double y2){
		double xDiff = Math.abs(x2-x1);
		double yDiff = Math.abs(y2-y1);
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}

	/**
	 * gets distance along x axis between 2 points
	 * @param heading
	 * @param pixels
	 * @return
	 */
	private double getXDistance(double heading, int pixels){
		double headingInRadians = degreesToRadians(heading);
		return pixels*Math.cos(getMinAngle(headingInRadians));
	}
	
	/**
	 * gets distance along y axis between 2 points
	 * @param heading
	 * @param pixels
	 * @return
	 */
	private double getYDistance(double heading, int pixels){
		double headingInRadians = degreesToRadians(heading);
		return pixels*Math.sin(getMinAngle(headingInRadians));
	}
	
	private double degreesToRadians (double degrees){
		return Math.toRadians(degrees);
	}
	
	/**
	 * gets smallest angle from horizontal
	 * @param degrees
	 * @return
	 */
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
