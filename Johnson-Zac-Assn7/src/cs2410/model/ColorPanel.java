package cs2410.model;

import javafx.event.ActionEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Vector;

public class ColorPanel extends HBox {
    private int score;
    private int highScore;
    private int numCorrect;

    ColorPane colorPane1 = new ColorPane(Color.BLUE, 1);
    ColorPane colorPane2 = new ColorPane(Color.RED, 2);
    ColorPane colorPane3 = new ColorPane(Color.YELLOW, 3);
    ColorPane colorPane4 = new ColorPane(Color.GREEN, 4);

    public ColorPanel() {
        initColorPanel(colorPane1);
        initColorPanel(colorPane2);
        initColorPanel(colorPane3);
        initColorPanel(colorPane4);

        this.getChildren().addAll(colorPane1, colorPane2, colorPane3, colorPane4);
    }


    public void playSequence(Vector<Integer> seq) {
        for(Integer i : seq) {
            if(i.equals(0))
                colorPane1.activate();
            if(i.equals(1))
                colorPane2.activate();
            if(i.equals(2))
                colorPane3.activate();
            if(i.equals(3))
                colorPane4.activate();
        }

    }

    /**
     * ButtonMash class -
     * Checks to see if the Buttons pressed match with the sequence
     * If not, calls lose function
     */
    public class ButtonMash  {
        public ButtonMash(){}

        public void actionPerformed(ActionEvent event) {
            ColorPanel temp = (ColorPanel) event.getSource();
            if(temp.equals(colorPane1) && seq.get(numCorrect) == 0) {
                numCorrect++;
                score++;
                colorPane1.activate();
            }
            else if(temp.equals(colorPane2) && seq.get(numCorrect) == 1) {
                numCorrect++;
                score++;
                colorPane2.activate();
            }
            else if(temp.equals(colorPane3) && seq.get(numCorrect) == 2) {
                numCorrect++;
                score++;
                colorPane3.activate();
            }
            else if(temp.equals(colorPane4) && seq.get(numCorrect) == 3) {
                numCorrect++;
                score++;
                colorPane4.activate();
            }
            else {
                lose();
            }
            if(numCorrect == seq.size()) {
                numCorrect = 0;
                playSequence();
            }
        }
        public void lose() {
            if(score > highScore)
                highScore = score;
            numCorrect = 0;
        }

    }
}
