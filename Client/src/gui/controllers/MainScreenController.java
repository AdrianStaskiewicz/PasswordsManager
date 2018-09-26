package gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import server.support.Client;

import java.io.IOException;

import static gui.controllers.LoadScreenController.*;

public class MainScreenController extends AbstractScreenController{

//    private Client client;

    @FXML
    private StackPane mainStackPane;

    @FXML
    @Override
    public void initialize(){
//       FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/LoadScreen.fxml"));
        GridPane gridPane = null;
        try {
            gridPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LoadScreenController loadScreenController = loader.getController();
        loadScreenController.setMainScreenController(this);
//        loadScreenController.setClient(client);
        setScreen(gridPane);
        loadScreenController.afterInit();
//        setFullScreen();
    }

    public void setScreen(GridPane gridPane){
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(gridPane);
    }

//    public void setClient(Client client){
//        this.client = client;
//    }

}
