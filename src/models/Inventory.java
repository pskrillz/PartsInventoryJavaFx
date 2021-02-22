package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Singleton class to hold all the data for parts and products.
 */


public class Inventory {

   private static String inventoryData = "inventoryData.txt";

    /**
     * The next few lines instantiates the singleton so the methods can be used in other controllers.
     */
    private static Inventory inv = new Inventory(null, null);

    /**
     * selects the singleton object with proper encapsulation
     * @return
     */

    public static Inventory getInstance() {
        return inv;
    }



    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    private Inventory(ObservableList<Part> allParts, ObservableList<Product> allProducts) {
        this.allParts = allParts;
        this.allProducts = allProducts;
    }

    /**
     * Add new part to inventory
     *
     * @param newPart
     */

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /**
     * Add new product to inventory
     *
     * @param newProduct
     */
    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /**
     * Sample flat file and parser for persistent data storage.
     * Errors: NoSuchFileException and RuntimeException
     * @throws IOException
     */

    public void loadData() throws IOException{
        Path filePath = Paths.get(inventoryData);
        BufferedReader br = Files.newBufferedReader(filePath);

        String input;

        try {
            while((input = br.readLine()) !=null){
                String[] data = input.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]) ;
                int stock = Integer.parseInt(data[3]);
                int min = Integer.parseInt(data[4]) ;
                int max = Integer.parseInt(data[5]) ;
                int uniqueField = Integer.parseInt(data[6]);

                // lets just do it for InHouse for  now

                Part part = new InHouse(id, name, price, stock, min, max, uniqueField);
                Inventory.getInstance().addPart(part);

                System.out.println(part.getName());


            }

        } finally {
            if(br != null){
                br.close();
            }

        }



    }




    /**
     * Look up by string name and return list
     *
     * @param partName
     * @return
     */

    public ObservableList<Part> lookupPart(String partName) {
        ObservableList<Part> results = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getName().contains(partName)) {
                results.add(part);
            }
        }
        return results;
    }


    public ObservableList<Part> lookupPart(int partId) {
        ObservableList<Part> results = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (part.getId() == partId) {
                results.add(part);
            }
        }
        return results;
    }



    public ObservableList<Product> lookupProduct(String productName) {
        ObservableList<Product> results = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (product.getName().contains(productName))
                results.add(product);
        }
        return results;
    }






    /**
     * Update parts and products (modify)
     *
     * @param index
     * @param selectedPart
     */

    public void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /**
     * Delete parts and products
     *
     * @param part
     * @return
     */



    public void deletePart(Part part) {
        allParts.remove(part);
    }

    public void deleteProduct(Product product) {
        allProducts.remove(product);
    }

    /**
     * Get all parts
     *
     * @return
     */

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Get all products
     *
     * @return
     */

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }


}
