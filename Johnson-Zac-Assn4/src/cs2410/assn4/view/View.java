package cs2410.assn4.view;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import cs2410.assn4.model.FileIO;
import cs2410.assn4.model.Model;
import cs2410.assn4.controller.Controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class View extends Application {
    /**
     * Instance of FileIO
     */
    private FileIO fileIO;

    /**
     * Instance of controller
     */
    private Controller controller;

    /**
     * Instance of ArrayList
     */
    private ArrayList<Model> listOfImages;

    /**
     * Instance of Scene
     */
    private Scene scene1;

    /**
     * Button for Previous image
     */
    private Button prevBtn;

    /**
     * Button for adding an image
     */
    private Button addBtn;

    /**
     * Button for deleting an image
     */
    private Button deleteBtn;

    /**
     * Button for next image
     */
    private Button nextBtn;

    /**
     * Counter for keeping track of place in ArrayList
     */
    private int counter = 0;

    /**
     * Default Constructor
     */
    public View(){ }

    /**
     * Main start function for UI
     * @param primaryStage Stage
     */
    @Override
    public void start(Stage primaryStage) {
        fileIO = new FileIO();
        controller = new Controller();
        listOfImages = fileIO.loadImages();


        Pane mainPane = new Pane();
        Pane buttonPane = new Pane();
        Pane imagePane = new Pane();
        imagePane.setPrefSize(500, 500);
        buttonPane.setPrefSize(500, 100);
        buttonPane.setLayoutY(500);

        Scene scene1 = new Scene(mainPane, 500, 600);
        primaryStage.setScene(scene1);

        ImageView view1 = new ImageView();
        view1.setPreserveRatio(true);
        view1.setFitWidth(500);
        view1.setSmooth(true);

        //NOTE: Image image1 = new Image("https://upload.wikimedia.org/wikipedia/commons/0/07/Emperor_Penguin_Manchot_empereur.jpg");

        Model startModel = fileIO.getFromList(listOfImages, counter);
        String startImageURL = startModel.getUrl();
        primaryStage.setTitle(startModel.getTitle());
        Image startImage = new Image(startImageURL);
        view1.setImage(startImage);

        prevBtn = new Button("Prev");
        prevBtn.setPrefWidth(50);
        prevBtn.setLayoutY(50);
        prevBtn.setLayoutX(120);
        prevBtn.setOnAction(event -> {
            if (event.getSource() == prevBtn && !prevBtn.isDisabled()) {
                if(counter <= 0) {
                    //Set counter to listOfImages.size()
                    counter = listOfImages.size() - 1;
                } else {
                    counter--;
                }

                Model model = controller.prevImage(counter, listOfImages);
                primaryStage.setTitle(model.getTitle());
                view1.setImage(createImage(model));
            }
        });

        addBtn = new Button("Add");
        addBtn.setPrefWidth(50);
        addBtn.setLayoutY(50);
        addBtn.setLayoutX(190);
        addBtn.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Film Directory");
            alert.setHeaderText(null);

            String url = "";
            String title = "";

            TextInputDialog dialog1 = new TextInputDialog("https://...");
            dialog1.setTitle("Enter Image URL");
            dialog1.setHeaderText(null);
            dialog1.setContentText("Enter Image URL:");

            TextInputDialog dialog2 = new TextInputDialog("Title goes here");
            dialog2.setTitle("Enter Image Title");
            dialog2.setHeaderText(null);
            dialog2.setContentText("Enter Image Title:");

            Optional<String> dialog1Result = dialog1.showAndWait();
            if (dialog1Result.isPresent()) {
                url += dialog1Result.get();
                dialog2.showAndWait();
            }

            Optional<String> dialog2Result = dialog2.showAndWait();
            if (dialog2Result.isPresent()) {
                title += dialog2Result.get();
                alert.close();
            }

            fileIO.fileWrite(url, title);
            controller.addImage(listOfImages, url, title);
            view1.setImage(new Image(fileIO.getFromList(listOfImages, listOfImages.size()-1).getUrl()));

            if (listOfImages.size() == 1) {
                prevBtn.setDisable(false);
                nextBtn.setDisable(false);
                deleteBtn.setDisable(false);
            }
        });

        deleteBtn = new Button("Delete");
        deleteBtn.setPrefWidth(50);
        deleteBtn.setLayoutY(50);
        deleteBtn.setLayoutX(260);
        deleteBtn.setOnAction(event -> {
            if(listOfImages.size() <= 0) {
                primaryStage.setTitle("No images found. Please add image.");
                prevBtn.setDisable(true);
                nextBtn.setDisable(true);
                deleteBtn.setDisable(true);
                view1.setImage(new Image("https://owensboro.kyschools.us/portals/0/Sutton/Images/StaffMembers/No%20Image.jpg?ver=2016-11-19-211337-060"));
            } else {
                Model toDelete = fileIO.getFromList(listOfImages,counter);
                String toBeDeleted = toDelete.getUrl() + " " + toDelete.getTitle();
                controller.delImage(counter, listOfImages);
                view1.setImage(new Image("https://vignette.wikia.nocookie.net/undertale-au/images/0/0e/8503702-deleted-stamp.jpg/revision/latest?cb=20170321162217"));
                try {
                    fileIO.removeLine(toBeDeleted);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                counter--;
            }
        });

        nextBtn = new Button("Next");
        nextBtn.setPrefWidth(50);
        nextBtn.setLayoutY(50);
        nextBtn.setLayoutX(330);
        nextBtn.setOnAction(event -> {
            if (event.getSource() == nextBtn && !nextBtn.isDisabled()) {
                if (counter >= listOfImages.size() - 1) {
                    counter = 0;
                } else {
                    counter++;
                }

                Model model = controller.nextImage(counter, listOfImages);
                primaryStage.setTitle(model.getTitle());
                view1.setImage(createImage(model));
            }

        });

        mainPane.getChildren().addAll(imagePane, buttonPane);
        buttonPane.getChildren().addAll(prevBtn, addBtn, deleteBtn, nextBtn);
        imagePane.getChildren().add(view1);

        primaryStage.show();
    }

    /**
     * Helper function for turning a Model into an Image
     * @param model Model
     * @return Image
     */
    private Image createImage(Model model) {
        String imageURL = model.getUrl();
        return new Image(imageURL);
    }
}
