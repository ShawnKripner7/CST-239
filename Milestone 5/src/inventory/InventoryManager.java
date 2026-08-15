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

        fileService = new JsonFileService();

        initializeInventory();
    }

    /**
     * Initializes the store inventory from
     * the external JSON inventory file.
     *
     * @throws FileServiceException if inventory cannot be loaded
     */
    public void initializeInventory()
            throws FileServiceException {

        SalableProduct[] products =
                fileService.readProducts(
                        INVENTORY_FILE);

        inventory =
                new ArrayList<SalableProduct>(
                        Arrays.asList(products));
    }

    /**
     * Saves the current inventory to the JSON file.
     *
     * @throws FileServiceException if inventory cannot be saved
     */
    public void saveInventory()
            throws FileServiceException {

        SalableProduct[] products =
                inventory.toArray(
                        new SalableProduct[0]);

        fileService.writeProducts(
                INVENTORY_FILE,
                products);
    }

    /**
     * Returns the complete inventory.
     *
     * @return the inventory ArrayList
     */
    public ArrayList<SalableProduct> getInventory() {
        return inventory;
    }

    /**
     * Removes a product from inventory.
     *
     * @param product the product to remove
     * @return true if removed successfully
     * @throws FileServiceException if inventory cannot be saved
     */
    public boolean removeProduct(
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
     * @param product the product to add
     * @return true if added successfully
     * @throws FileServiceException if inventory cannot be saved
     */
    public boolean addProduct(
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
     * Sorts the inventory in ascending order
     * by product name and then price.
     */
    public void sortAscending() {

        Collections.sort(inventory);
    }

    /**
     * Sorts the inventory in descending order
     * by product name and then price.
     */
    public void sortDescending() {

        Collections.sort(
                inventory,
                Collections.reverseOrder());
    }
}