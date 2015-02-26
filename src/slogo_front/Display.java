package slogo_front;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * currently includes methods to move turtle
 * NOTE: API (protected) methods except constructor
 * take x & y coordinates with (0,0) in the middle
 * of the screen
 *
 */
public class Display {
	//References the middle of the canvas
	private double xOrigin;
	private double yOrigin;
	//Outer bounds of the display
	private double maxCanvasWidth;
	private double minCanvasWidth = 0;
	private double maxCanvasHeight;
	private double minCanvasHeight = 0;
	//instances of canvas and graphics context
	private Pane overlay;
	private Canvas canvas;
	private Canvas background;
	private GraphicsContext graphics;
	private static final int defaultHeading = 0;
	
	// TODO move arrayList to manager class
//	private ArrayList<Turtle> turtles = new ArrayList<>();
	
	/**
	 * basic constructor
	 * @param xDimension
	 * @param yDimension
	 * @param myCanvas
	 */
	protected Display(double canvasWidth, double canvasHeight){
		overlay = new Pane();
		canvas = new Canvas (canvasWidth,canvasHeight);
		background = new Canvas(canvasWidth,canvasHeight);
		overlay.setPrefSize(canvasWidth, canvasHeight);
		overlay.getChildren().addAll(background,canvas);
		
		xOrigin = canvasWidth/2;
		yOrigin = canvasHeight/2;
		maxCanvasWidth = canvasWidth;
		maxCanvasHeight = canvasHeight;
		graphics = canvas.getGraphicsContext2D();

		// for testing
//		Turtle turtle = new Turtle(xOrigin,yOrigin, 0,0, Color.BLUE, 1, 
//				"/images/turtle_small.png", true,true);
//		Turtle turtle2 = new Turtle(xOrigin,yOrigin, 0,0, Color.BLUE,1,
//				"/images/turtle_small.png", true,true);
//		turtle.setHeading(170);
//		turtle2.setHeading(50);
//		forward(turtle2,10);
//		moveForward(turtle, 10000); // observer observable
//		turtle.setHeading(60);
//		turtle.setPenWidth(3);
//		moveForward(turtle,100);
//		left(turtle,50);
//		clearScreen(turtle);

		background.getGraphicsContext2D().rect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
		
//		test();
	}
	
	/**
	 * testing area
	 */
	private void test(){
		Turtle turtle = new Turtle(xOrigin,yOrigin, 0,0, Color.BLUE, 1, 
				"/images/turtle_small.png", true,true);
		Turtle turtle2 = new Turtle(xOrigin,yOrigin, 0,0, Color.BLUE,1,
				"/images/turtle_small.png", true,true);
		turtle.setHeading(170);
		turtle2.setHeading(50);
		forward(turtle2,10);
		moveForward(turtle, 10000); // observer observable
		turtle.setHeading(60);
		turtle.setPenWidth(3);
		moveForward(turtle,100);
		left(turtle,50);
	}
	
	// queries
	
	protected Node getDisplay(){
		return overlay;
	}
	
	protected double xCor(Turtle turtle){
		return turtle.getXloc()+xOrigin;
	}
	
	protected double yCor(Turtle turtle){
		return turtle.getYloc()+yOrigin;
	}
	
	protected double showing(Turtle turtle){
		if(turtle.getShowTurtle()){
			return 1;
		}else{
			return 0;
		}
	}
	
	protected double isPenDown(Turtle turtle){
		if(turtle.getPenDown()){
			return 1;
		}
		return 0;
	}
	
	// actions 
	
	protected void changeBackground(Color backgroundColor){
		background.getGraphicsContext2D().setFill(backgroundColor);
		background.getGraphicsContext2D().fillRect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
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
	
	// TODO add these methods to manager class
//	/**
//	 * adds turtle to array list of turtles
//	 * returns number of turtles in array list
//	 * @param turtle
//	 * @return
//	 */
//	protected int addTurtle(Turtle turtle){
//		turtles.add(turtle);
//		return turtles.size();
//	}
	
//	/**
//	 * removes turtle image from display & from active turtles list
//	 * returns number of turtles in array list
//	 * @param turtle
//	 */
//	protected int removeTurtle(Turtle turtle){
//		if(turtle.getShowTurtle()){
//			hide(turtle);
//		}
//		turtles.remove(turtle);
//		return turtles.size();
//	}
	
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
		turtle.setShowTurtle(true);
		drawTurtleImage(turtle);
		return 1;
	}

	/**
	 * draws image of turtle given specifications
	 * of location in turtle object
	 * @param turtle
	 */
	private void drawTurtleImage(Turtle turtle){
		ImageView turtleImage = turtle.getTurtleImage();
		turtleImage.setRotate(turtle.getAdjustedHeading());
		double widthAdj = turtleImage.getImage().getWidth() / 2;
		double heightAdj= turtleImage.getImage().getHeight() / 2;
		turtleImage.setTranslateX(turtle.getXloc() - widthAdj);
		turtleImage.setTranslateY(turtle.getYloc() - heightAdj);
		overlay.getChildren().add(turtleImage);
	}
	
	protected double hide(Turtle turtle){
		removeTurtleImage(turtle);
		turtle.setShowTurtle(false);
		return 0;
	}

	private void removeTurtleImage(Turtle turtle){
		overlay.getChildren().remove(turtle.getTurtleImage());
	}
	
	/**
	 * updates turtle image to reflect changes in turtle
	 * @param turtle
	 */
	private void updateTurtleImage(Turtle turtle){
		removeTurtleImage(turtle);
		if(turtle.getShowTurtle()){
			drawTurtleImage(turtle);
		}
	}
	
	/**
	 * turns turtle counterclockwise
	 * returns value of degrees
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double left(Turtle turtle,double degrees){
		double newHeading  =  turtle.getHeading() + degrees;
		if(newHeading > 360){
			newHeading -= 360;
		}
		turtle.setHeading(newHeading);
		updateTurtleImage(turtle);
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
		double newHeading  = turtle.getHeading() - degrees;
		if(newHeading < 0){
			newHeading+= 360;
		}
		turtle.setHeading(newHeading);
		updateTurtleImage(turtle);
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
		updateTurtleImage(turtle);
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
	protected double setXY(Turtle turtle, double x, double y){
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),x,y);
		turtle.setXPosition(x+xOrigin);
		turtle.setXPosition(y+yOrigin);
		updateTurtleImage(turtle);
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
		updateTurtleImage(turtle);
		return distance;
	}
	
	/**
	 * moves turtle forward in its current heading
	 * returns value of pixels
	 * @param turtle
	 * @param pixels
	 * @return
	 */
	protected double forward(Turtle turtle, double pixels){
		return moveForward(turtle, pixels);
	}
	
	/**
	 * moves turtle backward in its current heading
	 * returns value of pixels
	 * @param turtle
	 * @param pixels
	 * @return
	 */
	protected double back(Turtle turtle, double pixels){
		return moveForward(turtle,pixels*-1);
	}
	
	private double moveForward(Turtle turtle, double pixels){
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
	protected double towards(Turtle turtle,double x, double y){
		double oldHeading = turtle.getHeading();
		double newHeading = calculateDirection(x,y);
		turtle.setHeading(newHeading);
		updateTurtleImage(turtle);
		return Math.abs(oldHeading - newHeading);
	}
	
	/**
	 * calculates direction of turtle (adjusts for arctan values)
	 * @param x
	 * @param y
	 * @return
	 */
	private double calculateDirection(double x, double y){
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
	private void moveTurtle(Turtle turtle, double pixels, boolean leaveTrail){
		double heading = turtle.getHeading();
		if(pixels == 0){ // base case
			updateTurtleImage(turtle); // update image at very end
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
				moveTurtle(turtle,pixels,leaveTrail);
			}
		}
	}
	
	private boolean getOffScreen(double x, double y){
		return ((x > maxCanvasWidth) | (x < minCanvasWidth) | (y > maxCanvasHeight) | (y < minCanvasHeight));
	}
	
	private void paintLine(Turtle t, double x1, double y1, double x2, double y2){ 
		graphics.setStroke(t.getPenColor());
		graphics.setLineWidth(t.getPenWidth());
		graphics.strokeLine(x1, y1, x2, y2);
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
	private double getXDistance(double heading, double pixels){
		double headingInRadians = degreesToRadians(heading);
		return pixels*Math.cos(getMinAngle(headingInRadians));
	}
	
	/**
	 * gets distance along y axis between 2 points
	 * @param heading
	 * @param pixels
	 * @return
	 */
	private double getYDistance(double heading, double pixels){
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
