package slogo_front;

import java.util.List;

import slogo_back.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Manager {

	/**
	 * TODO: Please have all methods that back-end calls have "Number" as the
	 * return type. If there's nothing to return, simply return null.
	 */
	View view;
	// canvas
	Display display;
	private static final int xCanvas = 1000;
	private static final int yCanvas = 600;
	
	Turtle turtle = new Turtle(xCanvas/2, yCanvas/2, 0, 0, Color.BLACK, 1,
			"/images/turtle_small.png", true, true);
	Model model;

	Manager(View defaultView) {
		view = defaultView;
		display = view.getDisplay();
		model = new Model("resources/languages/English.properties");
		model.setManager(this);
		initialize();
		// turtle = new Turtle(0,0,0, )
	}

	// direct API between front and backend
	 public double home(){
		 return display.home(turtle);
	 }
	
	 public double hideTurtle(){
		 return display.hide(turtle);
	 }
	
	 public double showTurtle(){
		 return display.show(turtle);
	 }
	
	 public double penUp(){
		 return display.penUp(turtle);
	 }
	
	 public double penDown(){
		 return display.penDown(turtle);
	 }
	
	 public double setXY(double x, double y){
		 return display.setXY(turtle, x, y);
	 }
	
	 public double towards(double x, double y){
		 return display.towards(turtle, x, y);
	 }
	
	 public double setHeading(double degrees){
		 return display.setHeading(turtle, degrees);
	 }
	
	 public double right(double degrees){
		 return display.right(turtle, degrees);
	 }
	
	 public double left(double degrees){
		 return display.left(turtle, degrees);
	 }
	
	 public double back(double pixels){
		 return display.back(turtle, pixels);
	 }
	
	 public double forward(double pixels){
		 return display.forward(turtle, pixels);
	 }
	
//	 public double xCor(){
////		 return turtle.getXloc();
//		 //being implemented rightnow
//		 
//	 }
	
//	 public double yCor(){}
	
//	 public double isPenDown(){
//		 return display.penDown(turtle);
//	 }
	
//	 public double isPenUp(){
//		 return display.penUp(turtle);
//	 }
//	
//	 public double isShowing(){
//		 return turtle.getShowTurtle();
//	 }

	private void initialize() {
		// setting handlers
		view.setCommandLine(parse);
		view.setMoveForward(forwardEvent);
	}

	// private EventHandler parse = new EventHandler<KeyEvent>() {
	// public void handle(KeyEvent event) {
	//
	// view.
	// }
	//
	// };

	private EventHandler<KeyEvent> parse = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			String parse;
			KeyCode keyCode = event.getCode();
			if (keyCode == KeyCode.ENTER) {
				parse = view.commandLineText();
				if (parse.toLowerCase().equals("clear")) {
					view.clearHistoryText();
				}
				view.addHistoryText(parse);
				System.out.println(parse);

				// testing model methods
				List<Double> results = model.toBack(parse + "\n");
				for (Double value : results) {
					System.out.println(value);
				}
			}

		}
	};

	private EventHandler<MouseEvent> forwardEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
			display.forward(turtle, 100);
		}

	};

}
