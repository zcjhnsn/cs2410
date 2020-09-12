package controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import view.Cell;

import java.util.ArrayList;
import java.util.Collections;

/**
 * height is used to size the height of the board
 * width is used to help size the width of the board.
 * area is used to determine how many cells are on the board and helps determine how to win.
 * gameBoardContainer is a 2D array that stores Cells
 * data is an ArrayList used to create Cells and place bombs in Cells before putting the Cells into gameBoardContainer
 * difficulty is used to determine how many bombs will be placed in the gameBoardContainer
 * cellsToWin is the needed number of Cells that have to be cleared before a game can be won
 * clearedCellCount is the number Cells that have been cleared
 * bombs is the number of bombs that will be in the game
 * currentTime is used to display the current amount of time elapsed since the start of the game
 * timerRunning is a boolean used to determine whether or not the time needs to be stopped
 */
public class Controller {
    /**
     * Integer value for "height" or number of rows
     */
    public int height = 20;

    /**
     * Integer value for "width" or number of columns
     */
    public int width = 20;

    /**
     * Integer value for area of gameBoard
     */
    public int area;

    /**
     * 2D array of Cells to be displayed on the gameBoard
     */
    public Cell[][] gameBoard;

    /**
     * ArrayList for holding cells and put bombs in them before putting them in the gameBoard 2D array.
     */
    public ArrayList<Cell> data = new ArrayList<>();

    /**
     * Double value for holding the percentage of buttons that contain bombs
     */
    public double percentofBombs;

    /**
     * Double value for "difficulty" or the ratio of bombs to total buttons
     */
    public double difficulty = 0.25;

    /**
     * Integer value for number of spaces needed to be cleared in order to win
     */
    public int cellsToWin;

    /**
     * Integer counter for counting number of cleared cells
     */
    public int clearedCellCount = 0;

    /**
     * Integer value for number of bombs in the gameBoard
     */
    public int bombs;

    /**
     * Double value for holding the start time
     */
    public double currentTime;

    /**
     * Boolean value to see if the timer is running
     */
    public boolean timerRunning;

    /**
     * GridPane instance to display the 2D array gameBoard
     */
    public GridPane gameBoardContainer = new GridPane();

    /**
     * BoarderPane that holds the whole game
     */
    @FXML
    public BorderPane mainPane;

    /**
     * MenuBar instance that holds the New Game functionality and displays the rules
     */
    @FXML
    public MenuBar menu;

    /**
     * MenuItem to hold the New Game functionality
     */
    @FXML
    public MenuItem newGame;

    /**
     * Menu that holds the rules MenuItem
     */
    @FXML
    public Menu help;

    /**
     * MenuItem for the displaying the rules functionality
     */
    @FXML
    public MenuItem rules;

    /**
     * Text item to hold the time
     */
    @FXML
    public Text time;

    /**
     * Text item to hold the number of bombs remaining
     */
    @FXML
    public Text bombsRemaining;

    /**
     * Button to enable the board to be used
     */
    @FXML
    public Button startGame;

    /**
     * long value for timer updating
     */
    private long lastUpdate = 0;

    /**
     * AnimationTimer for holding the elapsed time
     */
    private AnimationTimer timer;

    /**
     * Integer value for holding the start time
     */
    private int newTime = 0;

    /**
     * Method that starts the timer
     */
    public void startTimer() {
        lastUpdate = 0;
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(now - lastUpdate >= 1000000000)
                {
                    currentTime = now;
                    lastUpdate = now;
                    time.setText(Integer.toString(newTime++).concat(" sec"));
                }
            }
        };
        timer.start();
        timerRunning = true;
    }

    /**
     * Method for stopping the timer
     */
    public void stopTimer() {
        timer.stop();
        newTime = 0;
        time.setText(Integer.toString(newTime));
    }

    /**
     * Method that fills the 2D array gameBoard with the cells from the ArrayList data
     */
    public void initGameBoard() {
        area = height * width;
        data.clear();
        gameBoard = new Cell[height][width];
        percentofBombs = difficulty;
        percentofBombs *= area;
        bombs = (int) percentofBombs;
        bombsRemaining.setText(Integer.toString(bombs));
        for (int k = 0; k < area; k++) {
            Cell cell = new Cell();
            if(k < percentofBombs)
            {
                cell.isBomb = true;
            }
           if(!cell.isBomb)
           {
               cellsToWin++;
           }
            data.add(cell);
        }

        Collections.shuffle(data);
        int count = 0;
        for (int i = 0; i < height; i++ ) {
            for (int j = 0; j < width; j++) {
                gameBoard[i][j] = data.get(count);
                Cell tmpCell = data.get(count);
                tmpCell.rowLocation = i;
                tmpCell.colLocation = j;
                count++;
            }
        }
    }
}
