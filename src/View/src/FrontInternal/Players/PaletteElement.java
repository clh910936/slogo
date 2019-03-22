package FrontInternal.Players;

import FrontInternal.Util.ColorUtils;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

/**
 * A PaletteElement is a widget found in the PaletteView.
 * @author Feroze
 */
public class PaletteElement extends ViewElement {
    public static final int WIDTH = 75;
    public static final int HEIGHT = 75;
    public static final int ARC_HEIGHT = 10;
    public static final int ARC_WIDTH = 10;
    private ResourceBundle myBundle;

    /**
     * Creates the widget, which is just a rectangle showing the color, a label for the index, and a label for the
     * color.
     * @param index Color index
     * @param r color r-value
     * @param g color g-value
     * @param b color b-value
     */
    public PaletteElement(int index, int r, int g, int b) {
        var rect = new Rectangle(WIDTH, HEIGHT, Color.rgb(r, g, b) );
        myBundle = getResourceBundle("PaletteElement");
        rect.setArcHeight(ARC_HEIGHT);
        rect.setArcWidth(ARC_WIDTH);
        getChildren().add(rect);

        String indexLabel = myBundle.getString("INDEX") + index;
        String colorLabel = myBundle.getString("COLOR") + ColorUtils.getColorNameFromRgb(r, g, b);
        makeLabels(indexLabel, colorLabel);
    }
}
