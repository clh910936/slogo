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

public class TurtleView extends Sprite {
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

    public TurtleView(Dimension d, GraphicsContext g, int id, SimpleDoubleProperty speed, IModelManager controller){
        super(id);
        myController = controller;
        String image = myImages.getString(TURTLE + myController.getTurtleImage(id));
        setImage(new Image(getClass().getClassLoader().getResourceAsStream(image), 75, 75, false, false));
        place(d.width/ 2, d.height/2);

        myPath = new Path();
        MoveTo m = new MoveTo(getCenterX(), getCenterY());
        myPath.getElements().add(m);

        gc = g;

        myPen = new Pen();
        isBusy = false;

        myScheduler = new TurtleScheduler(this);
        slideSpeed = speed;
        rotate(-90);
        setProperties();
    }

    private void setProperties() {
        myController.setXPos(getID(), new SimpleDoubleProperty(0));
        myController.setYPos(getID(), new SimpleDoubleProperty(0));
        //myController.setTurtleImage(getID(), 0);
        myController.setPenUp(getID(), new SimpleBooleanProperty(false));
        myController.setPenThickness(getID(), new SimpleDoubleProperty(Pen.DEFAULT_SIZE));
        myController.setR(getID(), new SimpleIntegerProperty(Pen.DEFAULT_R));
        myController.setG(getID(), new SimpleIntegerProperty(Pen.DEFAULT_G));
        myController.setB(getID(), new SimpleIntegerProperty(Pen.DEFAULT_B));
    }

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

    public TurtleScheduler getScheduler() {
        return myScheduler;
    }
    public void update() {
        myScheduler.update();
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

    public boolean getBusy() {
        return isBusy;
    }

    //TODO: REFACTOR THIS MASSIVE METHOD
    @Override
    public  void move(Double x, Double y) {
        setBusy(true);
        double xdisp = x - getLastX();
        double ydisp = y - getLastY();

        if (!(xdisp == 0 && ydisp == 0)) {
            PathTransition pt = new PathTransition();

            pt.setDuration(Duration.seconds(slideSpeed.get()));
            pt.setNode(this);

            LineTo l = new LineTo(getCenterX() + x, getCenterY() - y);

            //have to update turtle location after this
            myPath.getElements().addAll(l);
            pt.setPath(myPath);
            pt.setOrientation(PathTransition.OrientationType.NONE);
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
                    myController.getXPos(getID()).set(getTranslateX());
                    myController.getYPos(getID()).set(-getTranslateY());
                    System.out.println(myController.getYPos(getID()).get());

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
            myPath.getElements().clear();
            myPath.getElements().addAll(new MoveTo(l.getX(), l.getY()));
            setLastX(x);
            setLastY(y);
        }
        //setBusy(false);


    }

    public void setPen(boolean pen) {
        setBusy(true);
        myPen.setPenUp(pen);
        myController.getPenUp(getID()).set(pen);
        setBusy(false);
    }

    public void setPenColor(Color c) {
        setBusy(true);
        myPen.setColor(c);
        myController.getR(getID()).set((int) (c.getRed() * 255));
        myController.getG(getID()).set((int) (c.getGreen() * 255));
        myController.getB(getID()).set((int) (c.getBlue() * 255));
        setBusy(false);
    }

    public void setPenSize(double pixels) {
        setBusy(true);
        myPen.setSize(pixels);
        myController.getPenThickness(getID()).set(pixels);
        setBusy(false);
    }

    @Override
    public Node getPath() {
        return myPath;
    }

    public void setDisplayed(boolean display) {
        setVisible(display);
    }

    public void setTurtleShape(int index) {
        String image = myImages.getString(TURTLE + index);
        setImage(new Image(getClass().getClassLoader().getResourceAsStream(image), 75, 75, false, false));
    }
}
