package _main;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;
import models.Inventory;

import static models.Inventory.getAllParts;


/**
 * This is the main form's (default view) controller,
 * which allows user interaction to control the other _main.views of the program.
 */

public class mainController {
    @FXML
    private Button exitButton;
    @FXML
    private TableView partsTable;
    @FXML
    private TableColumn partIdColumn;
    @FXML
    private TableColumn partNameColumn;
    @FXML
    private TableColumn partStockColumn;
    @FXML
    private TableColumn partPriceColumn;






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
    }

    /**Error:
     * Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
     * @throws IOException
     *
     * Resolution: fxml view needed a controller to be correctly connected.
     */
    public void openModifyPart() throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ModifyPart.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


/*
    public void setPartsTable(){
        partsTable.setItems(getAllParts());
    }


 */




    public void closeWindow(){
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

}
