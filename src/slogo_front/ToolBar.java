package slogo_front;

import java.util.Locale;
import java.util.ResourceBundle;

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
	// html, should be another class
	private WebView browser = new WebView();
	private Scene secondScene = new Scene(browser);

	private MenuBar toolBar;
	// language support
	private ResourceBundle labels;
	private Locale[] supportedLocales = { Locale.ENGLISH, Locale.FRENCH };
	private Locale defaultLocale = Locale.ENGLISH;

	public ToolBar() {
		toolBar = new MenuBar();
		// Command Reference Sheet
		Menu menuHelp = new Menu("Help");
		MenuItem commandReference = new MenuItem("Command Reference");
		commandReference.setOnAction(launchWebView);
		menuHelp.getItems().add(commandReference);

		// Language
		Menu menuLanguage = new Menu("Change Language");
		setLanguages(menuLanguage);
		// setLanguageListener(menuLanguage);
		toolBar.getMenus().addAll(menuHelp, menuLanguage);

	}

	protected MenuBar getToolBar() {
		return toolBar;
	}

	// TODO language supports
	private void setLanguages(Menu languageMenu) {
		MenuItem english = new MenuItem("English");
		english.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				labels = ResourceBundle
						.getBundle("resources.languages/LabelsBundle",
								supportedLocales[0]);
				// TODO setLabels();
			}
		});

		MenuItem french = new MenuItem("French");
		french.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				labels = ResourceBundle
						.getBundle("resources.languages/LabelsBundle",
								supportedLocales[1]);
				// TODO setLabels();
			}
		});

		languageMenu.getItems().addAll(english, french);
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
