package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class WorkspaceController {
    @FXML Button accept;
    @FXML Button cancel;
    @FXML TextField filePath;

    @FXML
    public void initalize(){
        accept.setOnAction(event -> handleAccept(event));
        cancel.setOnAction(event -> System.exit(0));
    }

    private void handleAccept(ActionEvent event) {
        if(true)
            ;
            //TODO in options.tde eintragen
        else
            ;
            //TODO
    }
}
