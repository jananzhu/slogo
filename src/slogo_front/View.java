package slogo_front;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class View {

	// default size of the window
	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(1200, 767);

	// // information for getting labels from the resources files
	// private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	// public static final String IMAGEFILE_SUFFIXES =
	// String.format(".*\\.(%s)", String.join("|",
	// ImageIO.getReaderFileSuffixes()));
	// private static final String PAUSE_PLAY = "PAUSE_PLAY";
	// private static final String RESET = "RESET";
	// private static final String STEP = "STEP";
	// private static final String LOAD_NEW_FILE = "LOAD_NEW_FILE";
	// private static final String OPTIONS = "OPTIONS";
	//
	// // controls the amount of free space so individual dots are visible
	// private static final double PERCENT_EMPTY_SPACE = .2;
	//
	// // spaces out the bottom menu
	// private static final int MENU_SPACING = 50;
	// private static final int SLIDER_SPACING = 10;
	//
	// // keeps the entire simulation screen on the window
	// private static final double BUFFER_DISTANCE = 550;

	// various items that are located on the screen
	private Scene scene;
	// private ToolBar toolBar;
	private MenuBar menuBar;
	private TextField textField;
	private VBox history; 
	private ScrollPane historyScrollPane;
	private Canvas canvas; // TODO switch to different class
	private GraphicsContext gc;
	// private Button pausePlayButton;
	// private Button resetButton;
	// private Button stepButton;
	// private MenuBar menuBar;
	// private MenuItem fileLoad;
	// private Slider animationSpeedSlider;
	// private GridPane animationScreen;
	// private double speed;
	// private Menu menuFile;
	//
	// private ResourceBundle resources;

	public View() {
		// speed = 1;
		// resources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE +
		// language);
		BorderPane root = new BorderPane();
		root.setTop(makeMenu());
		root.setRight(makeCommandHistory());
		root.setBottom(makeTextField());
		root.setCenter(makeCanvas());
//		root.getChildren().add(makeCanvas());
		// root.setBottom(makeControlPanel());
		// root.setLeft(makeGraph());
		// root.setCenter(makeAnimationScreen());
		scene = new Scene(root, DEFAULT_SIZE.getWidth(),
				DEFAULT_SIZE.getHeight());
		scene.getStylesheets().add("css/view.css");
		scene.setOnKeyPressed(e -> handleKeyInput(e));
	}

	private Node makeCanvas(){
		
		canvas = new Canvas(995,300);
		canvas.getStyleClass().add("canvas");

		// sample canvas code
		gc =canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(1);
		gc.strokeLine(750, 750, 800, 30);
		int x = 50;
		int y = 50;
		while(y<10000){
			gc.strokeLine(x, y, x, y+5);
			y+=5;			
		}
		return canvas;
		
	}
	
	/**
	 * TODO implement animation
	 * 
	 * @param frame
	 */
	private void createAnimation(KeyFrame frame) {
		Timeline animation = new Timeline();
		animation.setCycleCount(Animation.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	
	private Node makeTextField() {
		textField = new TextField();
		textField.setPrefWidth(200);
		textField.setLayoutX(0);
		return textField;		
	}
	
	private void handleKeyInput(KeyEvent e) { //This entire method is part of my masterpiece.
		KeyCode keyCode = e.getCode();
		if (keyCode == KeyCode.ENTER) {
			String s = textField.getText();
			if(s.toLowerCase().equals("clear")){
				clearHistoryText();
			}
			addHistoryText(s);
			textField.clear();
		}
	}

	public Scene getScene() {
		return scene;
	}
	
	private void addHistoryText(String text){
		Text a = new Text(">> " + text);
		a.setWrappingWidth(205);
		history.getChildren().add(a);
		historyScrollPane.setVvalue(historyScrollPane.getVmax()+20);
	}
	
	private void clearHistoryText(){
		history.getChildren().clear();
	}
	
	// private Node makeToolbar() {
	// toolBar = new ToolBar(new Button("New"), new Button("Open"),
	// new Button("Save"), new Separator(), new Button("Clean"),
	// new Button("Compile"), new Button("Run"), new Separator(),
	// new Button("Debug"), new Button("Profile"));
	// return toolBar;
	//
	// }

	private Node makeMenu() {
		menuBar = new MenuBar();

		//Command Reference Sheet
		Menu menuHelp = new Menu("Help");

		//Language
		Menu menuLanguage = new Menu("Change Language");

		menuBar.getMenus().addAll(menuHelp, menuLanguage);

		return menuBar;

	}
	
	private Node makeCommandHistory() {
		 history = new VBox(5);
		 historyScrollPane = new ScrollPane();
		 historyScrollPane.getStyleClass().add("commandHistory");
		 historyScrollPane.setMinWidth(205);
		 historyScrollPane.setFitToWidth(true);
		 historyScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		 Text a = new Text( "Command History: ");
		 a.setWrappingWidth(205);
		 VBox.setVgrow(historyScrollPane, Priority.ALWAYS);
		 
		 history.getChildren().addAll(a);
		 historyScrollPane.setContent(history);
		 return historyScrollPane; 
	}

}
