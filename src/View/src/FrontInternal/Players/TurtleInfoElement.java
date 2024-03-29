package FrontInternal.Players;

import FrontInternal.Util.ColorUtils;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.NumberStringConverter;
import java.util.ResourceBundle;

/**
 * A TurtleInfoElement is a widget found in the TurtleInfoView. It gives information on the turtle image, id,
 * position, and pen status in real time using JavaFX Properties.
 * @author Feroze
 */
public class TurtleInfoElement extends ViewElement {
    public static final int IMAGE_HEIGHT = 50;
    public static final int IMAGE_WIDTH = 50;
    public static final int IMAGE_ANGLE_OFFSET = -90;
    public static final int DEFAULT_HEIGHT = 150;
    private ResourceBundle myBundle;
    private ResourceBundle myImages;
    private ComboBox myComboBox;
    private final String TURTLE = "Turtle";

    /**
     * Creates a widget and populates its information according to the following parameters:
     * @param turtleID id of the turtle
     * @param imageindex index of the turtle image
     * @param x current x-coordinate
     * @param y current y-coordinate
     * @param r r-value of its color
     * @param g g-value of its color
     * @param b b-value of its color
     * @param penup pen up/down
     * @param thickness pen thickness
     */
    public TurtleInfoElement(int turtleID, int imageindex, SimpleDoubleProperty x, SimpleDoubleProperty y,
                             SimpleIntegerProperty r, SimpleIntegerProperty g, SimpleIntegerProperty b,
                             SimpleBooleanProperty penup, SimpleDoubleProperty thickness) {
        myBundle = getResourceBundle("TurtleInfoElement");
        myImages = getResourceBundle("TurtleImages");
        String image = myImages.getString(TURTLE+imageindex);
        var display = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(image), IMAGE_WIDTH, IMAGE_HEIGHT,
                false, false));
        display.setRotate(IMAGE_ANGLE_OFFSET);
        getChildren().add(display);
        String idlabel = myBundle.getString("ID") + turtleID;
        makeLabels(idlabel);
        makePositionLabels(x, y);
        makePenLabels(r, g,b, penup, thickness);
        makeComboBox();
        expand(DEFAULT_HEIGHT);

    }

    private void makeComboBox() {
        ObservableList<String> options = FXCollections.observableArrayList();
        myImages.keySet().forEach(e -> options.add(e));
        myComboBox = new ComboBox(options);
        myComboBox.getSelectionModel().selectFirst();
        getChildren().add(myComboBox);
    }

    /**
     * Returns the selected image choice from the dropdown menu
     * @return new image label to set to
     */
    public String getChoice() {
        return (String) myComboBox.getValue();
    }

    private void makePenLabels(SimpleIntegerProperty r, SimpleIntegerProperty g,
                               SimpleIntegerProperty b, SimpleBooleanProperty penup,
                               SimpleDoubleProperty thickness) {
        var colorname = Bindings.createStringBinding(() -> ColorUtils.getColorNameFromRgb(r.get(), g.get(),
                b.get()), r, g, b);
        var textcolor = Bindings.createObjectBinding(() -> Color.rgb(r.get(), g.get(), b.get()), r, g, b);
        Label color = new Label();
        color.textProperty().bind(colorname);
        color.textFillProperty().bind(textcolor);
        var pc = new TextFlow(new Text("Pen color: "), color);
        Label pu = new Label();
        pu.textProperty().bindBidirectional(penup, new BooleanStringConverter());
        var pu_text = new TextFlow(new Text("Pen up: "), pu);
        Label pt = new Label();
        pt.textProperty().bindBidirectional(thickness, new NumberStringConverter());
        var pt_text = new TextFlow(new Text("Pen thickness: "), pt);
        getChildren().addAll(pc, pu_text, pt_text);

    }

    private void makePositionLabels(SimpleDoubleProperty x, SimpleDoubleProperty y) {
        Label xpos = new Label();
        xpos.textProperty().bindBidirectional(x, new NumberStringConverter());
        Label ypos = new Label();
        ypos.textProperty().bindBidirectional(y, new NumberStringConverter());
        TextFlow tf = new TextFlow(new Text("Position: ("), xpos, new Text(", "), ypos, new Text(")"));
        getChildren().add(tf);
    }
}
