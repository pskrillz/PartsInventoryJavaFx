package _main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.InHouse;
import models.Outsourced;
import models.Inventory;

import java.io.IOException;

public class AddPartController {

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
     * This method saves all the input fields, and
     * casts the data types to the right types for
     * each associated part's class constructors.
     */
@FXML
    public void savePart() throws IOException {
    // initializing variables in function scope before error handlers.
        String name; int stock = 0; double price = 0; int max = 0; int min = 0; String uniqueField = ""; int machineId = 0;
        // condition that prevents saving an incorrect entry on error
        boolean errorExitSave = false;

        name = this.nameField.getText();

    // validation to make sure that number fields don't receive string input
        try {
            stock  = Integer.parseInt(this.invField.getText());
            price = Double.parseDouble(this.priceField.getText());
            max = Integer.parseInt(this.maxField.getText());
            min = Integer.parseInt(this.minField.getText());
        } catch (NumberFormatException err){
            generateError("You entered letters in a number field\n" +
                    "Please correct your input before continuing");
            errorExitSave = true;
        }


      //  uniqueField = this.uniqueField.getText();


        if (isOutsourced == false && !errorExitSave){

            try {
                machineId = Integer.parseInt(this.uniqueField.getText());
            } catch (NumberFormatException err){
                generateError("You entered letters in a number field \n" +
                        "Please correct your input before continuing");
                return; // another way of exiting process on error
            }

             InHouse inHousePart = new InHouse(generatePartId(), name, price, stock, min, max, machineId);

            /**
             * Error: "Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException"
             * Cause Theories (deductive): Something to do with threads, handling exceptions, OOP on the static model, or maybe using the same field for both part types.
             * Solution: Needed to instantiate the instance within the singleton and create a way to access it from other the controller.
             */
            Inventory.getInstance().addPart(inHousePart);

            System.out.println(inHousePart.getId() + " " + inHousePart.getName());
            System.out.println(Inventory.getInstance().getAllParts());

            this.closeWindow();

        } else if (isOutsourced && !errorExitSave){
            uniqueField = this.uniqueField.getText();
            Outsourced outsourcedPart = new Outsourced(1, name, price, stock, min, max, uniqueField);
            Inventory.getInstance().addPart(outsourcedPart);

            System.out.println(outsourcedPart.getName() + " " + outsourcedPart.getId());

            this.closeWindow();
        }



    }

    /**
     * Bug: "Non-static variable cannot be referenced from a static context"
     * Problem: Trying to access method to generate and keep track of ID's for parts and products persistently saved in memory.
     * Resolution: Solution is to create a static variable-method pair to be shared between all members of the class.
     */

   public static int partId = 0;
    public static int generatePartId(){
        partId++;
        return partId;
    }




    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void generateError(String errorText){
        Alert inputValError = new Alert(Alert.AlertType.WARNING, errorText, ButtonType.OK);
        inputValError.show();
    }



}
