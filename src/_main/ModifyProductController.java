package _main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Inventory;
import models.Part;
import models.Product;

public class ModifyProductController {
    @FXML
    private TextField partSearchField;
    @FXML
    private Button exitButton;
    @FXML
    private Button saveButton;
    @FXML
    private TableView<Part> partsTable;
    @FXML
    private TableColumn<Part, Integer> partIdColumn;
    @FXML
    private TableColumn<Part, String> partNameColumn;
    @FXML
    private TableColumn<Part, Integer> partStockColumn;
    @FXML
    private TableColumn<Part, Double> partPriceColumn;
    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productStockField;
    @FXML
    private TextField productPriceField;
    @FXML
    private TextField productMaxField;
    @FXML
    private TextField productMinField;
    @FXML
    private TableView<Part> associatedPartsTable;
    @FXML
    private TableColumn<Part, Integer> associatedPartId;
    @FXML
    private TableColumn<Part, String> associatedPartName;
    @FXML
    private TableColumn<Part, Integer> associatedPartStock;
    @FXML
    private TableColumn<Part, Double> associatedPartPrice;
    @FXML
    private Button addAssociatedPartButton;
    @FXML
    private Button removeAssociatedPartButton;
    @FXML
    public void initialize(){
        selectedProductPartsList = selectedProduct.getAllAssociatedParts();
        setPartsTable();
        setAssociatedPartsTable();
        int selectedProductId = selectedProduct.getId();
        String selectedProductName = selectedProduct.getName();
        double selectedProductPrice = selectedProduct.getPrice();
        int selectedProductStock = selectedProduct.getStock();
        int selectedProductMin = selectedProduct.getMin();
        int selectedProductMax = selectedProduct.getMax();

        productIdField.setText(String.valueOf(selectedProductId));
        productNameField.setText(selectedProductName);
        productStockField.setText(String.valueOf(selectedProductStock));
        productPriceField.setText(String.valueOf(selectedProductPrice));
        productMinField.setText(String.valueOf(selectedProductMin));
        productMaxField.setText(String.valueOf(selectedProductMax));
    }


    public static Product selectedProduct;
    public static ObservableList<Part> selectedProductPartsList;
    public static int selectedProductIndex;




    public void setPartsTable(){
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getItems().setAll(Inventory.getInstance().getAllParts());
    }


    public void setAssociatedPartsTable(){
    //    setSelectedProductPartsList(selectedProductPartsList);
        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.getItems().setAll(selectedProduct.getAllAssociatedParts());
    }


    public void addAssociatedPart(){
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        selectedProductPartsList.add(selectedPart);
        setAssociatedPartsTable();
    }

    public void removeAssociatedPart(){
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        selectedProductPartsList.remove(selectedPart);
        setAssociatedPartsTable();
    }


    public void generateError(String errorText){
        Alert inputValError = new Alert(Alert.AlertType.WARNING, errorText, ButtonType.OK);
        inputValError.show();
    }



    public void closeWindow(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void onPartSearch() {

        ObservableList<Part> results = FXCollections.observableArrayList();
        String searchValue = partSearchField.getText();

        if (searchValue.length() != 0) {

            try {
                int intSearchValue = Integer.parseInt(searchValue);
                results = Main.inv.getInstance().lookupPart(intSearchValue);

            } catch (NumberFormatException notInt) {
                results = Main.inv.getInstance().lookupPart(searchValue);
            }

            if (results.size() > 0) {
                partsTable.getItems().setAll(results);
            } else {
                partsTable.getItems().clear();

            }
        } else {
            setPartsTable();
        }

    }


    public void saveProductModifications(){
        // initializing all form variables in broad scope for validation handling
        int id = selectedProduct.getId(); String name = ""; int stock = 0; double price = 0; int max = 0; int min = 0;


        name = productNameField.getText();

        try {
            stock = Integer.parseInt(productStockField.getText());
            price = Double.parseDouble(productPriceField.getText());
            max = Integer.parseInt(productMaxField.getText());
            min = Integer.parseInt(productMinField.getText());
        } catch (NumberFormatException numError){
            generateError("Data Type Error: \n" +
                    "You entered letters in a number field \n" +
                    "Please correct your entry");
            return;
        }

        Product newProduct = new Product(selectedProductPartsList, id, name, price, stock, min, max);
        Main.inv.updateProduct(selectedProductIndex, newProduct);

        closeWindow();

    }




}
