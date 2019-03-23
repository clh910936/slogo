package FrontExternal;

import java.awt.*;
import java.util.ResourceBundle;

import API.FrontExternalAPI;
import API.IModelManager;
import BackExternal.ModelManager;
import FrontInternal.Components.*;
import FrontInternal.Views.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

import javax.imageio.ImageIO;

/**
 * The GUI class holds all active players of the display window, including the Board and user views. The GUI can be
 * thought of as a workspace, so multiple workspaces are implemented by simply creating more instances of GUI. It is
 * a wrapper class and is flexible to the addition of new components - they just have to be added in the main HBox.
 * @author Feroze
 */
public class GUI implements FrontExternalAPI {
    private static final Dimension DEFAULT_SIZE = new Dimension(800,600);
    public static final double CONSOLE_WIDTH_ADJUSTMENT = 0.8;
    public static final int CONSOLE_HEIGHT_ADJUSTMENT = 4;
    public static final double BOARD_WIDTH_FACTOR = 0.75;
    private Scene myScene;

    private Console myConsole;
    private Board myBoard;
    private HBox myRoot;

    private ModelManager myController;
    private AllUserViews myToolBar;


    /**
     * Constructor of the GUI. Creates a board and all the user views, then attaches them. It also
     * instantiates a backend instance which it uses to keep state information and parse commands.
     * @param console Each GUI (instance of a workspace) requires a console to which it is attached,
     *                which it receives commands from.
     */
    public GUI(Console console) {
        var left = makeBoard();
        myController = new ModelManager(this);
        myController.populateBoard();
        myConsole = console;
        myToolBar = new AllUserViews(myController, myConsole);
        myRoot = new HBox(left, myToolBar);
        showConsole();
        myScene = new Scene(myRoot, DEFAULT_SIZE.width, DEFAULT_SIZE.height);
    }


    /**
     * @return Returns the backend associated with this workspace, because each workspace has a unique state
     * configuration.
     */
    public IModelManager getModelManager() {
        return myController;
    }

    private void showConsole() {
        myConsole.show();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        myConsole.setX((primScreenBounds.getWidth() - myConsole.getWidth()/ CONSOLE_WIDTH_ADJUSTMENT) );
        myConsole.setY((primScreenBounds.getHeight() - myConsole.getHeight()) / CONSOLE_HEIGHT_ADJUSTMENT);
    }

    private Node makeBoard() {
        myBoard = new Board(new Dimension((int) (DEFAULT_SIZE.width * BOARD_WIDTH_FACTOR), DEFAULT_SIZE.height), myController);
        return new HBox(myBoard);
    }

    /**
     * @return Returns the front-end representation of the workspace.
     */
    public Pane getPane() {
        return myRoot;
    }


    /**
     * @return Returns the main scene this workspace uses.
     */
    public Scene getScene() {
        return myScene;
    }

    /**
     * Clears everything on the Board and removes all turtles, then places a turtle in the center.
     */
    @Override
    public void clearBoard() {
        myBoard.clear();
    }

    /**
     * Sets the background color of the Board
     * @param index The index is the index into the color array.
     */
    @Override
    public void setBackgroundColor(int index) {
        for (View v: myToolBar.getViews()) {
            if (v instanceof PaletteView) {
                Color c = ((PaletteView) v).getColor(index);
                myBoard.setBackgroundColor(c);
            }
        }
    }

    /**
     * Sets the specified turtle to have pen up or down.
     * @param true_is_penup boolean for up or down
     * @param turtleId id of the turtle
     */
    @Override
    public void penUp(boolean true_is_penup, int turtleId) {
        myBoard.penUp(true_is_penup, turtleId);
    }

    /**
     * rotate the turtle of given id
     * @param degrees rotation angle
     * @param turtleId specified turtle id
     */
    @Override
    public void rotate(double degrees, int turtleId) {
        myBoard.rotate(degrees, turtleId);
    }

    /**
     * Moves a given turtle to the coordinates on a screen.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param turtleId specified turtle id
     */
    @Override
    public void move(double x, double y, int turtleId) {
        myBoard.move(x, y, turtleId);
    }

    /**
     * Sets the pen color of a turtle
     * @param index index of the color in the list
     * @param turtleId specified turtle id
     */
    @Override
    public void setPenColor(int index, int turtleId) {
        for (View v: myToolBar.getViews()) {
            if (v instanceof PaletteView) {
                Color c = ((PaletteView) v).getColor(index);
                myBoard.setPenColor(c, turtleId);
            }
        }
    }

    /**
     * Sets the pen size of a given turtle
     * @param pixels pen size in pixels
     * @param turtleId specified turtle id
     */
    @Override
    public void setPenSize(double pixels, int turtleId) {
        myBoard.setPenSize(pixels, turtleId);
    }

    /**
     * Sets the image of the turtle
     * @param index index into the list of images
     * @param turtleId specified turtle id
     */
    @Override
    public void setShape(int index, int turtleId) {
        myBoard.setTurtleShape(index, turtleId);
    }

    /**
     * Sets the display status of a given turtle
     * @param isDisplayed boolean true if to be displayed, false if hidden
     * @param turtleId specified turtle id
     */
    @Override
    public void setDisplayTurtle(boolean isDisplayed, int turtleId) {
        myBoard.setDisplayed(isDisplayed, turtleId);
    }

    /**
     * Adds a color to the palette
     * @param index index of the new color
     * @param r r-value of color
     * @param g g-value of color
     * @param b b-value of color
     */
    @Override
    public void setPalette(int index, int r, int g, int b) {
        for (View v: myToolBar.getViews()) {
            if (v instanceof PaletteView) {
                ((PaletteView) v).addColor(index, r,g,b);
            }
        }
    }

    /**
     * Adds a turtle to the display
     * @param turtleId id of the new turtle
     */
    @Override
    public void addTurtle(int turtleId) {
        myBoard.addTurtle(turtleId, myController);
    }

    /**
     * Update method that forces the views to query the backend after a change occurs.
     */
    @Override
    public void updateViews(){myToolBar.update();}
}
