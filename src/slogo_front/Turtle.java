package slogo_front;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * class for Turtle object + properties
 */
public class Turtle extends Turtleable{
	private static final int defaultHeading = 0;
	private int myID;
	private double xPosition;
	private double yPosition;
	private double heading; // from 0-360
	private Color penColor = Color.BLACK;
	private double penWidth = 1;
	private boolean penDown = true;
	private boolean showTurtle = true;
	private ImageView turtleImage;
	private Display myDisplay;
	private double maxCanvasWidth;
	private double maxCanvasHeight;
	private double minCanvasWidth;
	private double minCanvasHeight;
	
	/**
	 * basic constructor for Turtle object
	 * @param x
	 * @param y
	 * @param direction
	 * @param turtleID, display
	 * @param color
	 * @param imageURL
	 * @param leaveTrail
	 * @param showTurtleImage
	 */
	
	protected Turtle(double x, double y, float direction, int id,
			String imageURL, Display display){
		xPosition = x;
		yPosition = y;
		myID = id;
		heading = direction;
		turtleImage = getImageFromURL(imageURL);
		turtleImage.setVisible(false);
		myDisplay = display;
		maxCanvasWidth = myDisplay.getMaxCanvasWidth();
		maxCanvasHeight = myDisplay.getMaxCanvasHeight();
		minCanvasWidth = myDisplay.getMinCanvasWidth();
		minCanvasHeight = myDisplay.getMinCanvasHeight();
	}

	
	// getters
	protected int getID(){
		return myID;
	}
	
	
	protected double getXloc() {
		return xPosition;
	}

	protected double getYloc() {
		return yPosition;
	}
	
	protected double getHeading(){
		return heading;
	}
	
	protected double getAdjustedHeading(){
		// heading for image
		return 360-heading;
	}
	
	protected Color getPenColor(){
		return penColor;
	}
	
	protected double getPenWidth(){
		return penWidth;
	}
	
	protected boolean getPenDown(){
		return penDown;
	}
	
	protected boolean getShowTurtle(){
		return showTurtle;
	}
	
	protected ImageView getTurtleImage(){
		return turtleImage;
	}
	
	// setters
	protected void setXPosition(double x){
		xPosition = x;
	}
	protected void setYPosition(double y){
		yPosition = y;
	}
	
	protected double setHeading(double direction){
		double newHeading = direction;
		newHeading %= 360;
		heading = direction;
		myDisplay.updateTurtleImage(this);
		return Math.abs(heading - newHeading);
		
	}
	
	public void setPenColor(Color color){
		penColor = color;
	}
	
	protected void setPenWidth(double width){
		penWidth = width;
	}
	
	protected void setPenDown(Boolean leaveTrail){
		penDown = leaveTrail;
	}
	
	protected void setShowTurtle(Boolean showTurtleImage){
		showTurtle = showTurtleImage;
		turtleImage.setVisible(showTurtleImage);
	}
	
	public void setImage(String imageURL){
		turtleImage = getImageFromURL(imageURL);
	}
	

	
	/**
	 * takes location of image, retrieves and converts to imageview
	 * @param imageURL
	 * @return
	 */
	private ImageView getImageFromURL(String imageURL){
		ImageView i = new ImageView(new Image(getClass()
				.getResourceAsStream(imageURL)));
		return i;
	}


//	protected void forward(double pixel) {
//		moveTurtle(pixel);
//	}
//
//
//	protected void back(double pixel) {
//		moveTurtle(-1*pixel);
//		
//	}
	
	protected void moveTurtle(double pixels) {
		double tempHeading = heading;
		if (pixels < 0) {
			tempHeading += 180;
			pixels *= -1;
		}
		while (pixels != 0) {
			double xDistance = getXDistance(tempHeading, 1);
			double yDistance = getYDistance(tempHeading, 1);
			double x = xPosition;
			double y = yPosition;
			double x2 = x + xDistance;
			double y2 = y - yDistance;
			if (getOffScreen(x2, y2)) { // next pixel is off screen
				x = (x2 + maxCanvasWidth) % maxCanvasWidth;
				y = (y2 + maxCanvasHeight) % maxCanvasHeight;
				setXPosition(x);
				setYPosition(y);
			} else { // move turtle if next pixel is on screen
				if (penDown) {
					myDisplay.paintLine(this, x, y, x2, y2);
				}
				setXPosition(x2);
				setYPosition(y2);
				pixels--;
			}
		}
		myDisplay.updateTurtleImage(this); // update image at very end
	}
	
	
	private boolean getOffScreen(double x, double y) {
		return ((x > maxCanvasWidth) | (x < minCanvasWidth)
				| (y > maxCanvasHeight) | (y < minCanvasHeight));
	}
	
	/**
	 * gets distance along x axis between 2 points
	 * 
	 * @param heading
	 * @param pixels
	 * @return
	 */
	private double getXDistance(double heading, double pixels) {
		return pixels * Math.cos(Math.toRadians(heading));
	}
	
	/**
	 * gets distance along y axis between 2 points
	 * 
	 * @param heading
	 * @param pixels
	 * @return
	 */
	private double getYDistance(double heading, double pixels) {
		return pixels * Math.sin(Math.toRadians(heading));
	}


	@Override
	protected double home() {
		double distance = setXY(maxCanvasWidth/2,maxCanvasHeight/2);
		setXPosition(maxCanvasWidth/2);
		setYPosition(maxCanvasHeight/2);
		setHeading(defaultHeading);
		myDisplay.updateTurtleImage(this);
		return distance;
		
	}


	@Override
	protected double setXY(double x, double y) {
		// TODO Auto-generated method stub
		double distance = getDistance(xPosition, yPosition, x, y);
		setXPosition(x + maxCanvasWidth/2);
		setYPosition(maxCanvasHeight/2 - y);
		myDisplay.updateTurtleImage(this);
		return distance;
	}
	
	private double getDistance(double x1, double y1, double x2, double y2) {
		double xDiff = Math.abs(x2 - x1);
		double yDiff = Math.abs(y2 - y1);
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}


	@Override
	protected double left(double degree) {
		double newHeading = getHeading() + degree;
		newHeading %= 360;
		setHeading(newHeading);
		myDisplay.updateTurtleImage(this);
		return degree;
	}


	@Override
	protected double right(double degree) {
		double newHeading = getHeading() - degree;
		newHeading %= 360;
		setHeading(newHeading);
		myDisplay.updateTurtleImage(this);
		return degree;
	}


	@Override
	protected double towards(double x, double y) {
		double oldHeading = getHeading();
		double xTranslated = x + maxCanvasWidth;
		double yTranslated = maxCanvasHeight - y;
		double newHeading = calculateDirection(xTranslated - getXloc(),
				getYloc() - yTranslated);
		setHeading(newHeading);
		myDisplay.updateTurtleImage(this);
		return Math.abs(oldHeading - newHeading);
	}
	
	/**
	 * calculates direction of turtle (adjusts for arctan values)
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private double calculateDirection(double x, double y) {
		double rawAngle = Math.toDegrees(Math.atan(y / x));
		System.out.println(rawAngle);
		if (x < 0) {
			rawAngle += 180; // second & third quadrant
		}
		if (y < 0) {
			rawAngle += 360; // fourth quadrant
		}
		return rawAngle;
	}


	@Override
	protected double stamp() {
		// TODO Auto-generated method stub
		GraphicsContext stamp = myDisplay.getStampBackground().getGraphicsContext2D();
		

		double widthAdj = turtleImage.getImage().getWidth() / 2;
		double heightAdj = turtleImage.getImage().getHeight() / 2;
		
		stamp.drawImage(turtleImage.getImage(), xPosition - widthAdj, yPosition - heightAdj);
		return myID;
	}


	protected void clearStamp() {
		GraphicsContext stamp = myDisplay.getStampBackground().getGraphicsContext2D();
		stamp.clearRect(minCanvasWidth, minCanvasHeight, maxCanvasWidth, maxCanvasHeight);
		
	}



}
