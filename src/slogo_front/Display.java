package slogo_front;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * currently includes methods to move turtle NOTE: API (protected) methods
 * except constructor take x & y coordinates with (0,0) in the middle of the
 * screen
 *
 */
public class Display {
	// References the middle of the canvas
	private int turtleID = 0;
	private double xOrigin;
	private double yOrigin;
	// Outer bounds of the display
	private double maxCanvasWidth;
	private double minCanvasWidth = 0;
	private double maxCanvasHeight;
	private double minCanvasHeight = 0;
	// instances of canvas and graphics context
	private Pane overlay;
	private Canvas canvas;
	private Canvas background;
	private Canvas stamp;
	private GraphicsContext graphics;
	private static final int defaultHeading = 0;
	private double defaults[] = { xOrigin, yOrigin, defaultHeading };
	private double[] origins = { xOrigin, yOrigin };
	// turtle array
	//list of all turtles
	// private ArrayList<Turtle> turtleList = new ArrayList<>();
	private TurtleList turtleList;

	// display id

	/**
	 * basic constructor
	 * 
	 * @param xDimension
	 * @param yDimension
	 * @param myCanvas
	 */
	public Display(double canvasWidth, double canvasHeight) {
		turtleList = new TurtleList(this);
		overlay = new Pane();
		canvas = new Canvas(canvasWidth, canvasHeight);
		stamp = new Canvas(canvasWidth, canvasHeight);
		background = new Canvas(canvasWidth, canvasHeight);
		overlay.setPrefSize(canvasWidth, canvasHeight);
		overlay.getChildren().addAll(background, stamp, canvas);

		xOrigin = canvasWidth / 2;
		yOrigin = canvasHeight / 2;
		maxCanvasWidth = canvasWidth;
		maxCanvasHeight = canvasHeight;
		graphics = canvas.getGraphicsContext2D();

		background.getGraphicsContext2D().rect(minCanvasWidth, minCanvasHeight,
				maxCanvasWidth, maxCanvasHeight);

		// default turtle initialized in display
		addTurtle(null);

		// test();
	}

	// TODO fix return values

	// queries

	public Node getDisplay() {
		return overlay;
	}

	protected double xCor(double[] params) {
		return turtleList.getXloc() + xOrigin;
	}

	protected double yCor(double[] params) {
		return turtleList.getYloc() + yOrigin;
	}

	protected double showing(double[] params) {
		if (turtleList.getShowTurtle())
			return 1;
		return 0;
	}

	protected double isPenDown(double[] params) {
		if (turtleList.getPenDown())
			return 1;
		return 0;
	}

	// actions

	public void changeBackground(Color backgroundColor) {
		background.getGraphicsContext2D().setFill(backgroundColor);
		background.getGraphicsContext2D().fillRect(minCanvasWidth,
				minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
	}

	/**
	 * erases turtle's trails & sends it to home position returns distance
	 * turtle moved
	 * 
	 * @param turtle
	 * @return
	 */
	public double clearScreen(double[] params) {
		graphics.clearRect(minCanvasWidth, minCanvasHeight, maxCanvasWidth,
				maxCanvasHeight);
		
//		double distance = getDistance(turtleList.getXloc(), turtleList.getYloc(),
//				xOrigin, yOrigin);
//		home(params);
		return turtleList.home();
	}

	/**
	 * puts pen down
	 * 
	 * @param turtle
	 * @return
	 */
	protected double penDown(double[] params) {
		turtleList.setPenDown(true);
		return 1;
	}

	/**
	 * puts pen up
	 * 
	 * @param turtle
	 * @return
	 */
	protected double penUp(double[] params) {
		turtleList.setPenDown(false);
		return 0;
	}

	public double show(double[] params) {
		turtleList.setShowTurtle(true);
		return 1;
	}

	public double hide(double[] params) {
		turtleList.setShowTurtle(false);
		return 0;
	}

	/**
	 * updates turtle image to reflect changes in turtle
	 * 
	 * @param turtle
	 */
	protected void updateTurtleImage(Turtle turtle) {
		ImageView turtleImage = turtle.getTurtleImage();
		turtleImage.setRotate(turtle.getAdjustedHeading());
		double widthAdj = turtleImage.getImage().getWidth() / 2;
		double heightAdj = turtleImage.getImage().getHeight() / 2;
		turtleImage.setTranslateX(turtle.getXloc() - widthAdj);
		turtleImage.setTranslateY(turtle.getYloc() - heightAdj);
	}

	/**
	 * turns turtle counterclockwise returns value of degrees
	 * 
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double left(double[] params) {
		turtleList.left(params[0]);
		return params[0];
	}

	/**
	 * turns turtle clockwise returns value of degrees
	 * 
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double right(double[] params) {
		turtleList.right(params[0]);
		return params[0];
	}

	/**
	 * turns turtle to an absolute heading returns number of degrees moved
	 * 
	 * @param turtle
	 * @param degrees
	 * @return
	 */
	protected double setHeading(double[] params) {
		return turtleList.setHeading(params[0]);
		
	}

	/**
	 * moves turtle to absolute screen position returns distance turtle moved
	 * 
	 * @param turtle
	 * @param x
	 * @param y
	 * @return
	 */
	protected double setXY(double[] params) {
		return turtleList.setXY(params[0], params[1]);
	}

	/**
	 * moves turtle to center of screen returns distance turtle moved
	 * 
	 * @param turtle
	 * @return
	 */
	protected double home(double[] params) {
		return turtleList.home();
	}

	/**
	 * moves turtle forward in its current heading returns value of pixels
	 * 
	 * @param turtle
	 * @param pixels
	 * @return
	 */
	protected double forward(double[] params) {
		// moveTurtle(turtle, params[0], turtle.getPenDown());
		turtleList.moveTurtle(params[0]);
		return params[0];
	}

	/**
	 * moves turtle backward in its current heading returns value of pixels
	 * 
	 * @param turtle
	 * @param pixels
	 * @return
	 */
	protected double back(double[] params) {
//		moveTurtle(turtle, params[0] * -1, turtle.getPenDown());
		turtleList.moveTurtle(params[0] * -1);
		return params[0];
	}

	/**
	 * turns turtle to face the point (x,y) returns number of degrees turtle
	 * turned
	 * 
	 * @param turtle
	 * @param x
	 * @param y
	 * @return
	 */
	protected double towards(double[] params) {
		return turtleList.towards(params[0], params[1]);
	}


	protected void paintLine(Turtle t, double x1, double y1, double x2,
			double y2) {
		graphics.setStroke(t.getPenColor());
		graphics.setLineWidth(t.getPenWidth());
		graphics.strokeLine(x1, y1, x2, y2);
	}


	public TurtleList getTurtles() {
		return turtleList;
	}

	protected double getMaxCanvasWidth() {
		return maxCanvasWidth;
	}

	protected double getMaxCanvasHeight() {
		return maxCanvasHeight;
	}

	protected double getMinCanvasWidth() {
		return minCanvasWidth;
	}

	protected double getMinCanvasHeight() {
		return minCanvasHeight;
	}

	// multiple turtles
	protected void addTurtle(double[] params) {
		// method for control panel
		Turtle turtle = new Turtle(xOrigin, yOrigin, 0, turtleID,
				"/images/turtle_small.png", this);
		turtleID++;
		overlay.getChildren().add(turtle.getTurtleImage());
		turtleList.add(turtle);
		updateTurtleImage(turtle);

	}

	protected double stamp(double[] params){
		return turtleList.stamp();
	}
	
	protected double clearStamps(double[] params){
		turtleList.clearStamp();
		return 0;
	}
	
	protected Canvas getStampBackground(){
		return stamp;
	}
	
	protected void changeImage(ImageView image){
		overlay.getChildren().add(image);
	}
	

}
