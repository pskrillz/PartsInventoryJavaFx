package models;

/**
 * This is the Outscourced class, inherited from the abstract class Part
 */

public class Outsourced extends Part{

    /**
     * Outscourced part's name
     */
    private String companyName;

    /**
     * Outsoutced part-type's constructor
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * get outscourced part's company name
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * set outscourced part's company name
     * @return
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
