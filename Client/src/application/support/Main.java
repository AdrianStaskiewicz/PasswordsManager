package application.support;

import gui.controllers.LoadScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.scene.Parent;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/scopes/LoadScreen.fxml"));
        Parent root = loader.load();
        LoadScreenController loadScreenController = loader.getController();

        primaryStage.setTitle("PasswordsManager 1.0.0");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();

        final Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100000000; i++) {
                    updateProgress(i, 100000000);
                    updateMessage("Sprawdzanie aktualizacji: "+i/1000000+"%");
                }

                return null;
            }
        };


        task.setOnSucceeded((Event event) -> {
            FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("/gui/scopes/LoginScreen.fxml"));
            try {
                Stage stage = new Stage();
                Parent innerRoot = innerLoader.load();
                stage.setTitle("PasswordsManager 1.0.0");
                stage.setScene(new Scene(innerRoot, 800, 500));
                stage.setMaximized(true);
                stage.show();
                primaryStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        loadScreenController.getPBSplashValue().progressProperty().bind(task.progressProperty());
        loadScreenController.getlProgressLabel().textProperty().bind(task.messageProperty());

        new Thread(task).start();
    }

    public static void Main(String[] args) {
        launch(args);
    }
}
