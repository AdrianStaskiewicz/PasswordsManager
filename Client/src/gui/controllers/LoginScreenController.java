package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import server.support.Client;

import java.io.IOException;

public class LoginScreenController extends AbstractScreenController {

    //private MainScreenController mainScreenController;
    private Client client;
    private String tmp;

    @FXML
    private CheckBox cRemember;

    @FXML
    private Label lNotification;

    @FXML
    public void Login() {
//        client.sendRequest("TEST");
        if (cRemember.isSelected()) {
            System.out.println("PRZEJSCIE DO OKNA WYBORU BAZ KLUCZY. ZAPAMIETUJE DANE");
        } else {
            System.out.println("PRZEJSCIE DO OKNA WYBORU BAZ KLUCZY. NIE ZAPAMIETUJE DANYCH");
        }

        //Client.sendRequest("TEST");
       /* //weryfikacja
        if(Verifyaccount()){
            System.out.println("WERYFIKACJA POMYSLNA");
        }else{
            System.out.println("BLAD");
            lNotification.setText("Incorrect login or password!");
        }*/

        this.goToSelectionScreen();
    }

    @FXML
    public void SignUp() {
        this.goToRegisterScreen();
    }

//    public void goToSelectionScreen() {
//        System.out.println("PRZEJSCIE DO OKNA BAZ DANYCH");//LOG
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource("/gui/scopes/SelectionScreen.fxml"));
//        GridPane gridPane = null;
//        try {
//            gridPane = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        SelectionScreenController selectionScreenController = loader.getController();
//        selectionScreenController.setMainScreenController(mainScreenController);//PRZEKAZUJE KONTROLER DO MAINA NIE DO THIS
//        mainScreenController.setScreen(gridPane);
//    }

    public void goToRegisterScreen() {
        System.out.println("PRZEJSCIE DO OKNA REJESTRACJI");//LOG
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/RegisterScreen.fxml"));
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RegisterScreenController registerScreenController = loader.getController();
        registerScreenController.setMainScreenController(mainScreenController);//PRZEKAZUJE KONTROLER DO MAINA NIE DO THIS
        mainScreenController.setScreen(gridPane);
        this.mainScreenController.primaryStage.setFullScreen(true);
    }

    @Override
    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
}
