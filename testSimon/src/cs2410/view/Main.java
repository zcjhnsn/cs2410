package cs2410.view;

import cs2410.model.ColorPane;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class Main extends Application {
    private MenuBarController menuBarController = new MenuBarController(0, 0);

    private int numCorrect;
    private int highScore;
    private Random generateRandom;
    private int score;
    boolean isPlayerTurn = true;

    @FXML
    private ColorPane colorPane1;

    @FXML
    private ColorPane colorPane2;

    @FXML
    private ColorPane colorPane3;

    @FXML
    private ColorPane colorPane4;

    private Vector<Integer> seq;

    @FXML
    Button startBtn = menuBarController.getStartBtn();

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL url = getClass().getResource("main.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        BorderPane root = loader.load();

        //TODO: read in file here
        highScore = 0;

        score = 0;
        numCorrect = 0;

        generateRandom = new Random();
        ArrayList<ColorPane> colorPanes = new ArrayList<ColorPane>();
        /**
         * Configure the Buttons for the game
         */
        colorPane1 = new ColorPane(Color.BLUE);

        colorPane2 = new ColorPane(Color.RED);
        colorPane3 = new ColorPane(Color.YELLOW);
        colorPane4 = new ColorPane(Color.GREEN);
        colorPane1.setBackground(new Background(new BackgroundFill(colorPane1.getColor().darker(), CornerRadii.EMPTY, Insets.EMPTY)));
        colorPane2.setBackground(new Background(new BackgroundFill(colorPane2.getColor().darker(), CornerRadii.EMPTY, Insets.EMPTY)));
        colorPane3.setBackground(new Background(new BackgroundFill(colorPane3.getColor().darker(), CornerRadii.EMPTY, Insets.EMPTY)));
        colorPane4.setBackground(new Background(new BackgroundFill(colorPane4.getColor().darker(), CornerRadii.EMPTY, Insets.EMPTY)));
        colorPanes.add(colorPane1);
        colorPanes.add(colorPane2);
        colorPanes.add(colorPane3);
        colorPanes.add(colorPane4);

        int index = 0;
        for(int row = 0; row < 2; row++){
            for(int col = 0; col < 2; col++){
                GridPane.setRowIndex(colorPanes.get(index), row);
                GridPane.setColumnIndex(colorPanes.get(index), col);
                ((GridPane)root.getCenter()).getChildren().add(colorPanes.get(index));
                index++;
            }
        }


        Scene scene = new Scene(root, 500, 500);

        startGame();



        primaryStage.setTitle("Simonish");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void playGame() {
        startGame();

    }

    public void startGame() {
        startBtn.setOnAction(event -> {
            seq = new Vector<Integer>();
            playSequence();
            score = 0;
        });
    }



        public void playerTurn(ActionEvent event) {
            ColorPane temp = (ColorPane) event.getSource();
            if (temp.equals(colorPane1) && seq.get(numCorrect) == 0) {
                numCorrect++;
                score++;
                colorPane1.activate();
            } else if (temp.equals(colorPane2) && seq.get(numCorrect) == 1) {
                numCorrect++;
                score++;
                colorPane2.activate();
            } else if (temp.equals(colorPane3) && seq.get(numCorrect) == 2) {
                numCorrect++;
                score++;
                colorPane3.activate();
            } else if (temp.equals(colorPane4) && seq.get(numCorrect) == 3) {
                numCorrect++;
                score++;
                colorPane4.activate();
            } else {
                lose();
            }
            if (numCorrect == seq.size()) {
                numCorrect = 0;
                playSequence();
            }
        }


    public void lose() {
        if (score > highScore)
            highScore = score;
        numCorrect = 0;
    }

    public void playSequence() {
        seq.add(generateRandom.nextInt(4));

        /**
         * loops through the integers in the sequence and activates each one.
         */
        for (Integer i : seq) {
            if (i.equals(0))
                colorPane1.activate();
            if (i.equals(1))
                colorPane2.activate();
            if (i.equals(2))
                colorPane3.activate();
            if (i.equals(3))
                colorPane4.activate();
        }
    }

    private void setColorPaneHandler(ColorPane colorPane) {
        if(isPlayerTurn) {
            colorPane.setOnMousePressed(event -> {
                colorPane.pressed();
            });

            colorPane.setOnMouseReleased(event -> {
                colorPane.released();
            });
        }


        colorPane.setOnMouseClicked(event -> {
            if (menuBarController.changeColorMenuItem.isSelected()) {
                menuBarController.setFillPickerValue((Color) colorPane.getColor());

                if (menuBarController.getFillPickerValue() != colorPane.getColor()) {
                    colorPane.setBackground(new Background(new BackgroundFill(menuBarController.getFillPickerValue(), CornerRadii.EMPTY, Insets.EMPTY)));
                }

                menuBarController.setFillPickerAction(event1 -> {
                    colorPane.setBackground(new Background(new BackgroundFill(menuBarController.getFillPickerValue(), CornerRadii.EMPTY, Insets.EMPTY)));
                });
            }
        });

    }

    public class MenuBarController {
        @FXML
        private MenuBar menuBar;

        @FXML
        private MenuItem closeMenuItem;

        @FXML
        private RadioMenuItem changeColorMenuItem;

        @FXML
        private RadioMenuItem playGameMenuItem;

        @FXML
        private MenuItem aboutMenuItem;

        @FXML
        private MenuItem rulesMenuItem;

        //NOTE: Start Button
        @FXML
        private Button startBtn;

        @FXML
        private ColorPicker colorPicker;

        private int highScore;

        private int currentScore;

        @FXML
        private Label highScoreLbl;

        @FXML
        private Label currentScoreLbl;

        public MenuBarController(int score, int highScore) {
            this.currentScore = score;
            this.highScore = highScore;
            startBtn = new Button("Start Game");
            currentScoreLbl = new Label("Current Score: " + this.currentScore);
            highScoreLbl = new Label("High Score: " + this.highScore);
        }

        public void setFillPickerAction(EventHandler<ActionEvent> event) {
            colorPicker.setOnAction(event);
        }

        public void setFillPickerValue(Color color) {
            colorPicker.setValue(color);
        }

        public Color getFillPickerValue() {
            return colorPicker.getValue();
        }

        public Button getStartBtn() {
            return startBtn;
        }
    }

    private void initToolPane(RadioMenuItem btn) {
        btn.setOnMenuValidation(event -> {
            if (btn.isSelected()) {
                menuBarController.setFillPickerAction(event1 -> {
                    colorPane1.setBackground(new Background(new BackgroundFill(menuBarController.getFillPickerValue(), CornerRadii.EMPTY, Insets.EMPTY)));
                });
            }

        });
    }


}