package admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Provides network communication between the
 * Administration Application and Store Front.
 *
 * @author Shawn Kripner
 */
public class AdminService {

    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    /**
     * Connects to the Store Front server.
     *
     * @param ipAddress the server IP address
     * @param port the server port
     * @throws IOException if the connection fails
     */
    public void start(
            String ipAddress,
            int port)
            throws IOException {

        socket =
                new Socket(
                        ipAddress,
                        port);

        output =
                new PrintWriter(
                        socket.getOutputStream(),
                        true);

        input =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));
    }

    /**
     * Sends an administration command to the Store Front.
     *
     * @param command the command to send
     * @param payload the JSON payload
     * @return the response from the Store Front
     * @throws IOException if communication fails
     */
    public String sendCommand(
            String command,
            String payload)
            throws IOException {

        String message =
                command + "|" + payload;

        output.println(message);

        return input.readLine();
    }

    /**
     * Closes all network resources.
     *
     * @throws IOException if cleanup fails
     */
    public void cleanup()
            throws IOException {

        if (input != null) {
            input.close();
        }

        if (output != null) {
            output.close();
        }

        if (socket != null) {
            socket.close();
        }
    }
}