package FrontInternal.Players;
import FrontInternal.Components.Board;
import javafx.beans.property.SimpleDoubleProperty;


/**
 * Sprite manager is responsible for holding all sprite objects (like turtles), and cleaning up
 * sprite objects to be removed. IDEALLY WILL IMPLEMENT AN INTERFACE
 * @author Feroze
 */

//TODO: REFACTOR SO THAT SPRITE DOESNT HAVE TO HAVE EVERYTHING
public class TurtleManager extends SpriteManager {
    private Board myBoard;
    private SimpleDoubleProperty slideSpeed;

    public TurtleManager(Board b, SimpleDoubleProperty speed) {
        myBoard = b;
        slideSpeed = speed;
        //addSprites(new TurtleView(myBoard.getDimensions(), myBoard.getGC(), 0));



    }

    /**
     * VarArgs of sprite objects to be added to the game.
     * @param sprites
     */
    public void addSprites(Sprite... sprites) {
        myBoard.getChildren().addAll(sprites);
        for (Sprite s: sprites) {
            GAME_ACTORS.put(s.getID(), s);
        }
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

    public void setPenColor(int index, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, index);
    }

    public void setPenSize(double pixels, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, pixels);
    }

    public void setTurtleShape(int index, int turtleId) {
    }

    public void addTurtle(int turtleId) {
        addSprites(new TurtleView(myBoard.getDimensions(), myBoard.getGC(), turtleId, slideSpeed));
    }

    public void update() {
        GAME_ACTORS.values().forEach(Sprite::update);
    }
}
