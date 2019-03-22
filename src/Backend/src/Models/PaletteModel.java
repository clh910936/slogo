package Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author michaelzhang
 * Model that stores all palette information
 */
public class PaletteModel {

    Map<Integer, Palette> paletteMap;

    /**
     * Constructor - initializes map that stores palettes
     */
    public PaletteModel() {
        paletteMap = new HashMap<>();
    }

    /**
     * Adds a palette to the model
     * @param index
     * @param r
     * @param g
     * @param b
     */
    public void addPalette(int index, int r, int g, int b) {
        paletteMap.put(index, new Palette(r, g, b));
    }

    /**
     * Gets all colors in the model
     * @return
     */
    public Set<Integer> getColors() {
        return paletteMap.keySet();
    }

}
