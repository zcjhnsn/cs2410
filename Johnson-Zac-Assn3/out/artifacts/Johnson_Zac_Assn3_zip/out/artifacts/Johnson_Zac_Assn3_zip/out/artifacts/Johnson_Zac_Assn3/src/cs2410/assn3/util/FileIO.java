package cs2410.assn3.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import cs2410.assn3.gui.GuiDirectory;


public class FileIO {
    /**
     * Name of the file we'll be writing to
     */
    private static final String fileName = "data/cs2410-directory.data";

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
            System.out.printf("%-25s %-5s %s %2s stars %s/%s/%s\n", fileInput.nextLine(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next());
            fileInput.nextLine();
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
        fileOutput.printf(filmTitle + '\n' + rating + " " + yearReleased + " " + numberOfStars + " " + month + " " + day + " " + year + '\n');
        fileOutput.close();
    }

//    public String fileReadGUI() {
//        Scanner fileInput = null;
//        String line1 = "";
//
//        try {
//            fileInput = new Scanner(new FileReader(fileName));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // TODO: Print file and format the output
//
//        while (fileInput.hasNextLine()) {
//            line1 += String.format("%-25s%-5s%s%2s stars %s/%s/%s\n", fileInput.nextLine(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next(), fileInput.next());
//            fileInput.nextLine();
//        }
//        fileInput.close();
//
//        return line1;
//    }

    public double findAverageStars() {
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        double stars = 0;
        double i = 0;
        while (fileInput.hasNextLine()) {
            fileInput.nextLine();
            fileInput.next();
            fileInput.next();
            stars += Double.parseDouble(fileInput.next());
            fileInput.nextLine();
            i++;
        }

        return stars /= i;
    }
}