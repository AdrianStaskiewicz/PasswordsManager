package server.support;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{

    protected Socket echoSocket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;
    private String answerPocket;
    private String requestPocket;

    public Client(String host, int port) {
        try {
            String serverHostname = new String("127.0.0.1");
            System.err.println("Connecting to host " + serverHostname + " on port " + port + ".");

            try {
                echoSocket = new Socket(serverHostname, 8081);
                out = new PrintWriter(echoSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + serverHostname);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            /** {@link UnknownHost} object used to read from console */
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            this.run();

            /** Closing all the resources */
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        this.sendFirstRequest();
        while (true) {
            this.receiveAnswer();
            System.err.println("ODEBRANE: " + answerPocket);

            if (answerPocket.equals("1111111")) {
                break;
            }
            if(answerPocket.equals("0010001")){
                System.err.println("DZIALA");
                break;
            }

            requestPocket=this.generateRequest(answerPocket);
            this.sendRequest(requestPocket);
            System.err.println("WYSLANE: " + requestPocket);
        }
    }

    private String generateRequest(String answer){
        String request="1111111";
        if(answer.equals("0000001")){
            request="0000011";
        }
        if(answer.equals("0000100")){
            request="0000110";
        }
        if(answer.equals("0000111")){
            request="0010000";
        }
        if(answer.equals("0010001")){
            request="1010101";
        }
        return request;
    }

    public void sendFirstRequest(){
        out.println("0000011");
    }

    public static void sendRequest(String request){
        out.println(request);
    }

    public void receiveAnswer(){
        try {
            answerPocket=in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}