package inventory;

import product.SalableProduct;

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
	 * Initializes the store inventory with sample products.
	 */
	public void initializeInventory() {
		inventory = new SalableProduct[5];

		inventory[0] = new SalableProduct("Sword", "A sharp steel sword.", 100.00, 10);
		inventory[1] = new SalableProduct("Shield", "A sturdy iron shield.", 75.00, 8);
		inventory[2] = new SalableProduct("Health Potion", "Restores health.", 25.00, 20);
		inventory[3] = new SalableProduct("Helmet", "Protects your head.", 50.00, 5);
		inventory[4] = new SalableProduct("Armor", "Heavy battle armor.", 200.00, 3);
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