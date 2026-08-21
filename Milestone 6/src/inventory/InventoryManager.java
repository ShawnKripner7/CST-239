package inventory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import exception.FileServiceException;
import product.SalableProduct;
import service.FileService;
import service.JsonFileService;

/**
 * Manages the inventory of products in the store.
 *
 * @author Shawn Kripner
 */
public class InventoryManager {

    private ArrayList<SalableProduct> inventory;
    private FileService fileService;

    private static final String INVENTORY_FILE =
            "inventory.json";

    /**
     * Creates a new inventory manager and
     * initializes inventory from JSON.
     *
     * @throws FileServiceException if inventory cannot be loaded
     */
    public InventoryManager()
            throws FileServiceException {

        fileService =
                new JsonFileService();

        initializeInventory();
    }

    /**
     * Initializes inventory from JSON.
     *
     * @throws FileServiceException if inventory cannot be loaded
     */
    public synchronized void initializeInventory()
            throws FileServiceException {

        SalableProduct[] products =
                fileService.readProducts(
                        INVENTORY_FILE);

        inventory =
                new ArrayList<SalableProduct>(
                        Arrays.asList(products));
    }

    /**
     * Saves inventory to JSON.
     *
     * @throws FileServiceException if inventory cannot be saved
     */
    public synchronized void saveInventory()
            throws FileServiceException {

        SalableProduct[] products =
                inventory.toArray(
                        new SalableProduct[0]);

        fileService.writeProducts(
                INVENTORY_FILE,
                products);
    }

    /**
     * Returns the inventory.
     *
     * @return inventory ArrayList
     */
    public synchronized ArrayList<SalableProduct>
            getInventory() {

        return inventory;
    }

    /**
     * Returns the inventory as an array.
     *
     * @return inventory array
     */
    public synchronized SalableProduct[]
            getInventoryArray() {

        return inventory.toArray(
                new SalableProduct[0]);
    }

    /**
     * Removes a product from inventory.
     *
     * @param product product to remove
     * @return true if removed
     * @throws FileServiceException if inventory cannot be saved
     */
    public synchronized boolean removeProduct(
            SalableProduct product)
            throws FileServiceException {

        boolean removed =
                inventory.remove(product);

        if (removed) {
            saveInventory();
        }

        return removed;
    }

    /**
     * Adds a product to inventory.
     *
     * @param product product to add
     * @return true if added
     * @throws FileServiceException if inventory cannot be saved
     */
    public synchronized boolean addProduct(
            SalableProduct product)
            throws FileServiceException {

        boolean added =
                inventory.add(product);

        if (added) {
            saveInventory();
        }

        return added;
    }

    /**
     * Adds a new product or replenishes an existing
     * product with the same name.
     *
     * @param product product received from administration
     * @throws FileServiceException if inventory cannot be saved
     */
    public synchronized void updateProduct(
            SalableProduct product)
            throws FileServiceException {

        for (SalableProduct existingProduct
                : inventory) {

            if (existingProduct
                    .getName()
                    .equalsIgnoreCase(
                            product.getName())) {

                existingProduct.setQuantity(
                        existingProduct.getQuantity()
                                + product.getQuantity());

                saveInventory();

                return;
            }
        }

        inventory.add(product);

        saveInventory();
    }

    /**
     * Sorts inventory in ascending order.
     */
    public synchronized void sortAscending() {

        Collections.sort(inventory);
    }

    /**
     * Sorts inventory in descending order.
     */
    public synchronized void sortDescending() {

        Collections.sort(
                inventory,
                Collections.reverseOrder());
    }
}