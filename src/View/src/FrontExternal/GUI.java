package FrontExternal;

import java.awt.*;
import java.util.ResourceBundle;

import API.FrontExternalAPI;
import FrontInternal.Components.*;
import FrontInternal.Util.Operator;
import FrontInternal.Views.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import javax.imageio.ImageIO;

public class GUI implements FrontExternalAPI {
    private static final Dimension DEFAULT_SIZE = new Dimension(800,600);
    public static final String RESOURCE_FILENAME = "GUI";
    private Scene myScene;

    private Console myConsole;
    private Board myBoard;
    private HBox myRoot;

    private ResourceBundle myResources;
    private Operator myOperator;
    private AllUserViews myToolBar;


    // private
    public GUI() {
        myOperator = new Operator(this);
        myResources = ResourceBundle.getBundle(RESOURCE_FILENAME);
        var left = makeBoard();
        //var right = makeRightView();
        //myRoot = new HBox(left, right);
        myToolBar = new AllUserViews(myOperator);
        myOperator.addViewToUpdate(myBoard);
        myRoot = new HBox(left, myToolBar);
        //myRoot.setHgrow(right, Priority.ALWAYS);




        myScene = new Scene(myRoot, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }

    public Operator getOperator() {
        return myOperator;
    }


    private Node makeBoard() {
        myBoard = new Board(DEFAULT_SIZE.width * 3/4,DEFAULT_SIZE.height, myOperator);
        return new HBox(myBoard);
    }

//    private Node makeConsoleButton() {
//        var b = makeButton("OpenConsole", e -> openConsole(null));
//        b.disableProperty().bind(Bindings.createBooleanBinding(()-> myConsole.getDisplaying()));
//        return b;
//    }


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

    /**
     * THE FOLLOWING ARE TURTLE/BOARD COMMANDS, FEROZE WILL TAKE CARE OF THEM
     */
    @Override
    public void clearBoard() {
        myBoard.clear();
    }

    @Override
    public void setBackgroundColor(int index) {
        myBoard.setBackgroundColor(index);
    }

    @Override
    public void penUp(boolean true_is_penup, int turtleId) {
        myBoard.penUp(true_is_penup, turtleId);
    }

    @Override
    public void rotate(double degrees, int turtleId) {
        myBoard.rotate(degrees, turtleId);
    }

    @Override
    public void move(double x, double y, int turtleId) {
        myBoard.move(x, y, turtleId);
    }

    @Override
    public void setPenColor(int index, int turtleId) {
        myBoard.setPenColor(index, turtleId);
    }

    @Override
    public void setPenSize(double pixels, int turtleId) {
        myBoard.setPenSize(pixels, turtleId);
    }

    @Override
    public void setShape(int index, int turtleId) {
        myBoard.setShape(index, turtleId);
    }

    @Override
    public void setPalette(int index, int r, int g, int b) {
        myToolBar.setPalette(index, r, g, b);
    }

    @Override
    public void addTurtle(int turtleId) {
        myBoard.addTurtle(turtleId);
    }

    /**
     * TODO: @Carrie pls add these methods to ur AllUserViews which updates each specific view accordingly.
     */
    @Override
    public void updateHistory() {
        myToolBar.updateHistory();
    }

    @Override
    public void updateVariables() {
        myToolBar.updateVariables();
    }

    @Override
    public void updateUserDefinedCommands() {
        myToolBar.updateUserDefinedCommands();
    }
}
