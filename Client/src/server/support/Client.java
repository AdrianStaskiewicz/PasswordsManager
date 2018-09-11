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

    private static BufferedReader inputLine = null;
    private static boolean closed = false;

    public Client(){

    }

    public Client(String[] args){
        initializeConnectionParameters(args);

        openStreamsAndSocket();

        if (clientSocket != null && os != null && is != null) {
            try {

                /* Create a thread to read from the server. */
                new Thread(new Client()).start();
                while (!closed) {
                    os.println(inputLine.readLine().trim());
                }

                closeStreamsAndSocket();
            } catch (IOException e) {
                System.err.println("IOException:  " + e);
            }
        }
    }

    public void run() {

        String responseLine;
        try {
            while ((responseLine = is.readLine()) != null) {
                System.out.println(responseLine);
                if (responseLine.indexOf("CONNECTION CLOSE") != -1)
                    break;
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }


    public static void initializeConnectionParameters(String[] args){
        if (args.length < 2) {
            System.err.println("[LOG] [Client} Using default host: " +host);
            System.err.println("[LOG] [Client} Using default port number: " + portNumber);
        } else {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
            System.err.println("[LOG] [Client} Using custom host: " +host);
            System.err.println("[LOG] [Client} Using custom port number: " + portNumber);
        }
    }

    public static void openStreamsAndSocket(){
        try {
            clientSocket = new Socket(host, portNumber);
            inputLine = new BufferedReader(new InputStreamReader(System.in));
            os = new PrintStream(clientSocket.getOutputStream());
            is = new DataInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("[LOG] [Client} Unknown host: " +host);
        } catch (IOException e) {
            System.err.println("[LOG] [Client} Couldn't get I/O for the connection to the host " + host);
        }
    }

    public static void closeStreamsAndSocket(){
        try {
            os.close();
            is.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}