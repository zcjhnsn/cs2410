package cs2410.controller;

import cs2410.model.ColorPanel;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

public class MainController {
    @FXML
    private HBox menuBar;

    ColorPanel colorPanel;

    public MainController() {
        colorPanel = new ColorPanel();
    }

    public ColorPanel getColorPanel() {
        return colorPanel;
    }
}
