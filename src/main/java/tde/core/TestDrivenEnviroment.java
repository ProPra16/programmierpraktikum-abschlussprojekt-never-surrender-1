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
	private boolean path = false;
	private Stage work = new Stage();
	private Stage main = new Stage();

	static void init(String[] args){
		launch(args);
	}

	public void load(boolean accept) {
		path = accept;
		work.close();
		main.show();
	}

	@Override
    public void start(Stage stage) {
		Scene scene;

		//TODO wenn options.tde mit workspace nicht vorhanden
		if(true){
			try{
				Parent workspace = FXMLLoader.load(getClass().getResource("../gui/Workspace.fxml"));
				scene = new Scene(workspace);
				stage.setScene(scene);
				stage.setTitle("Workspace eingeben");
				stage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			Parent mainWindow = FXMLLoader.load(getClass().getResource("../gui/GUI.fxml"));
			scene = new Scene(mainWindow);
			stage.setScene(scene);
			stage.setTitle("TDE");
			stage.setMaximized(true);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}