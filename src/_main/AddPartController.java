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

        String name = this.nameField.getText();
        int stock  = Integer.parseInt(this.invField.getText()) ;
        double price = Double.parseDouble(this.priceField.getText());
        int max = Integer.parseInt(this.maxField.getText());
        int min = Integer.parseInt(this.minField.getText());
        String uniqueField = this.uniqueField.getText();

        if (isOutsourced == false){
             InHouse inHousePart = new InHouse(generatePartId(), name, price, stock, min, max, Integer.parseInt(uniqueField));

            /**
             * Error: "Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException"
             * Cause Theories (deductive): Something to do with threads, handling exceptions, OOP on the static model, or maybe using the same field for both part types.
             * Solution: Needed to instantiate the instance within the singleton and create a way to access it from other the controller.
             */
            Inventory.getInstance().addPart(inHousePart);

            System.out.println(inHousePart.getId() + " " + inHousePart.getName());
            System.out.println(Inventory.getInstance().getAllParts());

        } else if (isOutsourced){
            Outsourced outsourcedPart = new Outsourced(1, name, price, stock, min, max, uniqueField);
            Inventory.getInstance().addPart(outsourcedPart);

            System.out.println(outsourcedPart.getName() + " " + outsourcedPart.getId());
        }



        this.closeWindow();
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



}
