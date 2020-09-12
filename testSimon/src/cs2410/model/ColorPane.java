package cs2410.model;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class ColorPane extends Pane {
    private Color color;


    public ColorPane(Color color) {
        this.color = color;
    }

    private void blink() {
        pressed();
        System.out.println("pause");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            // TODO Auto-generated catch block
            ie.printStackTrace();
        }
        this.setBackground(new Background(new BackgroundFill(color.darker(), CornerRadii.EMPTY, Insets.EMPTY)));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            // TODO Auto-generated catch block
            ie.printStackTrace();
        }
    }

    public Color getColor() {
        return this.color;
    }

    public void activate() {
        blink();
    }

    public void pressed() {
        this.setBackground(new Background(new BackgroundFill(color.brighter(), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void released() {
        this.setBackground(new Background(new BackgroundFill(color.darker(), CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
