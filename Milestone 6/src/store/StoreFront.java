package store;

import java.util.ArrayList;
import java.util.Scanner;

import cart.ShoppingCart;
import exception.FileServiceException;
import inventory.InventoryManager;
import product.Armor;
import product.Health;
import product.SalableProduct;
import product.Weapon;
import server.ServerThread;

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
     *
     * @throws FileServiceException if inventory cannot be loaded
     */
    public StoreFront()
            throws FileServiceException {

        inventoryManager =
                new InventoryManager();

        shoppingCart =
                new ShoppingCart();

        scanner =
                new Scanner(System.in);
    }

    /**
     * Initializes the store.
     */
    public void initializeStore() {

        System.out.println(
                "=== Arena Store Front ===");

        System.out.println(
                "Welcome to the Arena Store Front.");

        System.out.println(
                "Inventory loaded from JSON successfully.");

        System.out.println(
                "Store initialized successfully.\n");
    }

    /**
     * Purchases one unit of a product.
     *
     * @param product the product to purchase
     */
    public void purchaseProduct(
            SalableProduct product) {

        if (product.getQuantity() <= 0) {

            System.out.println(
                    "That product is out of stock.");

            return;
        }

        SalableProduct cartProduct =
                createCartProduct(product);

        if (shoppingCart.addProduct(
                cartProduct)) {

            product.setQuantity(
                    product.getQuantity() - 1);

            try {

                inventoryManager.saveInventory();

                System.out.println(
                        product.getName()
                                + " purchased successfully.");

            } catch (FileServiceException e) {

                product.setQuantity(
                        product.getQuantity() + 1);

                shoppingCart.removeProduct(
                        cartProduct);

                System.out.println(
                        "Unable to update inventory: "
                                + e.getMessage());
            }

        } else {

            System.out.println(
                    "Shopping cart is full.");
        }
    }

    /**
     * Cancels a purchase and returns one unit
     * to inventory.
     *
     * @param product the product to cancel
     */
    public void cancelPurchase(
            SalableProduct product) {

        if (shoppingCart.removeProduct(
                product)) {

            SalableProduct inventoryProduct =
                    findInventoryProduct(
                            product.getName());

            if (inventoryProduct != null) {

                inventoryProduct.setQuantity(
                        inventoryProduct
                                .getQuantity() + 1);

                try {

                    inventoryManager.saveInventory();

                    System.out.println(
                            product.getName()
                                    + " purchase canceled.");

                } catch (FileServiceException e) {

                    inventoryProduct.setQuantity(
                            inventoryProduct
                                    .getQuantity() - 1);

                    shoppingCart.addProduct(
                            product);

                    System.out.println(
                            "Unable to update inventory: "
                                    + e.getMessage());
                }
            }

        } else {

            System.out.println(
                    "Product not found in shopping cart.");
        }
    }

    /**
     * Empties the shopping cart and restores
     * each product to inventory.
     */
    public void emptyShoppingCart() {

        ArrayList<SalableProduct> cartItems =
                shoppingCart.getShoppingCart();

        if (cartItems.isEmpty()) {

            System.out.println(
                    "The shopping cart is already empty.");

            return;
        }

        for (SalableProduct product
                : cartItems) {

            SalableProduct inventoryProduct =
                    findInventoryProduct(
                            product.getName());

            if (inventoryProduct != null) {

                inventoryProduct.setQuantity(
                        inventoryProduct
                                .getQuantity() + 1);
            }
        }

        try {

            inventoryManager.saveInventory();

            shoppingCart.emptyShoppingCart();

            System.out.println(
                    "Shopping cart emptied successfully.");

        } catch (FileServiceException e) {

            System.out.println(
                    "Unable to update inventory: "
                            + e.getMessage());
        }
    }

    /**
     * Creates a quantity-one product copy
     * for the shopping cart.
     *
     * @param product the inventory product
     * @return a copy for the shopping cart
     */
    private SalableProduct createCartProduct(
            SalableProduct product) {

        if (product instanceof Weapon) {

            Weapon weapon =
                    (Weapon) product;

            return new Weapon(
                    weapon.getName(),
                    weapon.getDescription(),
                    weapon.getPrice(),
                    1,
                    weapon.getDamage(),
                    weapon.getWeaponType());
        }

        if (product instanceof Armor) {

            Armor armor =
                    (Armor) product;

            return new Armor(
                    armor.getName(),
                    armor.getDescription(),
                    armor.getPrice(),
                    1,
                    armor.getDefense(),
                    armor.getMaterial());
        }

        if (product instanceof Health) {

            Health health =
                    (Health) product;

            return new Health(
                    health.getName(),
                    health.getDescription(),
                    health.getPrice(),
                    1,
                    health.getHealingAmount(),
                    health.getEffectDuration());
        }

        return new SalableProduct(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                1);
    }

    /**
     * Finds an inventory product by name.
     *
     * @param productName the product name
     * @return matching product or null
     */
    private SalableProduct findInventoryProduct(
            String productName) {

        ArrayList<SalableProduct> inventory =
                inventoryManager.getInventory();

        for (SalableProduct product
                : inventory) {

            if (product != null
                    && product.getName()
                            .equalsIgnoreCase(
                                    productName)) {

                return product;
            }
        }

        return null;
    }

    /**
     * Displays the current store inventory.
     */
    public void displayInventory() {

        System.out.println(
                "\nAvailable Products:");

        ArrayList<SalableProduct> inventory =
                inventoryManager.getInventory();

        for (int i = 0;
                i < inventory.size();
                i++) {

            SalableProduct product =
                    inventory.get(i);

            System.out.println(
                    (i + 1)
                            + ". "
                            + product.getName()
                            + " - $"
                            + String.format(
                                    "%.2f",
                                    product.getPrice())
                            + " ("
                            + product.getQuantity()
                            + " in stock)");

            System.out.println(
                    "   Description: "
                            + product
                                    .getDescription());

            displaySpecializedDetails(
                    product);
        }
    }

    /**
     * Displays specialized product information.
     *
     * @param product the product to display
     */
    private void displaySpecializedDetails(
            SalableProduct product) {

        if (product instanceof Weapon) {

            Weapon weapon =
                    (Weapon) product;

            System.out.println(
                    "   Damage: "
                            + weapon.getDamage());

            System.out.println(
                    "   Weapon Type: "
                            + weapon.getWeaponType());

        } else if (product instanceof Armor) {

            Armor armor =
                    (Armor) product;

            System.out.println(
                    "   Defense: "
                            + armor.getDefense());

            System.out.println(
                    "   Material: "
                            + armor.getMaterial());

        } else if (product instanceof Health) {

            Health health =
                    (Health) product;

            System.out.println(
                    "   Healing: "
                            + health.getHealingAmount());

            System.out.println(
                    "   Effect Duration: "
                            + health.getEffectDuration()
                            + " seconds");
        }
    }

    /**
     * Displays products currently
     * in the shopping cart.
     */
    public void displayShoppingCart() {

        System.out.println(
                "\nShopping Cart:");

        ArrayList<SalableProduct> cartItems =
                shoppingCart.getShoppingCart();

        if (cartItems.isEmpty()) {

            System.out.println(
                    "The shopping cart is empty.");

            return;
        }

        double total = 0.0;

        for (int i = 0;
                i < cartItems.size();
                i++) {

            SalableProduct product =
                    cartItems.get(i);

            System.out.println(
                    (i + 1)
                            + ". "
                            + product.getName()
                            + " - $"
                            + String.format(
                                    "%.2f",
                                    product.getPrice()));

            total += product.getPrice();
        }

        System.out.println(
                "Cart Total: $"
                        + String.format(
                                "%.2f",
                                total));
    }

    /**
     * Sorts inventory in ascending order
     * and displays the sorted products.
     */
    public void sortInventoryAscending() {

        inventoryManager.sortAscending();

        System.out.println(
                "\nInventory sorted "
                        + "in ascending order.");

        displayInventory();
    }

    /**
     * Sorts inventory in descending order
     * and displays the sorted products.
     */
    public void sortInventoryDescending() {

        inventoryManager.sortDescending();

        System.out.println(
                "\nInventory sorted "
                        + "in descending order.");

        displayInventory();
    }

    /**
     * Reads an integer without crashing
     * on invalid input.
     *
     * @param prompt the prompt shown to the user
     * @return the entered integer
     */
    private int readInteger(
            String prompt) {

        while (true) {

            System.out.print(prompt);

            if (scanner.hasNextInt()) {

                return scanner.nextInt();
            }

            System.out.println(
                    "Invalid input. "
                            + "Please enter a number.");

            scanner.next();
        }
    }

    /**
     * Displays the store menu and processes
     * user selections.
     */
    public void runStore() {

        boolean running = true;

        while (running) {

            System.out.println(
                    "\n=== Main Menu ===");

            System.out.println(
                    "1. Display Products");

            System.out.println(
                    "2. Purchase Product");

            System.out.println(
                    "3. Cancel Purchase");

            System.out.println(
                    "4. Display Shopping Cart");

            System.out.println(
                    "5. Empty Shopping Cart");

            System.out.println(
                    "6. Sort Products Ascending");

            System.out.println(
                    "7. Sort Products Descending");

            System.out.println(
                    "8. Exit");

            int choice =
                    readInteger(
                            "Select an option: ");

            switch (choice) {

            case 1:

                displayInventory();

                break;

            case 2:

                displayInventory();

                int purchaseIndex =
                        readInteger(
                                "\nEnter the product number "
                                        + "to purchase: ")
                                - 1;

                ArrayList<SalableProduct>
                        inventory =
                                inventoryManager
                                        .getInventory();

                if (purchaseIndex >= 0
                        && purchaseIndex
                                < inventory.size()) {

                    purchaseProduct(
                            inventory.get(
                                    purchaseIndex));

                } else {

                    System.out.println(
                            "Invalid product selection.");
                }

                break;

            case 3:

                displayShoppingCart();

                int cancelIndex =
                        readInteger(
                                "\nEnter the cart item number "
                                        + "to cancel: ")
                                - 1;

                ArrayList<SalableProduct>
                        cartItems =
                                shoppingCart
                                        .getShoppingCart();

                if (cancelIndex >= 0
                        && cancelIndex
                                < cartItems.size()) {

                    cancelPurchase(
                            cartItems.get(
                                    cancelIndex));

                } else {

                    System.out.println(
                            "Invalid cart selection.");
                }

                break;

            case 4:

                displayShoppingCart();

                break;

            case 5:

                emptyShoppingCart();

                break;

            case 6:

                sortInventoryAscending();

                break;

            case 7:

                sortInventoryDescending();

                break;

            case 8:

                running = false;

                System.out.println(
                        "Thank you for visiting "
                                + "the Arena Store Front.");

                break;

            default:

                System.out.println(
                        "Invalid option. "
                                + "Please select "
                                + "1 through 8.");

                break;
            }
        }

        scanner.close();
    }

    /**
     * Starts the Store Front application and
     * administration server.
     *
     * @param args command-line arguments
     */
    public static void main(
            String[] args) {

        try {

            StoreFront store =
                    new StoreFront();

            store.initializeStore();

            ServerThread serverThread =
                    new ServerThread(
                            store.inventoryManager);

            serverThread.start();

            store.runStore();

        } catch (FileServiceException e) {

            System.out.println(
                    "Store could not be initialized.");

            System.out.println(
                    "Error: "
                            + e.getMessage());
        }
    }
}