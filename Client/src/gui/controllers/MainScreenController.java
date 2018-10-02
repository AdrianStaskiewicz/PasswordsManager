package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainScreenController extends AbstractScreenController {

//    private Client client;

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize(){
//       FXMLLoader loader = new FXMLLoader();
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
        loginScreenController.setMainScreenController(this);
//        loadScreenController.setClient(client);
        setScreen(gridPane);
//        loadScreenController.afterInit();
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
