package models;

/**
 * This is the InHouse class, inherited from the abstract class Part
 */


public class InHouse extends Part {

    private int machineId;

    /**
     * InHouse part constructor
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param machineId
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * get the InHouse machine ID
     * @return
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * set the InHouse machine ID
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}


