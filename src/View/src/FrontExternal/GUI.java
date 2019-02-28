package FrontExternal;

import java.awt.Dimension;
import java.util.ResourceBundle;

import BackExternal.IModelManager;
import FrontInternal.Components.Board;
import FrontInternal.Components.Console;
import FrontInternal.Components.ListView;
import FrontInternal.Components.MapView;
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
    private ListView myListView;
    private MapView myMapView;
    private Console myConsole;
    private Board myBoard;
    private HBox myRoot;

    private ResourceBundle myResources;

    // private
    public GUI(IModelManager controller) {
        myResources = ResourceBundle.getBundle(RESOURCE_FILENAME);
        var left = makeBoard();
        var right = makeRightView();
        myRoot = new HBox(left, right);
        myRoot.setHgrow(right, Priority.ALWAYS);

        myScene = new Scene(myRoot, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }

    private Node makeRightView() {
        var x = new TextField();
        var y = new TextField();
        return new VBox(makeListView(),
                makeMapView(),
                x, y,
                makeButton("Move", e -> myBoard.move(Integer.parseInt(x.getText()),
                        Integer.parseInt(y.getText()))));
    }

    private Node makeBoard() {
        myBoard = new Board(DEFAULT_SIZE.width * 3/4,DEFAULT_SIZE.height);
        return new HBox(myBoard);
    }

    private Node makeConsoleButton() {
        var b = makeButton("OpenConsole", e -> openConsole(null));
        b.disableProperty().bind(Bindings.createBooleanBinding(()-> myConsole.getDisplaying()));
        return b;
    }

    private void openConsole(IModelManager b) {
        myConsole = new Console(b);
    }


    private Node makeMapView() {
        myMapView = new MapView("Variables");
        return myMapView.getPane();
    }

    private Node makeListView() {
        myListView = new ListView("History");
        return myListView.getPane();
    }


    // might not need this
    public void beginLoop() {
        return;
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

}
