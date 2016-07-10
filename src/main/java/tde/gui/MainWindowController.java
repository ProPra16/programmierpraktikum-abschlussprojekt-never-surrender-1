package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import java.io.IOException;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MainWindowController {
	@FXML HTMLEditor test;
	@FXML HTMLEditor code;
	@FXML VBox headerVBox;

	//wird nach dem intialisieren der Klasse aufgerufen
	public void initialize(){
		//lädt das Babystepsmodul
		try {
			HBox babysteps = FXMLLoader.load(getClass().getResource("/Babysteps.fxml"));
			headerVBox.getChildren().add(babysteps);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@FXML protected void testCode(ActionEvent event) {
		test.setDisable(false);
		code.setDisable(true);
	}
	
	@FXML protected void checkTest(ActionEvent event) {
		code.setDisable(false);
		test.setDisable(true);
	}
}
