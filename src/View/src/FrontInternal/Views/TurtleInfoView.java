package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Players.AddElement;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class TurtleInfoView extends HorizontalView {
    private HBox myRoot;
    private Pane myPane;
    private AddElement addNewTurtleButton;
    public TurtleInfoView(IModelManager manager) {
        super(manager);
        myRoot = new HBox();
        myPane = makeScrollPane(myRoot);
        addTurtles(manager);
        addNewTurtleButton = addPlus(myRoot, e->addNewTurtle());
        setContents(myPane);
    }

    private void addNewTurtle() {
    }

    private void addTurtles(IModelManager manager) {
    }

    @Override
    public void update() {
        return;
    }

    /* add a SimpleDoubleProperty to every turtle
       have a get simpledouble property given turtle index
       and a setsimpledouble property given turtle index

     */
}
