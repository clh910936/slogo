package FrontInternal.Players;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView extends Sprite {
    private double myX;
    private double myY;

    public TurtleView(){
        setImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle_default.png")));
    }

    public void place(int i, int j) {
        setX(i - getBoundsInLocal().getWidth() / 2);
        setY(j - getBoundsInLocal().getHeight() / 2);

        myX = getX() + getBoundsInLocal().getWidth()/2;
        myY = getY() + getBoundsInLocal().getHeight()/2;
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

    public void setCenterXY(double x, double y) {
        myX = x;
        myY = y;
    }

    public double getCurrentX() {
        return getTranslateX() + getCenterX();
    }

    public double getCurrentY() {
        return getTranslateY() + getCenterY();
    }
}
