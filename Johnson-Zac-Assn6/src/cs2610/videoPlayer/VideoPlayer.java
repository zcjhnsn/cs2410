package cs2610.videoPlayer;

import cs2610.controlBar.ControlBar;
import cs2610.menuBar.MyMenuBar;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Main driver class
 */
public class VideoPlayer extends Application {
    /**
     * Instance of MediaPlayer
     */
    private MediaPlayer player;

    /**
     * Instance of Media
     */
    private Media media;

    /**
     * Instance of MediaView
     */
    private MediaView mediaView = new MediaView();

    /**
     * Instance of MyMenuBar
     */
    private MyMenuBar myMenuBar;

    /**
     * Instance of ControlBar
     */
    private ControlBar controlBar;

    /**
     * Instance of FileChooser
     */
    private FileChooser fileChooser = new FileChooser();

    /**
     * Instance of BorderPane
     */
    private BorderPane mainPane;

    /**
     * Main start method for program execution
     * @param primaryStage Stage
     * @throws Exception Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Video Player");
        mainPane = new BorderPane();

        myMenuBar = new MyMenuBar();

        mainPane.setTop(myMenuBar);
        mainPane.setCenter(mediaView);

        mainPane.setPrefSize(600, 400);
        mainPane.setMinSize(600, 400);
        mediaView.fitWidthProperty().bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        mediaView.setPreserveRatio(true);

        initMenuBar(primaryStage);

        // controlBar = new ControlBar(player);
        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    /**
     * Initializes MenuBar and gives options functionality
     * @param pStage Stage
     */
    private void initMenuBar(Stage pStage) {
        myMenuBar.getOpenMenuItem().setOnAction(event -> {
            fileChooser.setTitle("FileChooserDemo FileChooser");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Video Files (.mp4)", "*.mp4", "*.m4v", "*.m4a"));
            File selectedFile = fileChooser.showOpenDialog(pStage);
            if(selectedFile != null) {
                media = new Media(selectedFile.toURI().toString());
                player = new MediaPlayer(media);
                mediaView.setMediaPlayer(player);
                player.play();
                controlBar = new ControlBar(player);
                mainPane.setBottom(controlBar);
            }
        });

        myMenuBar.getCloseMenuItem().setOnAction(event -> {
            player.dispose();
        });

        myMenuBar.getExitMenuItem().setOnAction(event -> {
            System.exit(0);
        });
    }
}