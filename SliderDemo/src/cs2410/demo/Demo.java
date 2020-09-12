package cs2410.demo;

import javafx.application.Application;
import javafx.stage.Stage;

public class Demo extends Application {
    public static int count = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread thread1 = new Thread(new MyRunnable("One"));
        Thread thread2 = new Thread(new MyRunnable("Two"));
        Thread thread3 = new Thread(new MyRunnable("Three"));
        Thread thread4 = new Thread(new MyRunnable("Four"));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

    private class MyRunnable implements Runnable {
        private String name;

        public MyRunnable(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(name + ": " + count);
            }

        }
    }

}
