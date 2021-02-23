package _main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import models.Inventory;
import models.Part;

public class AddProductController {
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
    private TextField productIdColumn;
    @FXML
    private TextField productNameColumn;
    @FXML
    private TextField productStockColumn;
    @FXML
    private TextField productPriceColumn;
    @FXML
    private TextField productMaxColumn;
    @FXML
    private TextField productMinColumn;
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
        setPartsTable();
        setAssociatedPartsTable();
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


    public void setPartsTable(){
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getItems().setAll(Inventory.getInstance().getAllParts());
    }


    public void setAssociatedPartsTable(){
        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartsTable.getItems().setAll(associatedPartsList);
    }


    public void addAssociatedPart(){
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.add(selectedPart);
        setAssociatedPartsTable();
    }

    public void removeAssociatedPart(){
        Part selectedPart = partsTable.getSelectionModel().getSelectedItem();
        associatedPartsList.remove(selectedPart);
        setAssociatedPartsTable();
    }


}
