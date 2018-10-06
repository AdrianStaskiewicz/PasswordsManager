package database.support;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static utilities.DateTimeFormatter.*;

public class DatabaseConnector {

    Connection connection;
    Statement statement;

    public void connect(){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            /*String server = "localhost";
            int port = 1433;
            String login = "PMDBUser";
            String password = "12345";
            String name = "PasswordManagerDatabase";*/

            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=PasswordManagerDatabase","PMDBUser","12345");

            System.err.println( utilities.DateTimeFormatter.getDateTime() + " [LOG] [Server} Connected with database");

            statement = connection.createStatement();
            System.err.println(utilities.DateTimeFormatter.getDateTime()+ " [LOG] [Server] Statement created");
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
            ResultSet rs = this.statement.executeQuery("SELECT * FROM Users");

            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                System.out.println(printRow(rs,columns));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String doQuery1() throws SQLException {
        ResultSet rs=null;
        String message="";
        try {
            rs = statement.executeQuery("SELECT * FROM Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int columns = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            message=message+printRow(rs,columns);
        }
        return message;
    }

    public String doQuery2(String pswd) throws SQLException {
        ResultSet rs=null;
        String message="";
        try {
            String query="SELECT * FROM Users WHERE Password LIKE '"+pswd+"'";
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int columns = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            message=message+printRow(rs,columns);
        }
        return message;
    }



    //QUERIES
//    -OPERATIONS
/*    public String connectionTest(){

    }*/
//    -USER OPERATIONS
    public String userCredentialsVerification(String[] parameters) throws SQLException {
        ResultSet rs=null;
        String message=null;
        try {
            if(parameters.length>=2) {
                String query = "SELECT Password FROM Users WHERE Login = '" + parameters[1] + "' OR Mail = '" + parameters[1] + "'";
                rs = statement.executeQuery(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(rs!=null){
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                message=printRow(rs,columns).substring(1);
            }
        }

        if(message==null){
        message="";
        }

        if(message.isEmpty()){
            return "User doesn't exist";
        }else{
            if(message.equals(parameters[2])){
                return "Welcome in Password Manager";
            }else{
                return "Invalid password";
            }
        }
    }
    /*
        public String newUserCredentialsAvailabilityCheck(){

        }
        public String userDataGenerate(){

        }
        public String userLogin(){

        }
        */
    public String newUserRegister(String[] parameters){
        ResultSet rs=null;

        try {
            String query = "INSERT INTO Users VALUES ('"+parameters[1]+"', '"+parameters[2]+"', '"+parameters[3]+"', '"+parameters[4]+"', '"+parameters[5]+"')";
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }
        return "CREATED";
    }
    /*
    public String userDataChange(){

    }
    public String userDelete(){

    }
//    -KEYGROUP OPERATIONS
    public String newKeyGroupNameAvailabilityCheck(){

    }
    public String keyGroupsListGenerate(){

    }
    public String newKeyGroupCreate(){

    }
    public String mergeKeyGroups(){

    }
    public String keyGroupDelete(){

    }
    public String keyGroupsDataChange(){

    }
//    -ACCOUNT OPERATIONS
    public String accountsListGenerate(){

    }
    public String newAccountNameAvailabilityCheck(){

    }
    public String accountDetailGenerate(){

    }
    public String newAccountCreate(){

    }
    public String moveAccountToAnotherKeyGroup(){

    }
    public String accountDataChange(){

    }
    public String accountDelete(){

    }
//    -SETTINGS OPERATIONS
    public String generalSettingsGenerate(){

    }
    public String customSettingsCreate(){

    }
    public String customSettingsGenerate(){

    }
    public String customSettingsChange(){

    }

    public String firstQuery(){

    }

    public String secondQuery(){

    }
*/
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
