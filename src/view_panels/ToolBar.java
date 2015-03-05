package view_panels;

import menu_tabs.MenuFile;
import menu_tabs.MenuHelp;
import menu_tabs.MenuLanguage;
import menu_tabs.MenuPlus;
import javafx.scene.control.MenuBar;

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
		
		MenuPlus plus = new MenuPlus(toolBar);

		// setLanguageListener(menuLanguage);
		

	}

	public MenuBar getToolBar() {
		return toolBar;
	}

	

}
