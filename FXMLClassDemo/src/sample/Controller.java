package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    @FXML
    private Button btn1;

    @FXML
    private  void btn1Press() {
        System.out.println("You pressed Btn1");
    }

    public void initialize() {
        btn1.setOnAction(event -> {
            System.out.println("You pressed Btn2");
        });
    }
}
