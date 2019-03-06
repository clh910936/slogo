package FrontInternal.Players;

import FrontInternal.Components.Board;
import FrontInternal.Util.Location;
import javafx.animation.PathTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.awt.*;

public class TurtleView extends Sprite {
    private double myX;
    private double myY;
    private double myAngle;

    private double myLastX;
    private double myLastY;

    private Path myPath;

    private GraphicsContext gc;

    private Pen myPen;

    public TurtleView(Dimension d, GraphicsContext g, int id){
        super(id);
        setImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle_default.png")));
        place(d.width/ 2, d.height/2);

        myPath = new Path();
        MoveTo m = new MoveTo(getCenterX(), getCenterY());
        myPath.getElements().add(m);
        gc = g;

        myPen = new Pen();
    }

    private void place(int i, int j) {
        setX(i - getBoundsInLocal().getWidth() / 2);
        setY(j - getBoundsInLocal().getHeight() / 2);

        myX = getX() + getBoundsInLocal().getWidth()/2;
        myY = getY() + getBoundsInLocal().getHeight()/2;
        myAngle = 90;

        myLastX = 0;
        myLastY = 0;
    }

    public void update() {

    }

    public double getCenterX() {
        myX = getX() + getBoundsInLocal().getWidth()/2;
        return myX;
    }
    public double getCenterY() {
        myY = getY() + getBoundsInLocal().getHeight()/2;
        return myY;
    }


    public void rotate(double angle) {
        setRotate(angle);
    }

    public double getCurrentX() {
        return getTranslateX() + getCenterX();
    }

    public double getCurrentY() {
        return getTranslateY() + getCenterY();
    }


    public double getLastX() {
        return myLastX;
    }

    public double getLastY() {
        return myLastY;
    }

    public void setLastX(double x) {
        myLastX = x;
    }

    public void setLastY(double y) {
        myLastY = y;
    }

    //TODO: REFACTOR THIS MASSIVE METHOD
    @Override
    public void move(double x, double y) {
        double xdisp = x-getLastX();
        double ydisp = y-getLastY();

        if (!(xdisp==0&&ydisp==0)) {
            PathTransition pt = new PathTransition();

            pt.setDuration(Duration.seconds(1));
            pt.setNode(this);

            LineTo l = new LineTo(getCenterX() + x, getCenterY() - y);

            //have to update turtle location after this
            myPath.getElements().addAll(l);
            pt.setPath(myPath);
            pt.setOrientation(PathTransition.OrientationType.NONE);
            //pt.setCycleCount(Timeline.INDEFINITE);
            //pt.setAutoReverse(true);
            pt.currentTimeProperty().addListener(new ChangeListener<Duration>() {

                Location oldLocation = null;

                /**
                 * Draw a line from the old location to the new location
                 */
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

                    // skip starting at 0/0
                    if (oldValue == Duration.ZERO)
                        return;

                    // get current location
                    double x = getCurrentX();
                    double y = getCurrentY();
                    //System.out.println("in loop");
                    //                System.out.println("current x: " + x);
                    //                System.out.println("current y: " + y);
                    //                System.out.println("angle: " + turtle.getRotate());

                    // initialize the location
                    if (oldLocation == null) {
                        oldLocation = new Location(x, y);
                        return;
                    }

                    // draw line
                    if (!myPen.getPenUp()) {
                        gc.setStroke(myPen.getColor());
                        gc.setFill(Color.YELLOW);
                        gc.setLineWidth(myPen.getSize());
                        gc.strokeLine(oldLocation.getX(), oldLocation.getY(), x, y);
                    }

                    oldLocation.setX(x);
                    oldLocation.setY(y);
                }
            });

            System.out.println("going to play");
            pt.play();
            System.out.println("played");
            myPath.getElements().clear();
            myPath.getElements().addAll(new MoveTo(l.getX(), l.getY()));

            setLastX(x);
            setLastY(y);
        }
//        javafx.scene.shape.Rectangle rect = new Rectangle(100, 40, 100, 100);
//        rect.setArcHeight(50);
//        rect.setArcWidth(50);
//        rect.setFill(Color.VIOLET);
//
//        //myBoard.getChildren().addAll(rect);
//
//
//        Path path = new Path();
//        path.getElements().add (new MoveTo(0f, 50f));
//        path.getElements().add (new CubicCurveTo(40f, 10f, 390f, 240f, 1904, 50f));
//
//        PathTransition pathTransition = new PathTransition();
//        pathTransition.setDuration(Duration.millis(100000));
//        pathTransition.setNode(this);
//        pathTransition.setPath(path);
//        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
//        pathTransition.setAutoReverse(true);
//
//        pathTransition.play();
    }

    public void setPen(boolean pen) {
        myPen.setPenUp(pen);
    }

    public void setPenColor(int index) {
        myPen.setColor(Color.RED);
    }

    @Override
    public Node getPath() {
        return myPath;
    }
}
