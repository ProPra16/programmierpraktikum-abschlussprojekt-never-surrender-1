package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Controller für die eigentliche Benutzeroberfläche
 */

public class MainWindowController {
	@FXML protected void handleLoadButtonAction(ActionEvent event) {
		boolean accept = true;
		TestDrivenEnviroment.load(accept);
	}
}
