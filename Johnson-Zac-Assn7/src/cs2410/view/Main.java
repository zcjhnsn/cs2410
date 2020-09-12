package cs2410.view;

import cs2410.controller.MainController;
import cs2410.controller.MenuBarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Vector;

public class Main extends Application {

    private StartGame start;

    private int numCorrect;
    private int highScore;
    private Random generateRandom;
    private MenuBarController menuBarController;
    private MainController mainController;
    private int score;
    private Vector<Integer> seq;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("cs2410/components/main.fxml"));
        primaryStage.setTitle("Simonish");
        primaryStage.setScene(new Scene(root, 675, 480));
        primaryStage.show();
    }

    public class StartGame {

        StartGame() {
            Button startBtn = MenuBarController.getStartBtn();
            startBtn.setOnAction(event -> {
                seq = new Vector<Integer>();
                playSequence();
                score = 0;
            });
        }
    }


    public void playSequence(){
        seq.add(generateRandom.nextInt(4));

        mainController.getColorPanel().playSequence(seq);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
