package demo.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Add a description of the class here
 *
 * @author Chad
 * @version XXX
 */
public class ButtonBarController {
    @FXML
    private Button btn1;

    @FXML
    private Button btn3;

    @FXML
    private void pressBtn2() {
        System.out.println("Button 2 Was pressed");
    }

    public void setBtn1Action(EventHandler<ActionEvent> handler) {
        btn1.setOnAction(handler);
    }

    //This gets called after the constructor. You can't use btn3 until after the constructor is completed
    public void initialize() {
        System.out.println("init");
        btn3.setOnAction(e -> {
            System.out.println("Button 3 was pressed");
        });
    }
}

