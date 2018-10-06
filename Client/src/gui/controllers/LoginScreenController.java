package gui.controllers;

import database.support.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import server.support.Client;

import java.sql.SQLException;

public class LoginScreenController extends AbstractScreenController {

    private DatabaseConnector localDatabase;
    private Client client;
    private String request;
    private String response;

    @FXML
    private TextField tfUserName;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private CheckBox cRemember;

    @FXML
    private Label lNotification;

    public void autologinCheck() {
        String rememberUser = null;
        try {
            rememberUser = localDatabase.rememberUserCheck();
            if (localDatabase.rememberUserCheck() != null) {
                String[] parameters = new String[3];
                parameters = rememberUser.split("\\s");
                runLoginProcedureForUser(parameters[1], parameters[2]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Login() {
//        client.sendRequest("PUSTY");

        autologinCheck();
        request = "login ";

        if (tfUserName.getText() == null) {
            displayNotification("Username field is empty!");
        } else {
            if (tfUserName.getText().isEmpty()) {
                displayNotification("Username field is empty!");
            } else {
                if (pfPassword.getText() == null) {
                    displayNotification("Password field is empty!");
                } else {
                    if (pfPassword.getText().isEmpty()) {
                        displayNotification("Password field is empty!");
                    } else {
                        client.sendRequest(request + tfUserName.getText() + " " + pfPassword.getText());
//                        waitForResponse();
                        response = client.getResponse();

                        if(response==null){
                            while(response==null){
                                response=client.getResponse();
                            }
                            response=client.getResponse();
                            client.clearResponse();
                        }

                        if (response.equals("Welcome in Password Manager")) {
                            if (cRemember.isSelected()) {
                                System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] Optional parameter REMEMBER ME set on: TRUE");
                            } else {
                                System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] Optional parameter REMEMBER ME set on: FALSE");
                            }
                            goToSelectionScreen();
                        } else {
                            displayNotification(response);
                            clearForm();
                        }

                    }
                }
            }
        }
    }

    @FXML
    public void SignUp() {
        this.goToRegisterScreen();
    }

//    public void waitForResponse() {
//        while (client.getResponse() == null) {
//            while ((response = client.getResponse())==null) {
//
//            }
//        }
//
//        if (response != null) {
//            while ((response = client.getResponse()).isEmpty()) {
//            }
//        }
//    }

    public void runLoginProcedureForUser(String username, String password) {
        //baza jesli ma polaczenie synchronizuje z kontem internetowym
        if (localDatabase.onlineAccountCheck(username)) {
            if (connectionCheck()) {
                //synchronizuj
            }
        }
        //loguj
    }

    public Boolean connectionCheck() {
        client.sendRequest("check");
        response = client.getResponse();
        if (response != null) {
            if (response.equals("ok")) {
                return true;
            }
        }
        return false;
    }

    public void displayNotification(String response) {
        lNotification.setText(response);
    }

    public void clearForm() {
        tfUserName.clear();
        pfPassword.clear();
    }

    @Override
    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @Override
    public void setLocalDatabase(DatabaseConnector localDatabase) {
        this.localDatabase = localDatabase;
    }

    @Override
    public void setClient(Client client) {
        this.client = client;
    }
}
