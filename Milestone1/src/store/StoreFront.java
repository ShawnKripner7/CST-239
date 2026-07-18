package store;

import java.util.Scanner;

import cart.ShoppingCart;
import inventory.InventoryManager;
import product.SalableProduct;

/**
 * Main Store Front application.
 * 
 * @author Shawn Kripner
 */
public class StoreFront {

	private InventoryManager inventoryManager;
	private ShoppingCart shoppingCart;
	private Scanner scanner;

	/**
	 * Creates a new Store Front.
	 */
	public StoreFront() {
		inventoryManager = new InventoryManager();
		shoppingCart = new ShoppingCart();
		scanner = new Scanner(System.in);
	}

	/**
	 * Initializes the store.
	 */
	public void initializeStore() {
		System.out.println("=== Arena Store Front ===");
		System.out.println("Store initialized successfully.\n");
	}

	/**
	 * Purchases a product.
	 * 
	 * @param product the product to purchase
	 */
	public void purchaseProduct(SalableProduct product) {

		if (inventoryManager.removeProduct(product)) {
			shoppingCart.addProduct(product);
			System.out.println(product.getName() + " purchased successfully.");
		} else {
			System.out.println("Product unavailable.");
		}
	}

	/**
	 * Cancels a purchase.
	 * 
	 * @param product the product to cancel
	 */
	public void cancelPurchase(SalableProduct product) {

		if (shoppingCart.removeProduct(product)) {
			inventoryManager.addProduct(product);
			System.out.println(product.getName() + " purchase canceled.");
		} else {
			System.out.println("Product not found in shopping cart.");
		}
	}

	/**
	 * Displays the current store inventory.
	 */
	public void displayInventory() {

		System.out.println("\nAvailable Products:");

		SalableProduct[] inventory = inventoryManager.getInventory();

		for (int i = 0; i < inventory.length; i++) {

			if (inventory[i] != null) {
				System.out.println((i + 1) + ". "
						+ inventory[i].getName()
						+ " - $"
						+ inventory[i].getPrice()
						+ " (" + inventory[i].getQuantity() + " in stock)");
			}
		}
	}

	/**
	 * Displays the products currently in the shopping cart.
	 */
	public void displayShoppingCart() {

		System.out.println("\nShopping Cart:");

		SalableProduct[] cartItems = shoppingCart.getCartItems();
		boolean cartEmpty = true;

		for (int i = 0; i < cartItems.length; i++) {

			if (cartItems[i] != null) {
				System.out.println((i + 1) + ". "
						+ cartItems[i].getName()
						+ " - $"
						+ cartItems[i].getPrice());

				cartEmpty = false;
			}
		}

		if (cartEmpty) {
			System.out.println("The shopping cart is empty.");
		}
	}

	/**
	 * Displays the store menu and processes the user's selections.
	 */
	public void runStore() {

		boolean running = true;

		while (running) {

			System.out.println("\n=== Main Menu ===");
			System.out.println("1. Display Products");
			System.out.println("2. Purchase Product");
			System.out.println("3. Cancel Purchase");
			System.out.println("4. Display Shopping Cart");
			System.out.println("5. Exit");
			System.out.print("Select an option: ");

			int choice = scanner.nextInt();

			switch (choice) {

			case 1:
				displayInventory();
				break;

			case 2:
				displayInventory();
				System.out.print("\nEnter the product number to purchase: ");
				int purchaseIndex = scanner.nextInt() - 1;

				SalableProduct[] inventory = inventoryManager.getInventory();

				if (purchaseIndex >= 0
						&& purchaseIndex < inventory.length
						&& inventory[purchaseIndex] != null) {

					purchaseProduct(inventory[purchaseIndex]);
				} else {
					System.out.println("Invalid product selection.");
				}
				break;

			case 3:
				displayShoppingCart();
				System.out.print("\nEnter the cart item number to cancel: ");
				int cancelIndex = scanner.nextInt() - 1;

				SalableProduct[] cartItems = shoppingCart.getCartItems();

				if (cancelIndex >= 0
						&& cancelIndex < cartItems.length
						&& cartItems[cancelIndex] != null) {

					cancelPurchase(cartItems[cancelIndex]);
				} else {
					System.out.println("Invalid cart selection.");
				}
				break;

			case 4:
				displayShoppingCart();
				break;

			case 5:
				running = false;
				System.out.println("Thank you for visiting the Arena Store Front.");
				break;

			default:
				System.out.println("Invalid option. Please select 1 through 5.");
				break;
			}
		}

		scanner.close();
	}

	/**
	 * Starts the Store Front application.
	 * 
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {

		StoreFront store = new StoreFront();

		store.initializeStore();
		store.runStore();
	}
}