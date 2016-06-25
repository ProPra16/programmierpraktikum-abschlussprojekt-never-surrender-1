package tde.core;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Hauptklasse der ganzen Anwendung
 * lädt die fxml-Datei für die gui
 */

public class TestDrivenEnviroment extends Application {
	@Override
    public void start(Stage primaryStage) throws Exception{
    	Parent root = new GridPane();
    	primaryStage.setTitle("TestDrivenEnviroment");
    	primaryStage.setScene(new Scene(root));
    	primaryStage.show();
    }
    
	public static void init(String[] args){
		launch(args);
	}
}
