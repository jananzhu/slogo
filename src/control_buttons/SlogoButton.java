package control_buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SlogoButton {
	private Button button;

	public SlogoButton(double width, EventHandler<MouseEvent> handler, VBox controlPanel) {
		button = new Button();
		button.setMaxWidth(width);
		button.setOnMouseClicked(handler);
		controlPanel.getChildren().add(button);
	}
	
	public Button getButton(){
		return button;
	}

}
