package cs2410.demo;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.*;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Demo extends Application {
    Button btn1 = new Button("Button 1");
    Button btn2 = new Button("Two");

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Two");
        btn1.setLayoutX(200);   // without this the btn tells javafx to put it at the origin (top left)
        btn1.setLayoutY(100);
        btn2.setLayoutX(400);
        btn2.setLayoutY(150);


        btn1.setOnAction(this);
        btn2.setOnAction(this);
        /*
        // Anonymous Inner Class
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("You pressed Button 1");
            }
        });
        */

        Pane pane = new Pane();
        //pane.getChildren().add(btn1);
        pane.getChildren().addAll(btn1, btn2);
        Scene scene = new Scene(pane, 500, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void handle(Event event) {
        if(event.getSource() == btn1) {
            System.out.println("You clicked button 1");
        } else if (event.getSource() == btn2) {
            System.out.println("You clicked button 2");
        }
        System.out.println("Now click this");
    }
}