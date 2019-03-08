package FrontInternal.Players;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class TurtleInfoElement extends ViewElement {
    private ResourceBundle myBundle;
    public TurtleInfoElement(int turtleID) {
        myBundle = getResourceBundle("TurtleInfoElement");
        getChildren().add(new Rectangle(50, 50, Color.RED));
    }
}
