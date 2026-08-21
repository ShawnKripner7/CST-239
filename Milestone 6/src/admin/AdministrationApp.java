package admin;

import java.io.IOException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import product.Armor;
import product.Health;
import product.SalableProduct;
import product.Weapon;

/**
 * Console application used by an administrator
 * to view and update Store Front inventory.
 *
 * @author Shawn Kripner
 */
public class AdministrationApp {

    private static final String SERVER_IP =
            "127.0.0.1";

    private static final int SERVER_PORT =
            6666;

    /**
     * Displays inventory in a readable format.
     *
     * @param json inventory JSON received from Store Front
     */
    private static void displayInventory(
            String json) {

        ObjectMapper mapper =
                new ObjectMapper();

        try {

            SalableProduct[] products =
                    mapper.readValue(
                            json,
                            SalableProduct[].class);

            System.out.println(
                    "\n=== Store Inventory ===");

            for (SalableProduct product
                    : products) {

                System.out.println(
                        "Name: "
                                + product.getName());

                System.out.println(
                        "Description: "
                                + product.getDescription());

                System.out.println(
                        "Price: $"
                                + String.format(
                                        "%.2f",
                                        product.getPrice()));

                System.out.println(
                        "Quantity: "
                                + product.getQuantity());

                if (product instanceof Weapon) {

                    Weapon weapon =
                            (Weapon) product;

                    System.out.println(
                            "Damage: "
                                    + weapon.getDamage());

                    System.out.println(
                            "Weapon Type: "
                                    + weapon.getWeaponType());

                } else if (product instanceof Armor) {

                    Armor armor =
                            (Armor) product;

                    System.out.println(
                            "Defense: "
                                    + armor.getDefense());

                    System.out.println(
                            "Material: "
                                    + armor.getMaterial());

                } else if (product instanceof Health) {

                    Health health =
                            (Health) product;

                    System.out.println(
                            "Healing Amount: "
                                    + health.getHealingAmount());

                    System.out.println(
                            "Effect Duration: "
                                    + health.getEffectDuration());
                }

                System.out.println(
                        "-------------------------");
            }

        } catch (IOException e) {

            System.out.println(
                    "Unable to read inventory response: "
                            + e.getMessage());
        }
    }

    /**
     * Runs the Administration Application.
     *
     * @param args command-line arguments
     */
    public static void main(
            String[] args) {

        AdminService adminService =
                new AdminService();

        Scanner scanner =
                new Scanner(System.in);

        try {

            adminService.start(
                    SERVER_IP,
                    SERVER_PORT);

            boolean running = true;

            System.out.println(
                    "=== Arena Store Administration ===");

            System.out.println(
                    "Connected to Store Front.");

            while (running) {

                System.out.println(
                        "\n=== Administration Menu ===");

                System.out.println(
                        "1. Retrieve Inventory");

                System.out.println(
                        "2. Update Inventory");

                System.out.println(
                        "3. Exit");

                System.out.print(
                        "Select an option: ");

                String choice =
                        scanner.nextLine();

                switch (choice) {

                case "1":

                    String inventoryJson =
                            adminService.sendCommand(
                                    "R",
                                    "");

                    displayInventory(
                            inventoryJson);

                    break;

                case "2":

                    System.out.println(
                            "\nEnter a Salable Product "
                                    + "as a single-line JSON string:");

                    String productJson =
                            scanner.nextLine();

                    String updateResponse =
                            adminService.sendCommand(
                                    "U",
                                    productJson);

                    System.out.println(
                            "Server Response: "
                                    + updateResponse);

                    break;

                case "3":

                    running = false;

                    break;

                default:

                    System.out.println(
                            "Invalid option. "
                                    + "Please select 1 through 3.");

                    break;
                }
            }

            adminService.cleanup();

        } catch (IOException e) {

            System.out.println(
                    "Administration connection error: "
                            + e.getMessage());

        } finally {

            scanner.close();
        }

        System.out.println(
                "Administration Application closed.");
    }
}