package tde.core;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    public void start(Stage stage) throws Exception {

		Scene scene;

		//TODO wenn options.tde mit workspace nicht vorhanden
		//if(OptionsFactory.loadPath().equals("")){
			DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Workspace angeben");
            File file = directoryChooser.showDialog(null);
            if(file != null) {
				dataStore.setWorkspace(file);
				OptionsFactory.createOptions(file.getPath());
			} else
				System.exit(0);
		//}

		try {
			//System.out.println("Bin in before fxml");
			URL url = getClass().getResource("/GUI.fxml");
			FXMLLoader loader = new FXMLLoader(url);
			//System.out.println("Bin in before loader");
			Parent mainWindow = loader.load();
			//System.out.println("Bin in after loader");
			((MWController)loader.getController()).dataStore = this.dataStore;

			scene = new Scene(mainWindow);
			stage.setScene(scene);
			stage.setTitle("TDE");
			//stage.setMaximized(true);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}