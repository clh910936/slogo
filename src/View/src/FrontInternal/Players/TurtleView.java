package FrontInternal.Players;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView extends Sprite {
    private double myX;
    private double myY;
    private double myAngle;

    private double myLastX;
    private double myLastY;

    public TurtleView(){
        setImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle_default.png")));
    }

    public void place(int i, int j) {
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

    public double getCurrentX() {
        return getTranslateX() + getCenterX();
    }

    public double getCurrentY() {
        return getTranslateY() + getCenterY();
    }

    public void rotate(double angle) {
        setRotate(angle);
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
}
