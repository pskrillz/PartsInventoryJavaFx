package _main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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

    static int selectedPart;

    public boolean isOutsourced;

    public void toggleIsOutsourced(){
        radioOutsourced.setSelected(true);
        this.partTypeSpecificLabel.setText("Company Name");


    }

    public void toggleIsNotOutsourced(){
        radioInHouse.setSelected(true);
        this.partTypeSpecificLabel.setText("Machine ID");

    }






    static void setSelectedPart(int index){
        selectedPart = index;
    }

    // TODO: Need to do validation

    @FXML
    public void saveModifications() throws IOException {
        setSelectedPart(selectedPart);

        String name = this.nameField.getText();
        int stock  = Integer.parseInt(this.invField.getText()) ;
        double price = Double.parseDouble(this.priceField.getText());
        int max = Integer.parseInt(this.maxField.getText());
        int min = Integer.parseInt(this.minField.getText());
        String uniqueField = this.uniqueField.getText();

        if (isOutsourced == false){
            InHouse inHousePart = new InHouse(generatePartId(), name, price, stock, min, max, Integer.parseInt(uniqueField));
            Inventory.getInstance().updatePart(selectedPart, inHousePart);

            System.out.println(inHousePart.getId() + " " + inHousePart.getName());
            System.out.println(Inventory.getInstance().getAllParts());

        } else if (isOutsourced){
                Outsourced outsourcedPart = new Outsourced(1, name, price, stock, min, max, uniqueField);
                Inventory.getInstance().updatePart(selectedPart, outsourcedPart);
                System.out.println(outsourcedPart.getName() + " " + outsourcedPart.getId());
        }



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