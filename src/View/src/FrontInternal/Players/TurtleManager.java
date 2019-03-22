package FrontInternal.Players;
import API.IModelManager;
import FrontInternal.Components.Board;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;



/**
 * Turtle manager is responsible for holding all sprite objects (like turtles), and cleaning up
 * sprite objects to be removed.
 * @author Feroze
 */

public class TurtleManager {
    private Board myBoard;
    private SimpleDoubleProperty slideSpeed;
    private Map<Integer, TurtleView> GAME_ACTORS;
    private IModelManager myController;

    /**
     * Creates a turtle manager with speed and associates it with a board.
     * @param b Board upon which to act
     * @param speed speed at which turtles move
     * @param controller backend instance
     */
    public TurtleManager(Board b, SimpleDoubleProperty speed, IModelManager controller) {
        myBoard = b;
        GAME_ACTORS = new HashMap<>();
        slideSpeed = speed;
        myController = controller;
    }

    private void addSprites(TurtleView... sprites) {
        myBoard.getChildren().addAll(sprites);
        for (TurtleView t: sprites) {
            GAME_ACTORS.put(t.getID(), t);
        }
    }

    protected Sprite get(int turtleId) {
        return GAME_ACTORS.get(turtleId);
    }

    /**
     * The following public methods are used to change the turtle properties by adding the action
     * to the turtle's scheduler. See `TurtleView.java` or `TurtleScheduler.java` for details about the scheduler.
     */
    public void move(double x, double y, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, x, y);
    }

    public void setPen(boolean penUp, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, penUp);
    }

    public void rotate(double degrees, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, -degrees);
    }

    public void setPenColor(Color c, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, c);
    }

    public void setPenSize(double pixels, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, pixels);
    }

    public void setTurtleShape(int index, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, index);
    }

    public void addTurtle(int turtleId, IModelManager controller) {
        var t = new TurtleView(myBoard.getDimensions(), myBoard.getGC(), turtleId, slideSpeed, controller);
        addSprites(t);
        t.setImageProp();
    }

    public void setDisplayed(boolean isDisplayed, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, isDisplayed);
    }

    /**
     * @return all the turtles currently on the screen
     */
    public Collection<TurtleView> getAllTurtles() {
        return GAME_ACTORS.values();
    }

    /**
     * Resets the list of turtles on the screen
     */
    public void clearTurtles() {
        GAME_ACTORS = new HashMap<>();
    }

    /**
     * Updates each turtle on the screen
     */
    public void update() {
        GAME_ACTORS.values().forEach(Sprite::update);
    }

}
