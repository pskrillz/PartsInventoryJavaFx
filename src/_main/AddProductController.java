package _main;

public class AddProductController {


    /**
     * Number generator
     */
    public static int productId = 0;
    public static int generateProductId(){
        productId++;
        return productId;
    }
}
