package slogo_front;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
		// root.setBottom(makeControlPanel());
		// root.setLeft(makeGraph());
		// root.setCenter(makeAnimationScreen());
		scene = new Scene(root, DEFAULT_SIZE.getWidth(),
				DEFAULT_SIZE.getHeight());
		scene.getStylesheets().add("css/view.css");
	}

	private Node makeTextField() {
		textField = new TextField();
		textField.setPrefWidth(200);
		textField.setLayoutX(0);
		return textField;		
	}

	public Scene getScene() {
		return scene;
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
		 VBox history = new VBox(5);
		 history.getStyleClass().add("vbox");
		 ScrollPane s1 = new ScrollPane();
//		 s1.setVmax(440);
		 s1.setMinWidth(205);
	     s1.setFitToWidth(true);
	     s1.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//		 Rectangle rect = new Rectangle(200, 200, Color.RED);
		 Text a = new Text( "This is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilities");
		 a.setWrappingWidth(205);
		 Text b = new Text("This is a testThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilities");
		 b.setWrappingWidth(205);
		 Text c = new Text( "This is a testThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilities");
		 c.setWrappingWidth(205);
		 Text d = new Text( "This is a tThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilitiesest");
		 d.setWrappingWidth(205);
		 Text e = new Text( "This isThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilities a test");
		 e.setWrappingWidth(205);
		 Text f = new Text( "ThThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilitiesis is a test");
		 f.setWrappingWidth(205);
		 Text g = new Text( "This is a This is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilitiestest");
		 g.setWrappingWidth(205);
		 Text h = new Text( "This is aThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilities test");
		 h.setWrappingWidth(205);
		 Text i = new Text( "This is aThis is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilities test");
		 i.setWrappingWidth(205);
		 Text j = new Text( "This is This is a test of our abilitiesThis is a test of our abilitiesThis is a test of our abilitiesa test");
		 j.setWrappingWidth(205);
		 VBox.setVgrow(s1, Priority.ALWAYS);
		 
		 history.getChildren().addAll(a,b,c,d,e,f,g,h,i,j);
		 s1.setContent(history);
//		 history.getChildren().add(rect);
//		 s1.setPrefSize(120, 120);
//		 s1.setContent(history);
		 
		 
		 return s1;
	}

}
