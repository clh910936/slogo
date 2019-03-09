package FrontInternal.Views;

import API.IModelManager;
import FrontInternal.Players.AddElement;
import FrontInternal.Players.TurtleInfoElement;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.Collections;

public class TurtleInfoView extends HorizontalView {
    private HBox myRoot;
    private Pane myPane;
    private AddElement addNewTurtleButton;
    private final String SETIMAGE = "setsh ";
    private final String DEFAULT_LANGUAGE = "English";
    private final String TELL = "tell [ %d ] ";
    public TurtleInfoView(IModelManager manager) {
        super(manager);
        myRoot = new HBox();
        myPane = makeScrollPane(myRoot);
        addTurtles(manager);
        setContents(myPane);
        setHeight(270);
        update();
    }

    private void addNewTurtle() {
        int newid = Collections.max(myManager.getTurtles()) + 1;
        myManager.parseCommand(String.format(TELL, newid), DEFAULT_LANGUAGE);
    }

    private void addTurtles(IModelManager manager) {
    }

    @Override
    public void update() {
        myRoot.getChildren().clear();
        for(int id: myManager.getTurtles()) {
            var te = new TurtleInfoElement(id, myManager.getTurtleImage(id), myManager.getXPos(id),
                    myManager.getYPos(id), myManager.getR(id), myManager.getG(id), myManager.getB(id),
                    myManager.getPenUp(id), myManager.getPenThickness(id));
            te.addButton("Change image", e->changeImage(id, te.getChoice()));
            myRoot.getChildren().add(te);
        }
        addNewTurtleButton = addPlus(myRoot, e->addNewTurtle());
    }

    private void changeImage(int id, String choice) {
        String tell = String.format(TELL, id);
        int index = Integer.parseInt(choice.substring(choice.length() - 1));
        myManager.parseCommand(tell + SETIMAGE + index, DEFAULT_LANGUAGE);
    }

}
