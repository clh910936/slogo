package FrontInternal.Components;

import BackExternal.IModelManager;
import BackExternal.ITurtle;
import BackExternal.IllegalTurtleStateException;
import FrontInternal.Players.TurtleView;
import FrontInternal.Util.Operator;
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
    private List<TurtleView> myTurtles = new ArrayList();

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

    public void move(TurtleView turtle, double x, double y, boolean penDown) {

        PathTransition pt = new PathTransition();

        pt.setDuration(Duration.seconds(0.5));
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
        if (t2.getUpdatedX().size() != t2.getIsPenUp().size()) throw new IllegalTurtleStateException();

        for (int i = 0; i < t2.getUpdatedX().size(); i++) {
            System.out.println("LOC: (" + t2.getUpdatedX() + "," + t2.getUpdatedY() + ")");
            System.out.println("ANGLE: " + t2.getHeadingAngle().get(i));
            System.out.println("PEN UP?: " + t2.getIsPenUp().get(i));
            System.out.println("DISPLAY?: " + t2.getIsDisplayed().get(i));

            double x = t2.getUpdatedX().get(i);
            double y = t2.getUpdatedY().get(i);
            double angle = t2.getHeadingAngle().get(i) - 90;
            boolean penDown = !t2.getIsPenUp().get(i);

            //FIXME: angle rotation
            //t1.rotate(angle);

            move(t1, x, y, penDown);
        }


    }
}
