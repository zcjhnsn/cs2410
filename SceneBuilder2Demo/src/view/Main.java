package view;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/components/mainWindow.fxml"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/mainWindow.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();

        //mainController.getToolBarController().registerHandler(event -> System.out.println("I hope this works"));



        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
