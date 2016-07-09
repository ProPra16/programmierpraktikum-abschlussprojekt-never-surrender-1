package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import tde.core.TestDrivenEnviroment;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MainWindowController {
	@FXML TextArea text1;
	@FXML TextArea text2;
	
	@FXML protected void testCode(ActionEvent event) {
		Region region1 = (Region) text1.lookup(".content");
		Region region2 = (Region) text2.lookup(".content");
		
		region1.setStyle("-fx-background-color: gray");
		text1.setDisable(true);
		region2.setStyle("-fx-background-color: white");
		text2.setDisable(false);
	}
	
	@FXML protected void checkTest(ActionEvent event) {
		Region region1 = (Region) text1.lookup(".content");
		Region region2 = (Region) text2.lookup(".content");
		
		region2.setStyle("-fx-background-color: gray");
		text2.setDisable(true);
		region1.setStyle("-fx-background-color: white");
		text1.setDisable(false);
	}
}
