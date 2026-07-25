package inventory;

import product.Armor;
import product.Health;
import product.SalableProduct;
import product.Weapon;

/**
 * Manages the inventory of products in the store.
 * 
 * @author Shawn Kripner
 */
public class InventoryManager {

	private SalableProduct[] inventory;

	/**
	 * Creates a new inventory manager and initializes the inventory.
	 */
	public InventoryManager() {
		initializeInventory();
	}

	/**
	 * Initializes the store inventory with weapons, armor, and health items.
	 */
	public void initializeInventory() {
		inventory = new SalableProduct[5];

		inventory[0] = new Weapon(
				"Sword",
				"A sharp steel sword.",
				100.00,
				10,
				25);

		inventory[1] = new Weapon(
				"Battle Axe",
				"A powerful two-handed battle axe.",
				150.00,
				6,
				40);

		inventory[2] = new Armor(
				"Shield",
				"A sturdy iron shield.",
				75.00,
				8,
				20);

		inventory[3] = new Armor(
				"Battle Armor",
				"Heavy armor that provides strong protection.",
				200.00,
				3,
				50);

		inventory[4] = new Health(
				"Health Potion",
				"Restores health when consumed.",
				25.00,
				20,
				50);
	}

	/**
	 * Returns the inventory.
	 * 
	 * @return the inventory array
	 */
	public SalableProduct[] getInventory() {
		return inventory;
	}

	/**
	 * Removes a product from inventory.
	 * 
	 * @param product the product to remove
	 * @return true if removed successfully
	 */
	public boolean removeProduct(SalableProduct product) {

		for (int i = 0; i < inventory.length; i++) {

			if (inventory[i] == product) {
				inventory[i] = null;
				return true;
			}
		}

		return false;
	}

	/**
	 * Adds a product to inventory.
	 * 
	 * @param product the product to add
	 * @return true if added successfully
	 */
	public boolean addProduct(SalableProduct product) {

		for (int i = 0; i < inventory.length; i++) {

			if (inventory[i] == null) {
				inventory[i] = product;
				return true;
			}
		}

		return false;
	}
}