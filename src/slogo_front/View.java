package slogo_front;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// TODO please comment this code tonight
/**
 * add comments here
 * 
 *
 */
public class View {

	private Scene scene;
	private Manager manager;
	// html
	WebView browser = new WebView();
	Scene secondScene = new Scene(browser);
	// private ToolBar toolBar;
	private MenuBar menuBar;
	private TextField commandLine;
	private VBox history;
	private ScrollPane historyScrollPane;
	private Display display;
	private ListView varList;
	private ListView commandList;
	private ObservableList<String> commandItems;
	private ObservableList<String> variableItems;
	private HashMap<String, Double> variables = new HashMap<>();

	// private VBox displayBackground ;
	// Canvas Dimensions
	private static final int xCanvas = 1000;
	private static final int yCanvas = 600;
	// Command History Dimensions
	private static final int HISTORY_WIDTH = 200;
	private static final int HISTORY_SPACING = 5;
	private static final int HISTORY_PADDING = 5;

	// Command Line Dimenstions
	private static final int COMMAND_HEIGHT = 100;

	// Locale Resources
	ResourceBundle labels;
	Locale[] supportedLocales = { Locale.ENGLISH, Locale.FRENCH };
	Locale defaultLocale = Locale.ENGLISH;

	// controlPanel Buttons
//	Button clearScreen;
//	Button clearCommandHistory;
//	Button moveForward;
//	Button moveBackward;
//	Label degreeLabel;
//	TextField degreeField;
//	Button turnTurtle;
//	Slider turnDegree;
//	ColorPicker turtleColor;
//	Button setColor;
//	Button setPen;
//	Button setTurtleImage;

	// TODO add array for turtles
	// TODO add "addTurtle()" method

	public View() {
		// resource bundles
		labels = ResourceBundle.getBundle("resources.languages/LabelsBundle",
				defaultLocale);
		
		BorderPane root = new BorderPane();
		ControlPanel control = new ControlPanel(10, 200);

		root.setTop(makeMenu());
		root.setRight(makeCommandHistory());
		root.setBottom(makeTextField());
		root.setCenter(makeCanvas());
		root.setLeft(control.getControlpanel());

		scene = new Scene(root);
		scene.getStylesheets().add("css/view.css");
		// scene.setOnKeyPressed(e -> handleKeyInput(e));
	}

	private Node makeCanvas() {
		display = new Display(xCanvas, yCanvas);
		return display.getDisplay();
	}

	protected Display getDisplay() {
		return display;
	}

	private Node makeTextField() {
		commandLine = new TextField();
		// textField.setPrefWidth(HISTORY_WIDTH);
		commandLine.setPrefHeight(COMMAND_HEIGHT);
		// commandLine.setLayoutX(0);
		return commandLine;
	}

	public Scene getScene() {
		return scene;
	}

	// gets and clears command line text
	protected String commandLineText() {
		String parse = commandLine.getText();
		commandLine.clear();
		return parse;
	}

	// figureout if this should be private somehow
	protected void addHistoryText(String text) {
		commandItems.add(">> " + text);
		commandList.setItems(commandItems);
	}

	protected void addVariableText(String variable, double value) {
		variables.put(variable, value);
		variableItems.clear();
		for (String s : variables.keySet()) {
			variableItems.add(s + ": " + variables.get(s));
		}
		varList.setItems(variableItems);
	}

	// private in the future?
	protected void clearHistoryText() {
		history.getChildren().clear();
	}

	private Node makeMenu() {
		menuBar = new MenuBar();
		// Command Reference Sheet
		Menu menuHelp = new Menu("Help");
		MenuItem commandReference = new MenuItem("Command Reference");
		commandReference.setOnAction(launchWebView);
		menuHelp.getItems().add(commandReference);

		// Language
		Menu menuLanguage = new Menu("Change Language");
		setLanguages(menuLanguage);
		// setLanguageListener(menuLanguage);
		menuBar.getMenus().addAll(menuHelp, menuLanguage);
		return menuBar;

	}

	private void setLanguages(Menu languageMenu) {
		MenuItem english = new MenuItem("English");
		english.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				labels = ResourceBundle
						.getBundle("resources.languages/LabelsBundle",
								supportedLocales[0]);
//TODO			setLabels();
			}
		});

		MenuItem french = new MenuItem("French");
		french.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				labels = ResourceBundle
						.getBundle("resources.languages/LabelsBundle",
								supportedLocales[1]);
//TODO				setLabels();
			}
		});

		languageMenu.getItems().addAll(english, french);
	}

	
	private Node makeCommandHistory() {
		commandList = new ListView<String>();
		commandList.setMaxWidth(200);
		resetHistory();

		return commandList;
	}
	
	private void resetHistory(){
		commandItems = FXCollections.observableArrayList("Command History");
		commandList.setItems(commandItems);
		commandList.setOnMouseClicked(historyEvent);
	}
	
	// set manager
	protected void setManager(Manager m) {
		manager = m;
	}

	// sets command line
	protected void setCommandLine(EventHandler<KeyEvent> handler) {
		commandLine.setOnKeyPressed(handler);
	}


	private EventHandler<MouseEvent> changeTurtleImage = new EventHandler<MouseEvent>() {
		// within manager?
		public void handle(MouseEvent event) {
//			Stage fileSystem = new Stage();
//			FileChooser fileChooser = new FileChooser();
//			fileChooser.setTitle("Open Resource File");
//			File file = fileChooser.showOpenDialog(fileSystem);
//			// some method to change the imageview in display
//			String filePath = "C:"+file.getPath();
//			int index = filePath.indexOf("/images");
////			System.out.println(index);
////			System.out.println(filePath);
////			System.out.println(filePath.substring(index));
//			Turtle turtle = manager.getTurtle();
//			display.hide(turtle);
//			manager.getTurtle().setImage(filePath.substring(index));
//			display.show(turtle);
		}

	};

	private EventHandler<MouseEvent> clear = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
//			display.clearScreen(manager.getTurtle());
//			addHistoryText("clearscreen");
		}

	};
	
	private EventHandler<MouseEvent> clearHistory = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			resetHistory();
		}

	};
	
	private EventHandler<MouseEvent> changeBackground = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
//			display.changeBackground(turtleColor.getValue());
		}

	};
	
	private EventHandler<MouseEvent> turnEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
//			double degree = turnDegree.getValue();
//			display.setHeading(manager.getTurtle(), degree);
//			if(degree >= 0){
//				addHistoryText("left " + (int) degree);
//			}else{
//				addHistoryText("right " + (int) Math.abs(degree));
//			}
		}

	};

	private EventHandler<MouseEvent> changePenColor = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
//			manager.getTurtle().setPenColor(turtleColor.getValue());

		}

	};

	// opens window for help page
	private EventHandler<ActionEvent> launchWebView = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {

			browser.getEngine().load(
					getClass().getResource("/html/english.html")
							.toExternalForm());
			Stage popUp = new Stage();
			popUp.setScene(secondScene);

			popUp.show();
		}

	};
	
	private EventHandler<MouseEvent> historyEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
//			String s = (String) commandList.getSelectionModel().getSelectedItem();
//			s = s.substring(3, s.length()); // remove ">> "
//			System.out.println(s);
//			
//			if (s.toLowerCase().equals("clear")) {
//				clearHistoryText();
//			}
////			addHistoryText(s);
//
//			// TODO throw error here
////			try{
//				List<Double> results = manager.getModel().toBack(s.toLowerCase() + "\n");
//				for (Double value : results) {
//					System.out.println(value);
//				}
////			}catch(InvalidParameterException e){
//				
////			}
		}
	};

	private EventHandler<MouseEvent> forwardEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
//			// multiple turtles
//			display.forward(manager.getTurtle(), 100);
//			addHistoryText("forward 100");
		}

	};

	private EventHandler<MouseEvent> backwardEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
//			// TODO turtle within display or in view? think about allowances for
//			// multiple turtles
//			display.back(manager.getTurtle(), 100);
//			addHistoryText("backward 100");
		}

	};

}
