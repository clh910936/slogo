package FrontInternal.Components;

import API.IModelManager;
import FrontInternal.Players.TurtleManager;
import FrontInternal.Views.ViewAPI;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.awt.*;

/**
 * The Board handles the actions of all turtle elements on the screen, including movement, changes to the pen, etc.
 * @author Feroze
 */
public class Board extends Pane implements ViewAPI {
    private Canvas myCanvas;
    private GraphicsContext gc;
    private Dimension myDimensions;
    private IModelManager myController;

    private TurtleManager myTurtleManager;
    private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private SimpleDoubleProperty slideSpeed;

    /**
     * Constructor of the board. Creates the canvas, a TurtleManager, and the animation.
     * @param d dimension inherited from the GUI
     * @param controller backend instance also inherited from GUI
     */
    public Board(Dimension d, IModelManager controller) {
        myDimensions = d;
        myController = controller;

        createCanvas(myDimensions.width, myDimensions.height);
        getChildren().addAll(myCanvas);

        slideSpeed = new SimpleDoubleProperty(1);
        myTurtleManager = new TurtleManager(this, slideSpeed, myController);

        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> update());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();

        addSlider();

    }

    private void addSlider() {
        Slider slider = new Slider();
        slider.setMin(0.1);
        slider.setMax(2);
        slider.setValue(1);
        slideSpeed.bind(slider.valueProperty());
        slider.setTranslateX(myDimensions.width - 150);
        slider.setTranslateY(myDimensions.height-50);
        getChildren().add(slider);
    }

    /**
     * Returns the dimensions of the board
     * @return myDimensions
     */
    public Dimension getDimensions() {
        return myDimensions;
    }
    

    private void createCanvas(int width, int height) {
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        setBackground(Color.BEIGE);

    }

    private void setBackground(Color c) {
        gc.setFill(c);
        gc.fillRect(0, 0, myDimensions.height, myDimensions.width);
    }

    /**
     * Calls upon the turtle manager to move the turtle on the canvas
     * @param x x-coordinate of new position
     * @param y y-coordinate of new position
     * @param turtleId specified turtle id
     */
    public void move(double x, double y, int turtleId) {
        myTurtleManager.move(x, y, turtleId);
    }

    /**
     * @return Returns the graphic context of the canvas, required for turtles to draw on it.
     */
    public GraphicsContext getGC() {
        return gc;
    }

    /**
     * Required to implement these next two methods from API
     */
    @Override
    public void update() {
        myTurtleManager.update();
    }

    @Override
    public Pane getPane() {
        return null;
    }


    /**
     * Clears the screen
     */
    public void clear() {
        setBackground(Color.BEIGE);
        myTurtleManager.getAllTurtles().forEach(e -> getChildren().remove(e));
        myTurtleManager.clearTurtles();
    }

    /**
     * Sets the background color of the screen
     * @param c new bg color
     */
    public void setBackgroundColor(Color c) {
        setBackground(c);
    }

    /**
     * Asks the turtle manager to set pen up status of turtle
     * @param true_is_penup pen up/down
     * @param turtleId specified turtle id
     */
    public void penUp(boolean true_is_penup, int turtleId) {
        myTurtleManager.setPen(true_is_penup, turtleId);
    }

    /**
     * Asks the turtle manager to rotate the given turtle
     * @param degrees rotation angle
     * @param turtleId specified turtle id
     */
    public void rotate(double degrees, int turtleId) {
        myTurtleManager.rotate(degrees, turtleId);
    }

    /**
     * Asks the turtle manager to set pen color of given turtle
     * @param c new pen color
     * @param turtleId specified turtle id
     */
    public void setPenColor(Color c, int turtleId) {
        myTurtleManager.setPenColor(c, turtleId);
    }

    /**
     * Asks turtle manager to set pen size of given turtle
     * @param pixels pen size in pixels
     * @param turtleId specified turtle id
     */
    public void setPenSize(double pixels, int turtleId) {
        myTurtleManager.setPenSize(pixels, turtleId);
    }

    /**
     * Asks turtle manager to set image of turtle
     * @param index index into image array
     * @param turtleId specified turtle id
     */
    public void setTurtleShape(int index, int turtleId) {
        myTurtleManager.setTurtleShape(index, turtleId);
    }

    /**
     * Asks turtle manager to add turtle to screen
     * @param turtleId id of new turtle
     * @param controller backend instance
     */
    public void addTurtle(int turtleId, IModelManager controller) {
        myTurtleManager.addTurtle(turtleId, controller);
    }

    /**
     * Asks turtle manager to set display status of given turtle
     * @param isDisplayed true if visible, false if hidden
     * @param turtleId specified turtle id
     */
    public void setDisplayed(boolean isDisplayed, int turtleId) {
        myTurtleManager.setDisplayed(isDisplayed, turtleId);
    }
}
