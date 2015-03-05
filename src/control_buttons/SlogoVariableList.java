package control_buttons;

import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class SlogoVariableList {
	private ListView varList;
	//what is this thing?
	private ObservableList<String> variableItems;
	private HashMap<String, Double> variables = new HashMap<>();
	
	public SlogoVariableList(VBox controlPanel) {
		// TODO refactor this class, get rid of magic numbers
		varList = new ListView<String>();
		variableItems = FXCollections.observableArrayList("Variables");
		varList.setMaxHeight(200);
		// testing

		addVariableText("y (demo)",10);
		addVariableText("x (demo)",250);
		controlPanel.getChildren().add(varList);
	}
	
	protected void addVariableText(String variable, double value) {
		variables.put(variable, value);
		variableItems.clear();
		for (String s : variables.keySet()) {
			variableItems.add(s + ": " + variables.get(s));
		}
		varList.setItems(variableItems);
	}

}
