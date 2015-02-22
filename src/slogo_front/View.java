package slogo_front;

import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JTextField;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
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
		
		canvas = new Canvas(995,300);
		canvas.getStyleClass().add("canvas");
		// sample canvas code
		gc =canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(1);
		gc.strokeLine(750, 750, 800, 30);
		gc.strokeLine(0,0, 10000, 1000);
		
		int x = 50;
		int y = 50;
		while(y<10000){
			gc.strokeLine(x, y, x, y+5);
			y+=5;			
		}
		return canvas;
		
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
