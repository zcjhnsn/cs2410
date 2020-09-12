package demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;


public class FileIO {
    /**
     * Name of the file we'll be writing to
     */
    private static final String fileName = "data/info.data";

    /**
     * This is the default constructor
     */
    public FileIO() { }

    /**
     * Method for reading in a file
     */
    public void fileRead() {
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: Print file and format the output
        while (fileInput.hasNextLine()) {
            System.out.println(fileInput.nextLine());
        }
        fileInput.close();
    }

    /**
     * Method for writing to the file
     */
    public void fileWrite(String filmTitle, String rating, String yearReleased, String numberOfStars, String month, String day, String year) {
        PrintWriter fileOutput = null;

        try {
            fileOutput = new PrintWriter(new FileOutputStream(fileName, true)); //try changing to false
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: print to file
        fileOutput.printf(filmTitle + '\n' + rating + " " + yearReleased + " " + numberOfStars + " stars " + month + "/" + day + "/" + year + '\n');
        fileOutput.close();
    }
}
