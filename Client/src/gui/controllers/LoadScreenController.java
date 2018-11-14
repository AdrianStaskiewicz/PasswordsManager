package gui.controllers;

import database.support.DatabaseConnector;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import server.support.Client;

import java.io.IOException;
import java.util.ResourceBundle;

public class LoadScreenController {

    private Client client;
    private DatabaseConnector localDatabase;
    private Stage primaryStage;

    @FXML
    private ProgressBar pbProgressBar;

    @FXML
    private Label lProgressLabel;

    @FXML
    public void initialize() {
//        super.initialize();
        getPBSplashValue().progressProperty().bind(task.progressProperty());
        getlProgressLabel().textProperty().bind(task.messageProperty());
        this.afterInit();
    }

    public void afterInit() {
        System.out.println("ROZPOCZECIE LADOWANIA");
        new Thread(task).start();

        SLinit.start();

        task.setOnSucceeded((Event event) -> {
            System.err.println(utilities.DateTimeFormatter.getDateTime() + " [LOG] [Client] Loading view: MAIN SCREEN");
            FXMLLoader innerLoader = new FXMLLoader(getClass().getResource("/gui/scopes/MainScreen.fxml"));
            ResourceBundle bundle = ResourceBundle.getBundle("gui.resources.lang");
            innerLoader.setResources(bundle);
            try {
                Stage stage = new Stage();
                Parent innerRoot = innerLoader.load();
                MainScreenController mainScreenController = innerLoader.getController();
                mainScreenController.setClient(client);
                mainScreenController.setLocalDatabase(localDatabase);
                stage.setTitle("PasswordsManager 1.0.0");
                stage.setScene(new Scene(innerRoot, 800, 500));
                stage.setMaximized(true);
                stage.show();
                primaryStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public String message = new String();
    public Integer progress = 0;

    final Task task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            for (int i = 1; i <= 100000000; i++) {
                updateProgress(i, 100000000);
                updateMessage(message + i / 1000000 + "%");
            }
            while (client == null || localDatabase == null) {

            }
            return null;
        }
    };

    Thread SLinit = new Thread(this::initializeApplication);
    Thread SCinit = new Thread(this::initializeClient);
    Thread LDBCinit = new Thread(this::initializeLocalDatabaseConnector);
    Thread CWLDB = new Thread(this::connectWithLocalDatabase);

    public ProgressBar getPBSplashValue() {
        return pbProgressBar;
    }

    public Label getlProgressLabel() {
        return lProgressLabel;
    }

    private void initializeClient() {
        String[] arg = new String[1];
        client = new Client(arg);
        System.out.println("SCinit finished");
    }

    private void initializeLocalDatabaseConnector() {
        localDatabase = new DatabaseConnector();
        localDatabase.name = "LDBC A";
        System.out.println("LDBCinit finished");
    }

    private void connectWithLocalDatabase() {
        localDatabase.connect();
        System.out.println("CWLDB finished");
    }

    private static DatabaseConnector createLocalDatabaseConnector() {
        return new DatabaseConnector();
    }

    private static void waitForThreadFinish(Thread thread) {
        while (thread.isAlive()) {

        }
    }

    private void initializeApplication() {
        try {
            message = "Tworzenie instacji klienta lokalnej bazy danych";
            Thread.sleep(2000);
            LDBCinit.start();
            waitForThreadFinish(LDBCinit);
            message = "Nawiazywanie polaczenia z lokalna baza danych ";
            Thread.sleep(2000);
            CWLDB.start();
            waitForThreadFinish(CWLDB);
            message = "Tworzenie instancji klienta ";
            Thread.sleep(2000);
            SCinit.start();
            waitForThreadFinish(SCinit);
            message = "Nawiazywanie polaczenia z serwerem ";

            message = "Sprawdzanie poprawnosci polaczenia ";

            message = "Weryfikowanie polaczenia ze zdalna baza danych ";

            message = "Pobieranie ustawien systemu ";
            //dopoki zaciaga dane z ustawieniami systemu
            message = "Ladowanie ustawien systemu";
//            progress = 0;
            message = "Sprawdzanie aktualizacji ";

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

}
