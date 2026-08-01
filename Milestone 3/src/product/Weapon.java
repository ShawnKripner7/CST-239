package product;

/**
 * Represents a weapon product sold in the store.
 * 
 * @author Shawn Kripner
 */
public class Weapon extends SalableProduct {

	private int damage;

	/**
	 * Creates a new weapon.
	 * 
	 * @param name the weapon name
	 * @param description the weapon description
	 * @param price the weapon price
	 * @param quantity the quantity available
	 * @param damage the amount of damage the weapon can cause
	 */
	public Weapon(String name, String description, double price, int quantity, int damage) {
		super(name, description, price, quantity);
		this.damage = damage;
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
}