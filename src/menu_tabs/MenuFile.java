package menu_tabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuFile {
	Menu file;
	
	public MenuFile(MenuBar toolBar) {
		file = new Menu("File");
		saveLoad();
		toolBar.getMenus().add(file);
	}
	
	public Menu getMenu(){
		return file;
	}
	
	private void saveLoad() {
		MenuItem save = new MenuItem("Save Display");
		save.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO emre save
			}
		});

		MenuItem load = new MenuItem("Load Display");
		load.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//TODO emre load
			}
		});

		file.getItems().addAll(save, load);
	}

}
