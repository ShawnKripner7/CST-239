package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.Armor;

/**
 * Tests the Armor class.
 *
 * @author Shawn Kripner
 */
public class ArmorTest {

    /**
     * Tests the Armor constructor
     * and inherited properties.
     */
    @Test
    public void testConstructor() {

        Armor armor =
                new Armor(
                        "Knight Armor",
                        "Heavy armor",
                        75.00,
                        2,
                        50,
                        "Steel");

        assertEquals(
                "Knight Armor",
                armor.getName());

        assertEquals(
                "Heavy armor",
                armor.getDescription());

        assertEquals(
                75.00,
                armor.getPrice(),
                0.001);

        assertEquals(
                2,
                armor.getQuantity());

        assertEquals(
                50,
                armor.getDefense());

        assertEquals(
                "Steel",
                armor.getMaterial());
    }

    /**
     * Tests Armor-specific setters.
     */
    @Test
    public void testArmorSetters() {

        Armor armor =
                new Armor();

        armor.setDefense(65);
        armor.setMaterial("Iron");

        assertEquals(
                65,
                armor.getDefense());

        assertEquals(
                "Iron",
                armor.getMaterial());
    }
}