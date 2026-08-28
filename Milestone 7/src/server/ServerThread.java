package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;

import exception.FileServiceException;
import inventory.InventoryManager;
import product.SalableProduct;

/**
 * Runs the Store Front administration server
 * on a background thread.
 *
 * @author Shawn Kripner
 */
public class ServerThread extends Thread {

    private static final int PORT = 6666;

    private InventoryManager inventoryManager;
    private ObjectMapper objectMapper;

    /**
     * Creates a new administration server thread.
     *
     * @param inventoryManager the Store Front inventory manager
     */
    public ServerThread(
            InventoryManager inventoryManager) {

        this.inventoryManager =
                inventoryManager;

        objectMapper =
                new ObjectMapper();

        setDaemon(true);
    }

    /**
     * Processes administration requests.
     */
    @Override
    public void run() {

        try (ServerSocket serverSocket =
                new ServerSocket(PORT)) {

            System.out.println(
                    "Administration server started "
                            + "on port "
                            + PORT + ".");

            while (true) {

                try (
                        Socket socket =
                                serverSocket.accept();

                        BufferedReader input =
                                new BufferedReader(
                                        new InputStreamReader(
                                                socket.getInputStream()));

                        PrintWriter output =
                                new PrintWriter(
                                        socket.getOutputStream(),
                                        true)
                ) {

                    String message;

                    while ((message =
                            input.readLine()) != null) {

                        processMessage(
                                message,
                                output);
                    }

                } catch (IOException e) {

                    System.out.println(
                            "Administration client error: "
                                    + e.getMessage());
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "Administration server error: "
                            + e.getMessage());
        }
    }

    /**
     * Processes a command received from
     * the Administration Application.
     *
     * @param message the command and payload
     * @param output response stream
     */
    private void processMessage(
            String message,
            PrintWriter output) {

        String[] parts =
                message.split(
                        "\\|",
                        2);

        String command =
                parts[0];

        String payload =
                parts.length > 1
                        ? parts[1]
                        : "";

        if (command.equalsIgnoreCase("R")) {

            sendInventory(output);

        } else if (command.equalsIgnoreCase("U")) {

            updateInventory(
                    payload,
                    output);

        } else {

            output.println(
                    "ERROR: Invalid command.");
        }
    }

    /**
     * Sends the current inventory as JSON.
     *
     * @param output response stream
     */
    private void sendInventory(
            PrintWriter output) {

        try {

            SalableProduct[] products =
                    inventoryManager
                            .getInventoryArray();

            String json =
                    objectMapper.writeValueAsString(
                            products);

            output.println(json);

        } catch (IOException e) {

            output.println(
                    "ERROR: Unable to serialize inventory.");
        }
    }

    /**
     * Updates Store Front inventory using
     * the received JSON product.
     *
     * @param payload JSON product payload
     * @param output response stream
     */
    private void updateInventory(
            String payload,
            PrintWriter output) {

        try {

            SalableProduct product =
                    objectMapper.readValue(
                            payload,
                            SalableProduct.class);

            inventoryManager
                    .updateProduct(product);

            output.println(
                    "OK: Inventory updated.");

        } catch (IOException e) {

            output.println(
                    "ERROR: Invalid JSON product.");

        } catch (FileServiceException e) {

            output.println(
                    "ERROR: Unable to save inventory.");
        }
    }
}