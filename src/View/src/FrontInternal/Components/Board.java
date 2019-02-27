package FrontInternal.Components;

import FrontInternal.Players.TurtleView;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class Board extends Pane {
    private Canvas myCanvas;
    private GraphicsContext gc;
    private int myWidth;
    private int myHeight;
    private TurtleView myTurtle;

    Circle pen = new Circle(0, 0, 4);


    public Board(int width, int height) {
        myWidth = width;
        myHeight = height;
        createCanvas(myWidth, myHeight);
        //setBackground(Color.BEIGE);

        myTurtle = new TurtleView();
        myTurtle.place(myWidth / 2, myHeight / 2);
        getChildren().addAll(myCanvas, myTurtle);

        getChildren().add(pen);


    }

    private void createCanvas(int width, int height) {
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();

    }

    private void setBackground(Color c) {
        gc.setFill(c);
        gc.fillRect(0, 0, myWidth, myHeight);
    }

    public void move() {
        //System.out.println("hello");
        Path path = new Path();

        path.setStroke(Color.RED);
        path.setStrokeWidth(10);

        path.getElements().addAll(new MoveTo(200, 200));
        Animation animation = createPathAnimation(path, Duration.seconds(5));
        getChildren().add(path);
        animation.play();
    }

    private Animation createPathAnimation(Path path, Duration duration) {

        // move a node along a path. we want its position


        // create path transition
        PathTransition pathTransition = new PathTransition( duration, path, pen);
        pathTransition.currentTimeProperty().addListener( new ChangeListener<Duration>() {

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
                double x = pen.getTranslateX();
                double y = pen.getTranslateY();
                System.out.println(x);

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

                // update old location with current one
                oldLocation.x = x;
                oldLocation.y = y;
            }
        });

        return pathTransition;
    }

    public static class Location {
        double x;
        double y;
    }
}
