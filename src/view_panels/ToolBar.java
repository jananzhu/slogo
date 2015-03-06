package view_panels;

import java.util.ArrayList;

import slogo_front.Display;
import slogo_front.View;
import menu_tabs.MenuFile;
import menu_tabs.MenuHelp;
import menu_tabs.MenuLanguage;
import menu_tabs.MenuPlus;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;

public class ToolBar {
	private View view;
	private MenuBar toolBar;
	
	private MenuHelp help;
	private MenuLanguage menuLanguage;
	private MenuFile file;
	private MenuPlus plus;
	

	public ToolBar(View view) {
		//Mediator between view and menutabs
		view = view;
		toolBar = new MenuBar();
		
		//menu_tabs
		
		// Command Reference Sheet
		help = new MenuHelp(toolBar);
		// Language
		menuLanguage = new MenuLanguage(toolBar);
		//file save/load
		file = new MenuFile(toolBar);
		//add new displays
		plus = new MenuPlus(toolBar);
		// setLanguageListener(menuLanguage);
		

	}
	
//	public ArrayList<Display> getDisplayList(){
//		return view
//	}
	public void setMenuPlusHandler(EventHandler<ActionEvent> handler){
		plus.getMenuAdd().setOnAction(handler);
	}
	public void addMenuPlusItem(MenuItem tab){
		plus.getMenuPlus().getItems().add(tab);
	} 
	
	public void setDisplay(int index){
		view.setActiveDisplay(index);
	}
	
	public MenuBar getToolBar() {
		return toolBar;
	}
	
	
	

}
