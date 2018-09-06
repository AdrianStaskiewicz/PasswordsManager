package application.support;

import database.support.DatabaseConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server extends Thread {
    public static final int PORT_NUMBER = 8081;

    protected Socket socket;
    public static DatabaseConnector database = new DatabaseConnector();
    //db.connect();

    private Server(Socket socket) {
        this.socket = socket;
        //this.database = new DatabaseConnector();
        System.out.println("New client connected from " + socket.getInetAddress().getHostAddress());
        start();
    }

    private String generateAnswer(String request){
        //request = request.substring(0, request.length()-1);
        String answer="1111111";
        if(request.equals("0000000")){
            answer="0000001";
        }
        if(request.equals("0000011")){
            answer="0000100";
        }
        if(request.equals("0000110")){
            answer="0000111";
        }
        if(request.equals("0010000")){
            answer="0010001";
        }
        return answer;
    }

    public void run(){
        InputStream in = null;
        OutputStream out = null;
        try{
            in = socket.getInputStream();
            out = socket.getOutputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            String request;

            while((request = br.readLine()) != null){
                if(!request.isEmpty()){
                    System.out.println("Message received:" + request);
                    request = generateAnswer(request)+'\n';
                    System.out.println("Message send:" + request);
                    out.write(request.getBytes());
                }
            }
        }catch(IOException ex){
            System.out.println("Unable to get streams from client");
        }finally{
            try{
                in.close();
                out.close();
                socket.close();
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date;
        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT_NUMBER);
            date = LocalDateTime.now();
            System.out.println(dtf.format(date)+ " [LOG] Server started");
            database.connect();
            while (true) {
                /**
                 * create a new {@link SocketServer} object for each connection
                 * this will allow multiple client connections
                 */
                new Server(server.accept());
                //database.connect();
            }
        } catch (IOException ex) {
            date = LocalDateTime.now();
            System.out.println(dtf.format(date)+ " [LOG] Unable to start server");
        } finally {
            try {
                if (server != null)
                    server.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}