package slogo_front;

import java.io.File;
import java.util.HashMap;
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
	private HashMap<String,Double> variables = new HashMap<>();
	
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
	Button clearScreen;
	Button moveForward;
	Button moveBackward;
	Label degreeLabel;
	TextField degreeField;
	Button turnTurtle;
	ColorPicker turtleColor;
	Button setColor;
	Button setPen;
	Button setTurtleImage;
	

	// TODO add array for turtles
	// TODO add "addTurtle()" method

	public View() {
		// resource bundles
		labels = ResourceBundle.getBundle("resources.languages/LabelsBundle",
				defaultLocale);
		// creating borderpane
		BorderPane root = new BorderPane();

		root.setTop(makeMenu());
		root.setRight(makeCommandHistory());
		root.setBottom(makeTextField());
		root.setCenter(makeCanvas());
		root.setLeft(makeControlPanel());

		scene = new Scene(root);
		scene.getStylesheets().add("css/view.css");
		// scene.setOnKeyPressed(e -> handleKeyInput(e));
	}

	private Node makeControlPanel() {
		VBox controlPanel = new VBox(10);
		controlPanel.setMaxWidth(200);
		// add language binding in properties files
		controlPanel.getStyleClass().add("controlPanel");

		clearScreen = new Button();
		clearScreen.setMaxWidth(Double.MAX_VALUE);

		moveForward = new Button();
		moveForward.setMaxWidth(Double.MAX_VALUE);
		moveForward.setOnMouseClicked(forwardEvent);

		moveBackward = new Button();
		moveBackward.setMaxWidth(Double.MAX_VALUE);
		moveBackward.setOnMouseClicked(backwardEvent);
		

		degreeLabel = new Label();
		degreeField = new TextField();
		degreeField.setMaxWidth(50);

		turnTurtle = new Button();

		HBox degree = new HBox();
		degree.getChildren().addAll(degreeLabel, degreeField, turnTurtle);
		degree.setSpacing(10);
		degree.setMaxWidth(Double.MAX_VALUE);

		turtleColor = new ColorPicker();
		turtleColor.setMaxWidth(Double.MAX_VALUE);

		setColor = new Button();
		setColor.setMaxWidth(Double.MAX_VALUE);
		setColor.setOnMouseClicked(changeBackground);

		setPen = new Button();
		setPen.setMaxWidth(Double.MAX_VALUE);

		setTurtleImage = new Button();
		setTurtleImage.setMaxWidth(Double.MAX_VALUE);
		setTurtleImage.setOnMouseClicked(changeTurtleImage);
		
		varList = new ListView<String>();
		variableItems = FXCollections.observableArrayList("Variables");
		// testing
		addVariableText("x",2);
		
		setLabels();

		controlPanel.getChildren().addAll(clearScreen, moveForward,
				moveBackward, degree, turtleColor, setColor, setPen,
				setTurtleImage,varList);

		return controlPanel;
	}

	private void setLabels() {
		// System.out.println(labels.getString("CLEAR"));
		clearScreen.setText(labels.getString("CLEAR"));
		moveForward.setText(labels.getString("FORWARD"));
		moveBackward.setText(labels.getString("BACKWARD"));
		degreeLabel.setText(labels.getString("DEGREE"));
		turnTurtle.setText(labels.getString("TURN"));
		setColor.setText(labels.getString("BACKGROUND"));
		setPen.setText(labels.getString("PEN"));
		setTurtleImage.setText(labels.getString("IMAGE"));
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
		commandItems.add(">> "  + text);
		commandList.setItems(commandItems);
	}
	
	protected void addVariableText(String variable, double value) {
		variables.put(variable, value);
		variableItems.clear();
		for(String s:variables.keySet()){
			variableItems.add(variable + ": " + value);
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
				setLabels();
			}
		});

		MenuItem french = new MenuItem("French");
		french.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				labels = ResourceBundle
						.getBundle("resources.languages/LabelsBundle",
								supportedLocales[1]);
				setLabels();
			}
		});

		languageMenu.getItems().addAll(english, french);
	}

	// private void setLanguageListener(Menu languageMenu){
	// languageMenu.getOnAction().
	//
	// languageMenu.addEventHandler(MouseEvent.MOUSE_CLICKED,
	// new EventHandler<MouseEvent>() {
	// public void handle (MouseEvent e) {
	// language = languageMenu.getText();
	// System.out.println(language);
	// }
	// });
	// }
	//
	private Node makeCommandHistory() {
		commandList = new ListView<String>();
		commandList.setMaxWidth(200);
		commandItems = FXCollections.observableArrayList ("Command History");
		commandList.setItems(commandItems);
		
		return commandList;
	}
	//set manager
	protected void setManager(Manager m){
		manager = m;
	}
	
	// sets command line
	protected void setCommandLine(EventHandler<KeyEvent> handler) {
		commandLine.setOnKeyPressed(handler);
	}
	
//	protected void setMoveForward(EventHandler<MouseEvent> handler){
//		moveForward.setOnMouseClicked(handler);
//	}

	private EventHandler<MouseEvent> changeTurtleImage = new EventHandler<MouseEvent>() {
		// within manager?
		public void handle(MouseEvent event) {
			Stage fileSystem = new Stage();
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			File file = fileChooser.showOpenDialog(fileSystem);
			// some method to change the imageview in display
		}

	};

	private EventHandler<MouseEvent> changeBackground = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			display.changeBackground(turtleColor.getValue());
		}

	};

	private EventHandler<MouseEvent> changePenColor = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
			// displaychangeBackground(turtleColor.getValue());

			// method for within manager, because turtle is within manager
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
	
	private EventHandler<MouseEvent> forwardEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
			display.forward(manager.getTurtle(), 100);
		}

	};
	
	private EventHandler<MouseEvent> backwardEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
			// TODO turtle within display or in view? think about allowances for
			// multiple turtles
			display.back(manager.getTurtle(), 100);
		}

	};

}
