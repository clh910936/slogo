package FrontInternal.Components;

import API.IModelManager;
import BackExternal.ITurtle;

import FrontInternal.Players.TurtleView;
import FrontInternal.Views.ViewAPI;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/*
    Board functions as the sprite manager (need to get rid of that class) and moves the sprite across the screen
 */
public class Board extends Pane implements ViewAPI {
    private Canvas myCanvas;
    private GraphicsContext gc;
    private int myWidth;
    private int myHeight;
    private List<TurtleView> myTurtles = new ArrayList<TurtleView>();

    private Path p;
    private Operator myOperator;
    private IModelManager myController;

    public Board(int width, int height, Operator operator) {
        myOperator = operator;
        myController = operator.getManager();
        myWidth = width;
        myHeight = height;
        createCanvas(myWidth, myHeight);

        var t = new TurtleView();
        t.place(myWidth / 2, myHeight / 2);
        getChildren().addAll(myCanvas, t);

        p = new Path();
        MoveTo m = new MoveTo(t.getCenterX(), t.getCenterY());
        p.getElements().add(m);
        myTurtles.add(t);


    }

    private void createCanvas(int width, int height) {
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();
        setBackground(Color.BEIGE);

    }

    private void setBackground(Color c) {
        gc.setFill(c);
        gc.fillRect(0, 0, myWidth, myHeight);
    }

    private void move(TurtleView turtle, double x, double y, boolean penDown) {

        PathTransition pt = new PathTransition();

        pt.setDuration(Duration.seconds(0.1));
        pt.setNode(turtle);

        LineTo l = new LineTo(turtle.getCenterX()+x,turtle.getCenterY()-y);

        //have to update turtle location after this
        p.getElements().addAll(l);
        pt.setPath(p);
        pt.setOrientation(PathTransition.OrientationType.NONE);
        //pt.setCycleCount(Timeline.INDEFINITE);
        //pt.setAutoReverse(true);
        pt.currentTimeProperty().addListener( new ChangeListener<Duration>() {

            Location oldLocation = null;

            /**
             * Draw a line from the old location to the new location
             */
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                // skip starting at 0/0
                if( oldValue == Duration.ZERO)
                    return;

                // get current location
                double x = turtle.getCurrentX();
                double y = turtle.getCurrentY();
//                System.out.println("current x: " + x);
//                System.out.println("current y: " + y);
//                System.out.println("angle: " + turtle.getRotate());

                // initialize the location
                if( oldLocation == null) {
                    oldLocation = new Location();
                    oldLocation.x = x;
                    oldLocation.y = y;
                    return;
                }

                // draw line
                if (penDown) {
                    gc.setStroke(Color.BLUE);
                    gc.setFill(Color.YELLOW);
                    gc.setLineWidth(4);
                    gc.strokeLine(oldLocation.x, oldLocation.y, x, y);
                }

                oldLocation.x = x;
                oldLocation.y = y;
            }
        });

        pt.play();
        p.getElements().clear();
        p.getElements().addAll(new MoveTo(l.getX(), l.getY()));
    }
    public static class Location {
        double x;
        double y;
    }

    public void update() {
        // needs to be in update bc might change
        List<ITurtle> myTurtleModels = myController.getTurtleList();
        for (int i = 0; i < myTurtles.size(); i++) {
            handleChange(myTurtles.get(i), myTurtleModels.get(i));
        }
    }

    @Override
    public Pane getPane() {
        return null;
    }

    private void handleChange(TurtleView t1, ITurtle t2) {
//        System.out.println("LOC: (" + t2.getUpdatedX() + "," + t2.getUpdatedY() + ")");
//        System.out.println("ANGLE: " + t2.getHeadingAngle());
//        System.out.println("PEN UP?: " + t2.getIsPenUp());
//        System.out.println("DISPLAY?: " + t2.getIsDisplayed());
//        System.out.println("LENGTH: " + t2.getUpdatedX().size());

//        if (t2.getUpdatedX().size() != t2.getIsDisplayed().size()) {
//            //throw new IllegalTurtleStateException();
//        }
        System.out.println(t2.getClearScreen());
        for (int i = 0; i < t2.getUpdatedX().size(); i++) {

            double x = t2.getUpdatedX().get(i);
            double xdisp = x-t1.getLastX();


            double y = t2.getUpdatedY().get(i);
            double ydisp = y-t1.getLastY();


            System.out.printf("xdisp: %f, ydisp: %f\n", xdisp, ydisp);
            double angle = 90-t2.getHeadingAngle().get(i);
            boolean penDown = !t2.getIsPenUp().get(i);
            boolean diplay = t2.getIsDisplayed().get(i);
            if(t2.getClearScreen().get(i)) {
                System.out.println("gonna clear screen now");
                clearScreen();
                continue;
            }

            t1.rotate(angle);
            if (!(xdisp==0&&ydisp==0)) {
                System.out.println("entering");
                t1.setLastX(x);
                t1.setLastY(y);
                move(t1, x, y, penDown);
            }
        }

    }

    private void clearScreen() {
        getChildren().clear();
        //gc.clearRect(0, 0, myWidth, myHeight);
        setBackground(Color.BEIGE);

        // add stuff to update front end turtles
        //List<ITurtle> myTurtleModels = myController.getTurtleList();
        myTurtles.add(new TurtleView());
    }
}
