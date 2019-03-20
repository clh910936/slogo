package FrontInternal.Views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * @author Carrie Hunner
 * This class extends HBox and creates an error box to be used in a scene for the UI.
 * It can be used to display an error message to the user.
 */
public class ErrorView extends HBox {

    private Background myNoErrorBackground;
    private Background myErrorBackground;
    private Text myText;

    private static final int INSET_SIZE = 5;
    private static final int RADII_SIZE = 0;
    private static final Paint ERROR_COLOR = Color.RED;
    private static final Paint NO_ERROR_COLOR = Color.WHITE;
    private static final String EMPTY_TEXT = "";

    /**
     * @param height int of the preferred errorBox height
     */
    public ErrorView(int height){
        this.setPrefHeight(height);
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

    private Background createBackground(Paint color){
        Insets insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        CornerRadii radii = new CornerRadii(RADII_SIZE);

        BackgroundFill fill = new BackgroundFill(color, radii, insets);
        return new Background(fill);
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
     * Called after the console runs.
     */
    public void clearError(){
        myText.setText(EMPTY_TEXT);
        setErrorlessBackground();
    }
}
