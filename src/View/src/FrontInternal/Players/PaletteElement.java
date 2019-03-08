package FrontInternal.Players;

import FrontInternal.Util.ColorUtils;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PaletteElement extends ViewElement {
    public PaletteElement(int index, int r, int g, int b) {
        var rect = new Rectangle(75, 75, Color.rgb(r, g, b) );
        rect.setArcHeight(10);
        rect.setArcWidth(10);
        getChildren().add(rect);

        String indexLabel = getResourceBundle().getString("INDEX") + index;
        String colorLabel = getResourceBundle().getString("COLOR") + ColorUtils.getColorNameFromRgb(r, g, b);
        makeLabels(indexLabel, colorLabel);
    }
}
