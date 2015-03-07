package view_panels;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import control_buttons.SlogoButton;
import control_buttons.SlogoColorPicker;
import control_buttons.SlogoSlider;
import control_buttons.SlogoVariableList;
import slogo_front.Display;
import slogo_front.Turtle;
import slogo_front.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ControlPanel {
	ResourceBundle labels;
	Locale[] supportedLocales = { Locale.ENGLISH, Locale.FRENCH };
	Locale defaultLocale = Locale.ENGLISH;
	// Display reference
	Display currentDisplay;
	Turtle currentTurtle;
	// Button UI elements
	private VBox controlPanel;
	private static final String CSS = "controlPanel";
	private SlogoButton clearScreen;
	private SlogoButton clearCommandHistory;
	private SlogoButton moveForward;
	private SlogoButton moveBackward;
	private SlogoButton turnTurtle;
	private SlogoSlider turnDegree;
	private SlogoColorPicker turtleColor;
	private SlogoButton setColor;
	private SlogoButton setPen;
	private SlogoButton setTurtleImage;
	// variable list objects
	private SlogoVariableList varList;
	// what is this thing?
	private ObservableList<String> variableItems;
	
	private View view;

	public ControlPanel(double offSet, double width) {
		labels = ResourceBundle.getBundle("resources.languages/LabelsBundle",
				defaultLocale);

		makeControlPanel(offSet, width);

		clearScreen = new SlogoButton(Double.MAX_VALUE, clear, controlPanel);
		clearCommandHistory = new SlogoButton(Double.MAX_VALUE, clearHistory,
				controlPanel);
		moveForward = new SlogoButton(Double.MAX_VALUE, forwardEvent,
				controlPanel);
		moveBackward = new SlogoButton(Double.MAX_VALUE, backwardEvent,
				controlPanel);

		// //turn turtle
		turnDegree = new SlogoSlider(-180, 180, controlPanel);
		turnTurtle = new SlogoButton(Double.MAX_VALUE, turnEvent, controlPanel);
		//
		turtleColor = new SlogoColorPicker(Double.MAX_VALUE, controlPanel);

		setColor = new SlogoButton(Double.MAX_VALUE, changeBackground,
				controlPanel);
		setPen = new SlogoButton(Double.MAX_VALUE, changePenColor, controlPanel);
		setTurtleImage = new SlogoButton(Double.MAX_VALUE, changeTurtleImage,
				controlPanel);

		varList = new SlogoVariableList(controlPanel);

		setLabels();
	}

	public VBox getControlpanel() {
		return controlPanel;
	}

	private void makeControlPanel(double offSet, double width) {
		controlPanel = new VBox(offSet);
		controlPanel.setMaxWidth(width);
		controlPanel.getStyleClass().add(CSS);
	}

	public void setActiveDisplay(Display display) {
		display = currentDisplay;
	}

	private void setLabels() {
		// TODO cound set text in the button class
		clearScreen.getButton().setText(labels.getString("CLEAR"));
		clearCommandHistory.getButton().setText(
				labels.getString("CLEARHISTORY"));
		moveForward.getButton().setText(labels.getString("FORWARD"));
		moveBackward.getButton().setText(labels.getString("BACKWARD"));
		turnTurtle.getButton().setText(labels.getString("TURN"));
		setColor.getButton().setText(labels.getString("BACKGROUND"));
		setPen.getButton().setText(labels.getString("PEN"));
		setTurtleImage.getButton().setText(labels.getString("IMAGE"));
	}

	// HANDLERS.. DONT TOUCH
	private EventHandler<MouseEvent> changeTurtleImage = new EventHandler<MouseEvent>() {
		//TODO need to allow for multiple turtles
		public void handle(MouseEvent event) {
			 Stage fileSystem = new Stage();
			 FileChooser fileChooser = new FileChooser();
			 fileChooser.setTitle("Open Resource File");
			 File file = fileChooser.showOpenDialog(fileSystem);
			 // some method to change the imageview in display
			 String filePath = "C:"+file.getPath();
			 int index = filePath.indexOf("/images");
			 Turtle turtle = view.getActiveDisplay().getTurtles().get(0);
			 view.getActiveDisplay().hide(turtle);
			 view.getActiveDisplay().getTurtles().get(0).setImage(filePath.substring(index));
			 view.getActiveDisplay().show(turtle);
		}

	};

	private EventHandler<MouseEvent> clear = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
//			 currentDisplay.clearScreen(currentTurtle);
//			 addHistoryText("clearscreen");
		}

	};

	private EventHandler<MouseEvent> clearHistory = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// resetHistory();
		}

	};

	private EventHandler<MouseEvent> changeBackground = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// display.changeBackground(turtleColor.getValue());
		}

	};

	private EventHandler<MouseEvent> turnEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
			// double degree = turnDegree.getValue();
			// display.setHeading(manager.getTurtle(), degree);
			// if(degree >= 0){
			// addHistoryText("left " + (int) degree);
			// }else{
			// addHistoryText("right " + (int) Math.abs(degree));
			// }
		}

	};

	private EventHandler<MouseEvent> changePenColor = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// manager.getTurtle().setPenColor(turtleColor.getValue());

		}

	};

	private EventHandler<MouseEvent> forwardEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
			// display.forward(manager.getTurtle(), 100);
			// addHistoryText("forward 100");
		}

	};

	private EventHandler<MouseEvent> backwardEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
			// display.back(manager.getTurtle(), 100);
			// addHistoryText("backward 100");
		}

	};

	public void setView(View v) {
		// TODO Auto-generated method stub
		view = v;
		
	}

}
