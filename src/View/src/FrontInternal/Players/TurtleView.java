package FrontInternal.Players;
import API.IModelManager;
import FrontInternal.Util.Location;
import javafx.animation.PathTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.util.Duration;

import java.awt.*;
import java.util.ResourceBundle;

/**
 * Basic turtle sprite element. Has an ImageView and draws upon a graphics context with its pen.
 * @author Feroze
 */
public class TurtleView extends Sprite {
    public static final int IMAGE_HEIGHT = 75;
    public static final int IMAGE_WIDTH = 75;
    public static final int ANGLE_OFFSET = -90;
    private double myX;
    private double myY;

    private double myLastX;
    private double myLastY;

    private Path myPath;

    private GraphicsContext gc;

    private Pen myPen;
    private boolean isBusy;

    private TurtleScheduler myScheduler;
    private SimpleDoubleProperty slideSpeed;
    private IModelManager myController;

    private final String TURTLE = "Turtle";
    private final ResourceBundle myImages = ResourceBundle.getBundle("TurtleImages");

    /**
     * Creating a turtle places it on the board and gives it the graphic context required to draw lines. Creating a
     * turtle also creates a scheduler for it, which can is used to add commands.
     * @param d Dimensions of the board (so it can be placed in the middle)
     * @param g graphic context
     * @param id turtle id #
     * @param speed speed at which to move
     * @param controller backend instance so the turtle can update its model
     */
    public TurtleView(Dimension d, GraphicsContext g, int id, SimpleDoubleProperty speed, IModelManager controller){
        super(id);
        myController = controller;
        String image = myImages.getString(TURTLE + myController.getTurtleImage(id));
        setImage(new Image(getClass().getClassLoader().getResourceAsStream(image), IMAGE_HEIGHT, IMAGE_WIDTH, false, false));
        place(d.width/ 2, d.height/2);

        myPath = new Path();
        MoveTo m = new MoveTo(getCenterX(), getCenterY());
        myPath.getElements().add(m);

        gc = g;

        myPen = new Pen();
        isBusy = false;

        myScheduler = new TurtleScheduler(this);
        slideSpeed = speed;
        rotate(ANGLE_OFFSET);
        setProperties();
    }

    private void setProperties() {
        myController.setXPos(getID(), new SimpleDoubleProperty(0));
        myController.setYPos(getID(), new SimpleDoubleProperty(0));
        myController.setPenUp(getID(), new SimpleBooleanProperty(false));
        myController.setPenThickness(getID(), new SimpleDoubleProperty(Pen.DEFAULT_SIZE));
        myController.setR(getID(), new SimpleIntegerProperty(Pen.DEFAULT_R));
        myController.setG(getID(), new SimpleIntegerProperty(Pen.DEFAULT_G));
        myController.setB(getID(), new SimpleIntegerProperty(Pen.DEFAULT_B));
    }

    /**
     * Set the turtle image to the default image
     */
    public void setImageProp() {
        myController.setTurtleImage(getID(), 0);
    }

    private void place(int i, int j) {
        setX(i - getBoundsInLocal().getWidth() / 2);
        setY(j - getBoundsInLocal().getHeight() / 2);

        myX = getX() + getBoundsInLocal().getWidth()/2;
        myY = getY() + getBoundsInLocal().getHeight()/2;

        myLastX = 0;
        myLastY = 0;
    }

    /**
     * @return the scheduler of the turtle
     */
    public TurtleScheduler getScheduler() {
        return myScheduler;
    }

    /**
     * Commands the scheduler to dequeue an action and update the turtle.
     */
    public void update() {
        myScheduler.update();
    }

    private double getCenterX() {
        myX = getX() + getBoundsInLocal().getWidth()/2;
        return myX;
    }
    private double getCenterY() {
        myY = getY() + getBoundsInLocal().getHeight()/2;
        return myY;
    }


    /**
     * Rotates the turtle
     * @param angle rotation angle
     */
    public void rotate(double angle) {
        setBusy(true);
        setRotate(angle);
        setBusy(false);
    }

    private double getCurrentX() {
        return getTranslateX() + getCenterX();
    }

    private double getCurrentY() {
        return getTranslateY() + getCenterY();
    }


    private double getLastX() {
        return myLastX;
    }

    private double getLastY() {
        return myLastY;
    }

    private void setLastX(double x) {
        myLastX = x;
    }

    private void setLastY(double y) {
        myLastY = y;
    }

    private void setBusy(boolean b) {
        isBusy = b;
    }

    /**
     * @return the busy status of a turtle, which indicates whether an action is still being completed or not
     */
    public boolean getBusy() {
        return isBusy;
    }

    /**
     * Moves the turtle to a new coordinate on the screen
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public void move(Double x, Double y) {
        setBusy(true);
        double xdisp = x - getLastX();
        double ydisp = y - getLastY();
        if (!(xdisp == 0 && ydisp == 0)) {
            PathTransition pt = startPath();
            LineTo l = new LineTo(getCenterX() + x, getCenterY() - y);
            myPath.getElements().addAll(l);
            pt.setPath(myPath);
            pt.setOrientation(PathTransition.OrientationType.NONE);
            pt.currentTimeProperty().addListener(new ChangeListener<Duration>() {
                Location oldLocation = null;
                @Override
                public void changed(ObservableValue<? extends Duration> observable, Duration oldValue,
                                  Duration newValue) {
                    // skip starting at 0/0
                    if (oldValue == Duration.ZERO)
                        return;

                    // get current location
                    double x = getCurrentX();
                    double y = getCurrentY();
                    myController.getXPos(getID()).set(getTranslateX());
                    myController.getYPos(getID()).set(-getTranslateY());

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
            pt.setOnFinished(e -> setBusy(false));
            pt.play();
            finishMovement(x, y, l);
        }
        setBusy(false);
    }

    private PathTransition startPath() {
        PathTransition pt = new PathTransition();
        pt.setDuration(Duration.seconds(slideSpeed.get()));
        pt.setNode(this);
        return pt;
    }

    private void finishMovement(Double x, Double y, LineTo l) {
        myPath.getElements().clear();
        myPath.getElements().addAll(new MoveTo(l.getX(), l.getY()));
        setLastX(x);
        setLastY(y);
    }

    /**
     * Sets the pen up or down
     * @param pen true is up
     */
    public void setPen(boolean pen) {
        setBusy(true);
        myPen.setPenUp(pen);
        myController.getPenUp(getID()).set(pen);
        setBusy(false);
    }

    /**
     * Sets the pen color to a color
     * @param c new pen color
     */
    public void setPenColor(Color c) {
        setBusy(true);
        myPen.setColor(c);
        myController.getR(getID()).set((int) (c.getRed() * 255));
        myController.getG(getID()).set((int) (c.getGreen() * 255));
        myController.getB(getID()).set((int) (c.getBlue() * 255));
        setBusy(false);
    }

    /**
     * Sets the pen size
     * @param pixels new pen size in pixels
     */
    public void setPenSize(double pixels) {
        setBusy(true);
        myPen.setSize(pixels);
        myController.getPenThickness(getID()).set(pixels);
        setBusy(false);
    }

    /**
     * Changes display status of turtle
     * @param display true = displayed
     */
    public void setDisplayed(boolean display) {
        setVisible(display);
    }

    /**
     * Changes the image of the turtle
     * @param index index into the image array
     */
    public void setTurtleShape(int index) {
        String image = myImages.getString(TURTLE + index);
        setImage(new Image(getClass().getClassLoader().getResourceAsStream(image), IMAGE_HEIGHT, IMAGE_WIDTH, false, false));
    }
}
