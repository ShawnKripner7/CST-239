package inventory;

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

    private SalableProduct[] inventory;
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

        inventory =
                fileService.readProducts(INVENTORY_FILE);
    }

    /**
     * Saves the current inventory to the JSON file.
     *
     * @throws FileServiceException if inventory cannot be saved
     */
    public void saveInventory()
            throws FileServiceException {

        fileService.writeProducts(
                INVENTORY_FILE,
                inventory);
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
     * @throws FileServiceException if inventory cannot be saved
     */
    public boolean removeProduct(
            SalableProduct product)
            throws FileServiceException {

        for (int i = 0; i < inventory.length; i++) {

            if (inventory[i] == product) {

                inventory[i] = null;

                saveInventory();

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
     * @throws FileServiceException if inventory cannot be saved
     */
    public boolean addProduct(
            SalableProduct product)
            throws FileServiceException {

        for (int i = 0; i < inventory.length; i++) {

            if (inventory[i] == null) {

                inventory[i] = product;

                saveInventory();

                return true;
            }
        }

        return false;
    }
}