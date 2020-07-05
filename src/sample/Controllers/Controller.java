package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.Exceptions.ErrorEnter;
import sample.Exceptions.Win;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button regulations;

    @FXML
    private TextArea out;

    @FXML
    private TextField in;

    @FXML
    private Button run;

    public void parseArray(String mas[], int newmas[]) throws ErrorEnter {
        for(int i=0; i<4;i++){
            newmas[i] = Integer.parseInt(mas[i]);
        }
        for(int i=0;i<3;i++){
            for(int j=i+1; j<4;j++){
                if(newmas[i]==newmas[j]){
                    throw new ErrorEnter();
                }
            }
        }

    }
    public  String counterBullsCows(int mas[], int numbers[]) {
        if(Arrays.equals(mas,numbers)){
            return "Победа";
        }
        int counterCows = 0;
        int counterBulls = 0;
        for(int i = 0 ; i<4;i++){
            for(int j = 0; j<4;j++){
                if(mas[i]==numbers[j]){
                    if(i==j){
                        counterBulls++;
                    }
                    else counterCows++;
                }
            }
        }
        return "Б"+counterBulls+", К"+counterCows+"\n";

    }
    static AtomicInteger counterAttempt = new AtomicInteger();
    @FXML
    void initialize() {

        int number[] = new int[4];
        Arrays.fill(number,-1);
        Random rand = new Random();
        for (int i =0; i<4;i++) {
            boolean check;
            do {
                number[i] = rand.nextInt(10);
                check = false;
                for(int j = 0; j<i;j++){
                    if(number[i]==number[j]){
                        check=true;
                    }
                }
            }while (check);

        }
        run.setOnAction(actionEvent -> {
try {


    String s = in.getText();
    boolean pr = s.matches("(\\d){4}");
    if (pr) {
        counterAttempt.getAndIncrement();
        int newMas[] = new int[4];
        String numbers[] = s.split("\\b?");
        parseArray(numbers, newMas);
        String result = counterBullsCows(number, newMas);
        if (result.equals("Победа")){
            throw new Win();
        }
        out.setText(out.getText() + result);
     out.setScrollTop(Double.MAX_VALUE);

    } else {
        throw new ErrorEnter();
    }
}catch (Win e){
   e.run();
}
catch ( ErrorEnter e){
    e.run();
}
        });
        regulations.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/FXML/regulations.fxml"));
            try {
                loader.load();
            }catch (IOException i){
                i.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Правила");
            stage.show();
        });




    }
}
