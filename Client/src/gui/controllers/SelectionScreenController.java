package gui.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

//import javax.swing.text.html.ListView;

public class SelectionScreenController extends AbstractScreenController {

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
