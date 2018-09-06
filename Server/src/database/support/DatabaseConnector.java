package database.support;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseConnector {

    Connection connection;
    Statement statement;

    public void connect(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            /*String server = "localhost";
            int port = 1433;
            String login = "PMDBUser";
            String password = "12345";
            String name = "PasswordManagerDatabase";*/

            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=PasswordManagerDatabase","PMDBUser","12345");
            date = LocalDateTime.now();
            System.out.println(dtf.format(date)+ " [LOG] Connected with database");

            statement = connection.createStatement();
            date = LocalDateTime.now();
            System.out.println(dtf.format(date)+ " [LOG] Statement created");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime date;
        try {

            statement.execute("SHUTDOWN");
            date = LocalDateTime.now();
            System.out.println(dtf.format(date)+ " [LOG] Database closed");
            statement.close();
            date = LocalDateTime.now();
            System.out.println(dtf.format(date)+ " [LOG] Statement closed");
            connection.close();
            date = LocalDateTime.now();
            System.out.println(dtf.format(date)+ " [LOG] Connection closed");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showUsers(){
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Users");

            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                System.out.println(printRow(rs,columns));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static String printRow(ResultSet rs, int columns) {
        //ResultSetMetaData rsmd = rs.getMetaData().getColumnCount();
        String row="";
        for(int i=1;i<= columns;i++){
            try {
                row=row+" "+rs.getString(i);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return row;
    }
}
