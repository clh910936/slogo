package FrontInternal.Players;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import java.util.ResourceBundle;

/**
 * A ViewElement is the basic widget found in some UserViews.
 * @author Feroze
 */
public abstract class ViewElement extends VBox{

    public static final int MIN_HEIGHT = 100;
    public static final int MIN_WIDTH = 100;
    public static final int INSET_TOP = 10;
    public static final int INSET_BOTTOM = 10;
    public static final int MIN_BUTTON_WIDTH = 50;

    /**
     * Creates a view element with default size parameters
     */
    public ViewElement() {
        setMinHeight(MIN_HEIGHT);
        setMinWidth(MIN_WIDTH);
        setAlignment(Pos.TOP_CENTER);
        setPadding(new Insets(INSET_TOP, INSET_BOTTOM, 0, 0));
    }

    protected void expand(int h) {
        setMinHeight(h);
    }

    protected ResourceBundle getResourceBundle(String s) {
        return ResourceBundle.getBundle(s);
    }

    protected void makeLabels(String... labels) {
        for (String s: labels) {
            getChildren().add(new Label(s));
        }
    }

    /**
     * Adds a button to the view element with some function
     * @param label button label
     * @param e action on button press
     */
    public void addButton(String label, EventHandler<ActionEvent> e) {
        var b = new Button(label);
        b.setMinWidth(MIN_BUTTON_WIDTH);
        b.setOnAction(e);
        getChildren().add(b);
    }

}
