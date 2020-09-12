package cs2410.assn4.model;

//NOTE: Reads in the file, manages data in an ArrayList, writes out the file

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
    /**
     * Name of the file we'll be writing to
     */
    private static final String fileName = "data/images.data";

    /**
     * This is the default constructor
     */
    public FileIO() { }

    /**
     * Method for reading in a file
     */
    public ArrayList<Model> loadImages() {
        Scanner fileInput = null;

        try {
            fileInput = new Scanner(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Model> images = new ArrayList();
        while (fileInput.hasNextLine()) {
            String line = fileInput.nextLine();
            String[] parts = line.split("(?<!^a)\\s+", 2);

            Model model = new Model(parts);
            images.add(model);
        }

        fileInput.close();
        return images;
    }

    /**
     * Gets Model from ArrayList by index
     * @param list ArrayList
     * @param index int
     * @return Model
     */
    public Model getFromList(ArrayList<Model> list, int index) {
        return list.get(index);
    }

    public void addToList(ArrayList<Model> list, String imageURL, String imageTitle) {
        imageURL += " " + imageTitle;
        String[] parts = imageURL.split("(?<!^a)\\s+", 2);
        Model newImage = new Model(parts);
        list.add(newImage);
    }

    /**
     * Worker function to delete Model from ArrayList
     * @param list ArrayList
     * @param index int
     */
    public void deleteFromList(ArrayList<Model> list, int index){
        list.remove(index);
    }

    /**
     * Method for writing to file
     * @param URL String
     * @param title String
     */
    public void fileWrite(String URL, String title) {
        PrintWriter fileOutput = null;

        try {
            fileOutput = new PrintWriter(new FileOutputStream(fileName, true)); //try changing to false
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // TODO: print to file
        fileOutput.printf(URL + " " + title + '\n');
        fileOutput.close();
    }

    /**
     * Function that edits the input file when deleting an image
     * @param lineContent String
     * @throws IOException IOException
     */
    public void removeLine(String lineContent) throws IOException
    {
        File file = new File("data/images.data");
        File temp = new File("_temp_");
        PrintWriter out = new PrintWriter(new FileWriter(temp));
        Files.lines(file.toPath())
                .filter(line -> !line.contains(lineContent))
                .forEach(out::println);
        out.flush();
        out.close();
        temp.renameTo(file);
    }
}
