package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import tde.timer.ITask;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MainWindowController implements ITask{
	@FXML HTMLEditor test;
	@FXML HTMLEditor code;
	@FXML VBox headerVBox;

	//wird nach dem intialisieren der Klasse aufgerufen
	public void initialize(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Babysteps.fxml")); //laedt das Babystepsmodul
		((BSController)loader.getController()).setParentController(this); //übergibt sich selbst dem BSController
		headerVBox.getChildren().add(loader.getRoot());
	}
	
	@FXML protected void testCode(ActionEvent event) {
		test.setDisable(false);
		code.setDisable(true);
	}
	
	@FXML protected void checkTest(ActionEvent event) {
		code.setDisable(false);
		test.setDisable(true);
	}

	/**
	 * Geht zum naesten Abschnitt vor. z.B. von Test schreiben zu Code schreiben
	 * Dabei wird erst geprueft, ob der naeste schritt gemacht werden darf
	 */
	public void nextTask(){

	}
}
