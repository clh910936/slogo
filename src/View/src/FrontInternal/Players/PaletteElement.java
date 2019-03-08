package FrontInternal.Players;

import FrontInternal.Util.ColorUtils;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class PaletteElement extends ViewElement {
    private ResourceBundle myBundle;
    public PaletteElement(int index, int r, int g, int b) {
        var rect = new Rectangle(75, 75, Color.rgb(r, g, b) );
        myBundle = getResourceBundle("PaletteElement");
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        getChildren().add(rect);

        String indexLabel = myBundle.getString("INDEX") + index;
        String colorLabel = myBundle.getString("COLOR") + ColorUtils.getColorNameFromRgb(r, g, b);
        makeLabels(indexLabel, colorLabel);
    }
}
