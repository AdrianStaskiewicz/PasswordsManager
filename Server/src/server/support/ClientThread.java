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

    private DataInputStream is = null;
    private PrintStream os = null;
    private Socket clientSocket = null;
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

            //Creating input and output streams
            is = new DataInputStream(clientSocket.getInputStream());
            os = new PrintStream(clientSocket.getOutputStream());

            //Sending a welcome message here
            os.println("Enter your name.");
            String name = is.readLine().trim();

            os.println("Hello " + name
                    + " to our chat room.\nTo leave enter /quit in a new line");

            while (true) {


                //READING MESSAGES
                String line = is.readLine();
                if (line.startsWith("/quit")) {
                    break;
                }

                //ANALYZING REQUEST
                String test = null;
                try {
                    if(line.contains("login")){
                        String[] parameters = new String[3];
                        parameters = line.split("\\s");
                        test = database.userCredentialsVerification(parameters);
                    }
                    else{
                        if(line.contains("add")){
                            String[] parameters = new String[3];
                            parameters = line.split("\\s");
                            test = database.newUserRegister(parameters);
                        }else{
                            test = "nie rozpoznano polecenia";
                        }

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                //SENDING MESSAGES
                if(!test.equals(" ")){
                    os.println(test);
                }else{
                    os.println("ERROR");
                }
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
