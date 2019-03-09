package Models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PaletteModel {

    Map<Integer, Palette> paletteMap;

    public PaletteModel() {
        paletteMap = new HashMap<>();
    }

    public void addPalette(int index, int r, int g, int b) {
        paletteMap.put(index, new Palette(r, g, b));
    }

    public Set<Integer> getColors() {
        return paletteMap.keySet();
    }

}
