package FrontInternal.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

/**
 * @author Carrie Hunner
 * This class extends HBox and creates an error box to be used in a scene for the UI.
 * It can be used to display an error message to the user.
 */
public class ErrorPane extends HBox {

    private Background myNoErrorBackground;
    private Background myErrorBackground;
    private Text myText;

    private final int INSET_SIZE = 5;
    private final int RADII_SIZE = 0;
    private final Paint ERROR_COLOR = Color.RED;
    private final Paint NO_ERROR_COLOR = Color.WHITE;
    private final String EMPTY_TEXT = "";

    /**
     * @param width int of the preferred errorBox width
     * @param height int of the preferred errorBox height
     */
    ErrorPane(int width, int height){
        this.setPrefHeight(height);
        this.setPrefWidth(width);
        initializeBackgrounds();
        initializeText();
    }

    private void initializeText() {
        myText = new Text();
        this.getChildren().add(myText);
        this.setAlignment(Pos.CENTER);
    }

    private void initializeBackgrounds() {
        myNoErrorBackground = createBackground(NO_ERROR_COLOR);
        myErrorBackground = createBackground(ERROR_COLOR);
        setErrorlessBackground();
    }

    private void initializeBorders(){
        this.setBorder(Border.EMPTY);
    }

    private Background createBackground(Paint color){
        Insets insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        CornerRadii radii = new CornerRadii(RADII_SIZE);

        BackgroundFill fill = new BackgroundFill(color, radii, insets);
        Background background = new Background(fill);
        return background;
    }

    private void setErrorlessBackground(){
        this.setBackground(myNoErrorBackground);
    }

    private void setErrorBackground(){
        this.setBackground(myErrorBackground);
    }

    /**
     * Changes the background color of the error box and sets the text of the
     * error to be displayed to the user
     * @param error String of the error to be displayed
     */
    public void displayError(String error){
        myText.setText(error);
        setErrorBackground();
    }

    /**
     * Changes the background color of the error box and
     * clears the error text being displayed.
     *Called after the console runs.
     */
    public void clearError(){
        myText.setText(EMPTY_TEXT);
        setErrorlessBackground();
    }
}
