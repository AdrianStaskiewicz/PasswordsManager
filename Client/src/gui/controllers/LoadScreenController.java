package gui.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import server.support.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class LoadScreenController extends AbstractScreenController implements Initializable {

    private MainScreenController mainScreenController;
    private Client client;
    private String tmp;
    private GridPane gridPane = null;

    private double value = 0.0;

    @FXML
    private ProgressBar pbProgressBar;

    @FXML
    private Label lProgressLabel;

    Task copyWorker;
    Scene scene;
    public Stage stage;

    /*@Override
    public void initialize() {
        this.stage= stage;
        this.scene=scene;
        //this.afterInit();
        //primaryStage.close();
        //goToLoginPage();
    }*/

    public ProgressBar getPBSplashValue()
    {
        return pbProgressBar;
    }

    public Label getlProgressLabel(){
        return lProgressLabel;
    }

    @Override
    public void afterInit() {
        this.pbProgressBar.setProgress(0.0);
        this.copyWorker = createWorker();
        this.pbProgressBar.progressProperty().unbind();
        this.pbProgressBar.progressProperty().bind(copyWorker.progressProperty());

        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                lProgressLabel.setText(newValue);
                if (pbProgressBar.getProgress() >= 0.99) {
                }
            }

        });

        Thread t1 = new Thread(copyWorker);
        t1.start();
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i <= 100; i++) {
                    //load settings here
                    //set a correct info and percentage here
                    Thread.sleep(50);
                    updateMessage("Task Completed : " + ((i)) + "%");
                    updateProgress(i, 100);
                }
                return true;
            }
        };
    }

    public void setPbProgressBar(double value) {
        //pbProgressBar.setProgress(this.getProgressValue()/100);
        pbProgressBar.setProgress(value);
    }

    /*public void setlProgressLabel() {
        lProgressLabel.setText(this.getProgressInfo());
    }*/


    public void updateProgressBar(double value) {
        pbProgressBar.setProgress(value);
    }

//    public void goToLoginPage() {
//        System.out.println("PRZEJSCIE DO OKNA LOGOWANIA");//LOG
//        setFullScreen();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource("/gui/scopes/LoginScreen.fxml"));
//        GridPane gridPane = null;
//        try {
//            gridPane = loader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        setFullScreen();
//        LoginScreenController loginScreenController = loader.getController();
//
//        loginScreenController.setMainScreenController(mainScreenController);//PRZEKAZUJE KONTROLER DO MAINA NIE DO THIS
//        mainScreenController.setScreen(gridPane);
//    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
