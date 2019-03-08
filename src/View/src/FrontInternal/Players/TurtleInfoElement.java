package FrontInternal.Players;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class TurtleInfoElement extends ViewElement {
    private ResourceBundle myBundle;
    public TurtleInfoElement(int turtleID, SimpleDoubleProperty x, SimpleDoubleProperty y) {
        myBundle = getResourceBundle("TurtleInfoElement");
        getChildren().add(new Rectangle(50, 50, Color.RED));
        Label l = new Label();
        getChildren().add(new Label());
        String idlabel = myBundle.getString("ID") + turtleID;
        String poslabel = myBundle.getString("POSITION") +
                String.format("(%f, %f)", x.doubleValue(), y.doubleValue());
        makeLabels(idlabel, poslabel);
    }
}
