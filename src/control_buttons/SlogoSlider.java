package control_buttons;

import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class SlogoSlider {
	private static final int TICK = 0;
	private static final int MAJOR_TICK = 90;
	private static final int MINOR_TICK = 10;
	private static final int BLOCK = 5;
	private Slider slider;

	public SlogoSlider(double min, double max, VBox controlPanel) {
		slider = new Slider();
		slider.setMin(min);
		slider.setMax(max);
		slider.setValue(TICK);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(MAJOR_TICK);
		slider.setMinorTickCount(MINOR_TICK);
		slider.setBlockIncrement(BLOCK);
		
		controlPanel.getChildren().add(slider);
	}
	
	public double getValue(){
		return slider.getValue();
	}

}
