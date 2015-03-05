package control_buttons;

import javafx.scene.control.ColorPicker;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SlogoColorPicker {
	private ColorPicker picker;
	
	public SlogoColorPicker(double width, VBox controlPanel) {
		picker = new ColorPicker();
		picker.setMaxWidth(width);
		controlPanel.getChildren().add(picker);
	}
	
	public Color getValue(){
		return picker.getValue();
	}
}
