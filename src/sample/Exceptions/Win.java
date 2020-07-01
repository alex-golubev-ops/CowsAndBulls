package sample.Exceptions;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class Win extends Exception {
public void run(){
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/sample/FXML/win.fxml"));
    try {
        loader.load();
    }catch (IOException i){
        i.printStackTrace();
    }
    Parent root = loader.getRoot();
    Stage stage = new Stage();
    stage.setScene(new Scene(root));
    stage.show();

    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
           System.exit(0);
        }
    });

}
}
