package _main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class AddPartController {

    @FXML
   // public RadioButton radioInHouse;
   // public RadioButton radioOutsourced;
    public Label partTypeSpecificField;


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




}
