package gui.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import server.support.Client;

import java.io.IOException;

public class MainScreenController {

    private Client client;

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/LoginScreen.fxml"));
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoginScreenController loginScreenController = loader.getController();
        loginScreenController.setMainScreenController(this);
        loginScreenController.setClient(client);
        setScreen(gridPane);
    }

    public void setScreen(GridPane gridPane){
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(gridPane);
    }

    public void setClient(Client client){
        this.client = client;
    }
}
