package tde.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import tde.timer.TaskTimer;

import java.util.Timer;

/**
 * Controller von Babysteps
 */
public class BSController{
    @FXML
    TextField BSTextField;
    @FXML
    CheckBox BSCheckBox;
    private MainWindowController controller;
    private Timer timer = new Timer();

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

    /**
     * startet einen Timer, der nach x Sekunden, die im BSTextField stehen die Methode nextTask in MainWindowController aufruft
     */
    private void startTimer(){
        timer.schedule(new TaskTimer(controller), Integer.parseInt(BSTextField.getText())*1000);
    }

    /**
     * stoppt die laufende aufgabe in timer
     */
    private void stopTimer(){
        timer.cancel();
    }

    void setParentController(MainWindowController controller){
        this.controller = controller;
    }
}
