package menu_tabs;

import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuPlus {
	// created by the toolBar
	private Menu menuPlus;
	private int INDEX = 0;
	private IntegerProperty displayIndex = new SimpleIntegerProperty();

	public MenuPlus(MenuBar toolBar) {
		displayIndex.set(INDEX);
		menuPlus = new Menu("Displays");
		plus();
		toolBar.getMenus().add(menuPlus);
	}

	private void plus() {
		MenuItem add = new MenuItem("Add Display");
		add.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				addDisplay();
				// create a new display in the view
				// bind property to the display index in manager?
			}

		});
		menuPlus.getItems().addAll(add);
		addDisplay();
	}

	private void addDisplay() {
		// TODO create display in VIew

		MenuItem display = new MenuItem("Display " + INDEX);
		display.setId("" + INDEX);
		display.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//
				Integer index = Integer.parseInt(display.getId());
				System.out.println(index);
			}
		});
		menuPlus.getItems().add(display);
		INDEX++;
		displayIndex.set(INDEX);
	}

	private EventHandler<ActionEvent> setIndex = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			// TODO set manager index to display index

		}

	};
}
