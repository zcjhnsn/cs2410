package inClass.demo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Chad
 *
 */
public class MenuDemo extends Application {
    Pane pane1 = new Pane();
    DemoMenuBar menuBar = new DemoMenuBar();
    ContextMenu contextMenu = new ContextMenu();
    MenuItem popUpItem1 = new MenuItem("Popup 1");
    MenuItem popUpItem2 = new MenuItem("Popup 2");
    Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
//        final String os = System.getProperty ("os.name");
//        if (os != null && os.startsWith ("Mac"))
//            menuBar.useSystemMenuBarProperty ().set (true);

        primaryStage.setTitle("Menus");

        this.primaryStage = primaryStage;
        BorderPane mainPane = new BorderPane();
        pane1.setPrefSize(200, 200);

        mainPane.setTop(menuBar);
        mainPane.setCenter(pane1);

        initContextMenu();

        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();

    }

    private void initContextMenu() {
        contextMenu.getItems().addAll(popUpItem1, popUpItem2);

        pane1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.SECONDARY) {
                    contextMenu.show(primaryStage, event.getScreenX(), event.getScreenY());
                }
            }
        });
    }
}

