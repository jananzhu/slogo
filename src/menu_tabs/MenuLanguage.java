package menu_tabs;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuLanguage {
	private Menu menuLanguage;

	// language support
	private ResourceBundle labels;
	private Locale[] supportedLocales = { Locale.ENGLISH, Locale.FRENCH };
	private Locale defaultLocale = Locale.ENGLISH;

	public MenuLanguage(MenuBar toolBar) {
		// Language
		menuLanguage = new Menu("Change Language");
		setLanguages();
		toolBar.getMenus().add(menuLanguage);
	}

	public Menu getMenu() {
		return menuLanguage;
	}

	// TODO language supports
	private void setLanguages() {
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

		menuLanguage.getItems().addAll(english, french);
	}

}
