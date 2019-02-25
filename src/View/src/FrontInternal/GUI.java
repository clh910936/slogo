package FrontInternal;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GUI {
    private static final int[] DIMENSIONS = new int[]{800,600};
    private String myName;
    private Group myRoot;


    public GUI(String name, Stage stage) {
        myName = name;
        myRoot = new Group();
        initialize(stage);
    }

    private void initialize(Stage stage) {
        var emptyScene = new Scene(myRoot, DIMENSIONS[0], DIMENSIONS[1], Color.BLACK);
        stage.setScene(emptyScene);
        stage.setTitle(myName);
        stage.show();

        new Console();
    }

    // might not need this
    public void beginLoop() {
        return;
    }

    public Scene getScene() {
    }
}
