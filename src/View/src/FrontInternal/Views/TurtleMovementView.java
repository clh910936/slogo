package FrontInternal.Views;


import API.IModelManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.ResourceBundle;

/**
 * @author Carrie Hunner
 * Creates a view to be added to the Accordion view of
 * User Views. Creates buttons that allow the
 * turtle to be moved using UI controls
 */
public class TurtleMovementView extends View {
    private GridPane myGridPane;
    private ResourceBundle myResources;
    private static final int BUTTON_WIDTH = 50;
    private static final int BUTTON_HEIGHT = 50;
    private static final String ARROW_IMAGE = "/arrow.png";
    private static final String RIGHT_ROTATE = "/right_rotate.png";
    private static final String LEFT_ROTATE = "/left_rotate.png";
    private static final int ROTATE_90 = 90;
    private static final int ROTATE_180 = 180;
    private static final int ROTATE_270 = 270;
    private static final String DEFAULT_LANGAUGE = "English";
    private static final int HEIGHT = 175;



    public TurtleMovementView(IModelManager manager){
        super(manager);
        myGridPane = new GridPane();
        myResources = ResourceBundle.getBundle("ViewBackgroundCommands");
        setContents(myGridPane);
        createAndAddControls();
        myGridPane.setMinHeight(HEIGHT);
    }

    private void createAndAddControls() {
        myGridPane.add(createFormatArrow(0, ARROW_IMAGE, "forward"), 1, 0);
        myGridPane.add(createFormatArrow(ROTATE_270, ARROW_IMAGE, "left"), 0, 1);
        myGridPane.add(createFormatArrow(ROTATE_90, ARROW_IMAGE, "right"), 2, 1);
        myGridPane.add(createFormatArrow(ROTATE_180, ARROW_IMAGE, "backward"), 1, 2);
        myGridPane.add(createFormatArrow(0, RIGHT_ROTATE, "rotateRight"), 2, 0);
        myGridPane.add(createFormatArrow(0, LEFT_ROTATE, "rotateLeft"), 0, 0);
    }

    private void handleClick(String resourceKey) {
        String command = myResources.getString(resourceKey);
        myManager.parseCommand(command, DEFAULT_LANGAUGE);
    }

    private Pane createFormatArrow(int rotation, String imageName, String command){
        Image arrow = new Image(imageName);
        ImageView image = new ImageView(arrow);
        image.setFitHeight(BUTTON_HEIGHT);
        image.setFitWidth(BUTTON_WIDTH);
        image.setRotate(rotation);
        Pane temp = new Pane();
        temp.getChildren().add(image);
        temp.setOnMouseClicked(e -> handleClick(command));
        return temp;
    }

    @Override
    public void update() {

    }

    @Override
    public Pane getPane() {
        return myGridPane;
    }
}
