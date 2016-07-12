package tde.core;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import tde.gui.MWController;
import tde.file.OptionsFactory;

/**
 * Hauptklasse der ganzen Anwendung
 * l?dt die fxml-Datei f?r die gui
 */

public class TestDrivenEnviroment extends Application {
	private TDEDataStore dataStore = new TDEDataStore();

	static void init(String[] args){
		launch(args);
	}

	@Override
    public void start(Stage stage) {
		Scene scene;

		//TODO wenn options.tde mit workspace nicht vorhanden
		if(true){
			DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Workspace angeben");
            File file = directoryChooser.showDialog(null);
            if(file != null)
                dataStore.workspace = file.getPath();		
            	OptionsFactory.createOptions(file.getPath());
            else
				System.exit(0);
		}

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI.fxml"));
			Parent mainWindow = loader.load();
			((MWController)loader.getController()).dataStore = this.dataStore;
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