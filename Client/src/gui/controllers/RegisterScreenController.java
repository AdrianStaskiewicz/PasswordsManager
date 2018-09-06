package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class RegisterScreenController {

    private MainScreenController mainScreenController;

    @FXML
    private Label lNotification;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfUserName;

    @FXML
    private PasswordField pfPassword;

    @FXML
    private PasswordField pfRepeatPassword;

    @FXML
    private TextField tfEmail;

    @FXML
    private CheckBox cAccept;

    @FXML
    public void Regulations(){

    }

    @FXML
    public void SignUp(){

    }

    @FXML
    public void LogIn(){
        System.out.println("PRZEJSCIE DO OKNA LOGOWANIA");//LOG
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/LoginScreen.fxml"));
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainScreenController.setScreen(gridPane);
    }

    public void setMainScreenController(MainScreenController mainScreenController){
        this.mainScreenController = mainScreenController;
    }
}
