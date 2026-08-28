package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import exception.FileServiceException;
import inventory.InventoryManager;
import product.SalableProduct;

/**
 * Tests the InventoryManager class.
 *
 * @author Shawn Kripner
 */
public class InventoryManagerTest {

    private InventoryManager manager;

    /**
     * Creates an InventoryManager
     * before each test.
     *
     * @throws FileServiceException
     *         if inventory cannot be loaded
     */
    @Before
    public void setUp()
            throws FileServiceException {

        manager =
                new InventoryManager();
    }

    /**
     * Tests that inventory is loaded.
     */
    @Test
    public void testGetInventory() {

        ArrayList<SalableProduct> inventory =
                manager.getInventory();

        assertNotNull(inventory);
    }

    /**
     * Tests conversion of inventory
     * to an array.
     */
    @Test
    public void testGetInventoryArray() {

        SalableProduct[] products =
                manager.getInventoryArray();

        assertNotNull(products);

        assertEquals(
                manager.getInventory().size(),
                products.length);
    }

    /**
     * Tests ascending inventory sorting.
     */
    @Test
    public void testSortAscending() {

        manager.getInventory().clear();

        manager.getInventory().add(
                new SalableProduct(
                        "Sword",
                        "Sword",
                        25.00,
                        1));

        manager.getInventory().add(
                new SalableProduct(
                        "Armor",
                        "Armor",
                        50.00,
                        1));

        manager.sortAscending();

        assertEquals(
                "Armor",
                manager.getInventory()
                        .get(0)
                        .getName());

        assertEquals(
                "Sword",
                manager.getInventory()
                        .get(1)
                        .getName());
    }

    /**
     * Tests descending inventory sorting.
     */
    @Test
    public void testSortDescending() {

        manager.getInventory().clear();

        manager.getInventory().add(
                new SalableProduct(
                        "Armor",
                        "Armor",
                        50.00,
                        1));

        manager.getInventory().add(
                new SalableProduct(
                        "Sword",
                        "Sword",
                        25.00,
                        1));

        manager.sortDescending();

        assertEquals(
                "Sword",
                manager.getInventory()
                        .get(0)
                        .getName());

        assertTrue(
                manager.getInventory()
                        .get(0)
                        .compareTo(
                                manager.getInventory()
                                        .get(1))
                        > 0);
    }
}