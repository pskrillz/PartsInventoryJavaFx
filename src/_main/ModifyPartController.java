package _main;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.InHouse;
import models.Inventory;
import models.Outsourced;
import models.Part;

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
    @FXML
    private RadioButton radioInHouse;
    @FXML
    private TextField partIdField;
    @FXML
    private RadioButton radioOutsourced;
    @FXML
    public void initialize(){

        /**
         * These methods checks to see whether the selectedPart is of which
         * part-type class, and modifies the form based on that.
         */
        if(Main.inv.getAllParts().get(selectedPart) instanceof InHouse){
            toggleIsNotOutsourced();
            int selectedMachineID = ((InHouse) Main.inv.getAllParts().get(selectedPart)).getMachineId();
            uniqueField.setText(String.valueOf(selectedMachineID));
        }
        if(Main.inv.getAllParts().get(selectedPart) instanceof Outsourced){
            toggleIsOutsourced();
            String selectedCompanyName = ((Outsourced) Main.inv.getAllParts().get(selectedPart)).getCompanyName();
            uniqueField.setText(selectedCompanyName);
        }
        /**
         *  Gets the selectedPart's data and populates the textFields with it
         */
        int selectedPartId = Main.inv.getAllParts().get(selectedPart).getId();
        String selectedPartName = Main.inv.getAllParts().get(selectedPart).getName();
        double selectedPartPrice = Main.inv.getAllParts().get(selectedPart).getPrice();
        int selectedPartStock = Main.inv.getAllParts().get(selectedPart).getStock();
        int selectedPartMin = Main.inv.getAllParts().get(selectedPart).getMin();
        int selectedPartMax = Main.inv.getAllParts().get(selectedPart).getMax();

        partIdField.setText(String.valueOf(selectedPartId));
        nameField.setText(selectedPartName);
        invField.setText(String.valueOf(selectedPartStock));
        priceField.setText(String.valueOf(selectedPartPrice));
        minField.setText(String.valueOf(selectedPartMin));
        maxField.setText(String.valueOf(selectedPartMax));
    }

    /**
     * selectedPart passed in from main Controller
     */
    static int selectedPart;

    /**
     * boolean state tracker of whether the product is outsourced or not
     * and the ability to toggle between
     */
    public boolean isOutsourced;

    public void toggleIsOutsourced(){
        isOutsourced = true;
        radioOutsourced.setSelected(true);
        this.partTypeSpecificLabel.setText("Company Name");


    }

    public void toggleIsNotOutsourced(){
        isOutsourced = false;
        radioInHouse.setSelected(true);
        this.partTypeSpecificLabel.setText("Machine ID");

    }


    /**
     * @saveModifications()
     * Gets all the inputted textField data and parses it into the right data type for the
     * part type, includes EAFP (easier to ask for forgiveness than permission) error handling
     * @throws IOException
     */
    @FXML
    public void saveModifications() throws IOException {
        String name = ""; int stock = 0; double price = 0; int max = 0; int min = 0; String uniqueField = ""; int machineId = 0;


       name = this.nameField.getText();

       try {
           stock = Integer.parseInt(this.invField.getText());
           price = Double.parseDouble(this.priceField.getText());
           max = Integer.parseInt(this.maxField.getText());
           min = Integer.parseInt(this.minField.getText());
       } catch (NumberFormatException err){
           generateError("You entered letters in a number field\n" +
                   "Please correct your input before continuing");
          return;
       }

       uniqueField = this.uniqueField.getText();

        /**
         * generates "Inventory Errors" when the min max and current inventory levels
         * don't follow proper logic
         */
        if (!(stock >= min) || !(stock <= max)){
            generateError("Inventory Error: \n" +
                    "Current stock must be greater than minimum supply \n" +
                    "and less than maximum supply");
            return;
        }

        if(min > max || max < min){
            generateError("Inventory Error: \n" +
                    "Minimum must be less than maximum and " +
                    "maximum must be greater than minimum");
            return;
        }


        if (isOutsourced == false){
            InHouse inHousePart = new InHouse(generatePartId(), name, price, stock, min, max, Integer.parseInt(uniqueField));
            Inventory.getInstance().updatePart(selectedPart, inHousePart);

            System.out.println(inHousePart.getId() + " " + inHousePart.getName());
            System.out.println(Inventory.getInstance().getAllParts());

        } else if (isOutsourced){

                Outsourced outsourcedPart = new Outsourced(generatePartId(), name, price, stock, min, max, uniqueField);
                Inventory.getInstance().updatePart(selectedPart, outsourcedPart);
                System.out.println(outsourcedPart.getName() + " " + outsourcedPart.getId());
        }



         this.closeWindow();
    }


    /**
     * generate error alert for modifyPart
     * @param errorText
     */
    public void generateError(String errorText){
        Alert inputValError = new Alert(Alert.AlertType.WARNING, errorText, ButtonType.OK);
        inputValError.show();
    }


    /**
     * generate part Id's
     */
    public static int partId = 0;
    public static int generatePartId(){
        partId++;
        return partId;
    }


    /**
     * close window method
     */
    public void closeWindow(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }


}