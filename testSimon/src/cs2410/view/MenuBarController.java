//package cs2410.view;
//
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.paint.Color;
//
//public class MenuBarController {
//
//    // NOTE: MenuBar
//    @FXML
//    private MenuBar menuBar;
//
//    @FXML
//    private MenuItem closeMenuItem;
//
//    @FXML
//    private MenuItem changeColorMenuItem;
//
//    @FXML
//    private MenuItem changeModeMenuItem;
//
//    @FXML
//    private MenuItem aboutMenuItem;
//
//    @FXML
//    private MenuItem rulesMenuItem;
//
//    //NOTE: Start Button
//    @FXML
//    private static Button startBtn;
//
//    @FXML
//    private ColorPicker colorPicker;
//
//    private int highScore;
//
//    private int currentScore;
//
//    @FXML
//    private Label highScoreLbl;
//
//    @FXML
//    private Label currentScoreLbl;
//
//    public MenuBarController(int score, int highScore) {
//        this.currentScore = score;
//        this.highScore = highScore;
//        startBtn = new Button("Start Game");
//        currentScoreLbl = new Label("Current Score: " + this.currentScore);
//        highScoreLbl = new Label("High Score: " + this.highScore);
//    }
//
//    public void setFillPickerAction(EventHandler<ActionEvent> event) {
//        colorPicker.setOnAction(event);
//    }
//
//    public void setFillPickerValue(Color color) {
//        colorPicker.setValue(color);
//    }
//
//    public Color getFillPickerValue() {
//        return colorPicker.getValue();
//    }
//
//    public static Button getStartBtn() {
//        return startBtn;
//    }
//}
