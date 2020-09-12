package cs2410.assn4.controller;

//NOTE: Contains methods that are called by the user interface (View class)
//NOTE: Each method (except quit) should return an Image object for the view to display

import java.util.ArrayList;
import cs2410.assn4.model.Model;
import cs2410.assn4.model.FileIO;

public class Controller {
    /**
     * Instance of FileIO to access worker functions
     */
    private FileIO fileIO = new FileIO();

    /**
     * Default constructor
     */
    public Controller(){ }

    /**
     * returns next image in ArrayList
     * @param counter int
     * @param list ArrayList
     * @return Model
     */
    public Model nextImage(int counter, ArrayList<Model> list) {
        return fileIO.getFromList(list, counter);
    }

    /**
     * Returns previous image in ArrayList
     * @param counter int
     * @param list ArrayList
     * @return Model
     */
    public Model prevImage(int counter, ArrayList<Model> list) {
        return fileIO.getFromList(list, counter);
    }

    /**
     * Adds Model to ArrayList
     * @param list  ArrayList
     * @param newURL String
     * @param newTitle String
     */
    public void addImage(ArrayList<Model> list, String newURL, String newTitle) {
        fileIO.addToList(list, newURL, newTitle);
    }

    /**
     * Deletes Model from ArrayList
     * @param counter int
     * @param list ArrayList
     */
    public void delImage(int counter, ArrayList<Model> list) {
        fileIO.deleteFromList(list, counter);
    }

    /**
     * Quits program
     */
    void quit() {
        System.exit(0);
    }
}
