package _main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.InHouse;
import models.Inventory;
import models.Outsourced;
import models.Part;

public class Main extends Application {




    @Override
    public void start(Stage primaryStage) throws Exception{


       // inv.addPart(new InHouse(1,"Screw",600.00,100,50,200,5));

        Parent main = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory Management V1");
        primaryStage.setScene(new Scene(main, 1010, 375));
        primaryStage.show();

       // public Part testPart = new InHouse("Wheel",10.50, 2, 10, 30)


    }


    public static void main(String[] args) {
        launch(args);

        Inventory inv = new Inventory(null, null);

      //  inv.addPart(new InHouse());


    }
}
