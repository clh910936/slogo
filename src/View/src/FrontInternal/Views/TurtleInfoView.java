package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Players.AddElement;
import FrontInternal.Players.TurtleInfoElement;
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
        setContents(myPane);
        setHeight(210);
        update();
    }

    private void addNewTurtle() {
    }

    private void addTurtles(IModelManager manager) {
    }

    @Override
    public void update() {
        myRoot.getChildren().clear();
        for(int id: myManager.getTurtles()) {
            myRoot.getChildren().add(new TurtleInfoElement(id, myManager.getXPos(id), myManager.getYPos(id)));
        }
        addNewTurtleButton = addPlus(myRoot, e->addNewTurtle());
    }

    /* add a SimpleDoubleProperty to every turtle
       have a get simpledouble property given turtle index
       and a setsimpledouble property given turtle index

     */
}
