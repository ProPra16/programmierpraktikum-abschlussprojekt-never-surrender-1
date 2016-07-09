package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.web.HTMLEditor;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MainWindowController {
	@FXML HTMLEditor test;
	@FXML HTMLEditor code;
	
	@FXML protected void testCode(ActionEvent event) {
		test.setDisable(true);
		code.setDisable(false);
	}
	
	@FXML protected void checkTest(ActionEvent event) {
		test.setDisable(true);
		code.setDisable(false);
	}
}
