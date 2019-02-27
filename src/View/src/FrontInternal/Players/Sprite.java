package FrontInternal.Players;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class Sprite extends ImageView {
    /**
     * Current display node
     */
    public Node node;

    /**
     * Updates this sprite object's animations.
     */
    public abstract void update();

}
