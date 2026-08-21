package product;

/**
 * Represents a health item sold in the store.
 *
 * @author Shawn Kripner
 */
public class Health extends SalableProduct {

    private int healingAmount;
    private int effectDuration;

    /**
     * Creates an empty health item.
     * Required for JSON deserialization.
     */
    public Health() {
        super();
        this.healingAmount = 0;
        this.effectDuration = 0;
    }

    /**
     * Creates a new health item.
     *
     * @param name the item name
     * @param description the item description
     * @param price the item price
     * @param quantity the quantity available
     * @param healingAmount the amount healed
     * @param effectDuration duration of the effect
     */
    public Health(
            String name,
            String description,
            double price,
            int quantity,
            int healingAmount,
            int effectDuration) {

        super(name, description, price, quantity);

        this.healingAmount = healingAmount;
        this.effectDuration = effectDuration;
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

    /**
     * Gets the effect duration.
     *
     * @return the effect duration
     */
    public int getEffectDuration() {
        return effectDuration;
    }

    /**
     * Sets the effect duration.
     *
     * @param effectDuration the new effect duration
     */
    public void setEffectDuration(int effectDuration) {
        this.effectDuration = effectDuration;
    }
}