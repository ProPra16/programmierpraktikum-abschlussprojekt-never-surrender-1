package tde.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {
	
	public static void main(String[] args) {
        Application.launch((java.lang.String[])null);
    }

    @Override
    public void start(Stage stage) {
    	BorderPane page;
		try {
			page = (BorderPane) FXMLLoader.load(getClass().getResource("GUI.fxml"));
			Scene scene = new Scene(page);
	    	stage.setScene(scene);
	    	stage.setTitle("TDDT");
	    	//stage.setMaxmized(true);
	    	stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
