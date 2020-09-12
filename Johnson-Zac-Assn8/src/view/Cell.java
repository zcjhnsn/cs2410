package view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

/**
 * Class for Cell instance
 */
public class Cell extends Button {
    /**
     * Boolean value to determing if Cell is a bomb
     */
    public boolean isBomb = false;

    /**
     * Integer value for the number of bombs surrounding the Cell
     */
    public int neighborBombs = 0;

    /**
     * Integer value for the row the cell is on
     */
    public int rowLocation;

    /**
     * Integer value for the column the cell is on
     */
    public int colLocation;

    /**
     * ImageView for the image of a flag
     */
    public ImageView flagIcon = new ImageView(new Image("file:graphics/mineFlag.png"));

    /**
     * ImageView for the image of a bomb
     */
    public ImageView bombIcon = new ImageView(new Image("file:graphics/mineBomb.png"));

    /**
     * ImageView for the image of a mine
     */
    public ImageView questionIcon = new ImageView(new Image("file:graphics/mineQuestion.png"));

    /**
     * String that helps with conversion of int to text to display on screen
     */
    private String stringBombs;

    /**
     * Integer value used to help update number of bombs remaining
     */
    private int numberBombs;

    /**
     * Boolean to know if the cell has been cleared or not
     */
    public boolean isCleared;


    /**
     * Constructor for Cell Instance
     */
    public Cell() {
        flagIcon.setFitWidth(15);
        flagIcon.setFitHeight(15);
        bombIcon.setFitWidth(15);
        bombIcon.setFitHeight(15);


        this.setMaxSize(25,25);
        this.setMinSize(25,25);
        this.setStyle("-fx-faint-focus-color: transparent");
        this.setStyle("-fx-font-size: 10");

        this.setOnMousePressed(event -> {
            if(event.getButton().equals(MouseButton.PRIMARY)) {
                if(this.isBomb) {
                    this.setGraphic(bombIcon);
                    this.setText(null);
                    Scoreboard.gameLost();
                    Scoreboard.gameplayController.stopTimer();
                } else if(this.neighborBombs == 0) {
                    Scoreboard.gameplayController.startTimer();
                    Scoreboard.clearZeros(this, this.rowLocation, this.colLocation);
                } else {
                    this.setText(neighborBombs + "");
                    this.setGraphic(null);
                    this.setDisable(true);
                    Scoreboard.gameplayController.clearedCellCount++;
                    Scoreboard.gameplayController.startTimer();
                }

                if(Scoreboard.gameplayController.clearedCellCount == Scoreboard.gameplayController.cellsToWin) {
                    Scoreboard.gameWon();
                }
            }
        });

        this.setOnMouseClicked(event -> {
            if(event.getButton().equals(MouseButton.SECONDARY)) {
                if(this.getGraphic() == flagIcon) {
                    this.setGraphic(questionIcon);
                    stringBombs = Scoreboard.gameplayController.bombsRemaining.getText();
                    numberBombs = Integer.valueOf(stringBombs);
                    numberBombs++;
                    Scoreboard.gameplayController.bombsRemaining.setText(Integer.toString(numberBombs));
                }
                else if(this.getGraphic() == questionIcon) {
                    this.setGraphic(null);
                } else {
                    this.setGraphic(flagIcon);
                    stringBombs = Scoreboard.gameplayController.bombsRemaining.getText();
                    numberBombs = Integer.valueOf(stringBombs);
                    numberBombs--;
                    Scoreboard.gameplayController.bombsRemaining.setText(Integer.toString(numberBombs));
                }
            }
        });
    }

    /**
     * Method for incrementing the number of bombs surrounding the cell.
     */
    public void incrementBombCounter()
    {
        neighborBombs++;
    }
}
