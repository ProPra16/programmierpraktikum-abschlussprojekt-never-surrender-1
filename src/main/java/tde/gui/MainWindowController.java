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
	@FXML HTMLEditor text1;
	@FXML HTMLEditor text2;
	
	@FXML protected void testCode(ActionEvent event) {
		text1.setDisable(true);
		text2.setDisable(false);
	}
	
	@FXML protected void checkTest(ActionEvent event) {
		text2.setDisable(true);
		text1.setDisable(false);
	}
}
