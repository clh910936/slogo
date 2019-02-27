package FrontInternal.Components;

import FrontInternal.Players.SpriteManager;
import FrontInternal.Players.TurtleView;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

public class Board extends Pane {
    private Canvas myCanvas;
    private GraphicsContext gc;
    private int myWidth;
    private int myHeight;
    private TurtleView myTurtle;

    private Path p;

    public Board(int width, int height) {
        myWidth = width;
        myHeight = height;
        createCanvas(myWidth, myHeight);

        myTurtle = new TurtleView();
        myTurtle.place(myWidth / 2, myHeight / 2);
        getChildren().addAll(myCanvas, myTurtle);

        p = new Path();
        MoveTo m = new MoveTo(myTurtle.getCenterX(), myTurtle.getCenterY());
        p.getElements().add(m);
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

    public void move(int x, int y) {

        PathTransition pt = new PathTransition();

        pt.setDuration(Duration.seconds(2));
        pt.setNode(myTurtle);

        LineTo l = new LineTo(myTurtle.getCenterX()+x,myTurtle.getCenterY()-y);

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
                double x = myTurtle.getCurrentX();
                double y = myTurtle.getCurrentY();

                // initialize the location
                if( oldLocation == null) {
                    oldLocation = new Location();
                    oldLocation.x = x;
                    oldLocation.y = y;
                    return;
                }

                // draw line
                gc.setStroke(Color.BLUE);
                gc.setFill(Color.YELLOW);
                gc.setLineWidth(4);
                gc.strokeLine(oldLocation.x, oldLocation.y, x, y);
                //System.out.printf("old x: %f\t new x: %f", oldLocation.x, x);
                // update old location with current one
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
}
