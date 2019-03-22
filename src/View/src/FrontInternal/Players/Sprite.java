package FrontInternal.Players;

import javafx.scene.image.ImageView;

public abstract class Sprite extends ImageView {

    private int myId;

    public Sprite(int id) {
        myId = id;
    }

    /**
     * Updates this sprite object's animations.
     */
    public abstract void update();


    protected int getID() {
        return myId;
    }

    public abstract TurtleScheduler getScheduler();


}
