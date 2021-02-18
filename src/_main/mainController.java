package _main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This is the main form's (default view) controller,
 * which allows user interaction to control the other _main.views of the program.
 */

public class mainController {

    public void openAddPart() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

    }

}
