package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import tde.core.Test;
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

	private Test tester = new Test();

	/**
	 * wird nach dem intialisieren der Klasse aufgerufen
	 */
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
				//TODO Test in .tde Datei schreiben
				int testResult = tester.run(tester.init(""));//TODO Pathname einfuegen
				code.setDisable(false);
				test.setDisable(true);
				status++;
			case 1: //code
				test.setDisable(false);
				code.setDisable(true);
				status++;
			case 2: //refactor
			default: //nichts
		}
	}
}
