package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

import java.util.TimerTask;

/**
 * Controller von Babysteps
 */
public class BSController{
    @FXML
    TextField BSTextField;
    @FXML
    CheckBox BSCheckBox;
    private MainWindowController controller;

    public BSController(){

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

    void setParentController(MainWindowController controller){
        this.controller = controller;
    }
}
