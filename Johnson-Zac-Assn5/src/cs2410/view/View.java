package cs2410.view;

import cs2410.toolPane.ToolPane;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

/**
 * Main driver class for drawing tablet assignment (#5).
 * @author zac johnson
 * @version 1.0
 */
public class View extends Application{
    /**
     * Instance of Rectangle
     */
    private Rectangle tempRect;

    /**
     * Instance of Ellipse
     */
    private Ellipse tempEll;

    /**
     * Instance of Path
     */
    private Path tempPath;

    /**
     * Double value for holding starting X position of a shape
     */
    private double startingX = 0;

    /**
     * Double value for holding starting Y position of a shape
     */
    private double startingY = 0;

    /**
     * Instance of ToolPane
     */
    private ToolPane toolPane;

    /**
     * Instance of Pane for the draw pane
     */
    private Pane drawPane;

    /**
     * ArrayList for holding all the shapes in drawPane
     */
    private List<Shape> listOfShapes = new ArrayList<>();

    /**
     * Main start method for application function
     * @param primaryStage Stage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Advanced Drawing Tablet for Kindergarteners");
        Pane mainPane = new Pane();
        toolPane = new ToolPane();
        toolPane.setPrefSize(600, 37);
        drawPane = new Pane();
        drawPane.setPrefSize(600, 500);
        drawPane.setLayoutY(37);

        initDrawPane(drawPane);
        initToolPane(toolPane.getEditBtn());

        Rectangle clip = new Rectangle();
        clip.setHeight(500);
        clip.setWidth(600);

        drawPane.setClip(clip);

        mainPane.getChildren().addAll(toolPane, drawPane);

        Scene scene = new Scene(mainPane);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Helper function to draw shapes.
     * Creates shapes with fill, stroke, and stroke width upon mouse being pressed.
     * Upon dragging, shape is added to the draw pane.
     * While dragging, a preview of the shape is displayed.
     * @param pane Pane
     */
    private void initDrawPane(Pane pane) {
        pane.setOnMousePressed(event -> {
            if (toolPane.rectBtnSelected()) {
                tempRect = new Rectangle();
                tempRect.setFill(toolPane.getFillPickerValue());
                tempRect.setStroke(toolPane.getStrokePickerValue());
                tempRect.setStrokeWidth(toolPane.getStrokeSizeValue());
                startingX = event.getX();
                startingY = event.getY();
                tempRect.setY(event.getY());
                setShapeHandler(tempRect);
            } else if (toolPane.ellBtnSelected()) {
                tempEll = new Ellipse();
                tempEll.setFill(toolPane.getFillPickerValue());
                tempEll.setStroke(toolPane.getStrokePickerValue());
                tempEll.setStrokeWidth(toolPane.getStrokeSizeValue());
                startingX = event.getX();
                startingY = event.getY();
                setShapeHandler(tempEll);
            } else if (toolPane.freeBtnSelected()) {
                tempPath = new Path();
                tempPath.getElements().clear();
                tempPath.setStroke(toolPane.getStrokePickerValue());
                tempPath.setStrokeWidth(toolPane.getStrokeSizeValue());
                startingX = event.getX();
                startingY = event.getY();
                tempPath.getElements().add(new MoveTo(startingX, startingY));
                setShapeHandler(tempPath);
            }
            pane.setCursor(Cursor.NONE);
        });

        pane.setOnDragDetected(event -> {
            if (toolPane.rectBtnSelected()) {
                pane.getChildren().add(tempRect);
                listOfShapes.add(tempRect);
            } else if (toolPane.ellBtnSelected()) {
                pane.getChildren().add(tempEll);
                listOfShapes.add(tempEll);
            } else if (toolPane.freeBtnSelected()) {
                pane.getChildren().add(tempPath);
                listOfShapes.add(tempPath);
            }
        });

        pane.setOnMouseDragged(event -> {
            if (toolPane.rectBtnSelected()) {
                tempRect.setX(startingX);
                tempRect.setY(startingY);

                tempRect.setWidth(Math.abs((event.getX() - tempRect.getX())));
                tempRect.setHeight(Math.abs((event.getY() - tempRect.getY())));

                if(tempRect.getX() > event.getX()) {
                    tempRect.setX(event.getX());
                }

                if(tempRect.getY() > event.getY()) {
                    tempRect.setY(event.getY());
                }
            } else if (toolPane.ellBtnSelected()) {
                tempEll.setCenterX((startingX + event.getX()) / 2);
                tempEll.setCenterY((startingY + event.getY()) / 2);

                tempEll.setRadiusX(Math.abs(event.getX() - tempEll.getCenterX()));
                tempEll.setRadiusY(Math.abs(event.getY() - tempEll.getCenterY()));

            } else if (toolPane.freeBtnSelected()) {
                tempPath.getElements().add(new LineTo(event.getX(), event.getY()));
            }
        });

        pane.setOnMouseReleased(event -> {
            pane.setCursor(Cursor.DEFAULT);
        });
    }

    /**
     * Helper function for drag and drop functionality, changing fill, stroke, and stroke width of shape.
     * Also helps with erase functionality.
     * @param shape Shape
     */
    private void setShapeHandler(Shape shape) {
        shape.setOnMouseEntered(event -> {
            if (toolPane.editBtnSelected()) {
                shape.setCursor(Cursor.OPEN_HAND);
            } else if (toolPane.eraseBtnSelected()) {
                shape.setCursor(Cursor.CROSSHAIR);
            }
        });
        shape.setOnMousePressed(event -> {
            shape.setCursor(Cursor.CLOSED_HAND);
            if (toolPane.editBtnSelected()) {
                shape.toFront();
                startingX = event.getX();
                startingY = event.getY();
            }
        });

        shape.setOnMouseDragged(event -> {
            if (toolPane.editBtnSelected()) {
                shape.setTranslateX(shape.getTranslateX() + event.getX() - startingX);
                shape.setTranslateY(shape.getTranslateY() + event.getY() - startingY);
            }
        });

        shape.setOnMouseClicked(event -> {
            if (toolPane.editBtnSelected()) {
                if (shape instanceof Rectangle || shape instanceof Ellipse) {
                    toolPane.setFillPickerValue((Color) shape.getFill());
                    toolPane.setStrokePickerValue((Color) shape.getStroke());
                    toolPane.setStrokeSizeValue((int) shape.getStrokeWidth());
                } else if (shape instanceof Path) {
                    toolPane.setStrokePickerValue((Color) shape.getStroke());
                    toolPane.setStrokeSizeValue((int) shape.getStrokeWidth());
                }

                if (toolPane.getFillPickerValue() != shape.getFill()) {
                    shape.setFill(toolPane.getFillPickerValue());
                }
                if (toolPane.getStrokePickerValue() != shape.getStroke()) {
                    shape.setStroke(toolPane.getStrokePickerValue());
                }
                if (toolPane.getStrokeSizeValue() != shape.getStrokeWidth()) {
                    shape.setStrokeWidth(toolPane.getStrokeSizeValue());
                }

                toolPane.setFillPickerAction(event1 -> {
                    shape.setFill(toolPane.getFillPickerValue());
                });

                toolPane.setStrokePickerAction(event1 -> {
                    shape.setStroke(toolPane.getStrokePickerValue());
                });

                toolPane.setStrokeSizeAction(event1 -> {
                    shape.setStrokeWidth(toolPane.getStrokeSizeValue());
                });
            } else if (toolPane.eraseBtnSelected()) {
                drawPane.getChildren().remove(shape);
            }
        });

        shape.setOnMouseReleased(event -> {
            shape.setCursor(Cursor.OPEN_HAND);
        });
    }

    /**
     * Helper function that sets the default shape to edit when editBtn is toggled on.
     * @param btn ToggleButton
     */
    private void initToolPane(ToggleButton btn) {
        btn.setOnMouseClicked(event -> {
            if (listOfShapes.size() >= 1) {
                Shape shape = listOfShapes.get(listOfShapes.size() - 1);
                if (btn.isSelected()) {
                    toolPane.setFillPickerAction(event1 -> {
                        shape.setFill(toolPane.getFillPickerValue());
                    });

                    toolPane.setStrokePickerAction(event1 -> {
                        shape.setStroke(toolPane.getStrokePickerValue());
                    });

                    toolPane.setStrokeSizeAction(event1 -> {
                        shape.setStrokeWidth(toolPane.getStrokeSizeValue());
                    });
                }
            }
        });
    }
}
