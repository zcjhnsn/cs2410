package cs2410.assn3.gui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import cs2410.assn3.Film;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;
import cs2410.assn3.util.FileIO;

public class GuiDirectory extends Application {

    /**
     * File name
     */
    private static final String fileName = "data/cs2410-directory.data";

    /**
     * Scanner for file reading/writing
     */
    private Scanner scan = new Scanner(System.in);

    /**
     * Main start method for GUI operation
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Film Directory");
        alert.setHeaderText(null);

        ArrayList<String> list = new ArrayList();
        list.add("Show Directory");
        list.add("Add Film to Directory");
        list.add("Show Average Stars");
        list.add("Quit");


        ChoiceDialog<String> choice = new ChoiceDialog(null, list);
        choice.setTitle("Film Directory");
        choice.setHeaderText(null);
        choice.setContentText("Make a Selection");

        FileIO fileIO = new FileIO();
        Film myFilm = new Film();

        TextInputDialog dialog1 = new TextInputDialog("Default Name");
        dialog1.setTitle("Film Directory");
        dialog1.setHeaderText(null);
        dialog1.setContentText("Please enter the title of the film:");

        Optional<String> choice1 = choice.showAndWait();
        if (choice1.isPresent()) {
            if (choice1.get().equals(list.get(0))) {
                alert.setContentText(formatString());
            } else if (choice1.get().equals(list.get(1))) {
                dialog1.showAndWait();
            } else if (choice1.get().equals(list.get(2))) {
                alert.setContentText("Average stars: " + formatAverage());
            } else {
                System.exit(0);
            }
        }

        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == ButtonType.OK) {
            choice.showAndWait();
        } else if (result1.get() == ButtonType.CLOSE) {
            System.exit(0);
        }

        TextInputDialog dialog2 = new TextInputDialog("Default Name");
        dialog2.setTitle("Film Directory");
        dialog2.setHeaderText(null);
        dialog2.setContentText("Please enter the rating of the film:");

        TextInputDialog dialog3 = new TextInputDialog("0000");
        dialog3.setTitle("Film Directory");
        dialog3.setHeaderText(null);
        dialog3.setContentText("Please enter the year the film released:");

        TextInputDialog dialog4 = new TextInputDialog("0");
        dialog4.setTitle("Film Directory");
        dialog4.setHeaderText(null);
        dialog4.setContentText("Please enter the number of stars:");

        TextInputDialog dialog5 = new TextInputDialog("00");
        dialog5.setTitle("Film Directory");
        dialog5.setHeaderText(null);
        dialog5.setContentText("Please enter the month of year the film was last watched:");

        TextInputDialog dialog6 = new TextInputDialog("00");
        dialog6.setTitle("Film Directory");
        dialog6.setHeaderText(null);
        dialog6.setContentText("Please enter the day of the month the film was last watched:");

        TextInputDialog dialog7 = new TextInputDialog("00");
        dialog7.setTitle("Film Directory");
        dialog7.setHeaderText(null);
        dialog7.setContentText("Please enter the year the film was last watched:");

        Optional<String> dialog1Result = dialog1.showAndWait();
        if (dialog1Result.isPresent()) {
            myFilm.setFilmTitle(dialog1Result.get());
            dialog2.showAndWait();
        }

        Optional<String> dialog2Result = dialog2.showAndWait();
        if (dialog2Result.isPresent()) {
            myFilm.setFilmRating(dialog2Result.get());
            dialog3.showAndWait();
        }


        Optional<String> dialog3Result = dialog3.showAndWait();
        if (dialog3Result.isPresent()) {
            myFilm.setYearReleased(dialog3Result.get());
            dialog4.showAndWait();
        }


        Optional<String> dialog4Result = dialog4.showAndWait();
        if (dialog3Result.isPresent()) {
            myFilm.setNumberOfStars(dialog4Result.get());
            dialog5.showAndWait();
        }

        Optional<String> dialog5Result = dialog5.showAndWait();
        if (dialog5Result.isPresent()) {
            myFilm.setMonth(dialog5Result.get());
            dialog6.showAndWait();
        }

        Optional<String> dialog6Result = dialog6.showAndWait();
        if (dialog5Result.isPresent()) {
            myFilm.setDay(dialog6Result.get());
            dialog7.showAndWait();
        }

        Optional<String> dialog7Result = dialog7.showAndWait();
        if (dialog7Result.isPresent()) {
            myFilm.setYear(dialog7Result.get());
            fileIO.fileWrite(myFilm.getFilmTitle(), myFilm.getFilmRating(), myFilm.getYearReleased(), myFilm.getNumberOfStars(), myFilm.getMonth(), myFilm.getDay(), myFilm.getYear());
            alert.setContentText("You added: "+'\n' + myFilm.getFilmTitle() + " Rating: " + myFilm.getFilmRating() + " " +myFilm.getYearReleased() +'\n'+ "Stars: " + myFilm.getNumberOfStars() + '\n' + "Last watched: " + myFilm.getMonth()+ "/" + myFilm.getDay() +"/"+ myFilm.getYear());
            choice.showAndWait();
        }
    }

    /**
     * Prints to file and to application
     * @return String
     */
    private String formatString() {
        String output = "";
        Scanner fileInput = null;
        String line1 = "";

        try {
            fileInput = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: Print file and format the output

        while (fileInput.hasNextLine()) {
            line1 += String.format("%-25s%-5s%s%2s stars %s/%s/%s\n", fileInput.nextLine(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next());
            fileInput.nextLine();
        }
        fileInput.close();

        return line1;
    }

    /**
     * Returns average stars
     * @return String
     */
    public String formatAverage() {
        FileIO fileIO = new FileIO();

        return Double.toString(fileIO.findAverageStars());
    }
}
