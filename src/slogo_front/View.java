package slogo_front;

import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import slogo_back.Model;
import view_panels.CommandHistory;
import view_panels.CommandLine;
import view_panels.ControlPanel;
import view_panels.ToolBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

// TODO please comment this code tonight
/**
 * add comments here
 * 
 *
 */
public class View {

	private Scene scene;
	private Manager manager;
	// components of view
	private BorderPane root;
	private ControlPanel control;
	private CommandHistory history;
	private ToolBar tools;
	private CommandLine commandLine;

	// probably going to remove this instance
	private Display activeDisplay;
	// keeps track of the multiple displays
	private ArrayList<Display> displayList = new ArrayList<>();

	// private VBox displayBackground ;
	// Canvas Dimensions
	private static final int xCanvas = 1000;
	private static final int yCanvas = 600;

	// Command Line Dimenstions
	private static final int COMMAND_HEIGHT = 50;

	// Locale Resources
	ResourceBundle labels;
	Locale[] supportedLocales = { Locale.ENGLISH, Locale.FRENCH };
	Locale defaultLocale = Locale.ENGLISH;

	// TODO add array for turtles
	// TODO add "addTurtle()" method

	public View() {
		// resource bundles
//		activeDisplay = new Display(xCanvas, yCanvas);
//		displayList.add(activeDisplay);

		labels = ResourceBundle.getBundle("resources.languages/LabelsBundle",
				defaultLocale);

		root = new BorderPane();
		control = new ControlPanel(10, 200);
		control.setView(this);
		history = new CommandHistory(200);
		tools = new ToolBar(this);
		commandLine = new CommandLine(COMMAND_HEIGHT);

		root.setTop(tools.getToolBar());
		root.setRight(history.getCommandHistory());
		root.setBottom(commandLine.getCommandLine());
//		root.setCenter(null);
//		Pane placeHolder = new Pane();
		Display placeHolder = new Display(xCanvas, yCanvas);
		placeHolder.changeBackground(Color.GRAY);
		root.setCenter(placeHolder.getDisplay());

		root.setLeft(control.getControlpanel());

		scene = new Scene(root);
		scene.getStylesheets().add("css/view.css");
//		initializeDisplays();
		initializeToolbarHandlers();
		System.out.println(displayList.size());
	}

//	private void initializeDisplays() {
//		
//		
//	}

	// TODO
	private void initializeToolbarHandlers() {
		tools.setMenuPlusHandler(addDisplay);
	}

	public CommandLine getCommandLine() {
		return commandLine;
	}

	public CommandHistory getCommandHistory() {
		return history;
	}

	public Display getActiveDisplay() {
		return activeDisplay;
	}

	// attach new display when active display set
	public void setActiveDisplay(int displayIndex) {
//		if (displayIndex >= displayList.size()) {
//			System.out.println("too big");
//			return;
//		}
		System.out.println("sisze" + displayList.size());
		activeDisplay = displayList.get(displayIndex);
		root.setCenter(activeDisplay.getDisplay());
		return;
	}

	public void createDisplay() {
//		activeDisplay = new Display(xCanvas, yCanvas);
		displayList.add(new Display(xCanvas, yCanvas));
	}

	public Scene getScene() {
		return scene;
	}

	// gets and clears command line text
	// TODO handlers need to be working
	// protected String commandLineText() {
	// return null;
	// // String parse = commandLine.getText();
	// // commandLine.clear();
	// // return parse;
	// }

	// figureout if this should be private somehow
	// protected void addHistoryText(String text) {
	// commandItems.add(">> " + text);
	// commandList.setItems(commandItems);
	// }

	// protected void addVariableText(String variable, double value) {
	// variables.put(variable, value);
	// variableItems.clear();
	// for (String s : variables.keySet()) {
	// variableItems.add(s + ": " + variables.get(s));
	// }
	// varList.setItems(variableItems);
	// }

	// private in the future?
	// protected void clearHistoryText() {
	// history.getChildren().clear();
	// }

	// private Node makeMenu() {
	// menuBar = new MenuBar();
	// // Command Reference Sheet
	// Menu menuHelp = new Menu("Help");
	// MenuItem commandReference = new MenuItem("Command Reference");
	// commandReference.setOnAction(launchWebView);
	// menuHelp.getItems().add(commandReference);
	//
	// // Language
	// Menu menuLanguage = new Menu("Change Language");
	// setLanguages(menuLanguage);
	// // setLanguageListener(menuLanguage);
	// menuBar.getMenus().addAll(menuHelp, menuLanguage);
	// return menuBar;
	//
	// }

	// private void setLanguages(Menu languageMenu) {
	// MenuItem english = new MenuItem("English");
	// english.setOnAction(new EventHandler<ActionEvent>() {
	// public void handle(ActionEvent t) {
	// labels = ResourceBundle
	// .getBundle("resources.languages/LabelsBundle",
	// supportedLocales[0]);
	// //TODO setLabels();
	// }
	// });
	//
	// MenuItem french = new MenuItem("French");
	// french.setOnAction(new EventHandler<ActionEvent>() {
	// public void handle(ActionEvent t) {
	// labels = ResourceBundle
	// .getBundle("resources.languages/LabelsBundle",
	// supportedLocales[1]);
	// //TODO setLabels();
	// }
	// });
	//
	// languageMenu.getItems().addAll(english, french);
	// }

	// private Node makeCommandHistory() {
	// commandList = new ListView<String>();
	// commandList.setMaxWidth(200);
	// resetHistory();
	//
	// return commandList;
	// }

	// private void resetHistory(){
	// commandItems = FXCollections.observableArrayList("Command History");
	// commandList.setItems(commandItems);
	// // commandList.setOnMouseClicked(historyEvent);
	// }

	// set manager
	public void setManager(Manager m) {
		manager = m;
	}

	public Manager getManager() {
		return manager;
	}

	// sets command line
	public void setCommandLine(EventHandler<KeyEvent> handler) {
		commandLine.setCommandHandler(handler);
	}

	public void setCommandHistory(EventHandler<MouseEvent> handler) {
		history.setCommandHandler(handler);
	}

	private EventHandler<ActionEvent> addDisplay = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			MenuItem display = new MenuItem("Display " + displayList.size());
			display.setId("" + displayList.size());
			display.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					System.out.println(Integer.parseInt(display.getId()));
					setActiveDisplay(Integer.parseInt(display.getId()));
				}
			});
			//add menuitem to the menu
			createDisplay();
			tools.addMenuPlusItem(display);
		}
	};

	private EventHandler<MouseEvent> changeTurtleImage = new EventHandler<MouseEvent>() {
		// within manager?
		public void handle(MouseEvent event) {
			// Stage fileSystem = new Stage();
			// FileChooser fileChooser = new FileChooser();
			// fileChooser.setTitle("Open Resource File");
			// File file = fileChooser.showOpenDialog(fileSystem);
			// // some method to change the imageview in display
			// String filePath = "C:"+file.getPath();
			// int index = filePath.indexOf("/images");
			// // System.out.println(index);
			// // System.out.println(filePath);
			// // System.out.println(filePath.substring(index));
			// Turtle turtle = manager.getTurtle();
			// display.hide(turtle);
			// manager.getTurtle().setImage(filePath.substring(index));
			// display.show(turtle);
		}

	};

	private EventHandler<MouseEvent> clear = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// display.clearScreen(manager.getTurtle());
			// addHistoryText("clearscreen");
		}

	};

	// private EventHandler<MouseEvent> clearHistory = new
	// EventHandler<MouseEvent>() {
	// public void handle(MouseEvent event) {
	// resetHistory();
	// }
	//
	// };

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

	// opens window for help page
	// private EventHandler<ActionEvent> launchWebView = new
	// EventHandler<ActionEvent>() {
	// public void handle(ActionEvent event) {
	//
	// browser.getEngine().load(
	// getClass().getResource("/html/english.html")
	// .toExternalForm());
	// Stage popUp = new Stage();
	// popUp.setScene(secondScene);
	//
	// popUp.show();
	// }
	//
	// };

}
