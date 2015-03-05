package menu_tabs;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuPlus {
	private Menu menuPlus;
	private final static int INDEX = 0;
	private IntegerProperty displayIndex = new SimpleIntegerProperty();

	public MenuPlus(MenuBar toolBar) {
		displayIndex.add(INDEX);
		menuPlus = new Menu("Displays");
		plus();
		toolBar.getMenus().add(menuPlus);
	}

	private void plus() {
		MenuItem add = new MenuItem("Add Display");
		add.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				addDisplay();
			}

		});

		menuPlus.getItems().addAll(add);
	}

	private void addDisplay() {
		MenuItem display = new MenuItem("Display " + displayIndex);
		display.setOnAction(setIndex);
		menuPlus.getItems().add(display);
		int index = displayIndex.get() + 1;
		displayIndex.add(index);
	}

	private EventHandler<ActionEvent> setIndex = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			//TODO set manager index to display index
		}

	};
}
