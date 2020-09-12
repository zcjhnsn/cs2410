package cs2610.controlBar;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Class for ControlBar object
 */
public class ControlBar extends BorderPane {
    /**
     * Instance of MediaPlayer
     */
    private MediaPlayer mp;

    /**
     * Instance of bool
     */
    private final boolean repeat = false;

    /**
     * Instance of bool
     */
    private boolean stopRequested = false;

    /**
     * Instance of bool
     */
    private boolean atEndOfMedia = false;

    /**
     * Instance of Duration
     */
    private Duration duration;

    /**
     * Instance of play/pause Button
     */
    private Button playPauseBtn = new Button("Play");

    /**
     * Instance of stop Button
     */
    private Button stopBtn = new Button("Stop");

    /**
     * Instance of volume Slider
     */
    private Slider volumeSlider = new Slider();

    /**
     * Instance of time Slider
     */
    private Slider timeSlider = new Slider();

    /**
     * Instance of spacer Label
     */
    private Label spacer1 = new Label("   ");

    /**
     * Instance of spacer label
     */
    private Label spacer2 = new Label("   ");

    /**
     * Instance of time Label
     */
    private Label timeLabel = new Label("Time: ");

    /**
     * Instance of volume Label
     */
    private Label volumeLabel = new Label("Volume: ");

    /**
     * Instance of HBox
     */
    private HBox mediaBar;

    /**
     * Constructor for ControlBar
     * @param mp MediaPlayer
     */
    public ControlBar(final MediaPlayer mp) {
        this.mp = mp;
        setStyle("-fx-background-color: #bfc2c7;");

        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        BorderPane.setAlignment(mediaBar, Pos.CENTER);
        initControlBar();

        setBottom(mediaBar);
    }

    /**
     * Method for Initialization of ControlBar object
     */
    private void initControlBar() {
        // Add buttons and labels to ControlBar
        playPauseBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MediaPlayer.Status status = mp.getStatus();

                if (status == MediaPlayer.Status.UNKNOWN  || status == MediaPlayer.Status.HALTED) {
                    // don't do anything in these states
                    return;
                }

                if ( status == MediaPlayer.Status.PAUSED || status == MediaPlayer.Status.READY || status == MediaPlayer.Status.STOPPED) {
                    // rewind the movie if we're sitting at the end
                    if (atEndOfMedia) {
                        mp.seek(mp.getStartTime());
                        atEndOfMedia = false;
                    }
                    mp.play();
                    playPauseBtn.setText("Play");
                    updateValues();
                } else {
                    mp.pause();
                }
            }
        });

        stopBtn.setOnAction(event -> {
            MediaPlayer.Status status = mp.getStatus();

            if (status == MediaPlayer.Status.PLAYING || status == MediaPlayer.Status.PAUSED) {
                mp.stop();
                mp.seek(mp.getStartTime());
                playPauseBtn.setText("Play");
                updateValues();
            }
        });


        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(javafx.beans.Observable observable) {
                if (volumeSlider.isValueChanging()) {
                    mp.setVolume(volumeSlider.getValue() / 100.0);
                }
            }
        });

        mp.setOnPlaying(new Runnable() {
            public void run() {
                if (stopRequested) {
                    mp.pause();
                    stopRequested = false;
                } else {
                    playPauseBtn.setText("Pause");
                }
            }
        });

        mp.setOnPaused(new Runnable() {
            public void run() {
                System.out.println("onPaused");
                playPauseBtn.setText("Play");
            }
        });

        mp.setOnReady(new Runnable() {
            public void run() {
                duration = mp.getMedia().getDuration();
                updateValues();
            }
        });

        mp.setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);
        mp.setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playPauseBtn.setText("Play");
                    stopRequested = true;
                    atEndOfMedia = true;
                }
            }
        });

        mp.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) -> {
            timeSlider.setValue(newValue.toSeconds());
        });

        mediaBar.getChildren().add(playPauseBtn);
        mediaBar.getChildren().add(spacer1);
        mediaBar.getChildren().add(stopBtn);
        mediaBar.getChildren().add(spacer2);
        mediaBar.getChildren().add(timeLabel);
        HBox.setHgrow(timeSlider, Priority.ALWAYS);

        // adjust timeSlider Size
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);
        timeSlider.maxProperty().bind(Bindings.createDoubleBinding(
                () -> mp.getTotalDuration().toSeconds(),
                mp.totalDurationProperty()));
        timeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(javafx.beans.Observable ov) {
                if (timeSlider.isValueChanging()) {
                    // multiply duration by percentage calculated by slider position
                    mp.seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });
        mediaBar.getChildren().add(timeSlider);

        // adjust volume slider
        mediaBar.getChildren().add(volumeLabel);
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);
        mediaBar.getChildren().add(volumeSlider);
    }

    /**
     * Method for updating timeSlider and volumeSlider
     */
    private void updateValues() {
        if (timeSlider != null && volumeSlider != null) {
            Platform.runLater(new Runnable() {
                public void run() {
                    Duration currentTime = mp.getCurrentTime();
                    timeSlider.setDisable(duration.isUnknown());

                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toSeconds()
                                * 100.0);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int)Math.round(mp.getVolume()
                                * 100));
                    }
                }
            });
        }
    }
}



