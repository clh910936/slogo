package FrontInternal.Players;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class Sprite extends ImageView {
    /**
     * Current display node
     */
    public Node node;

    private int myId;

    public Sprite(int id) {
        myId = id;
    }

    /**
     * Updates this sprite object's animations.
     */
    public abstract void update();

    public abstract void move(double x, double y);

    public int getID() {
        return myId;
    }

    public abstract void setPen(boolean penUp);

    public abstract void rotate(double degrees);

    public abstract void setPenColor(int index);
}
