package product;

/**
 * Represents an armor product sold in the store.
 *
 * @author Shawn Kripner
 */
public class Armor extends SalableProduct {

    private int defense;
    private String material;

    /**
     * Creates an empty armor item.
     * Required for JSON deserialization.
     */
    public Armor() {
        super();
        this.defense = 0;
        this.material = "";
    }

    /**
     * Creates a new armor item.
     *
     * @param name the armor name
     * @param description the armor description
     * @param price the armor price
     * @param quantity the quantity available
     * @param defense the defense value
     * @param material the armor material
     */
    public Armor(
            String name,
            String description,
            double price,
            int quantity,
            int defense,
            String material) {

        super(name, description, price, quantity);

        this.defense = defense;
        this.material = material;
    }

    /**
     * Gets the defense value.
     *
     * @return the defense value
     */
    public int getDefense() {
        return defense;
    }

    /**
     * Sets the defense value.
     *
     * @param defense the new defense value
     */
    public void setDefense(int defense) {
        this.defense = defense;
    }

    /**
     * Gets the armor material.
     *
     * @return the armor material
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Sets the armor material.
     *
     * @param material the new armor material
     */
    public void setMaterial(String material) {
        this.material = material;
    }
}