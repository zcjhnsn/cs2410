package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainController {
    // Note: We are injecting the other FXML file, giving the type of the main container of the toolBar
    @FXML
    private HBox toolBar;

    @FXML  // Note the naming convention:
    private ToolBarController toolBarController;

    public ToolBarController getToolBarController() {
        return toolBarController;
    }

    public void initialize() {
        toolBarController.registerHandler(new EventHandler() {
            @Override
            public void handle(Event event) {
                System.out.println("it works");
            }
        });
    }
}
