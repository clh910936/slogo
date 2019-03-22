package FrontInternal.Players;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public abstract class Sprite extends ImageView {

    private int myId;

    public Sprite(int id) {
        myId = id;
    }

    /**
     * Updates this sprite object's animations.
     */
    public abstract void update();

    public abstract void move(Double x, Double y);

    protected int getID() {
        return myId;
    }

    public abstract void setPen(boolean penUp);

    public abstract void rotate(double degrees);

    public abstract TurtleScheduler getScheduler();


}
