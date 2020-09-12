package view;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

/**
 * Class instance for program execution
 */
public class Scoreboard extends Application {

    /**
     * Instance of Controllor for gameplay execution
     */
    public static Controller gameplayController;

    /**
     * Alert window for displaying the rules
     */
    private Alert rules = new Alert(Alert.AlertType.INFORMATION);

    /**
     * Alert window for congratulating player upon winning the game
     */
    private  static Alert winningStatement = new Alert(Alert.AlertType.INFORMATION);

    /**
     * start() method for program execution
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../components/Gameboard.fxml"));
        Parent root = loader.load();
        gameplayController = (Controller) loader.getController();

        rules.setHeaderText("How To Play");
        rules.setContentText("1. Click on any square to start the game\n" +
                "2. The numerical hints tell you how many bombs are touching that square.\n" +
                "3. Choose wisely!\n" +
                "If you know where a bomb is, right-click once to put a warning flag down. " +
                "If you aren't sure, right-click one more time to put a question mark.");
        rules.showAndWait();

        primaryStage.setTitle("Minesweeperish");
        primaryStage.setScene(new Scene(root, 500, 600));
        primaryStage.show();
        primaryStage.setResizable(false);


        gameplayController.mainPane.setBottom(gameplayController.gameBoardContainer);

        gameplayController.initGameBoard();
        buildBoard();

        gameplayController.newGame.setOnAction(event -> {
            gameplayController.gameBoardContainer.getChildren().clear();
            gameplayController.initGameBoard();
            buildBoard();
            if(gameplayController.timerRunning) {
                gameplayController.stopTimer();
            }
        });

        gameplayController.startGame.setOnMouseClicked(event -> {
            for(int k = 0; k < gameplayController.height; k++) {
                for (int m = 0; m < gameplayController.width; m++) {
                    gameplayController.gameBoard[k][m].setDisable(false);
                }
            }

        });
        gameplayController.rules.setOnAction(event -> {
            rules.showAndWait();
        });

    }

    /**
     * Public class method for building the game board.
     *
     * Takes a 2D array and populates the GridPane while updating counts of surrounding bombs.
     */
    public void buildBoard() {
        for (int i = 0; i < gameplayController.height; i++) {
            for (int j = 0; j < gameplayController.width; j++) {
                gameplayController.gameBoardContainer.add(gameplayController.gameBoard[i][j], j, i);
                Cell tmpCell = gameplayController.gameBoard[i][j];
                tmpCell.setDisable(true);

                try {
                    if (gameplayController.gameBoard[i - 1][j - 1].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }

                try {
                    if (gameplayController.gameBoard[i - 1][j].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }

                try {
                    if (gameplayController.gameBoard[i - 1][j + 1].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }

                try {
                    if (gameplayController.gameBoard[i][j - 1].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }

                try {
                    if (gameplayController.gameBoard[i][j + 1].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }

                try {
                    if (gameplayController.gameBoard[i + 1][j - 1].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }

                try {
                    if (gameplayController.gameBoard[i + 1][j].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }

                try {
                    if (gameplayController.gameBoard[i + 1][j + 1].isBomb) tmpCell.incrementBombCounter();
                } catch (IndexOutOfBoundsException error) {
                }
            }
        }
    }

    /**
     * When the number of neighbor bombs is 0 checks for all of the
     * 0's around it and clears them away from the board.
     * @param cell Cell
     * @param i int
     * @param j int
     */

    public static void clearZeros(Cell cell, int i, int j) {

        if(!cell.isCleared)
        {
            gameplayController.clearedCellCount++;
            cell.isCleared = true;
        }
        if (cell.neighborBombs == 0 && !cell.isDisabled()) {
            cell.setText(" ");
            cell.setDisable(true);
            cell.isCleared = true;
            try{
                clearZeros(gameplayController.gameBoard[i - 1][j], i - 1, j);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

            try{
                clearZeros(gameplayController.gameBoard[i + 1][j], i + 1, j);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

            try{
                clearZeros(gameplayController.gameBoard[i][j - 1], i, j - 1);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

            try{
                clearZeros(gameplayController.gameBoard[i][j + 1], i, j + 1);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

            try{
                clearZeros(gameplayController.gameBoard[i-1][j -1], i-1, j - 1);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

            try{
                clearZeros(gameplayController.gameBoard[i-1][j + 1], i-1, j + 1);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

            try{
                clearZeros(gameplayController.gameBoard[i+1][j + 1], i+1, j - 1);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

            try{
                clearZeros(gameplayController.gameBoard[i+1][j - 1], i+1, j + 1);
            } catch (NullPointerException error) {}
            catch(ArrayIndexOutOfBoundsException error){}

        } else {
            cell.setDisable(true);
            if(cell.neighborBombs == 0)
            {
                cell.setText(null);
            }
            else {
                cell.setText(cell.neighborBombs + "");
            }
        }

    }

    /**
     * Method for when button containing a bomb is pressed. Shows location of all bombs while highlighting
     * bombs in green if they were flagged or red if they were not found.
     */
    public static void gameLost()
    {
        if(gameplayController.timerRunning) {
            gameplayController.stopTimer();
        }
        for(int i = 0; i < Scoreboard.gameplayController.height; i++)
        {
            for(int j = 0; j < Scoreboard.gameplayController.width; j++)
            {
                Cell tmpCell = gameplayController.gameBoard[i][j];
                if(gameplayController.gameBoard[i][j].isBomb && tmpCell.getGraphic() == tmpCell.flagIcon)
                {
                    gameplayController.gameBoard[i][j].setGraphic(tmpCell.bombIcon);
                    gameplayController.gameBoard[i][j].setStyle("-fx-background-color: darkgreen");
                    gameplayController.gameBoard[i][j].setDisable(true);
                }
                else if(!gameplayController.gameBoard[i][j].isBomb && gameplayController.gameBoard[i][j].getGraphic() == tmpCell.flagIcon)
                {
                    gameplayController.gameBoard[i][j].setGraphic(tmpCell.flagIcon);
                    gameplayController.gameBoard[i][j].setStyle("-fx-background-color: yellow");
                    gameplayController.gameBoard[i][j].setDisable(true);
                }
                else if(gameplayController.gameBoard[i][j].isBomb && gameplayController.gameBoard[i][j].getGraphic() != tmpCell.flagIcon)
                {
                    gameplayController.gameBoard[i][j].setGraphic(tmpCell.bombIcon);
                    gameplayController.gameBoard[i][j].setStyle("-fx-background-color: red");
                    gameplayController.gameBoard[i][j].setDisable(true);
                }
            }
        }
    }

    /**
     * Method that displays victory window when all squares have been cleared without touching a bomb
     */
    public static void gameWon() {
        gameplayController.stopTimer();
        winningStatement.setHeaderText("You Win!");
        winningStatement.setContentText("You found all the bombs without going boom!\n Start a new game from the File menu.");
        gameLost();
    }
}
