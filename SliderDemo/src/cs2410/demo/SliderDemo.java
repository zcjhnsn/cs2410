package cs2410.demo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class SliderDemo extends Application {
    Slider slider = new Slider();
    Slider slider2 = new Slider();
    Text text1 = new Text();
    Text text2 = new Text();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // NOTE: In media player, we want to call these after the media player has loaded
        slider.setMax(100);
        slider.setMinorTickCount(4);
        slider.setMajorTickUnit(10);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setStyle("-fx-foreground-color: black");

        slider2.setMax(100);
        slider2.setMinorTickCount(4);
        slider2.setMajorTickUnit(10);
        slider2.setShowTickMarks(true);
        slider2.setShowTickLabels(true);
        slider2.setStyle("-fx-foreground-color: black");

        initItems();

        BorderPane pane = new BorderPane();
        pane.setTop(slider);
        VBox vBox = new VBox();
        vBox.getChildren().addAll(text1, text2);
        pane.setCenter(vBox);
        pane.setBottom(slider2);

        Scene scene = new Scene(pane);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initItems() {
        // Property binding
        slider.valueProperty().bindBidirectional(slider2.valueProperty());

        slider2.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                text1.setText(newValue.toString());

                // This only changes the text2 value if slider2 is changed by mouse
                // NOTE: This will be important for the homework
                if(slider2.isValueChanging()) {
                    text2.setText(newValue.toString());
                }
            }
        });
    }
}

