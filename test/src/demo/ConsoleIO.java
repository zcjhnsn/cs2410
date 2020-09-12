package demo;

import demo.Film;
import java.util.Scanner;

public class ConsoleIO {
    private Scanner input;

    public ConsoleIO() {
        input = new Scanner(System.in);
        boolean isDone = false;
        printMenu();

        while (!isDone) {

            int choice = input.nextInt();
            if (choice == 1) {
                // TODO: Print directory
                // TODO: Read file
                FileIO fileInput = new FileIO();
                fileInput.fileRead();
                printMenu();
            } else if (choice == 2) {
                // TODO: Add film to directory
                Film myFilm = new Film();
                System.out.print("Enter the film title: ");
                myFilm.setFilmTitle(input.nextLine());

                System.out.print("Enter the film's rating: ");
                myFilm.setFilmRating(input.next());

                System.out.print("Enter the year the film was released: ");
                myFilm.setYearReleased(input.next());

                System.out.print("Enter your rating out of 5 stars: ");
                myFilm.setFilmRating(input.next());

                System.out.print("Enter the month you last watched the film: ");
                myFilm.setMonth(input.next());

                System.out.print("Enter the day you last watched the film: ");
                myFilm.setDay(input.next());

                System.out.print("Enter the year you last watched the film: ");
                myFilm.setYear(input.next());

                // TODO: Write input to file
                FileIO fileIO = new FileIO();
                fileIO.fileWrite(myFilm.getFilmTitle(), myFilm.getFilmRating(), myFilm.getYearReleased(), myFilm.getNumberOfStars(), myFilm.getMonth(), myFilm.getDay(), myFilm.getYear());

                System.out.println("You have entered: ");
                System.out.println(myFilm.getFilmTitle());
                System.out.println(myFilm.getFilmRating() + " " + myFilm.getYearReleased() + " " + myFilm.getNumberOfStars() + " " + myFilm.getMonth() + " " + myFilm.getDay() + " " + myFilm.getYear());
                printMenu();
            } else if (choice == 3) {


                System.out.println("Average Stars: ");
            } else if (choice == 4){
                isDone = true;
            } else {
                System.out.print("Invalid Entry. Please enter a valid menu item (1-4).");
                printMenu();
            }
        }
    }

    public static void main(String[] args) {
        new ConsoleIO();
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
