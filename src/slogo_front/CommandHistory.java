package slogo_front;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class CommandHistory {
	private ListView commandList;
	private ObservableList<String> commandItems;
	
	
	public CommandHistory(double width) {
		commandList = new ListView<String>();
		commandList.setMaxWidth(width);
		resetHistory();
	}
	
	private void resetHistory(){
		commandItems = FXCollections.observableArrayList("Command History");
		commandList.setItems(commandItems);
		commandList.setOnMouseClicked(historyEvent);
	}
	
	protected ListView getCommandHistory(){
		return commandList;
	}
	
	
	//TODO this handler needs reference to manager model
	private EventHandler<MouseEvent> historyEvent = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent event) {
//			// TODO turtle within display or in view? think about allowances for
//			// multiple turtles
//			String s = (String) commandList.getSelectionModel().getSelectedItem();
//			s = s.substring(3, s.length()); // remove ">> "
//			System.out.println(s);
//			
//			if (s.toLowerCase().equals("clear")) {
//				clearHistoryText();
//			}
////			addHistoryText(s);
//
//			// TODO throw error here
////			try{
//				List<Double> results = manager.getModel().toBack(s.toLowerCase() + "\n");
//				for (Double value : results) {
//					System.out.println(value);
//				}
////			}catch(InvalidParameterException e){
//				
////			}
		}
	};
	
//	private EventHandler<MouseEvent> clearHistory = new EventHandler<MouseEvent>() {
//	public void handle(MouseEvent event) {
//		resetHistory();
//	}
//
//};

}
