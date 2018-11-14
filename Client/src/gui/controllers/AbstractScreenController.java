package gui.controllers;

import database.support.DatabaseConnector;
import javafx.fxml.FXML;
import server.support.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ResourceBundle;

public class AbstractScreenController {
    public MainScreenController mainScreenController;
    public DatabaseConnector localDatabase;// = new DatabaseConnector();
    public Client client;// = new Client();

    public Stage primaryStage= new Stage();
    public FXMLLoader loader = new FXMLLoader();

    @FXML
    public void initialize(){

    }

    public void setStage(Stage stage){
        this.primaryStage=stage;
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void setLocalDatabase(DatabaseConnector localDatabase) {
        this.localDatabase = localDatabase;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void goToSelectionScreen() {
        System.err.println(utilities.DateTimeFormatter.getDateTime()+" [LOG] [Client] Loading view: SELECTION SCREEN");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/SelectionScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
        loader.setResources(bundle);
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SelectionScreenController selectionScreenController = loader.getController();
        selectionScreenController.setMainScreenController(mainScreenController);//PRZEKAZUJE KONTROLER DO MAINA NIE DO THIS
        selectionScreenController.setLocalDatabase(mainScreenController.localDatabase);
        selectionScreenController.setClient(mainScreenController.client);
        mainScreenController.setScreen(gridPane);
    }

    public void goToRegisterScreen() {
        System.err.println(utilities.DateTimeFormatter.getDateTime()+" [LOG] [Client] Loading view: REGISTER SCREEN");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/RegisterScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
        loader.setResources(bundle);
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RegisterScreenController registerScreenController = loader.getController();
        registerScreenController.setMainScreenController(mainScreenController);//PRZEKAZUJE KONTROLER DO MAINA NIE DO THIS
        registerScreenController.setLocalDatabase(mainScreenController.localDatabase);
        registerScreenController.setClient(mainScreenController.client);
        mainScreenController.setScreen(gridPane);
    }

    public void goToLoginScreen() {
        System.err.println(utilities.DateTimeFormatter.getDateTime()+" [LOG] [Client] Loading view: LOGIN SCREEN");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/LoginScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
        loader.setResources(bundle);
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginScreenController loginScreenController = loader.getController();
        loginScreenController.setMainScreenController(mainScreenController);
        loginScreenController.setLocalDatabase(mainScreenController.localDatabase);
        loginScreenController.setClient(mainScreenController.client);
        mainScreenController.setScreen(gridPane);
        loginScreenController.autologinCheck();
    }
}
