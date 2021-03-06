package _main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Inventory;
import models.Part;
import models.Product;

public class AddProductController {
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
        /**
         * initializes the tables
         */
        setPartsTable();
        setAssociatedPartsTable();

        /**
         * Set buttons to being enabled only if item is selected
         */
        partsTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                System.out.println(partsTable.getSelectionModel().getSelectedItem());
                // add and delete buttons are only enabled if an item is selected
                addAssociatedPartButton.setDisable(false);
            }
        });
        associatedPartsTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                removeAssociatedPartButton.setDisable(false);
            }
        });
    }



    /**
     * Number generator for products
     */
    public static int productId = 0;
    public static int generateProductId(){
        productId++;
        return productId;
    }


    public ObservableList<Part> associatedPartsList = FXCollections.observableArrayList();

    /**
     * @setPartsTable
     * sets up the total parts table
     */
    public void setPartsTable(){
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getItems().setAll(Inventory.getInstance().getAllParts());
    }

    /**
     * @setAssociatedPartsTable()
     * sets up the associated parts table
     */
    public void setAssociatedPartsTable(){
        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.getItems().setAll(associatedPartsList);
    }

    /**
     * @addAssociatedPart()
     * adds an associated part to the product's associated part attribute
     */
    public void addAssociatedPart(){

        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.add(selectedPart);
        setAssociatedPartsTable();
    }

    /**
     * removes an associated part to the product
     */
    public void removeAssociatedPart(){
        Part selectedPart = associatedPartsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.remove(selectedPart);
        setAssociatedPartsTable();
    }

    /**
     * saveProduct() method gets all the current data entered into the fields and adds a new
     * product to the inventory based on that, with data type validation"
     */
    public void saveProduct(){
        // initializing all form variables in broad scope for validation handling
        int id = 0; String name = ""; int stock = 0; double price = 0; int max = 0; int min = 0;

        id = generateProductId();
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

        Product newProduct = new Product(associatedPartsList, id, name, price, stock, min, max);

        Main.inv.addProduct(newProduct);
        closeWindow();


    }

    /**
     * generate error alerts with text parameter
     * @param errorText
     */
    public void generateError(String errorText){
        Alert inputValError = new Alert(Alert.AlertType.WARNING, errorText, ButtonType.OK);
        inputValError.show();
    }


    /**
     * close window
     */
    public void closeWindow(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * handles keyboard input events into the search field
     */
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

}
