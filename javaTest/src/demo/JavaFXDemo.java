package demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Add a description of the class here
 *
 * @author Chad
 * @version XXX
 */
public class JavaFXDemo extends Application {

    public void start(Stage primaryStage) {
        //controlToScene(primaryStage);
        paneToScene(primaryStage);
    }

    private void controlToScene(Stage primaryStage) {
        Button btn1 = new Button("Click Me"); //create a control node
        Scene scene = new Scene(btn1, 200, 200); //create a scene, setting the root node (the button)
        primaryStage.setTitle("Control to Scene");
        primaryStage.setScene(scene); //place the scene in the stage
        primaryStage.show();
    }

    private void paneToScene(Stage primaryStage) {
        Button btn1 = new Button("Click Me"); //create a control node
        Pane pane = new Pane(); //create a pane node
        pane.getChildren().add(btn1); //add the button node to the pane node
        Scene scene = new Scene(pane, 200, 200); //create a scene, setting the root node (the pane)
        primaryStage.setTitle("Pane to Scene");
        primaryStage.setScene(scene); //place the scene in the stage
        primaryStage.show();
    }

}