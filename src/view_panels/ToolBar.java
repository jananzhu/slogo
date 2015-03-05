package view_panels;

import java.util.Locale;
import java.util.ResourceBundle;

import menu_tabs.MenuFile;
import menu_tabs.MenuHelp;
import menu_tabs.MenuLanguage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ToolBar {

	private MenuBar toolBar;
	

	public ToolBar() {
		toolBar = new MenuBar();
		// Command Reference Sheet
		MenuHelp help = new MenuHelp(toolBar);
		
		// Language
		MenuLanguage menuLanguage = new MenuLanguage(toolBar);
		//file save/load
		MenuFile file = new MenuFile(toolBar);

		// setLanguageListener(menuLanguage);
		

	}

	public MenuBar getToolBar() {
		return toolBar;
	}

	

}
