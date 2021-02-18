package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * add new parts and products to inventory
     * @param newPart
     */

    public void addPart(Part newPart){
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * look up part else return null
     * @param partId
     * @return
     */
    public Part lookupPart(int partId){
        for (Part part : allParts){
            if (part.getId() == partId)
                return part;
        }
        return null;
    };

    /**
     * look up product else return null
     * @param productId
     * @return
     */

    public Product lookupProduct(int productId){
        for (Product product : allProducts){
            if (product.getId() == productId)
                return product;
        }
        return null;
    }

    /**
     * Look up by string name and return list
     * @param partName
     * @return
     */

    public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> results = FXCollections.observableArrayList();
        for (Part part : allParts){
            if (part.getName().equals(partName)){
                results.add(part);
            }
        }
        return results;
    }

    public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> results = FXCollections.observableArrayList();
        for (Product product : allProducts){
            if (product.getName().equals(productName))
                results.add(product);
        }
        return results;
    }

    /**
     * Update parts and products (modify)
     * @param index
     * @param selectedPart
     */

    public void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    public void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    }

    /**
     * Delete parts and products
     * @param part
     * @return
     */


// TODO: Check if working
    public boolean deletePart(Part part){
        allParts.remove(part);
        return true;
    }

    public boolean deleteProduct(Product product){
        allProducts.remove(product);
        return true;
    }

    /**
     * Get all parts
     * @return
     */

    public ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Get all products
     * @return
     */

    public ObservableList<Product> getAllProducts(){
        return allProducts;
    }

}
