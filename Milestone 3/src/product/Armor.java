package product;

/**
 * Represents an armor product sold in the store.
 *
 * @author Shawn Kripner
 */
public class Armor extends SalableProduct {

    private int defense;

    /**
     * Creates a new armor item.
     *
     * @param name the armor name
     * @param description the armor description
     * @param price the armor price
     * @param quantity the quantity available
     * @param defense the defense value
     */
    public Armor(String name, String description, double price, int quantity, int defense) {
        super(name, description, price, quantity);
        this.defense = defense;
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
}