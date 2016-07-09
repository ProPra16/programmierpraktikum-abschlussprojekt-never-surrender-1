package tde.core;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hauptklasse der ganzen Anwendung
 * lÃ¤dt die fxml-Datei fÃ¼r die gui
 */

public class TestDrivenEnviroment extends Application {
	static void init(String[] args){
		launch(args);
	}

	@Override
    public void start(Stage stage) {
		Scene scene;

		//TODO wenn options.tde mit workspace nicht vorhanden
		if(true){
			/*try{
				Parent workspace = FXMLLoader.load(getClass().getResource("/Workspace.fxml"));
				scene = new Scene(workspace);
				stage.setScene(scene);
				stage.setTitle("Workspace eingeben");  			//Wird später gelöscht noch nicht jetzt aber
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}

		try {
			Parent mainWindow = FXMLLoader.load(getClass().getResource("/GUI.fxml"));
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