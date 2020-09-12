package cs2410.model;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.awt.event.ActionListener;


public class ColorPane extends Pane {
    private Color color;

    private int id;

    public ColorPane(Color color, int id) {
        this.color = color;
        this.id = id;
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
