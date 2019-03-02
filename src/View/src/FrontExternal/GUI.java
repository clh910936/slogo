package FrontExternal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import BackExternal.IModelManager;
import FrontInternal.Components.*;
import FrontInternal.Util.Operator;

import FrontInternal.Views.*;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import javax.imageio.ImageIO;

public class GUI {
    private static final Dimension DEFAULT_SIZE = new Dimension(800,600);
    public static final String RESOURCE_FILENAME = "GUI";
    private Scene myScene;
    private HistoryView history;
    private VariableView variables;
    private UserDefinedCommandsView commands;

    private List<ViewAPI> views = new ArrayList<>();

    private Console myConsole;
    private Board myBoard;
    private HBox myRoot;

    private ResourceBundle myResources;
    private Operator myOperator;



    public GUI(Operator operator) {
        myOperator = operator;
        myResources = ResourceBundle.getBundle(RESOURCE_FILENAME);
        var left = makeBoard();
        //var right = makeRightView();
        //myRoot = new HBox(left, right);
        var right = new AllUserViews(myOperator);
        myOperator.addViewToUpdate(myBoard);
        myRoot = new HBox(left, right);
        //myRoot.setHgrow(right, Priority.ALWAYS);




        myScene = new Scene(myRoot, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }


    public IModelManager getModelManager() {
        return myOperator.getManager();
    }

    private Node makeBoard() {
        myBoard = new Board(DEFAULT_SIZE.width * 3/4,DEFAULT_SIZE.height, myOperator);
        return new HBox(myBoard);
    }

    private Node makeConsoleButton() {
        var b = makeButton("OpenConsole", e -> openConsole(null));
        //b.disableProperty().bind(Bindings.createBooleanBinding(()-> myConsole.getDisplaying()));
        return b;
    }

    private void openConsole(IModelManager b) {
        myConsole = new Console(myOperator);
    }


    public Scene getScene() {
        return myScene;
    }

    // makes a button using either an image or a label
    private Button makeButton (String property, EventHandler<ActionEvent> handler) {
        // represent all supported image suffixes
        final var IMAGEFILE_SUFFIXES =
                String.format(".*\\.(%s)", String.join("|", ImageIO.getReaderFileSuffixes()));

        var result = new Button();
        var label = myResources.getString(property);
        if (label.matches(IMAGEFILE_SUFFIXES)) {
            result.setGraphic(new ImageView(
                    new Image(getClass().getResourceAsStream(label))));
        }
        else {
            result.setText(label);
        }
        result.setOnAction(handler);
        return result;
    }

//    public void update() {
//        for (ViewAPI v: views) {
//            v.update();
//        }
//    }
}
