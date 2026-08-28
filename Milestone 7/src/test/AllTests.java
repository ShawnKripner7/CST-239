package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Runs all Milestone 7 unit tests.
 *
 * @author Shawn Kripner
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SalableProductTest.class,
        WeaponTest.class,
        ArmorTest.class,
        HealthTest.class,
        InventoryManagerTest.class
})
public class AllTests {

}