package slogo_front;

import javafx.application.Application;
import javafx.stage.*;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		View view = new View();


        stage.setTitle("SLOGO");
        stage.setScene(view.getScene());
        stage.show();
		
	}

}
