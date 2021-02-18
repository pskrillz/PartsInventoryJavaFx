package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This is the class attributes, which has an observableArrayList of class Part that it is composed of.
 */

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;


    public Product(ObservableList<Part> associatedParts, int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = associatedParts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Getters
     */

    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    /**
     * Setters
     */

    public void setAssociatedParts(ObservableList<Part> associatedParts) {
        this.associatedParts = associatedParts;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Associated Parts list manipulation
     */

    public void addAssociatedPart(Part part){
        this.associatedParts.add(part);
    }

    // TODO: check if works, doc says its boolean
    public boolean deleteAssociatedPart(Part part){
        this.associatedParts.remove(part);
        return true;
    }


}
