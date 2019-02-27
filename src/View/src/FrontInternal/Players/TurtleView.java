package FrontInternal.Players;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TurtleView extends Sprite {
    public TurtleView(){
        setImage(new Image(getClass().getClassLoader().getResourceAsStream("turtle_default.png")));
    }

    public void place(int i, int j) {
        setX(i - getBoundsInLocal().getWidth() / 2);
        setY(j - getBoundsInLocal().getHeight() / 2);
    }

    public void update() {

    }

    public double getCenterX() {
        return getX() + getBoundsInLocal().getWidth()/2;
    }
    public double getCenterY() {
        return getY() + getBoundsInLocal().getHeight()/2;
    }
}
