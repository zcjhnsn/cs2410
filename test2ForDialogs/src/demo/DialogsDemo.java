package demo;

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

/**
 * Add a description of the class here
 *
 * @author Chad
 * @version 1.0
 */
public class DialogsDemo extends Application {
    private Scanner scan = new Scanner(System.in);

    public void start(Stage primaryStage) throws Exception {
        Alert alert = new Alert(Alert.AlertType.NONE);
//        alert.setContentText("This is an Alert with type NONE\nThe x button doesn't work");
        // Uncomment the following two lines so the x button works
//        Window window = alert.getDialogPane().getScene().getWindow();
//        window.setOnCloseRequest(event -> window.hide());
//        alert.showAndWait();

        // Notice we're using the same Alert object identifier,
        // but creating a new object for it to refer to
        alert = new Alert(Alert.AlertType.CONFIRMATION); //try other types
        alert.setTitle("The Title Goes Here");
        alert.setHeaderText(null); //try setting to null
        alert.setContentText("I have a great message for you!");

        ArrayList<String> list = new ArrayList();
        list.add("a");
        list.add("b");
        list.add("c");

        ChoiceDialog<String> choice = new ChoiceDialog(null, list);
        choice.setTitle("Title Goes Here");
        choice.setHeaderText(null);
        choice.setContentText("Choose one");

        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == ButtonType.OK) {
            System.out.println("You clicked OK");
        } else if (result1.get() == ButtonType.CANCEL) {
            System.out.println("You clicked Cancel");
        }

        TextInputDialog dialog = new TextInputDialog("Default Name");
        dialog.setTitle("Title Goes Here");
        dialog.setHeaderText("This is the Header");
        dialog.setContentText("Please enter your name:");

        Optional<String> result2 = dialog.showAndWait();
        if (result2.isPresent()) {
            System.out.println("Your name: " + result2.get());
        }



        result2 = choice.showAndWait();
        if (result2.isPresent()) {
            System.out.println("You chose: " + result2.get());
        }

    }
}