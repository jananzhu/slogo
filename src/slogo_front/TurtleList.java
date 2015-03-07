package slogo_front;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

//keeps a list of turtles
public class TurtleList extends Turtleable {
	private Display myDisplay;
	private ArrayList<Turtle> turtles = new ArrayList<Turtle>();
	
	
	public TurtleList(Display display) {
		myDisplay = display;
	}

	protected int getID() {
		return getLastTurtle().getID();
	}

	@Override
	protected double getXloc() {
		return getLastTurtle().getXloc();
	}

	@Override
	protected double getYloc() {
		// TODO Auto-generated method stub
		return getLastTurtle().getYloc();
	}

//	@Override
//	protected double getHeading() {
//		// TODO Auto-generated method stub
//		
//		return getLastTurtle().getHeading();
//	}
//
//	@Override
//	protected double getAdjustedHeading() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	protected Color getPenColor() {
		// TODO Auto-generated method stub
		return getLastTurtle().getPenColor();
	}

	@Override
	protected double getPenWidth() {
		// TODO Auto-generated method stub
		return getLastTurtle().getPenWidth();
	}

	@Override
	protected boolean getPenDown() {
		// TODO Auto-generated method stub
		return getLastTurtle().getPenDown();
	}

	@Override
	protected boolean getShowTurtle() {
		// TODO Auto-generated method stub
		return getLastTurtle().getShowTurtle();
	}

	@Override
	protected ImageView getTurtleImage() {
		// TODO Auto-generated method stub
		return getLastTurtle().getTurtleImage();
	}

//	@Override
//	protected void setXPosition(double x) {
//		// TODO Auto-generated method stub
//
//	}

//	@Override
//	protected void setYPosition(double y) {
//		// TODO Auto-generated method stub
//
//	}

//	@Override
//	protected void setDisplayID(int id) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	protected double setHeading(double direction) {
		double distance = getLastTurtle().setHeading(direction);
		turtles.forEach(turtle-> turtle.setHeading(direction));
		return distance;

	}

	@Override
	public void setPenColor(Color color) {
		turtles.forEach(turtle->turtle.setPenColor(color));

	}

	@Override
	protected void setPenWidth(double width) {
		turtles.forEach(turtle->turtle.setPenWidth(width));

	}

	@Override
	protected void setPenDown(Boolean leaveTrail) {
		turtles.forEach(turtle->turtle.setPenDown(leaveTrail));

	}

	@Override
	protected void setShowTurtle(Boolean showTurtleImage) {
		turtles.forEach(turtle-> turtle.setShowTurtle(showTurtleImage));

	}

	@Override
	public void setImage(String imageURL) {
		turtles.forEach(turtle -> turtle.setImage(imageURL));

	}

	@Override
	protected void moveTurtle(double pixel) {
		turtles.forEach(turtle -> turtle.moveTurtle(pixel));

	}
	
	private Turtle getLastTurtle(){
		return turtles.get(turtles.size()-1);
	}
	
	protected int size(){
		return turtles.size();
	}

	protected void add(Turtle turtle) {
		turtles.add(turtle);
		
	}
	
	protected ArrayList<Turtle> getTurtleList(){
		return turtles;
	}

	@Override
	protected double home() {
		//TODO return 
		double distance = getLastTurtle().home();
		turtles.forEach(turtle -> turtle.home());
		return distance;
		
	}

	@Override
	protected double setXY(double x, double y) {
		double distance = getLastTurtle().setXY(x, y);
		turtles.forEach(turtle->turtle.setXY(x, y));
		return distance;
		
	}

	@Override
	protected double left(double degree) {
		turtles.forEach(turtle -> turtle.left(degree));
		return degree;
	}

	@Override
	protected double right(double degree) {
		turtles.forEach(turtle -> turtle.right(degree));
		return degree;
	}

	@Override
	protected double towards(double x, double y) {
		double distance = getLastTurtle().towards(x, y);
		turtles.forEach(turtle->turtle.towards(x,y));
		return distance;
	}

}
