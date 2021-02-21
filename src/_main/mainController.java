package _main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import models.Inventory;
import models.Part;


/**
 * This is the main form's (default view) controller,
 * which allows user interaction to control the other _main.views of the program.
 */

public class mainController {
    @FXML
    private Button exitButton;
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
    private Button modifyPartButton;
    @FXML
    private Button deletePartButton;
    @FXML
    public void initialize(){
     //   modifyButton.setDisable(true);
        deletePartButton.setDisable(true);
        modifyPartButton.setDisable(true);
        setPartsTable();

        /** this sets an event listener to the table to detect if an item is selected
         *
         */
        partsTable.setOnMouseClicked((MouseEvent event) -> {
            if(event.getButton().equals(MouseButton.PRIMARY)){
                System.out.println(partsTable.getSelectionModel().getSelectedItem());
                // modify and delete buttons are only enabled if an item is selected
                deletePartButton.setDisable(false);
                modifyPartButton.setDisable(false);
            }
        });


    }


    public void deletePart(){
        Part selectedPartIndex = partsTable.getSelectionModel().getSelectedItem();
        System.out.println(selectedPartIndex);
        if (confirmationMessage("Are you sure you want to delete this part?")) {
            Main.inv.deletePart(selectedPartIndex);
        }
        setPartsTable();
    }




    /**
     * Error: "(JavaFX) IllegalStateException: Location not set"
     *
     * Problem: The views, models, and controllers were all in
     * separate packages that were outside of scope of one another,
     * so the fxml file on line 32 was not getting picked up even
     * though file path was correct.
     *
     * Resolution: Modify project structure to include controllers and views in the same directory.
     * @throws IOException
     */
    public void openAddPart() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setOnHiding(event -> setPartsTable()); // refreshes table

        // TODO (optional): Persistent data storage and retrieval not part of requirements.
        // Inventory.getInstance().loadData();
    }

    /**Error:
     * Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
     * @throws IOException
     *
     * Resolution: fxml view needed a controller to be correctly connected.
     */
    public void openModifyPart() throws Exception {
//        if (itemSelected )
//        {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
//        }else{
    //        Alert a = new Alert(AlertType.WARNING, "Please select part first to modify");
//            a.show();}
        //            /**
        //             * OK to have alert here, but better UX if button is disabled until an item is selected

    }


    public void openAddProduct() throws Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        // TODO: set this up
       // stage.setOnHiding(event -> setProductsTable());
    }


    /*
    Error: RuntimeException java.lang.reflect.InvocationTargetException
    Cause Theory: Unknown... literally just doing the same thing all 3 other similar functions are doing
    Solution: ModifyProductController was not hooked up to the right .fxml file
     */
    public void openModifyProduct() throws  Exception{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyProduct.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }






    public void closeWindow(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }



    public void setPartsTable(){
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.getItems().setAll(Inventory.getInstance().getAllParts());
    }


    private boolean confirmationMessage(String notificationText){
        Alert error = new Alert(Alert.AlertType.CONFIRMATION, notificationText, ButtonType.YES, ButtonType.NO);
        error.showAndWait();
        return error.getResult() == ButtonType.YES;
    }
}
