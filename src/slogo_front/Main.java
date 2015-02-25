package slogo_front;

import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		View view = new View();
		Manager manager = new Manager();
		stage.setResizable(false);
        stage.setTitle("SLOGO");
        stage.setScene(view.getScene());
        stage.show();
		
	}

}
