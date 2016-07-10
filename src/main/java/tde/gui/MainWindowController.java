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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Babysteps.fxml")); //laedt das Babystepsmodul
		((BSController)loader.getController()).setParentController(this); //übergibt sich selbst dem BSController
		headerVBox.getChildren().add(loader.getRoot());
	}
	
	@FXML public void testCode(ActionEvent event) {
		test.setDisable(false);
		code.setDisable(true);
	}
	
	@FXML public void checkTest(ActionEvent event) {
		code.setDisable(false);
		test.setDisable(true);
	}
}
