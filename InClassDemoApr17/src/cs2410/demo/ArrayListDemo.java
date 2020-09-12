package cs2410.demo;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ArrayListDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        Random rand = new Random();

        for(int i = 0; i < 50; i++) {
            list.add(rand.nextInt());
        }

        Iterator<Integer> iterator = list.iterator(); // literally just a for-each loop

        while(iterator.hasNext()) {
            // do stuff
        }
    }

}
