package tde.core;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Hauptklasse der ganzen Anwendung
 * lädt die fxml-Datei für die gui
 */

public class TestDrivenEnviroment extends Application {
	static boolean path = false;
	static Stage work = new Stage();
	static Stage main = new Stage();
	
	public static void main(String[] args) {
        Application.launch((java.lang.String[])null);
    }
	
	public static void load(boolean accept) {
		path = accept;
		work.close();
		main.show();
	}
	
	@Override
    public void start(Stage stage) {
		BorderPane page;
		Pane pane;
		Scene scene;
		
		try {
			page = (BorderPane) FXMLLoader.load(getClass().getResource("../gui/GUI.fxml"));
			scene = new Scene(page);
			main.setScene(scene);
			main.setTitle("TDDT");
			main.setMaximized(true);
			main.hide();
			
			pane = FXMLLoader.load(getClass().getResource("../gui/Workspace.fxml"));
			scene = new Scene(pane);
			work.setScene(scene);
			work.setTitle("Workspace");
			work.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
}