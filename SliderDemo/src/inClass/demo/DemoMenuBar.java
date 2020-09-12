package inClass.demo;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Add a description of the class here
 *
 * @author Chad
 * @version XXX
 */
public class DemoMenuBar extends MenuBar {
    private Menu menu1 = new Menu("Menu 1");
    private Menu menu2 = new Menu("Menu 2");
    private Menu menu3 = new Menu("Menu 3");

    private Menu menu4 = new Menu("Menu 4");
    private RadioMenuItem radio1 = new RadioMenuItem("Radio 1");
    private RadioMenuItem radio2 = new RadioMenuItem("Radio 2");
    private RadioMenuItem radio3 = new RadioMenuItem("Radio 3");

    private Menu menu5 = new Menu("Menu 5");
    private CheckMenuItem check1 = new CheckMenuItem("Check 1");
    private CheckMenuItem check2 = new CheckMenuItem("Check 2");
    private CheckMenuItem check3 = new CheckMenuItem("Check 3");

    private MenuItem menuItem1 = new MenuItem("Menu Item 1");
    private MenuItem menuItem2 = new MenuItem("Menu Item 2");

    // private CustomMenuItem custom1 = new CustomMenuItem();
    // private CustomMenuItem custom2 = new CustomMenuItem();

    private SeparatorMenuItem sep = new SeparatorMenuItem();

    public DemoMenuBar() {
        this.getMenus().addAll(menu1, menu2, menu3);

        menu1.getItems().addAll(menu4, sep, menu5);
        menu4.getItems().addAll(radio1, radio2, radio3);
        ToggleGroup group1 = new ToggleGroup();
        group1.getToggles().addAll(radio1, radio2, radio3);
        radio1.setSelected(true);

        menu5.getItems().addAll(check1, check2, check3);
        check1.setSelected(true);
        ToggleGroup diffGroup = new ToggleGroup();

        menu2.getItems().addAll(menuItem1, menuItem2);
        menuItem1.setGraphic(new ImageView(new Image("file:31-Menus/img/bird.png")));//might need to fix this path

//        menu3.getItems().addAll(custom1, custom2);
        Slider slider = new Slider(0, 10, 5);
        slider.setShowTickLabels(true);
//        custom1.setContent(slider);

        Button button = new Button("Button in a Menu");
//        custom2.setContent(button);

        initHandlers();
    }

    private void initHandlers() {
//        ((Slider)custom1.getContent()).setOnMouseClicked(this::custom1MenuAction);
//        custom2.setOnAction(this::custom2MenuAction);
        menuItem1.setOnAction(this::menuItem1Action);
        menuItem2.setOnAction(this::menuItem2Action);
    }

    private void custom1MenuAction(MouseEvent e) {
        String msg = "Here's the slider value: ";
        msg += ((Slider)e.getSource()).getValue();
        Alert alertMessage = new Alert(Alert.AlertType.INFORMATION, msg);
        alertMessage.setHeaderText(null);
        alertMessage.showAndWait();
    }

    private void custom2MenuAction(ActionEvent e) {
        String msg = "You clicked the Button in the Menu";
        Alert alertMessage = new Alert(Alert.AlertType.INFORMATION, msg);
        alertMessage.setHeaderText(null);
        alertMessage.showAndWait();
    }

    private void menuItem1Action(ActionEvent e) {
        String msg = "You clicked Menu Item 1";
        Alert alertMessage = new Alert(Alert.AlertType.INFORMATION, msg);
        alertMessage.setHeaderText(null);
        alertMessage.showAndWait();
    }

    private void menuItem2Action(ActionEvent e) {
        String msg = "You clicked Menu Item 2";
        Alert alertMessage = new Alert(Alert.AlertType.INFORMATION, msg);
        alertMessage.setHeaderText(null);
        alertMessage.showAndWait();
    }
}

