package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import product.Health;

/**
 * Tests the Health class.
 *
 * @author Shawn Kripner
 */
public class HealthTest {

    /**
     * Tests the Health constructor
     * and inherited properties.
     */
    @Test
    public void testConstructor() {

        Health health =
                new Health(
                        "Health Potion",
                        "Restores health",
                        15.00,
                        10,
                        50,
                        30);

        assertEquals(
                "Health Potion",
                health.getName());

        assertEquals(
                "Restores health",
                health.getDescription());

        assertEquals(
                15.00,
                health.getPrice(),
                0.001);

        assertEquals(
                10,
                health.getQuantity());

        assertEquals(
                50,
                health.getHealingAmount());

        assertEquals(
                30,
                health.getEffectDuration());
    }

    /**
     * Tests Health-specific setters.
     */
    @Test
    public void testHealthSetters() {

        Health health =
                new Health();

        health.setHealingAmount(75);
        health.setEffectDuration(60);

        assertEquals(
                75,
                health.getHealingAmount());

        assertEquals(
                60,
                health.getEffectDuration());
    }
}