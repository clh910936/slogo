package FrontInternal.Views;

import API.IModelManager;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author Carrie Hunner
 * This class creates a template for a user view window with a title at the top
 * This is used to format all of the views
 */
public abstract class View {
    protected IModelManager myManager;
    protected static final Paint DEFAULT_COLOR = Color.BLACK;
    private static final int INSET = 10;
    private static final Insets myInsets = new Insets(INSET, INSET, INSET, INSET);

    private VBox myVBox;
    private ScrollPane myVariableScroll;
    protected static final String DEFAULT_LANGUAGE = "English";
    private static final int DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_WIDTH = 250;



    /**
     * Used to create a template for Views
     */
    public View(IModelManager manager){
        myManager = manager;
        myVBox = new VBox();

        myVBox.setFillWidth(true);
        myVBox.setPrefHeight(DEFAULT_HEIGHT);
        myVBox.setMinWidth(DEFAULT_WIDTH);

        myVariableScroll = new ScrollPane();
        myVariableScroll.setFitToWidth(true);
        myVariableScroll.setFitToHeight(true);

        myVBox.getChildren().add(myVariableScroll);
        myVariableScroll.setBorder(Border.EMPTY);
    }


    /**
     * Needs to be called by any subclasses so the content is actually displayed
     * @param p Pane that contains the content to be displayed
     */
    protected void setContents(Pane p){
        p.setPrefHeight(DEFAULT_HEIGHT);
        p.setPrefWidth(myVBox.getWidth());
        p.setPadding(myInsets);
        myVariableScroll.setContent(p);
    }


    /**
     * Updates the contents of the pane
     */
    public abstract void update();

    /**
     * @return Pane of the view that can be used in the GUI
     */
    public Pane getPane() {
        return myVBox;
    }

    /**
     * Used to override the default height of the pane
     * @param height int of the height
     */
    public void setHeight(int height){
        myVBox.setPrefHeight(height);
        myVariableScroll.setPrefHeight(height);
    }
}
