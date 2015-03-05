package view_panels;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuPlus {
	private Menu menuPlus;
	private int displayIndex = 0;

	public MenuPlus(MenuBar toolBar) {
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
		displayIndex++;
	}

	private EventHandler<ActionEvent> setIndex = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			//TODO set manager index to display index
		}

	};
}
