package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AbstractScreenController {
    public Stage primaryStage= new Stage();
    public FXMLLoader loader = new FXMLLoader();

    @FXML
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
    }

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
        LoadScreenController loadScreenController = new LoadScreenController();
        loadScreenController.initialize();
    }
}
