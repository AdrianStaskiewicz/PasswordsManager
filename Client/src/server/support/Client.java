package server.support;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

    // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static PrintStream os = null;
    // The input stream
    private static DataInputStream is = null;
    // The default port.
    public static int portNumber = 8080;
    // The default host.
    public static String host = "localhost";
    // The respone line from server
    public static String responseLine;

    private static BufferedReader inputLine = null;
    private static boolean closed = false;

    public Client() {
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Initializing server connector1");
    }

    public Client(String[] args) {
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Initializing server connector2");
        initializeConnectionParameters(args);

        openStreamsAndSocket();

        if (clientSocket != null && os != null && is != null) {

            /* Create a thread to read from the server. */
            Thread second = new Thread(new Client());
            second.start();

//            while (!closed) {
////                    sendRequest();
//            }

//            closeStreamsAndSocket();
        }
    }

    public void run() {

        try {
            while ((responseLine = is.readLine()) != null) {

                if (responseLine.indexOf("CONNECTION CLOSE") != -1) {
                    break;
                }
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }


    public static void initializeConnectionParameters(String[] args) {
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client} SERVER CONNECTOR: Initializing connection");
        if (args.length < 2) {
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client} SERVER CONNECTOR: Using default host: " + host);
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client} SERVER CONNECTOR: Using default port number: " + portNumber);
        } else {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Using custom host: " + host);
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Using custom port number: " + portNumber);
        }
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Connection initialized successfully");
    }

    public static void openStreamsAndSocket() {
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client} SERVER CONNECTOR: Opening streams and socket");
        try {
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Initializing server connector socket");
            clientSocket = new Socket(host, portNumber);
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Socket initialized successfully");
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Initializing streams");
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Streams initialized successfully");
        } catch (UnknownHostException e) {
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Unknown host: " + host);
        } catch (IOException e) {
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] SERVER CONNECTOR: Couldn't get I/O for the connection to the host " + host);
        }
    }

    public static void closeStreamsAndSocket() {
        try {
            os.close();
            is.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendRequest() {
        try {
            os.println(inputLine.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendRequest(String request) {
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] Request to server: " + request);
        os.println(request.trim());
    }

    public static String getResponse() {
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] Response from server: " + responseLine);
        return responseLine;
    }

    public static void clearResponse() {
        responseLine = null;
    }
}