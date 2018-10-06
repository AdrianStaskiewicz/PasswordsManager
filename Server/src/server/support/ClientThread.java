package server.support;

import database.support.DatabaseConnector;

import javax.naming.spi.DirStateFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;

class ClientThread extends Thread {

    // The client socket
    private static Socket clientSocket = null;
    // The output stream
    private static PrintStream os = null;
    // The input stream
    private static DataInputStream is = null;
    // The request line from client
    private static String requestLine = null;

    private final ClientThread[] threads;
    private int maxClientsCount;
    DatabaseConnector database;

    public ClientThread(Socket clientSocket, ClientThread[] threads, DatabaseConnector database) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        this.maxClientsCount = threads.length;
        this.database = database;
    }

    public void run() {

        int maxClientsCount = this.maxClientsCount;
        ClientThread[] threads = this.threads;

        try {
            openStreams();

            //Sending a welcome message here
            //os.println("Enter your name.");
            //String name = is.readLine().trim();

            //os.println("Hello " + name
                //    + " to our chat room.\nTo leave enter /quit in a new line");

            while (true) {

                //READING MESSAGES
                requestLine = is.readLine();

                System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Server] Request from client: " + requestLine);

                if (requestLine.startsWith("/quit")) {
                    //Sending a exit message method here
                    sendResponse("CONNECTION CLOSE");

                    //Making a space method here
                    for (int i = 0; i < maxClientsCount; i++) {
                        if (threads[i] == this) {
                            threads[i] = null;
                        }
                    }

                    closeStreamsAndSocket();
                    break;
                }

                //ANALYZING REQUEST
                String test = null;
                try {
                    if (requestLine.contains("login")) {
                        String[] parameters = new String[3];
                        parameters = requestLine.split("\\s");
                        test = database.userCredentialsVerification(parameters);
                    } else {
                        if (requestLine.contains("add")) {
                            String[] parameters = new String[3];
                            parameters = requestLine.split("\\s");
                            test = database.newUserRegister(parameters);
                        } else {
                            if(requestLine.contains("check")){
                                test="ok";
                            }else{
                                test = "nie rozpoznano polecenia";
                            }
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //SENDING MESSAGES
                if (test!=null) {
                    sendResponse(test);
                } else {
                    os.println("Bledny login lub haslo");
                }
            }

        } catch (IOException e) {
        }
    }

    public static void sendResponse(String response) {
        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Server] Response to client: " + response);
        os.println(response.trim());
    }

    public static void openStreams() {
        try {
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
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
}
