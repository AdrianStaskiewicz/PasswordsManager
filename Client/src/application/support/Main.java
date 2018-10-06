package application.support;

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
import server.support.Client;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class Main extends Application {

    String message=new String();
    Integer progress=0;
    Client client;
    //Database connector
    DatabaseConnector localDatabase = new DatabaseConnector();

    @Override
    public void start(Stage primaryStage) throws Exception {

        //NA CHWILE
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] Client initialization");
                    String[] arg= new String[1];
                    client = new Client(arg);
            }
        });
        thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] LOCAL DATABASE: database connection");
//                String[] arg= new String[1];
                 localDatabase.connect();
            }
        });

        thread3.start();
        //NA CHWILE


//        Properties config = new Properties();
//        config.load();

        System.err.println(utilities.DateTimeFormatter.getDateTime()+" [LOG] [Client] Loading view: LOAD SCREEN");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/scopes/LoadScreen.fxml"));
        ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
        loader.setResources(bundle);
//        loader.setResources(config);
        Parent root = loader.load();
        LoadScreenController loadScreenController = loader.getController();
        loadScreenController.setClient(client);
        loadScreenController.setLocalDatabase(localDatabase);

        primaryStage.setTitle("PasswordsManager 1.0.0");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();


        //NA CHWILE
        Thread thread = new Thread(new Runnable() {
            public void run() {

                try {
                    message="Ladowanie ustawien systemu ";
                    progress=0;
                    Thread.sleep(2000);
                    message="Tworzenie instancji klienta ";
                    Thread.sleep(1000);
                    message="Nawiazywanie polaczenia z serwerem ";
                    Thread.sleep(2000);
                    message="Sprawdzanie poprawnosci polaczenia ";
                    Thread.sleep(1000);
                    message="Weryfikowanie polaczenia ze zdalna baza danych ";
                    Thread.sleep(2000);
                    message="Sprawdzanie aktualizacji ";
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        //NA CHWILE

        final Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 1; i <= 100000000; i++) {
                    updateProgress(i, 100000000);
                    updateMessage(message+i/1000000+"%");
                }
                return null;
            }
        };


        task.setOnSucceeded((Event event) -> {
            System.err.println(utilities.DateTimeFormatter.getDateTime()+" [LOG] [Client] Loading view: MAIN SCREEN");
            FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("/gui/scopes/MainScreen.fxml"));
            //ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
            innerLoader.setResources(bundle);
//            MainScreenController mainScreenController = loader.getController();
//            mainScreenController.setClient(client);
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
