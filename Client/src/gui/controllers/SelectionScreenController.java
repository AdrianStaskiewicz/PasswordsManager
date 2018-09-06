package gui.controllers;

import javafx.fxml.FXML;

//import javax.swing.text.html.ListView;
import javafx.scene.control.ListView;

public class SelectionScreenController {

    private MainScreenController mainScreenController;

    @FXML
    private ListView lvView;

    @FXML
    public void Select(){

    }

    @FXML
    public void Create(){

    }


    public void setMainScreenController(MainScreenController mainScreenController){
        this.mainScreenController = mainScreenController;
    }
}
