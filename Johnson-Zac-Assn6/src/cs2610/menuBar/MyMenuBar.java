package cs2610.menuBar;

import javafx.scene.control.*;

/**
 * Class for MenuBar object
 */
public class MyMenuBar extends MenuBar {
    /**
     * Instance of Menu for File
     */
    private Menu menu1 = new Menu("File");

    /**
     * Instance of Help Menu
     */
    private Menu menu2 = new Menu("Help");

    /**
     * MenuItem for open
     */
    private MenuItem openMenuItem = new MenuItem("Open");

    /**
     * MenuItem for close
     */
    private MenuItem closeMenuItem = new MenuItem("Close");

    /**
     * MenuItem for exit
     */
    private MenuItem exitMenuItem = new MenuItem("Exit");

    /**
     * MenuItem for documentation
     */
    private MenuItem docMenuItem = new MenuItem("Documentation");

    /**
     * MenuItem for about
     */
    private MenuItem aboutMenuItem = new MenuItem("About");

    /**
     * MyMenuBar constructor
     */
    public MyMenuBar() {
        this.getMenus().addAll(menu1, menu2);

        menu1.getItems().addAll(openMenuItem, closeMenuItem, exitMenuItem);
        menu2.getItems().addAll(docMenuItem, aboutMenuItem);

    }

    /**
     * Getter for Open MenuItem
     * @return MenuItem
     */
    public MenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    /**
     * Getter for Close MenuItem
     * @return MenuItem
     */
    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }

    /**
     * Getter for Exit MenuItem
     * @return MenuItem
     */
    public MenuItem getExitMenuItem() {
        return exitMenuItem;
    }
}

