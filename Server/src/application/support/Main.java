package application.support;

import database.support.DatabaseConnector;

public class Main{
    public static void main(String[] args) {
        //DatabaseConnector db = new DatabaseConnector();
        //db.connect();

        DatabaseConnector db = new DatabaseConnector();
        db.connect();
        String ipAddress = "";
        String portNumber = "8081";

        /*ServerSocket server = null;
        try {
            server = new ServerSocket(8085);
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
           /*     new Server(server.accept());
            }
        } catch (IOException ex) {
            System.out.println("Unable to start server.");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }*/
        //Server server = new Server();
        //server.runWithParameters(ipAddress, portNumber);

        //server.database
    }
}


