package application.support;

import gui.controllers.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import server.support.Client;

public class Main extends Application {

    public static void Main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        String host = "127.0.0.1";
        int port = 8081;
        Client client = new Client(host, port);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/gui/scopes/MainScreen.fxml"));
        StackPane stackpane = loader.load();

        MainScreenController mainScreenController = loader.getController();
        mainScreenController.setClient(client);

        Scene scene = new Scene(stackpane,600,400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PasswordsManager 1.0.0");
        primaryStage.show();
    }
}
