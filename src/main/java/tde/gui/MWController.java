package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.web.HTMLEditor;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import tde.core.TDEDataStore;
import tde.core.Test;
import tde.file.XMLParser;
import tde.timer.ITask;

import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import static tde.file.XMLParser.catalogeToCode;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MWController implements ITask{
	@FXML TextArea test;
	@FXML TextArea code;
	@FXML MenuItem newFile;
	@FXML MenuItem newProject;

	@FXML MenuItem dezimalUmwandler;
	@FXML MenuItem fizzBuzz;
	@FXML MenuItem keineDuplikate;
	@FXML MenuItem noD;
	@FXML MenuItem noName;
	@FXML MenuItem surrenderFormel;

	@FXML VBox headerVBox;
	@FXML TreeView<String> testTree;
	@FXML TreeView<String> codeTree;
	@FXML Line redLine;
	@FXML Line greenLine;
	@FXML Line blackLine;
	@FXML Label redText;
	@FXML Label greenText;
	@FXML Label blackText;
	@FXML Menu cataloge;

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
	
	@FXML protected Bloom textGlow() {
		Bloom bloom = new Bloom();
		bloom.setThreshold(0.2);
		
		return bloom;
	}

	/**
	 * Geht zum naesten Abschnitt vor. z.B. von Test schreiben zu Code schreiben
	 * Dabei wird erst geprueft, ob der naeste schritt gemacht werden darf
	 */
	public void nextTask(){
		int failedTests;
		DropShadow borderGlow = new DropShadow();
		borderGlow.setWidth(40);
		borderGlow.setHeight(40);
		
		borderGlow.setColor(Color.RED);
		redLine.setEffect(borderGlow);
		redText.setEffect(textGlow());
		greenLine.setEffect(null);
		greenText.setEffect(null);
		blackLine.setEffect(null);
		blackText.setEffect(null);

		switch (status){
			case 0: //test
				XMLParser.codeToData(dataStore, test.getText(), 0);
				//XMLParser.codeToData(dataStore.getProjectName(), dataStore.getAktivFile(), "test", 1);
				tester.init(dataStore);
				/*failedTests = tester.run();
				if(failedTests  == 0)
					showDialog("Fehler", "Alle Tests waren erfolgreich", "Bitte schreiben sie einen Test, der fehlschlägt!", Alert.AlertType.WARNING);
					//TODO test in der Datei löschen
				else if(failedTests == 1){*/
					code.setDisable(false);
					test.setDisable(true);
					borderGlow.setColor(Color.RED);
					redLine.setEffect(borderGlow);
					redText.setEffect(textGlow());
					greenLine.setEffect(null);
					greenText.setEffect(null);
					blackLine.setEffect(null);
					blackText.setEffect(null);
					status++;
				}
				break;
			case 1: //code
				//TODO Code in .tde Datei schreiben
				tester.init(dataStore);
				failedTests = tester.run();
				if(failedTests == 0) {
					test.setDisable(false);
					borderGlow.setColor(Color.GREEN);
					greenLine.setEffect(borderGlow);
					greenText.setEffect(textGlow());
					redLine.setEffect(null);
					redText.setEffect(null);
					blackLine.setEffect(null);
					blackText.setEffect(null);
					status++;
				}
				else
					showDialog("Fehler", failedTests + " sind fehlgeschlagen", "Bitte korriegieren sie ihren Code!", Alert.AlertType.WARNING);
				break;
			case 2: //refactor
				tester.init(dataStore);
				failedTests = tester.run();
				if(failedTests == 0) {
					code.setDisable(true);
					borderGlow.setColor(Color.BLACK);
					blackLine.setEffect(borderGlow);
					blackText.setEffect(textGlow());
					redLine.setEffect(null);
					redText.setEffect(null);
					greenLine.setEffect(null);
					greenText.setEffect(null);
					status = 0;
				}
				else
					showDialog("Fehler", failedTests + " sind fehlgeschlagen", "Bitte korriegieren sie ihren Code!", Alert.AlertType.WARNING);
				break;
			default: //nichts
				break;
		}
	}

	private void showDialog(String title, String header, String content, Alert.AlertType alertType){
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}
	
	@FXML protected void openNewFile(ActionEvent event) throws IOException{
		String fileName = showMSG("Neue Datei");
		File f = new File(dataStore.getProjectFolder(), fileName);
		dataStore.setAktivFile(f);
		f.createNewFile();
		TreeItem<String> fileTest = new TreeItem<>(fileName);
		fileTest.setExpanded(true);
		testTree.getRoot().getChildren().add(fileTest);
		TreeItem<String> fileCode = new TreeItem<>(fileName);
		fileCode.setExpanded(true);
		codeTree.getRoot().getChildren().add(fileCode);
	}
	
	@FXML protected void openNewProject(ActionEvent event){
		String projectName = showMSG("Neues Projekt");
		dataStore.setProjectName(projectName);
		File dir = new File(dataStore.getWorkspace() + System.getProperty("file.separator") + projectName);
		dir.mkdir();
		TreeItem<String> projectTest = new TreeItem<>(projectName);
		projectTest.setExpanded(true);
		testTree.setRoot(projectTest);
		TreeItem<String> projectCode = new TreeItem<>(projectName);
		projectCode.setExpanded(true);
		codeTree.setRoot(projectCode);
	}
	
	private String showMSG(String titel){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle(titel);
		dialog.setHeaderText(null);
		dialog.setContentText("Bitte geben sie den Namen ein:");
		
		Optional<String> result = dialog.showAndWait();

		if(result.isPresent())
			return result.get();
		else
			return "";
	}

	/**
	 * öffnet den DezimalUmwandler aus dem Katalog und gibt seinen inhalt auf die beiden fenster aus. die folgenden methoden sind dieselben, nur für die unterschiedlichen elemente des katalogs
	 * @param event damit ist der mausklick gemeint
	 * @return nüx
	 */
	@FXML protected void openDezimalUmwandler(ActionEvent event){
		do_it("DezimalUmwandler");

	}

	private void do_it(String name){
		ArrayList<String> list = catalogeToCode(name);
		code.setText(list.get(1));
		test.setText(list.get(2));
		File projectFolder = new File(dataStore.getWorkspace(), name);
		dataStore.setProjectName(name);
		dataStore.setProjectFolder(projectFolder);
		dataStore.setAktivFile(new File(projectFolder, name + ".xml"));
		dataStore.getProjectFolder().mkdirs();
		try {
			Files.copy(
                    Paths.get(getClass().getResource(String.format("/Katalog/%s.xml", name)).toURI()),
                    dataStore.getAbsoluteActiveFilePath(),
					StandardCopyOption.REPLACE_EXISTING
            );

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@FXML protected void openFizzBuzz(ActionEvent event){
		do_it("FizzBuzz");
		//So nun die beiden einträge 1 und 2 ausgeben im fenster
	}

	@FXML protected void openKeineDuplikate(ActionEvent event){
		String projectName = "KeineDuplikate";
		ArrayList<String> list = new ArrayList<String>();
		list = catalogeToCode(projectName);
		code.setText(list.get(1));
		test.setText(list.get(2));
		//So nun die beiden einträge 1 und 2 ausgeben im fenster
	}

	@FXML protected void openNoD(ActionEvent event){
		String projectName = "NoD";
		ArrayList<String> list = new ArrayList<String>();
		list = catalogeToCode(projectName);
		code.setText(list.get(1));
		test.setText(list.get(2));
		//So nun die beiden einträge 1 und 2 ausgeben im fenster
	}

	@FXML protected void openFibonaccifolge(ActionEvent event){
		String projectName = "Fibonaccifolge";
		ArrayList<String> list = new ArrayList<String>();
		list = catalogeToCode(projectName);
		code.setText(list.get(1));
		test.setText(list.get(2));
		//So nun die beiden einträge 1 und 2 ausgeben im fenster
	}

	@FXML protected void openSurrenderFormel(ActionEvent event){
		String projectName = "Surrenderformel";
		ArrayList<String> list = new ArrayList<String>();
		list = catalogeToCode(projectName);
		code.setText(list.get(1));
		test.setText(list.get(2));
		//So nun die beiden einträge 1 und 2 ausgeben im fenster
	}
}
