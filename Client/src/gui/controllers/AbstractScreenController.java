package gui.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import server.support.Client;

import java.io.IOException;

public class AbstractScreenController {
    public MainScreenController mainScreenController;
    private Client client;
    private String tmp;

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

    public void setClient(Client client) {
        this.client = client;
    }

    public void goToSelectionScreen() {
        System.out.println("PRZEJSCIE DO OKNA BAZ DANYCH");//LOG
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/SelectionScreen.fxml"));
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        SelectionScreenController selectionScreenController = loader.getController();
        selectionScreenController.setMainScreenController(mainScreenController);//PRZEKAZUJE KONTROLER DO MAINA NIE DO THIS
        this.mainScreenController.setScreen(gridPane);
    }
}
