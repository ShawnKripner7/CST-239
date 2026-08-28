package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import product.SalableProduct;

/**
 * Tests the SalableProduct class.
 *
 * @author Shawn Kripner
 */
public class SalableProductTest {

    /**
     * Tests the parameterized constructor
     * and getter methods.
     */
    @Test
    public void testConstructorAndGetters() {

        SalableProduct product =
                new SalableProduct(
                        "Sword",
                        "Basic sword",
                        25.99,
                        5);

        assertEquals(
                "Sword",
                product.getName());

        assertEquals(
                "Basic sword",
                product.getDescription());

        assertEquals(
                25.99,
                product.getPrice(),
                0.001);

        assertEquals(
                5,
                product.getQuantity());
    }

    /**
     * Tests the setter methods.
     */
    @Test
    public void testSetters() {

        SalableProduct product =
                new SalableProduct();

        product.setName("Potion");
        product.setDescription(
                "Restores health");
        product.setPrice(10.50);
        product.setQuantity(8);

        assertEquals(
                "Potion",
                product.getName());

        assertEquals(
                "Restores health",
                product.getDescription());

        assertEquals(
                10.50,
                product.getPrice(),
                0.001);

        assertEquals(
                8,
                product.getQuantity());
    }

    /**
     * Tests product comparison by name.
     */
    @Test
    public void testCompareToByName() {

        SalableProduct first =
                new SalableProduct(
                        "Armor",
                        "Armor",
                        30.00,
                        1);

        SalableProduct second =
                new SalableProduct(
                        "Sword",
                        "Sword",
                        20.00,
                        1);

        assertTrue(
                first.compareTo(second) < 0);
    }

    /**
     * Tests product comparison by price
     * when names are equal.
     */
    @Test
    public void testCompareToByPrice() {

        SalableProduct first =
                new SalableProduct(
                        "Potion",
                        "Potion",
                        5.00,
                        1);

        SalableProduct second =
                new SalableProduct(
                        "Potion",
                        "Potion",
                        10.00,
                        1);

        assertTrue(
                first.compareTo(second) < 0);
    }
}