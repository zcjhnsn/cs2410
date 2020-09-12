package demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This class uses a lambda expression to define the EventHandler
 *
 * @author Chad
 * @version XXX
 */
public class ButtonActionHandlerDemo5 extends Application {
    private Scene scene1;
    private Button btn1;
    private Pane pane1;
    private EventHandler<ActionEvent> handler;

    @Override
    public void start(Stage primaryStage) {
        btn1 = new Button("Press Me");
        btn1.setPrefWidth(100);
        btn1.setOnAction(event -> {
            if (event.getSource() == btn1) {
                btn1.setText(String.valueOf((int)(Math.random()*1000)));
            }
        });

        pane1 = new Pane();
        pane1.getChildren().add(btn1);
        scene1 = new Scene(pane1);

        primaryStage.setScene(scene1);

        primaryStage.show();
    }
}