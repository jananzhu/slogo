package slogo_front;



import java.util.List;

import slogo_back.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Manager {
	
	/**
	 * TODO: Please have all methods that back-end calls have "Number" as the return type. 
	 * If there's nothing to return, simply return null.
	 */
	View view;
	//canvas
	Display display;
	Turtle turtle;
	Model model;
	
	Manager(View defaultView){
		view = defaultView;
		display = view.getDisplay();
		model = new Model("resources/languages/English.properties");
		model.setManager(this);
		initialize();
//		turtle = new Turtle(0,0,0, )
	}
	//direct API between front and backend
//	public double home(){
//		return display.home();
//	}
//	
//	public double hideTurtle(){
//		return display.showTurtle(turtle, showTurtle)
//	}
//	
//	public double showTurtle(){}
//	
//	public double penUp(){}
//	
//	public double penDown(){}
//	
//	public double setXY(double x, double y){}
//	
//	public double towards(double x, double y){}
//	
//	public double setHeading(double degree){}
//	
//	public double right(double degree){}
//	
//	public double left(double degree){}
//	
//	public double back(double pixels){}
//	
//	public double forward(double pixels){}
//	
//	public double xCor(){}
//	
//	public double yCor(){}
//	
//	public double isPenDown(){}
//	
//	public double isPenUp(){}
//	
//	public double isShowing(){}

	private void initialize() {
		//setting handlers
		view.setCommandLine(parse);
	}
	
//	private EventHandler parse = new EventHandler<KeyEvent>() {
//	    public void handle(KeyEvent event) {
//	    		
//	    		view.
//	    }
//	
//	};
	
	private EventHandler<KeyEvent> parse = new EventHandler<KeyEvent>()
			{
				public void handle(KeyEvent event)
				{
					String parse;
					KeyCode keyCode = event.getCode();
					if (keyCode == KeyCode.ENTER) {
						parse = view.commandLineText();
						if(parse.toLowerCase().equals("clear")){
							view.clearHistoryText();
						}
						view.addHistoryText(parse);
						System.out.println(parse);
						
						//testing model methods
						List<Double> results = model.toBack(parse + "\n");
						for (Double value : results) {
							System.out.println(value);
						}
					}
					
				}
			};

}
