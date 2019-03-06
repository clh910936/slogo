package FrontInternal.Components;

import API.IModelManager;

import FrontInternal.Players.TurtleManager;
import FrontInternal.Views.ViewAPI;
import javafx.animation.PathTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;

/*
    Board functions as the sprite manager (need to get rid of that class) and moves the sprite across the screen
 */
public class Board extends Pane implements ViewAPI {
    private Canvas myCanvas;
    private GraphicsContext gc;
    private Dimension myDimensions;

    private TurtleManager myTurtleManager;

    public Board(Dimension d) {
        myDimensions = d;
        createCanvas(myDimensions.width, myDimensions.height);
        getChildren().addAll(myCanvas);

        myTurtleManager = new TurtleManager(this);


    }

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

    public void move(double x, double y, int turtleId) {
        myTurtleManager.moveTurtle(x, y, turtleId);

    }

    public GraphicsContext getGC() {
        return gc;
    }

    @Override
    public void update() {
        
    }

    @Override
    public Pane getPane() {
        return null;
    }


    public void clear() {
        getChildren().clear();
        //gc.clearRect(0, 0, myWidth, myHeight);
        setBackground(Color.BEIGE);
    }

    public void setBackgroundColor(int index) {
    }

    public void penUp(boolean true_is_penup, int turtleId) {
        myTurtleManager.setPen(true_is_penup, turtleId);
    }

    public void rotate(double degrees, int turtleId) {
        myTurtleManager.rotateTurtle(degrees, turtleId);
    }

    public void setPenColor(int index, int turtleId) {
        myTurtleManager.setPenColor(index, turtleId);
    }

    public void setPenSize(double pixels, int turtleId) {
        myTurtleManager.setPenSize(pixels, turtleId);
    }

    public void setTurtleShape(int index, int turtleId) {
        myTurtleManager.setTurtleShape(index, turtleId);
    }

    public void addTurtle(int turtleId) {
        myTurtleManager.addTurtle(turtleId);
    }
}
