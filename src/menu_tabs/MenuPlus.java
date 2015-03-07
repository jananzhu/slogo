package menu_tabs;

import java.util.ResourceBundle;

import view_panels.ToolBar;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class MenuPlus {
	//toolBar has access to view
	private ToolBar toolBar;
	private Menu menu;
	private MenuItem menuAdd;
	private int INDEX = 0;
	private IntegerProperty displayIndex = new SimpleIntegerProperty();
	
	MenuItem display;
	

	public MenuPlus(MenuBar toolBar) {
		toolBar = toolBar;
		displayIndex.set(INDEX);
		menu = new Menu("Displays");
		initialize();
		toolBar.getMenus().add(menu);
	}

	private void initialize() {
		menuAdd = new MenuItem("Add Display");
//		menuAdd.setOnAction(new EventHandler<ActionEvent>() {
//			public void handle(ActionEvent t) {
//				addTab();
//				// create a new display in the view
//				// bind property to the display index in manager?
//			}
//
//		});
		menu.getItems().addAll(menuAdd);
//		addTab();
	}

//	private void addTab() {
//		// TODO create display in VIew
//
//		display = new MenuItem("Display " + INDEX);
//		display.setId("" + INDEX);
//		display.setOnAction(addDisplay);
//		menu.getItems().add(display);
//		INDEX++;
//		displayIndex.set(INDEX);
//	}
	
	//API for toolbar
	public MenuItem getMenuAdd(){
		return menuAdd;
	}
	public Menu getMenuPlus(){
		return menu;
	}

//	private void setActiveIndex(ActionEvent event, MenuItem display) {
//		System.out.println(Integer.parseInt(display.getId()));
//		toolBar.createDisplay();
////		toolBar.setDisplay(Integer.parseInt(display.getId()));
//	}
	private EventHandler<ActionEvent> addDisplay = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {
			// display.changeBackground(turtleColor.getValue());
//			toolBar.setDisplay();
			
		}

	};
}
