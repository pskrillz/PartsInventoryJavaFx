package _main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
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
    private Button modifyButton;
    @FXML
    private Button deleteButton;
    @FXML
    public void initialize(){
     //   modifyButton.setDisable(true);
        deleteButton.setDisable(true);
        setPartsTable();
    }



    public boolean itemSelected = false;



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
        stage.setOnHiding(event -> setPartsTable()); // update the table

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
//        }
//        else
//        {
//            /**
//             * OK to have alert here, but better UX if button is disabled until they select
//             */
//            Alert a = new Alert(AlertType.WARNING, "Please select part first to modify");
//
//            a.show();
//        }
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


}
