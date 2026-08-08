package product;

/**
 * Represents a weapon product sold in the store.
 *
 * @author Shawn Kripner
 */
public class Weapon extends SalableProduct {

    private int damage;
    private String weaponType;

    /**
     * Creates an empty weapon.
     * Required for JSON deserialization.
     */
    public Weapon() {
        super();
        this.damage = 0;
        this.weaponType = "";
    }

    /**
     * Creates a new weapon.
     *
     * @param name the weapon name
     * @param description the weapon description
     * @param price the weapon price
     * @param quantity the quantity available
     * @param damage the damage value
     * @param weaponType the type of weapon
     */
    public Weapon(
            String name,
            String description,
            double price,
            int quantity,
            int damage,
            String weaponType) {

        super(name, description, price, quantity);

        this.damage = damage;
        this.weaponType = weaponType;
    }

    /**
     * Gets the weapon damage.
     *
     * @return the weapon damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Sets the weapon damage.
     *
     * @param damage the new weapon damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * Gets the weapon type.
     *
     * @return the weapon type
     */
    public String getWeaponType() {
        return weaponType;
    }

    /**
     * Sets the weapon type.
     *
     * @param weaponType the new weapon type
     */
    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }
}