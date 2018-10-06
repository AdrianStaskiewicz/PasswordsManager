package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import server.support.Client;

import java.io.IOException;

public class RegisterScreenController extends AbstractScreenController{

//    private MainScreenController mainScreenController;
    private Client client;

    @FXML
    private Label lNotification;

    @FXML
    private TextField tfForename;

    @FXML
    private TextField tfSurename;

    @FXML
    private TextField tfEmail;

    @FXML
    private TextField tfUsername;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfRepeatPassword;

    @FXML
    private CheckBox cStatus;

    @FXML
    private CheckBox cAccept;

    @FXML
    public void Regulations(){

    }

    @FXML
    public void SignUp(){
        String request = "reg";

        client.sendRequest(request);
        //String response=client.getResponse();
    }

    @FXML
    public void LogIn(){
        goToLoginScreen();
//        goToSelectionScreen();
    }

    @Override
    public void setMainScreenController(MainScreenController mainScreenController){
        this.mainScreenController = mainScreenController;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
