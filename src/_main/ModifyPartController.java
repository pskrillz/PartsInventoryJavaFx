package _main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InHouse;
import models.Inventory;
import models.Outsourced;

import java.io.IOException;

public class ModifyPartController {


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

    public boolean isOutsourced = false;

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


    // TODO edit for modifyPart specificity

    @FXML
    public void modifyPart() throws IOException {

        /*
        String name = this.nameField.getText();
        int stock  = Integer.parseInt(this.invField.getText()) ;
        double price = Double.parseDouble(this.priceField.getText());
        int max = Integer.parseInt(this.maxField.getText());
        int min = Integer.parseInt(this.minField.getText());
        String uniqueField = this.uniqueField.getText();

        if (isOutsourced == false){
            InHouse inHousePart = new InHouse(generatePartId(), name, price, stock, min, max, Integer.parseInt(uniqueField));
            Inventory.getInstance().addPart(inHousePart);

            System.out.println(inHousePart.getId() + " " + inHousePart.getName());
            System.out.println(Inventory.getInstance().getAllParts());

        } else if (isOutsourced){
                Outsourced outsourcedPart = new Outsourced(1, name, price, stock, min, max, uniqueField);
                Inventory.getInstance().addPart(outsourcedPart);
                System.out.println(outsourcedPart.getName() + " " + outsourcedPart.getId());
        }

         */

        this.closeWindow();
    }




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