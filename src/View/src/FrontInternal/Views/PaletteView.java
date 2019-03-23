package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Players.AddElement;
import FrontInternal.Players.PaletteElement;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import java.util.*;
import java.util.function.Predicate;

/**
 * PaletteView holds all the colors actively in play, with their indices and names. Also allows users to add a new
 * color to the palette.
 * @author Feroze
 */
public class PaletteView extends HorizontalView{
    private static final int DEFAULT_HEIGHT = 250;
    private Pane myPane;
    private HBox myRoot;
    private Map<Integer, Color> ACTIVE_COLORS = new LinkedHashMap<>();
    private final int[][] DEFAULT_COLORS = {{202,31,31}, {2,2,2}, {245, 245, 220}};
    private static final String DEFAULT_LANGUAGE = "English";
    private AddElement addNewColor;

    /**
     * Creates the view and its surrounding HBox.
     * @param manager backend instance to which it sends commands
     */
    public PaletteView(IModelManager manager) {
        super(manager);
        myRoot = new HBox();
        myPane = makeScrollPane(myRoot);
        addDefaultColors();
        addNewColor = addPlus(myRoot, e->openInput());

        setContents(myPane);
        setHeight(DEFAULT_HEIGHT);
    }


    private void openInput() {
        TextInputDialog dialog = new TextInputDialog("22, 100, 245, 216");

        dialog.setTitle("Add Color");
        dialog.setHeaderText("Enter Index and RGB Values for New Color:");
        dialog.setContentText("Index, R, G, B: ");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            List<String> args = Arrays.asList(name.split(", "));
            Predicate<Integer> validcolor = e -> (e >= 0 && e <= 255);
            Predicate<Integer> validid = e -> !myManager.getColors().contains(e);
            int arg1 = validid.test(Integer.parseInt(args.get(0))) ? Integer.parseInt(args.get(0)) :
                    Collections.max(myManager.getColors()) + 1;
            int arg2 = validcolor.test(Integer.parseInt(args.get(1))) ? Integer.parseInt(args.get(1)) : 0;
            int arg3 = validcolor.test(Integer.parseInt(args.get(2))) ? Integer.parseInt(args.get(2)) : 0;
            int arg4 = validcolor.test(Integer.parseInt(args.get(3))) ? Integer.parseInt(args.get(3)) : 0;
            addColor(arg1, arg2, arg3, arg4);
        });
    }

    /**
     * Return the color with the corresponding index
     * @param index index to query
     * @return Color at index
     */
    public Color getColor(int index) {
        if(ACTIVE_COLORS.keySet().contains(index)) {
            return ACTIVE_COLORS.get(index);
        }
        else {
            return Color.BLUE;
        }
    }

    private void addDefaultColors() {
        for (int i = 0; i < DEFAULT_COLORS.length; i++) {
            addColor(i, DEFAULT_COLORS[i][0], DEFAULT_COLORS[i][1], DEFAULT_COLORS[i][2]);
        }

    }

    /**
     * Adds a color from RGB values with corresponding index, checking if the index already exists and if the r, g,
     * and b values are between 0 and 255.
     * @param index new color index
     * @param r new color r-value
     * @param g new color g-value
     * @param b new color b-value
     */
    public void addColor(int index, int r, int g, int b) {
        if(addNewColor != null) {
            myRoot.getChildren().remove(addNewColor);
        }
        if (ACTIVE_COLORS.keySet().contains(index)) {
            return;
        }
        ACTIVE_COLORS.put(index, Color.rgb(r, g, b));
        PaletteElement p = new PaletteElement(index, r, g, b);
        p.addButton("SET BG", e-> myManager.parseCommand("SETBG " + index, DEFAULT_LANGUAGE));
        p.addButton("SET PC", e -> myManager.parseCommand("SETPC " + index, DEFAULT_LANGUAGE));
        myManager.addPalette(index, r, g, b);
        myRoot.getChildren().add(p);
        //add button
        if (addNewColor != null) {
            myRoot.getChildren().add(addNewColor);
        }
    }

    @Override
    public void update() {
        // doesn't have to update
    }


}
