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
	private double defaults[] = {xOrigin,yOrigin,defaultHeading};
	private double[] origins = {xOrigin,yOrigin};
	//turtle array
	private ArrayList<Turtle> turtleList = new ArrayList<>();
	//display id
	private int displayID;
	
	/**
	 * basic constructor
	 * @param xDimension
	 * @param yDimension
	 * @param myCanvas
	 */
	public Display(double canvasWidth, double canvasHeight, int id){
		displayID = id;
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
		
		background.getGraphicsContext2D().rect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
		
		//default turtle initialized in display
		Turtle turtle = new Turtle(xOrigin, yOrigin, 0, 0, Color.BLACK, 1, "/images/turtle_small.png",true, true);
		turtleList.add(turtle);
		
//		test();
	}
	
	/**
	 * testing area
	 */
//	private void test(){
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
//	}
	
	// queries
	
	public Node getDisplay(){
		return overlay;
	}
	
	protected double xCor(int display, Turtle turtle, double[] params){
		return turtle.getXloc()+xOrigin;
	}
	
	protected double yCor(int display, Turtle turtle, double[] params){
		return turtle.getYloc()+yOrigin;
	}
	
	protected double showing(int display, Turtle turtle, double[] params){
		if(turtle.getShowTurtle()){
			return 1;
		}else{
			return 0;
		}
	}
	
	protected double isPenDown(int display, Turtle turtle, double[] params){
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
	protected double clearScreen(int display, Turtle turtle, double[] params){
		graphics.clearRect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),xOrigin,yOrigin);
		home(display,turtle,params);
		return distance;
	}
	
	/**
	 * puts pen down
	 * @param turtle
	 * @return
	 */
	protected double penDown(int display, Turtle turtle, double[] params){
		return setPen(turtle,true);
	}
	
	/**
	 * puts pen up
	 * @param turtle
	 * @return
	 */
	protected double penUp(int display, Turtle turtle, double[] params){
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
	
	protected double show(int display, Turtle turtle, double[] params){
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
	
	protected double hide(int display, Turtle turtle, double[] params){
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
	protected double left(int display, Turtle turtle, double[] params){
		double newHeading  =  turtle.getHeading() + params[0];
		if(newHeading > 360){
			newHeading -= 360;
		}
		turtle.setHeading(newHeading);
		updateTurtleImage(turtle);
		return params[0];
	}
	
	/**
	 * turns turtle clockwise
	 * returns value of degrees
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double right(int display, Turtle turtle, double[] params){
		double newHeading  = turtle.getHeading() - params[0];
		if(newHeading < 0){
			newHeading+= 360;
		}
		turtle.setHeading(newHeading);
		updateTurtleImage(turtle);
		return params[0];
	}
	
	/**
	 * turns turtle to an absolute heading
	 * returns number of degrees moved
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double setHeading(int display, Turtle turtle, double[] params){
		double degrees = params[0];
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
	protected double setXY(int display, Turtle turtle, double[] params){
		double x = params[0];
		double y = params[1];
		double distance = getDistance(turtle.getXloc(),turtle.getYloc(),x,y);
		turtle.setXPosition(x+xOrigin);
		turtle.setYPosition(yOrigin-y);
		updateTurtleImage(turtle);
		return distance;
	}
	
	/**
	 * moves turtle to center of screen
	 * returns distance turtle moved
	 * @param turtle
	 * @return
	 */
	protected double home(int display, Turtle turtle, double[] params){
		double distance = setXY(turtle.getDisplayID(),turtle,origins);
		turtle.setXPosition(xOrigin);
		turtle.setYPosition(yOrigin);
		double[] heading = {defaultHeading};
		setHeading(turtle.getDisplayID(),turtle,heading);
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
	protected double forward(int display, Turtle turtle, double[] params){
		return moveForward(turtle, params[0]);
	}
	
	/**
	 * moves turtle backward in its current heading
	 * returns value of pixels
	 * @param turtle
	 * @param pixels
	 * @return
	 */
	protected double back(int display, Turtle turtle, double[] params){
		return moveForward(turtle,params[0]*-1);
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
	protected double towards(int display, Turtle turtle, double[] params){
		double oldHeading = turtle.getHeading();
		double newHeading = calculateDirection(params[0],params[1]);
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
	 * moves turtle forward/backward depending on sign of pixels
	 * @param turtle
	 * @param pixels
	 * @param leaveTrail
	 */
	private void moveTurtle(Turtle turtle, double pixels, boolean leaveTrail){
		double heading = turtle.getHeading();
		if(pixels<0){
			heading+=180;
		}
		while(pixels != 0){
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
			}else{ // move turtle if next pixel is on screen
				if(leaveTrail){
					paintLine(turtle,x,y,x2,y2);
				}
				turtle.setXPosition(x2);
				turtle.setYPosition(y2);
				if(pixels > 0){
					pixels--;
				}else{
					pixels++;
				}
			}
		}
		updateTurtleImage(turtle); // update image at very end
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

	public ArrayList<Turtle> getTurtles() {
		return turtleList;
	}

}
