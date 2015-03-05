package menu_tabs;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MenuHelp {
	// html, should be another class
		private WebView browser = new WebView();
		private Scene secondScene = new Scene(browser);
		
		private Menu menuHelp;

	public MenuHelp(MenuBar toolBar) {
		menuHelp = new Menu("Help");
		MenuItem commandReference = new MenuItem("Command Reference");
		commandReference.setOnAction(launchWebView);
		menuHelp.getItems().add(commandReference);
		toolBar.getMenus().add(menuHelp);
	}
	
	public Menu getHelp(){
		return menuHelp;
	}
	
	private EventHandler<ActionEvent> launchWebView = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent event) {

			browser.getEngine().load(
					getClass().getResource("/html/english.html")
							.toExternalForm());
			Stage popUp = new Stage();
			popUp.setScene(secondScene);

			popUp.show();
		}

	};

}
