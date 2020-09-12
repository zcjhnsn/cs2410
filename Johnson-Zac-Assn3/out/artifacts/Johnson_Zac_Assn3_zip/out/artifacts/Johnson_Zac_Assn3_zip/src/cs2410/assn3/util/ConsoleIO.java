package cs2410.assn3.util;

import java.util.Scanner;

import cs2410.assn3.util.FileIO;
import cs2410.assn3.Film;

public class ConsoleIO {
    /**
     * Scanner for console input
     */
    private Scanner input;

    /**
     * default constructor
     */
    public ConsoleIO() {
        input = new Scanner(System.in);
        boolean isDone = false;
        printMenu();
        FileIO fileInput = new FileIO();

        while (!isDone) {

            int choice = input.nextInt();
            if (choice == 1) {
                fileInput.fileRead();
                printMenu();
            } else if (choice == 2) {
                Film myFilm = new Film();
                System.out.print("Enter the film title: ");
                input.nextLine();
                myFilm.setFilmTitle(input.nextLine());

                System.out.print("Enter the film's rating: ");
                myFilm.setFilmRating(input.next());
                input.nextLine();
                System.out.print("Enter the year the film was released: ");
                myFilm.setYearReleased(input.next());
                input.nextLine();
                System.out.print("Enter your rating out of 5 stars: ");
                myFilm.setNumberOfStars(input.next());
                input.nextLine();
                System.out.print("Enter the month you last watched the film: ");
                myFilm.setMonth(input.next());
                input.nextLine();
                System.out.print("Enter the day you last watched the film: ");
                myFilm.setDay(input.next());
                input.nextLine();
                System.out.print("Enter the year you last watched the film: ");
                myFilm.setYear(input.next());
                input.nextLine();
                FileIO fileIO = new FileIO();
                fileIO.fileWrite(myFilm.getFilmTitle(), myFilm.getFilmRating(), myFilm.getYearReleased(), myFilm.getNumberOfStars(), myFilm.getMonth(), myFilm.getDay(), myFilm.getYear());

                System.out.println("You have entered: ");
                System.out.printf("%-25s %-5s %-5s %5s stars %s/%s/%s\n", myFilm.getFilmTitle(), myFilm.getFilmRating(), myFilm.getYearReleased(), myFilm.getNumberOfStars(), myFilm.getMonth(), myFilm.getDay(), myFilm.getYear());
                //System.out.println(myFilm.getFilmTitle() + " " + myFilm.getFilmRating() + " " + myFilm.getYearReleased() + " " + myFilm.getNumberOfStars() + " stars " + myFilm.getMonth() + " " + myFilm.getDay() + " " + myFilm.getYear());
                printMenu();
            } else if (choice == 3) {
                System.out.println("Average Stars: " + fileInput.findAverageStars());
                printMenu();
            } else if (choice == 4){
                isDone = true;
            } else {
                System.out.print("Invalid Entry. Please enter a valid menu item (1-4).");
                printMenu();
            }
        }
    }

    /**
     * Prints the main menu for program navigation
     */
    private void printMenu() {
        System.out.println("Enter Menu Item: ");
        System.out.println("1) Show Directory");
        System.out.println("2) Add Film");
        System.out.println("3) Show Average Stars");
        System.out.println("4) Quit");
    }
}
