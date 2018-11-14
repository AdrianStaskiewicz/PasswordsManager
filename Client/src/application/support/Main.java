package application.support;

import server.support.Client;
import database.support.DatabaseConnector;
import gui.controllers.LoadScreenController;
import gui.controllers.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ResourceBundle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] Loading view: LOAD SCREEN");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/scopes/LoadScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
        loader.setResources(bundle);
        Parent root = loader.load();
        LoadScreenController loadScreenController = loader.getController();
        loadScreenController.setStage(primaryStage);

        primaryStage.setTitle("PasswordsManager 1.0.0");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public static void Main(String[] args) {
        launch(args);
    }

}
