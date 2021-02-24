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
        /**
         * Loads sample parts to the main Parts table
         */
       inv.addPart(new Outsourced(4532, "Battery",
               3.95, 6,5,20, "Toyota"));
       inv.addPart(new Outsourced(6832, "Starter",
                25.95, 3,1,20, "Mitsubishi"));
       inv.addPart(new Outsourced(4532, "Transmission",
                1000.99, 2,1,20, "Honda"));
        inv.addPart(new InHouse(4532, "Hydraulics",
                329.95, 2,1,20, 83002));

        /**
         * Loads sample products to the main Products table
         */
       inv.addProduct(new Product(33, "Camry", 21000, 2, 1, 3 ));
       inv.addProduct(new Product(47, "Prius", 23000, 2, 1, 3 ));
       inv.addProduct(new Product(47, "Tesla", 50000, 2, 1, 3 ));

        Parent main = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Inventory Management V1");
        primaryStage.setScene(new Scene(main, 1010, 375));
        primaryStage.show();

        /**
         * Feature to Add in Future Version:
         * If I were to add a feature to this application I would create an inventory backlog
         * list which would indicate which items (parts and products) that need stock replenishment;
         * Basically, when the stock on any particular item gets to be within
         * the MIN specified, that item will go to the backlog for an admin
         * to basically have a checklist of items that need to be replenished.
         *
         * This could also be automated, so that stock is always within the right amounts,
         * specified.
         */


    }

    public static void main(String[] args){
        launch(args);
    }
}
