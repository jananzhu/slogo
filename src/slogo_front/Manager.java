package slogo_front;

import java.lang.reflect.Method;
import java.util.ArrayList;
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
	private ArrayList<Turtle> turtles = new ArrayList<>();
	private int turtleCount = 1;
	Turtle turtle = new Turtle(xCanvas/2, yCanvas/2, 0, 0, Color.BLACK, 1,1,
			"/images/turtle_small.png", true, true);
	Model model;

	Manager(View defaultView) {
		view = defaultView;
		display = view.getDisplay();
		defaultView.setManager(this);
		
		model = new Model("resources/languages/English.properties");
		model.setManager(this);
		initialize();
		// turtle = new Turtle(0,0,0, )
	}
	
	/**
	 * reflection to GUI (for methods in display)
	 * TODO make helper methods more efficient (remove loops)
	 * @param methodName
	 * @param turtleID
	 * @param params
	 * @return
	 */
	public Double toGUI(String methodName, int[] turtleID, double[] params){
		// add getter for turtles from turtleID & display from turtle ID
		
		Method toRun;
		Double returnValue = null;
		try {
			toRun = Display.class.getMethod(methodName, Turtle[].class, double[].class); // TODO modify all methods in display
			returnValue = (Double) toRun.invoke(checkDisplays(turtleID),turtles.get(turtleID[0]), params); //change to arraylist of turtles 
			// all methods take in displayID, array of turtles, parameters (int display, Turtle turtle, double[] params)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	private Turtle[] getTurtles(int [] turtleID){ // TODO make more efficient
		Turtle[] turtleList = new Turtle[turtleID.length];
		for(int i = 0 ; i < turtleID.length; i++){
			turtleList[i] = turtles.get(i);
		}
		return turtleList;
	}
	
	private int checkDisplays(int[] turtleID){ // TODO make more efficient
		int display = turtles.get(turtleID[0]).getDisplayID();
		for(int i : turtleID){
			if (turtles.get(i).getDisplayID() != display){
				return -1;
			}
		}
		return display;
	}
	
	/*
	 * BACKEND AND FRONTEND API INTEGRATION
	 * generic signature: list of turtles, list of double parameters (degrees, heading, x/y coordinates ,etc)
	 */
	 public double home(double[] var){
		 return display.home(turtle);
	 }
	
	 public double hideTurtle(double[] var){
		 return display.hide(turtle);
	 }
	
	 public double showTurtle(double[] var){
		 return display.show(turtle);
	 }
	
	 public double penUp(double[] var){
		 return display.penUp(turtle);
	 }
	
	 public double penDown(double[] var){
		 return display.penDown(turtle);
	 }
	
	 public double setXY(double[] pos){
		 return display.setXY(turtle, pos[0], pos[1]);
	 }
	
	 public double towards(double[] pos){
		 return display.towards(turtle, pos[0], pos[1]);
	 }
	
	 public double setHeading(double[] degrees){
		 return display.setHeading(turtle, degrees[0]);
	 }
	
	 public double right(double[] degrees){
		 return display.right(turtle, degrees[0]);
	 }
	
	 public double left(double[] degrees){
		 return display.left(turtle, degrees[0]);
	 }
	
	 public double back(double[] pixels){
		 return display.back(turtle, pixels[0]);
	 }
	
	 public double forward(double[] pixels){
		 return display.forward(turtle, pixels[0]);
	 }
	
	 public double xCor(double[] var){
		 return display.xCor(turtle);
		 
	 }
	
	 public double yCor(double[] var){
		 return display.yCor(turtle);
	 }
	
	 public double isPenDown(double[] var){
		 return display.isPenDown(turtle);
	 }
	
	 public double isShowing(double[] var){
		 return display.showing(turtle);
	 }
	 
	 public Turtle getTurtle(){
		 return turtle;
	 }
	 
	 public Model getModel(){
		 return model;
	 }

	private void initialize() {
		// setting handlers
		Turtle turtle = new Turtle(xCanvas/2, yCanvas/2, 0, 0,0, Color.BLACK, turtleCount,
				"/images/turtle_small.png", true, true);
		turtles.add(turtle);
		view.setCommandLine(parse);
//		view.setMoveForward(forwardEvent);
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

				// TODO throw error here
//				try{
					List<Double> results = model.toBack(parse.toLowerCase() + "\n");
					for (Double value : results) {
						System.out.println(value);
					}
//				}catch(InvalidParameterException e){
					
//				}
			}

		}
	};
	// TODO add these methods
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
//	private EventHandler<MouseEvent> forwardEvent = new EventHandler<MouseEvent>() {
//		public void handle(MouseEvent event) {
//			// TODO turtle within display or in view? think about allowances for
//			// multiple turtles
//			display.forward(turtle, 100);
//		}
//
//	};

}
