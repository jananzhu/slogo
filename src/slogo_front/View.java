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
import javafx.scene.layout.HBox;
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
	private static final int yDimension = 800;
//	private static final Dimension2D DEFAULT_SIZE = new Dimension2D(xDimension, yDimension);
	
	private Scene scene;
	// private ToolBar toolBar;
	private MenuBar menuBar;
	private TextField commandLine;
	private VBox history; 
	private ScrollPane historyScrollPane;
	private Canvas canvas; // TODO switch to different class
	private GraphicsContext gc;
	private String[] languages = {"Chinese","English","French","German","Italian","Japanese","Korean",
			"Portugese","Russian","Spanish"};
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
	
	//TODO add array for turtles
	//TODO add "addTurtle()" method
	
	public View() {
		BorderPane root = new BorderPane();
		root.setTop(makeMenu());
		root.setRight(makeCommandHistory());
		root.setBottom(makeTextField());
		root.setCenter(makeCanvas());
		scene = new Scene(root);
		scene.getStylesheets().add("css/view.css");
		scene.setOnKeyPressed(e -> handleKeyInput(e));
	}

	private Node makeCanvas(){
		
//		displayBackground = new VBox();
		
//		canvas = new Canvas(1000,600);
//		canvas.getGraphicsContext2D().setStroke(Color.BLUE);
//		canvas.getGraphicsContext2D().setLineWidth(1);
//		canvas.getGraphicsContext2D().strokeLine(0, 0, 100, 300);
		
//		displayBackground.getStyleClass().add("background");
	
//		displayBackground.getChildren().add(canvas);
		
//		canvas.getGraphicsContext2D().setFill(Color.WHITE);
//		canvas.getGraphicsContext2D().fillRect(0, 0, 1000,600);
		display = new Display(xCanvas, yCanvas);
		return display.getDisplay();
	}
	
//	private Display getDisplay(){
//		return display;
//	}
	
	private Node makeTextField() {
		commandLine = new TextField();
//		textField.setPrefWidth(HISTORY_WIDTH);
		commandLine.setPrefHeight(COMMAND_HEIGHT);
//		commandLine.setLayoutX(0);
		return commandLine;		
	}
	
	private void handleKeyInput(KeyEvent e) { //This entire method is part of my masterpiece.
		KeyCode keyCode = e.getCode();
		if (keyCode == KeyCode.ENTER) {
			String s = commandLine.getText();
			if(s.toLowerCase().equals("clear")){
				clearHistoryText();
			}
			addHistoryText(s);
			commandLine.clear();
		}
	}

	public Scene getScene() {
		return scene;
	}
	
	private void addHistoryText(String text){
		Text newText = new Text(">> " + text);
		newText.setWrappingWidth(HISTORY_WIDTH);
		history.getChildren().add(newText);
		historyScrollPane.setVvalue(historyScrollPane.getVmax());
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

}
