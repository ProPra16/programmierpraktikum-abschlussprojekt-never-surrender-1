package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import tde.timer.ITask;

import java.io.IOException;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MWController implements ITask{
	@FXML HTMLEditor test;
	@FXML HTMLEditor code;
	@FXML VBox headerVBox;
	private int status = 0;

	//wird nach dem intialisieren der Klasse aufgerufen
	public void initialize() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Babysteps.fxml")); //laedt das Babystepsmodul
			loader.load();
			((BSController)loader.getController()).setParentController(this); //übergibt sich selbst dem BSController
			headerVBox.getChildren().add(loader.getRoot());
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	@FXML protected void testCode(ActionEvent event) {
		nextTask();
	}
	
	@FXML protected void checkTest(ActionEvent event) {
		nextTask();
	}

	/**
	 * Geht zum naesten Abschnitt vor. z.B. von Test schreiben zu Code schreiben
	 * Dabei wird erst geprueft, ob der naeste schritt gemacht werden darf
	 */
	public void nextTask(){
		switch (status){
			case 0: //test

				code.setDisable(false);
				test.setDisable(true);
				status++;
			case 1: //code
				test.setDisable(false);
				code.setDisable(true);
				//TODO aendern, dass es entweder in refactor oder test geht
				status = 1;
			case 2: //refactor
			default: //nichts
		}
	}
}
