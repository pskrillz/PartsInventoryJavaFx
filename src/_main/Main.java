package _main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.*;

import java.io.IOException;

public class Main extends Application {

    public static Inventory inv = Inventory.getInstance();


    @Override
    public void start(Stage primaryStage) throws Exception{
    Inventory inv = Inventory.getInstance();
        // sample data
       inv.addPart(new Outsourced(4532, "Battery",
               3.95, 6,5,20, "Toyota"));
       inv.addPart(new Outsourced(6832, "Starter",
                25.95, 3,1,20, "Mitsubishi"));
       inv.addPart(new Outsourced(4532, "Transmission",
                1000.99, 2,1,20, "Honda"));
        inv.addPart(new InHouse(4532, "Hydraulics",
                329.95, 2,1,20, 83002));

       inv.addProduct(new Product(33, "Camry", 21000, 2, 1, 3 ));
       inv.addProduct(new Product(47, "Prius", 23000, 2, 1, 3 ));
       inv.addProduct(new Product(47, "Tesla", 50000, 2, 1, 3 ));

        Parent main = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory Management V1");
        primaryStage.setScene(new Scene(main, 1010, 375));
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }
}
