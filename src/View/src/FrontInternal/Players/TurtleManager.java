package FrontInternal.Players;
import API.IModelManager;
import FrontInternal.Components.Board;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Sprite manager is responsible for holding all sprite objects (like turtles), and cleaning up
 * sprite objects to be removed. IDEALLY WILL IMPLEMENT AN INTERFACE
 * @author Feroze
 */

//TODO: REFACTOR SO THAT SPRITE DOESNT HAVE TO HAVE EVERYTHING
public class TurtleManager {
    private Board myBoard;
    private SimpleDoubleProperty slideSpeed;
    private Map<Integer, TurtleView> GAME_ACTORS = new HashMap<>();
    private IModelManager myController;

    public TurtleManager(Board b, SimpleDoubleProperty speed, IModelManager controller) {
        myBoard = b;
        slideSpeed = speed;
        myController = controller;



    }

    /**
     * VarArgs of sprite objects to be added to the game.
     * @param sprites
     */
    public void addSprites(TurtleView... sprites) {
        myBoard.getChildren().addAll(sprites);
        for (TurtleView t: sprites) {
            GAME_ACTORS.put(t.getID(), t);
        }
    }

    protected Sprite get(int turtleId) {
        return GAME_ACTORS.get(turtleId);
    }

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

    public Collection<TurtleView> getAllTurtles() {
        return GAME_ACTORS.values();
    }
    public void clearTurtles() {
        GAME_ACTORS = new HashMap<>();
    }

    public void update() {
        GAME_ACTORS.values().forEach(Sprite::update);
    }

    public void setDisplayed(boolean isDisplayed, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, isDisplayed);
    }
}
