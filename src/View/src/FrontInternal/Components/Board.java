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

    public Board(int width, int height) {
        myWidth = width;
        myHeight = height;
        createCanvas(myWidth, myHeight);

        myTurtle = new TurtleView();
        myTurtle.place(myWidth / 2, myHeight / 2);
        getChildren().addAll(myCanvas, myTurtle);
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
        Path p = new Path();
        MoveTo m = new MoveTo(myTurtle.getCenterX(), myTurtle.getCenterY());

        LineTo l = new LineTo(myTurtle.getCenterX()+x,myTurtle.getCenterY()-y);

        //have to update turtle location after this
        p.getElements().addAll(m, l);
        pt.setPath(p);
        pt.setOrientation(PathTransition.OrientationType.NONE);
        //pt.setCycleCount(Timeline.INDEFINITE);
        //pt.setAutoReverse(true);
        pt.play();
    }
}
