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
 * and the easy ability to add individual lines of text as well as clear the text.
 *
 * It extends BorderPane such that it is a Pane object that can be easily addded to a scene.
 *
 * This is used to format the HistoryView, VariablesView,
 * and UserDefinedCommandsView
 */
public abstract class View implements ViewAPI {
    protected IModelManager myManager;
    protected static final Paint DEFAULT_COLOR = Color.BLACK;
    private Insets myInsets = new Insets(10, 10, 10, 10);

    private VBox myVBox;
    private ScrollPane myVariableScroll;
    protected static final String DEFAULT_LANGUAGE = "English";
    private static final int HEIGHT = 100;



    /**
     * Used to create a template for the Variable,User Defined Commands, and History view
     */
    public View(IModelManager manager){
        myManager = manager;
        myVBox = new VBox();

        myVBox.setFillWidth(true);
        myVBox.setPrefHeight(HEIGHT);

        myVariableScroll = new ScrollPane();
        myVariableScroll.setFitToWidth(true);
        myVariableScroll.setFitToHeight(true);

        myVBox.getChildren().add(myVariableScroll);
        myVariableScroll.setBorder(Border.EMPTY);
    }





    protected void setContents(Pane p){
        p.setPrefHeight(HEIGHT);
        p.setPrefWidth(myVBox.getWidth());
        p.setPadding(myInsets);
        myVariableScroll.setContent(p);
    }



    @Override
    public abstract void update();

    @Override
    public Pane getPane() {
        return myVBox;
    }
}
