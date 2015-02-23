package slogo_front;

import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

// TODO please comment this code tonight
/**
 * add comments here
 * 
 *
 */
public class View {

	// default size of the window
	private static final int xDimension = 1200;
	private static final int yDimension = 767;
	private static final int xDimCanvas = 995;
	private static final int yDimCanvas = 300;
	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(xDimension, yDimension);
	
	private Scene scene;
	// private ToolBar toolBar;
	private MenuBar menuBar;
	private TextField textField;
	private VBox history; 
	private ScrollPane historyScrollPane;
	private Canvas canvas; // TODO switch to different class
	private GraphicsContext gc;
	private String[] languages = {"Chinese","English","French","German","Italian","Japanese","Korean",
			"Portugese","Russian","Spanish"};
	private String language;
	private Display display;
	
	//TODO add array for turtles
	//TODO add "addTurtle()" method
	
	public View() {
		BorderPane root = new BorderPane();
		root.setTop(makeMenu());
		root.setRight(makeCommandHistory());
		root.setBottom(makeTextField());
		root.setCenter(makeCanvas());
		scene = new Scene(root, DEFAULT_SIZE.getWidth(),
				DEFAULT_SIZE.getHeight());
		scene.getStylesheets().add("css/view.css");
		scene.setOnKeyPressed(e -> handleKeyInput(e));
	}

	private Node makeCanvas(){
		
		VBox background = new VBox();
		
		canvas = new Canvas(xDimCanvas,yDimCanvas);
		
		background.getStyleClass().add("background");
	
		background.getChildren().add(canvas);
		display = new Display(xDimension, yDimension, canvas);
		// for testing
		Turtle myTurtle = new Turtle(0, 0, 10, 10, Color.BLUE, 
				"", true, false);
//		display.moveHome(myTurtle); // sets turtle to middle of screen
//		display.moveForward(myTurtle, -100);
//		display.moveHome(myTurtle);
//		display.moveForward(myTurtle, 1000);
		// end testing
		return background;
	}
	
	private Display getDisplay(){
		return display;
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
	
	private Node makeMenu() {
		menuBar = new MenuBar();
		language = "English";
		//Command Reference Sheet
		Menu menuHelp = new Menu("Help");

		//Language
		Menu menuLanguage = new Menu("Change Language");
		setLanguages(menuLanguage);
//		setLanguageListener(menuLanguage);
		menuBar.getMenus().addAll(menuHelp, menuLanguage);
		return menuBar;

	}
	
	private void setLanguages(Menu languageMenu){
		for(String l:languages){
			MenuItem languageItem = new MenuItem(l);
			languageMenu.getItems().add(languageItem);
		}
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