package slogo_front;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import slogo_back.Model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Manager{
	
	/**
	 * TODO: Please have all methods that back-end calls have "Number" as the
	 * return type. If there's nothing to return, simply return null.
	 */
	private View view;
	// canvas

	public static final int DEFAULT_DISPLAY = 0;
	private ArrayList<Integer> displayIndex = new ArrayList<>();
//	private ArrayList<Integer> activeDisplayIndex = new ArrayList<>();
	private IntegerProperty activeDisplayIndex = new SimpleIntegerProperty();
//	private Display currentDisplay;
	private Model model;

	Manager(View defaultView) {
		activeDisplayIndex.set(DEFAULT_DISPLAY);
		view = defaultView;
//		currentDisplay = view.getActiveDisplay();

		defaultView.setManager(this);
		//set model language and references
		model = new Model("resources/languages/English.properties");
		model.setManager(this);
		//handlers from manager to view
		initializeViewHandlers();
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
			Class<?>[] param_types = new Class<?>[2];
//			param_types[0] = Integer.TYPE;
			param_types[0] = Turtle.class;
			param_types[1] = double[].class;
			toRun = Display.class.getDeclaredMethod(methodName, param_types); // TODO modify all methods in display
			returnValue = (Double) toRun.invoke(view.getActiveDisplay(), view.getActiveDisplay().getTurtles().get(0), params); //change to arraylist of turtles 
			// all methods take in displayID, array of turtles, parameters (int display, Turtle turtle, double[] params)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	
//	private Turtle[] getTurtles(int [] turtleID){ // TODO make more efficient
//		Turtle[] turtleList = new Turtle[turtleID.length];
//		for(int i = 0 ; i < turtleID.length; i++){
//			turtleList[i] = turtles.get(i);
//		}
//		return turtleList;
//	}
	
//	private int checkDisplays(int[] turtleID){ // TODO make more efficient
//		int display = turtles.get(turtleID[0]).getDisplayID();
//		for(int i : turtleID){
//			if (turtles.get(i).getDisplayID() != display){
//				return -1;
//			}
//		}
//		return display;
//	}
	
	/*
	 * BACKEND AND FRONTEND API INTEGRATION
	 * generic signature: list of turtles, list of double parameters (degrees, heading, x/y coordinates ,etc)
	 */
	/*
//	 public double home(double[] var){
//		 return display.home(turtle);
//	 }
//	
//	 public double hideTurtle(double[] var){
//		 return display.hide(turtle);
//	 }
//	
//	 public double showTurtle(double[] var){
//		 return display.show(turtle);
//	 }
//	
//	 public double penUp(double[] var){
//		 return display.penUp(turtle);
//	 }
//	
//	 public double penDown(double[] var){
//		 return display.penDown(turtle);
//	 }
//	
//	 public double setXY(double[] pos){
//		 return display.setXY(turtle, pos[0], pos[1]);
//	 }
//	
//	 public double towards(double[] pos){
//		 return display.towards(turtle, pos[0], pos[1]);
//	 }
//	
//	 public double setHeading(double[] degrees){
//		 return display.setHeading(turtle, degrees[0]);
//	 }
//	
//	 public double right(double[] degrees){
//		 return display.right(turtle, degrees[0]);
//	 }
//	
//	 public double left(double[] degrees){
//		 return display.left(turtle, degrees[0]);
//	 }
//	
//	 public double back(double[] pixels){
//		 return display.back(turtle, pixels[0]);
//	 }
//	
//	 public double forward(double[] pixels){
//		 return display.forward(turtle, pixels[0]);
//	 }
//	
//	 public double xCor(double[] var){
//		 return display.xCor(turtle);
//		 
//	 }
//	
//	 public double yCor(double[] var){
//		 return display.yCor(turtle);
//	 }
//	
//	 public double isPenDown(double[] var){
//		 return display.isPenDown(turtle);
//	 }
//	
//	 public double isShowing(double[] var){
//		 return display.showing(turtle);
//	 }
//<<<<<<< HEAD
//	 */
//	
//	public Turtle getTurtle(){
//		 return turtle;
//=======
	 
	 public Turtle getTurtle(){
		return null;
//		 return turtle;
//>>>>>>> 691e92c581de2438aabe7e3df2cae5624cb27030
	 }
	 
	 public Model getModel(){
		 return model;
	 }

	private void initializeViewHandlers() {
		// setting handlers
//		Turtle turtle = new Turtle(xCanvas/2, yCanvas/2, 0, 0,0, Color.BLACK, turtleCount,
//				"/images/turtle_small.png", true, true);
//		turtles.add(turtle);
		view.setCommandLine(parse);
		view.setCommandHistory(historyEvent);
//		display.show(0, turtle, new double[] {});
		
//		view.setMoveForward(forwardEvent);
	}
	
	private EventHandler<MouseEvent> historyEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// // TODO turtle within display or in view? think about allowances
			// for
			// multiple turtles
			String s = (String) view.getCommandHistory().getCommandList().getSelectionModel()
					.getSelectedItem();
			s = s.substring(3, s.length()); // remove ">> "
			System.out.println(s);

			if (s.toLowerCase().equals("clear")) {
				view.getCommandHistory().resetHistory();
			}
			// addHistoryText(s);

			// TODO throw error here
			// try{
			List<Double> results = view.getManager().getModel().toBack(
					s.toLowerCase() + "\n");
			for (Double value : results) {
				System.out.println(value);
			}
			// }catch(InvalidParameterException e){

			// }
		}
	};
	
	private EventHandler<KeyEvent> parse = new EventHandler<KeyEvent>() {
		public void handle(KeyEvent event) {
			String parse;
			KeyCode keyCode = event.getCode();
			if (keyCode == KeyCode.ENTER) {
				parse = view.getCommandLine().getText();
				if (parse.toLowerCase().equals("clear")) {
					view.getCommandHistory().resetHistory();
				}
//				view.addHistoryText(parse);
				view.getCommandHistory().addHistoryText(parse);
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
