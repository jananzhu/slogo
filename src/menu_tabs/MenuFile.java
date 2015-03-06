package menu_tabs;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;

import slogo_xml.TypeChecker;
import slogo_xml.XMLmanager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuFile {
	Menu file;
	XMLmanager manager;
	TypeChecker checker;
	
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
				//imageURL background color language turtle count pendown linestyle
				String[] myProperties = null; // TODO michael populate from view
				String path = checker.getNewPath();
				manager.writeXML(myProperties, path);
			}
		});
		

		MenuItem load = new MenuItem("Load Display");
		load.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				 Stage fileSystem = new Stage();
				 FileChooser fileChooser = new FileChooser();
				 fileChooser.setTitle("Open Display XML File");
				 File file = fileChooser.showOpenDialog(fileSystem);
				 String filePath = file.getPath();
				 HashMap<String, String> params = manager.readXML(filePath);

				 String imageURL = params.get("imageURL");
				 Color bkgrnd = checker.getAsColor(params.get("backgroundcolor"));
				 String language = params.get("language");
				 int turtleCount = checker.getAsInt(params.get("turtlecount"));
				 boolean pendown = checker.getAsBool(params.get("line"));
				 String linestyle = params.get("linestyle");
				 
				 //TODO: michael add reference to view 
				
			}
		});

		file.getItems().addAll(save, load);
	}

}
