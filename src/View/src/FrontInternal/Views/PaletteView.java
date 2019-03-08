package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Players.AddElement;
import FrontInternal.Players.PaletteElement;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.util.*;


public class PaletteView extends HorizontalView{
//    private ScrollPane myScrollPane;
//    private IModelManager myManager;
    private Pane myPane;
    private HBox myRoot;
    private Map<Integer, Color> ACTIVE_COLORS = new LinkedHashMap<>();
    private final int[][] DEFAULT_COLORS = {{202,31,31}, {2,2,2}, {245, 245, 220}};
    private static final String DEFAULT_LANGUAGE = "English";
    private AddElement addNewColor;

    //TODO: PALETTEVIEW HAS TO TELL THE BOARD ITS COLORS SOMEHOW
    public PaletteView(IModelManager manager) {
        super(manager);
        myRoot = new HBox();
        myPane = makeScrollPane(myRoot);
        //myPane.setMinHeight(210);
        //makeScrollPane();
        addDefaultColors();
        addNewColor = addPlus(myRoot, e->openInput());
        //addPlus();

        setContents(myPane);
        setHeight(250);

    }

//    private void addPlus() {
//        addNewColor = new AddElement(e -> openInput());
//        myRoot.getChildren().add(addNewColor);
//    }

    private void openInput() {
        TextInputDialog dialog = new TextInputDialog("22, 100, 245, 216");

        dialog.setTitle("Add Color");
        dialog.setHeaderText("Enter Index and RGB Values for New Color:");
        dialog.setContentText("Index, R, G, B: ");

        Optional<String> result = dialog.showAndWait();

        //TODO ACCOUNT FOR POORLY FORMATTED INPUT
        result.ifPresent(name -> {
            List<String> args = Arrays.asList(name.split(", "));
            addColor(Integer.parseInt(args.get(0)), Integer.parseInt(args.get(1)),
                    Integer.parseInt(args.get(2)), Integer.parseInt(args.get(3)));
        });
    }

    public Color getColor(int index) {
        if(ACTIVE_COLORS.keySet().contains(index)) {
            return ACTIVE_COLORS.get(index);
        }
        else {
            //TODO: ADD EXCEPTION
            return null;
        }
    }

    //
    private void addDefaultColors() {
        for (int i = 0; i < DEFAULT_COLORS.length; i++) {
            addColor(i, DEFAULT_COLORS[i][0], DEFAULT_COLORS[i][1], DEFAULT_COLORS[i][2]);
        }

    }

    public void addColor(int index, int r, int g, int b) {
        //remove button
        //TODO MAKE ADDING AND REMOVING THE BUTTON CLEANER
        if(addNewColor != null) {
            myRoot.getChildren().remove(addNewColor);
        }
        if (ACTIVE_COLORS.keySet().contains(index)) {
            //TODO: THROW EXCEPTION
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

//    private void makeScrollPane() {
//        myScrollPane = new ScrollPane();
//        myScrollPane.setFitToHeight(true);
//
//        myScrollPane.setContent(myRoot);
//        //myScrollPane.setMinViewportHeight(200);
//        var y = new HBox(myScrollPane);
//        y.setMinHeight(300);
//        //y.setMaxWidth(180);
//
//        myPane = y;
//    }

    @Override
    public void update() {
        return;
    }


}
