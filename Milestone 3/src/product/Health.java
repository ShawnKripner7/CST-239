package product;

/**
 * Represents a health item sold in the store.
 *
 * @author Shawn Kripner
 */
public class Health extends SalableProduct {

    private int healingAmount;

    /**
     * Creates a new health item.
     *
     * @param name the item name
     * @param description the item description
     * @param price the item price
     * @param quantity the quantity available
     * @param healingAmount the amount healed
     */
    public Health(String name, String description, double price, int quantity, int healingAmount) {
        super(name, description, price, quantity);
        this.healingAmount = healingAmount;
    }

    /**
     * Gets the healing amount.
     *
     * @return the healing amount
     */
    public int getHealingAmount() {
        return healingAmount;
    }

    /**
     * Sets the healing amount.
     *
     * @param healingAmount the new healing amount
     */
    public void setHealingAmount(int healingAmount) {
        this.healingAmount = healingAmount;
    }
}