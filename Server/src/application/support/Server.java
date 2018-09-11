package application.support;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;

public class Server {

    // The default port number.
    public static int portNumber = 8080;
    // The default server socket.
    private static ServerSocket serverSocket = null;
    // The default client socket.
    private static Socket clientSocket = null;
    // Limit of clients number per session
    private static final int maxClientsCount = 2;
    //Array of threads supporting client connections
    private static final ClientThread[] threads = new ClientThread[maxClientsCount];

    public Server(){

    }

    public Server(String[] args){
        initializeConnectionParameters(args);

        openSocket();

        //Waiting for clients connections
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                int i = 0;
                for (i = 0; i < maxClientsCount; i++) {
                    if (threads[i] == null) {
                        (threads[i] = new ClientThread(clientSocket, threads)).start();
                        break;
                    }
                }

                if (i == maxClientsCount) {
                    PrintStream os = new PrintStream(clientSocket.getOutputStream());
                    os.println("Server too busy. Try later.");
                    os.close();
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }


    public static void initializeConnectionParameters(String[] args){
        if (args.length < 1) {
            System.err.println("[LOG] [Server} Using default port number: " + portNumber);
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
            System.err.println("[LOG] [Server} Using custom port number: " + portNumber);
        }
    }

    public static void openSocket(){
        try {
            serverSocket = new ServerSocket(portNumber);
            System.err.print("[LOG] [Server] Default socket created successfully.");
        } catch (IOException e) {
            System.out.println(e);
            System.err.print("[LOG] [Server] Default socket creation failed.");
        }
    }
}