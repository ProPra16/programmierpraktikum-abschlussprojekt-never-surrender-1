package tde.gui;

import com.sun.jnlp.ApiDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import tde.core.TDEDataStore;
import tde.core.Test;
import tde.timer.ITask;

import java.io.IOException;
import java.util.Optional;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MWController implements ITask{
	@FXML HTMLEditor test;
	@FXML HTMLEditor code;
	@FXML MenuItem newFile;
	@FXML MenuItem newProject;
	@FXML VBox headerVBox;
	private int status = 0;

	private Test tester = new Test();
	public TDEDataStore dataStore;

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
		int failedTests;

		switch (status){
			case 0: //test
				//TODO Test in .tde Datei schreiben
				failedTests = tester.run(tester.init(""));//TODO Pathname einfuegen
				if(failedTests  == 0)
					showDialog("Fehler", "Alle Tests waren erfolgreich", "Bitte schreiben sie einen Test, der fehlschlägt!", Alert.AlertType.WARNING);
					//TODO test in der Datei löschen
				else if(failedTests == 1){
					code.setDisable(false);
					test.setDisable(true);
					status++;
				}
			case 1: //code
				//TODO Code in .tde Datei schreiben
				failedTests = tester.run(tester.init(""));//TODO Pathname einfuegen
				if(failedTests == 0) {
					test.setDisable(false);
					status++;
				}
				else
					showDialog("Fehler", failedTests + " sind fehlgeschlagen", "Bitte korriegieren sie ihren Code!", Alert.AlertType.WARNING);
			case 2: //refactor
				failedTests = tester.run(tester.init(""));//TODO Pathname einfuegen
				if(failedTests == 0) {
					code.setDisable(true);
					status = 0;
				}
				else
					showDialog("Fehler", failedTests + " sind fehlgeschlagen", "Bitte korriegieren sie ihren Code!", Alert.AlertType.WARNING);
			default: //nichts
		}
	}

	private void showDialog(String title, String header, String content, Alert.AlertType alertType){
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}
	
	@FXML protected void openNewFile(ActionEvent event) {
		showMSG("Neue Datei");
	}
	
	@FXML protected void openNewProjekt(ActionEvent event){
		showMSG("Neues Projekt");
	}
	
	private void showMSG(String titel){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(titel);
		dialog.setHeaderText(null);
		dialog.setContentText("Bitte geben sie den Namen ein:");
		
		Optional<String> result = dialog.showAndWait();
	}
}
