package gui.controllers;

import database.support.DatabaseConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import server.support.Client;

import java.io.IOException;
import java.util.ResourceBundle;

public class AbstractScreenController {
    public MainScreenController mainScreenController;
    public DatabaseConnector localDatabase;
    public Client client;

    public Stage primaryStage= new Stage();
    public FXMLLoader loader = new FXMLLoader();

    /*@FXML
    public void initialize(){
        loader.setLocation(this.getClass().getResource("/gui/scopes/MainScreen.fxml"));
        StackPane stackpane = null;
        try {
            stackpane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainScreenController mainScreenController = loader.getController();
        //mainScreenController.setClient(client);

        Scene scene = new Scene(stackpane,600,400);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.setTitle("PasswordsManager 1.0.0");
        primaryStage.show();
//        primaryStage.close();

        onInit();
        afterInit();
    }*/

    public void onInit(){

    }

    public void afterInit(){

    }

    public void setFullScreen(){
        this.primaryStage.setFullScreen(true);
//        this.primaryStage.show()
    }

    public void setScreen(){
    }

    public void goToScreen(){
        //LoadScreenController loadScreenController = new LoadScreenController();
        //loadScreenController.initialize();
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
        mainScreenController.setScreen(gridPane);
        loginScreenController.autologinCheck();
    }

    public void goBack(){

    }
}
