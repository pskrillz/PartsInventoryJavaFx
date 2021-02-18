package _main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class AddPartController {

    // public RadioButton radioInHouse;
    // public RadioButton radioOutsourced;

    @FXML
    private Label partTypeSpecificField;
    @FXML
    private Button cancelButton;

// In-House parts are default.
    public boolean isOutsourced = false;

    /**
     * These methods control the radio buttons
     * to toggle part type form from In-House to
     * Outsourced mode.
     */

    public void toggleIsOutsourced(){
        if (isOutsourced == false){
            isOutsourced = true;
            partTypeSpecificField.setText("Company Name");
        }
    }

    public void toggleIsNotOutsourced(){
        if (isOutsourced == true){
            isOutsourced = false;
          partTypeSpecificField.setText("Machine ID");
      }
    }


    // TODO: finish save button, but added close window to it for now
    public void savePart(){

        this.closeWindow();
    }



    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



}
