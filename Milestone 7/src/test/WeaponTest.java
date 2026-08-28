package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.Weapon;

/**
 * Tests the Weapon class.
 *
 * @author Shawn Kripner
 */
public class WeaponTest {

    /**
     * Tests the Weapon constructor
     * and inherited properties.
     */
    @Test
    public void testConstructor() {

        Weapon weapon =
                new Weapon(
                        "Long Sword",
                        "Steel sword",
                        50.00,
                        3,
                        25,
                        "Sword");

        assertEquals(
                "Long Sword",
                weapon.getName());

        assertEquals(
                "Steel sword",
                weapon.getDescription());

        assertEquals(
                50.00,
                weapon.getPrice(),
                0.001);

        assertEquals(
                3,
                weapon.getQuantity());

        assertEquals(
                25,
                weapon.getDamage());

        assertEquals(
                "Sword",
                weapon.getWeaponType());
    }

    /**
     * Tests Weapon-specific setters.
     */
    @Test
    public void testWeaponSetters() {

        Weapon weapon =
                new Weapon();

        weapon.setDamage(40);
        weapon.setWeaponType("Axe");

        assertEquals(
                40,
                weapon.getDamage());

        assertEquals(
                "Axe",
                weapon.getWeaponType());
    }
}