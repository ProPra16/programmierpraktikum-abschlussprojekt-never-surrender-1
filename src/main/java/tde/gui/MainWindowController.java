package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.web.HTMLEditor;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MainWindowController {
	@FXML HTMLEditor test;
	@FXML HTMLEditor code;
	@FXML TextField BSTextField;
	@FXML CheckBox BSCheckBox;
	
	@FXML protected void testCode(ActionEvent event) {
		test.setDisable(false);
		code.setDisable(true);
	}
	
	@FXML protected void checkTest(ActionEvent event) {
		code.setDisable(false);
		test.setDisable(true);
	}

	@FXML protected void toggleBabysteps(ActionEvent event){
		if(BSCheckBox.isSelected()){
			BSTextField.setDisable(false);
			startTimer();
		}
		else{
			BSTextField.setDisable(true);
			stopTimer();
		}
	}

	private void startTimer(){

	}

	private void stopTimer(){

	}
}
