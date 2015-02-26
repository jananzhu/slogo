package slogo_front;

import java.awt.Event;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import javafx.stage.Stage;

// TODO please comment this code tonight
/**
 * add comments here
 * 
 *
 */
public class View {

	// default size of the window
	private static final int xDimension = 1200;
	private static final int yDimension = 800;
//	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(xDimension, yDimension);
	
	private Scene scene;
	//html
	WebView browser = new WebView();
	Scene secondScene = new Scene(browser);
	// private ToolBar toolBar;
	private MenuBar menuBar;
	private TextField commandLine;
	private VBox history; 
	private ScrollPane historyScrollPane;
	private Canvas canvas; // TODO switch to different class
	private GraphicsContext gc;
	private String[] languages = {"English","French"};
	private String language;
	private Display display;
//	private VBox displayBackground ;
	//Canvas Dimensions
	private static final int xCanvas = 1000;
	private static final int yCanvas = 600;
	//Command History Dimensions
	private static final int HISTORY_WIDTH = 200;
	private static final int HISTORY_SPACING = 5;
	private static final int HISTORY_PADDING = 5;
	
	//Command Line Dimenstions
	private static final int COMMAND_HEIGHT = 100;
	
	//Locale Resources
	ResourceBundle labels;
	Locale[] supportedLocales = {
		    Locale.ENGLISH,
		    Locale.FRENCH
		};
	Locale defaultLocale = Locale.ENGLISH;
	
	//controlPanel Buttons
	Button clearScreen;
	Button moveForward;
	Button moveBackward;
	Label degreeLabel;
	TextField degreeField;
	Button turnTurtle;
	ColorPicker turtleColor;
	Button setColor;
	Button setPen;
	
	
	//TODO add array for turtles
	//TODO add "addTurtle()" method
	
	public View() {
		//resource bundles
		labels = ResourceBundle.getBundle("resources.languages/LabelsBundle", defaultLocale);
		//creating borderpane
		BorderPane root = new BorderPane();
		
		root.setTop(makeMenu());
		root.setRight(makeCommandHistory());
		root.setBottom(makeTextField());
		root.setCenter(makeCanvas());
		root.setLeft(makeControlPanel());
		
		scene = new Scene(root);
		scene.getStylesheets().add("css/view.css");
//		scene.setOnKeyPressed(e -> handleKeyInput(e));
	}
	
	private Node makeControlPanel(){
		VBox controlPanel = new VBox(10);
		controlPanel.setMaxWidth(200);
		//add language binding in properties files
		controlPanel.getStyleClass().add("controlPanel");
		
		clearScreen = new Button();
		clearScreen.setMaxWidth(Double.MAX_VALUE);
//		clearScreen.textProperty().bind(labels.("CLEAR"));
		
		moveForward = new Button();
		moveForward.setMaxWidth(Double.MAX_VALUE);
		
		moveBackward = new Button();
		moveBackward.setMaxWidth(Double.MAX_VALUE);
		
		degreeLabel = new Label();
		degreeField = new TextField ();
		degreeField.setMaxWidth(50);
		
		
		turnTurtle = new Button();
		
		HBox degree = new HBox();
		degree.getChildren().addAll(degreeLabel,degreeField, turnTurtle);
		degree.setSpacing(10);
		degree.setMaxWidth(Double.MAX_VALUE);
		
		turtleColor = new ColorPicker();
		turtleColor.setMaxWidth(Double.MAX_VALUE);
		
		setColor = new Button();
		setColor.setMaxWidth(Double.MAX_VALUE);
		setColor.setOnMouseClicked(changeBackground);
		
		setPen = new Button();
		setPen.setMaxWidth(Double.MAX_VALUE);
		
		setLabels();
		
		controlPanel.getChildren().addAll(clearScreen,moveForward,moveBackward,degree,turtleColor, setColor, setPen);
		
		return controlPanel;
	}

	private void setLabels() {
//		System.out.println(labels.getString("CLEAR"));
		clearScreen.setText(labels.getString("CLEAR"));
		moveForward.setText(labels.getString("FORWARD"));
		moveBackward.setText(labels.getString("BACKWARD"));
		degreeLabel.setText(labels.getString("DEGREE"));
		turnTurtle.setText(labels.getString("TURN"));
		setColor.setText(labels.getString("BACKGROUND"));
		setPen.setText(labels.getString("PEN"));
	}

	private Node makeCanvas(){
		display = new Display(xCanvas, yCanvas);
		return display.getDisplay();
	}
	
	protected Display getDisplay(){
		return display;
	}
	
	private Node makeTextField() {
		commandLine = new TextField();
//		textField.setPrefWidth(HISTORY_WIDTH);
		commandLine.setPrefHeight(COMMAND_HEIGHT);
//		commandLine.setLayoutX(0);
		return commandLine;		
	}
	
	//might delete
//	private void handleKeyInput(KeyEvent e) { //This entire method is part of my masterpiece.
//		KeyCode keyCode = e.getCode();
//		if (keyCode == KeyCode.ENTER) {
//			String s = commandLineText();
//			if(s.toLowerCase().equals("clear")){
//				clearHistoryText();
//			}
//			addHistoryText(s);
////			commandLine.clear();
//		}
//	}

	public Scene getScene() {
		return scene;
	}
	
	//gets and clears command line text
	protected String commandLineText(){
		String parse = commandLine.getText();
		commandLine.clear();
		return parse;
	}
	
	//sets command line 
	protected void setCommandLine(EventHandler<KeyEvent> handler){
		commandLine.setOnKeyPressed(handler);
	}
	
	
	
	
	//figureout if this should be private somehow
	protected void addHistoryText(String text){
		Text newText = new Text(">> " + text);
		newText.setWrappingWidth(HISTORY_WIDTH);
		history.getChildren().add(newText);
		historyScrollPane.setVvalue(historyScrollPane.getVmax());
	}
	//private in the future?
	protected void clearHistoryText(){
		history.getChildren().clear();
	}
	
	private Node makeMenu() {
		menuBar = new MenuBar();
		language = "English";
		//Command Reference Sheet
		Menu menuHelp = new Menu("Help");
		MenuItem commandReference = new MenuItem("Command Reference");
		commandReference.setOnAction(launchWebView);
		menuHelp.getItems().add(commandReference);

		//Language
		Menu menuLanguage = new Menu("Change Language");
		setLanguages(menuLanguage);
//		setLanguageListener(menuLanguage);
		menuBar.getMenus().addAll(menuHelp, menuLanguage);
		return menuBar;

	}
	
	private void setLanguages(Menu languageMenu){
			MenuItem english = new MenuItem("English");
			english.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	            	labels = ResourceBundle.getBundle("resources.languages/LabelsBundle", supportedLocales[0]);
	            	setLabels();
	            }
	        });
			
			MenuItem french = new MenuItem("French");
			french.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	            	labels = ResourceBundle.getBundle("resources.languages/LabelsBundle", supportedLocales[1]);
	            	setLabels();
	            }
	        });
			
			
			languageMenu.getItems().addAll(english, french);
		}
	
	
//	private void setLanguageListener(Menu languageMenu){
//		languageMenu.getOnAction().
//		
//		languageMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, 
//				new EventHandler<MouseEvent>() {
//			public void handle (MouseEvent e) {
//				language = languageMenu.getText();
//				System.out.println(language);
//			}
//		});
//	}
//	
	private Node makeCommandHistory() {
		 history = new VBox(HISTORY_SPACING);
		 historyScrollPane = new ScrollPane();
		 historyScrollPane.getStyleClass().add("commandHistory");
		 historyScrollPane.setMinWidth(HISTORY_WIDTH);
//		 historyScrollPane.setFitToWidth(true);
		 historyScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		 Text title = new Text( "Command History: ");
		 title.setWrappingWidth(HISTORY_WIDTH-HISTORY_PADDING);
		 VBox.setVgrow(historyScrollPane, Priority.ALWAYS);
		 
		 history.getChildren().addAll(title);
		 historyScrollPane.setContent(history);
		 
		 return historyScrollPane; 
	}
	
	
	private EventHandler changeBackground = new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent event) {
	    		display.changeBackground(turtleColor.getValue());
	    }
	
	};
	
	private EventHandler changePenColor = new EventHandler<MouseEvent>() {
	    public void handle(MouseEvent event) {
	    	//TODO turtle within display or in view? think about allowances for multiple turtles
	    		//displaychangeBackground(turtleColor.getValue());
	    }
	
	};
	
	private EventHandler launchWebView = new EventHandler<ActionEvent>() {
	    public void handle(ActionEvent event) {
//	    	Label secondLabel = new Label("Hello");
            
//            Pane secondaryLayout = new Pane();
////            secondaryLayout.getChildren().add(secondLabel);
//            secondaryLayout.getChildren().add(browser);
             
            
            browser.getEngine().load(getClass()
            	    .getResource("/html/english.html")
            	    .toExternalForm());
//	    	webEngine.load();
	    	Stage popUp = new Stage();
	    	popUp.setScene(secondScene);
	    	
	    	popUp.show();
//	    	System.out.println("hello");
	    	
	    	
	    }
	
	};

}
