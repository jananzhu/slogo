package view_panels;

import slogo_front.View;
import menu_tabs.MenuFile;
import menu_tabs.MenuHelp;
import menu_tabs.MenuLanguage;
import menu_tabs.MenuPlus;
import javafx.scene.control.MenuBar;

public class ToolBar {
	private View view;
	private MenuBar toolBar;
	
	private MenuHelp help;
	private MenuLanguage menuLanguage;
	private MenuFile file;
	private MenuPlus plus;
	

	public ToolBar(View view) {
		//assembles toolbar, assigns handlers
		
		view = view;
		
		toolBar = new MenuBar();
		// Command Reference Sheet
		help = new MenuHelp(toolBar);
		
		// Language
		menuLanguage = new MenuLanguage(toolBar);
		//file save/load
		file = new MenuFile(toolBar);
		
		plus = new MenuPlus(toolBar);

		// setLanguageListener(menuLanguage);
		

	}

	public MenuBar getToolBar() {
		return toolBar;
	}

	

}
