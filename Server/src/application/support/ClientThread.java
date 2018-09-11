package application.support;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

class ClientThread extends Thread {

    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
    private final ClientThread[] threads;
    private int maxClientsCount;

    public ClientThread(Socket clientSocket, ClientThread[] threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
        maxClientsCount = threads.length;
    }

    public void run() {
        int maxClientsCount = this.maxClientsCount;
        ClientThread[] threads = this.threads;

        try {

            //Creating input and output streams
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());

            //Sending a welcome message here
            os.println("Enter your name.");
            String name = is.readLine().trim();

            os.println("Hello " + name
                    + " to our chat room.\nTo leave enter /quit in a new line");

            while (true) {
                String line = is.readLine();
                if (line.startsWith("/quit")) {
                    break;
                }
                //READING AND SENDING MESSAGES method here
                os.println(line);
            }

            //Sending a exit message method here
            os.println("*** Bye " + name + " ***");

            //Making a space method here
            for (int i = 0; i < maxClientsCount; i++) {
                if (threads[i] == this) {
                    threads[i] = null;
                }
            }

            /*
             * Close the output stream, close the input stream, close the socket.
             */
            is.close();
            os.close();
            clientSocket.close();
        } catch (IOException e) {
        }
    }
}
