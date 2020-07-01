package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import sample.Controllers.Controller;

public class ControllerWin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private  Text counter;

    @FXML

    void initialize() {
result();

    }
    public  void result(){
        counter.setText("Количество попыток:"+" "+ Controller.counterAttempt);
    }
}
