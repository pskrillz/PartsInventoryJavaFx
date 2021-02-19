package _main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InHouse;
import models.Outsourced;
import models.Inventory;
import _main.numberGenerator;

public class AddPartController {

   // public int partId = -1;
    // public RadioButton radioInHouse;
    // public RadioButton radioOutsourced;

    @FXML
    private Label partTypeSpecificLabel;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField nameField;
    @FXML
    private TextField invField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField maxField;
    @FXML
    private TextField minField;
    @FXML
    private TextField uniqueField;

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
            this.partTypeSpecificLabel.setText("Company Name");
        }
    }

    public void toggleIsNotOutsourced(){
        if (isOutsourced == true){
            isOutsourced = false;
          this.partTypeSpecificLabel.setText("Machine ID");
      }
    }


    /**
     * This method saves all the input fields, and casts the data types to the right types for
     * each associated part's class constructors.
     */

    public void savePart(){


        String name = this.nameField.getText();
        int stock  = Integer.parseInt(this.invField.getText()) ;
        double price = Double.parseDouble(this.priceField.getText());
        int max = Integer.parseInt(this.maxField.getText());
        int min = Integer.parseInt(this.minField.getText());
        String uniqueField = this.uniqueField.getText();

        /**
         * Bug: "Non-static variable cannot be referenced from a static context"
         * Problem: Trying to access method to generate and keep track of ID's for parts and products saved in memory.
          */

        if (isOutsourced == false){
            // InHouse inHousePart = new InHouse(_main.numberGenerator.generatePartId(), name, price, stock, min, max, Integer.parseInt(uniqueField));
          //  System.out.println(inHousePart.getId());
          //  System.out.println(generatePartId());

        } else if (isOutsourced){
            Outsourced outsourcedPart = new Outsourced(1, name, price, stock, min, max, uniqueField);
            System.out.println(outsourcedPart);
        }



        this.closeWindow();
    }






    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }



}