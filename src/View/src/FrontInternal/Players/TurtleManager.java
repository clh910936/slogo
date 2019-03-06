package FrontInternal.Players;
import FrontInternal.Components.Board;
import javafx.animation.PathTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


/**
 * Sprite manager is responsible for holding all sprite objects (like turtles), and cleaning up
 * sprite objects to be removed. IDEALLY WILL IMPLEMENT AN INTERFACE
 * @author Feroze
 */

//TODO: REFACTOR SO THAT SPRITE DOESNT HAVE TO HAVE EVERYTHING
public class TurtleManager extends SpriteManager {
    private Board myBoard;

    public TurtleManager(Board b) {
        myBoard = b;
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
        get(turtleId).setPen(penUp);
    }

    public void rotate(double degrees, int turtleId) {
        String name = new Object(){}.getClass().getEnclosingMethod().getName();
        get(turtleId).getScheduler().addToSchedule(name, degrees);
    }

    public void setPenColor(int index, int turtleId) {
        get(turtleId).setPenColor(index);
    }

    public void setPenSize(double pixels, int turtleId) {
    }

    public void setTurtleShape(int index, int turtleId) {
    }

    public void addTurtle(int turtleId) {
        addSprites(new TurtleView(myBoard.getDimensions(), myBoard.getGC(), turtleId));
    }

    public void update() {
        GAME_ACTORS.values().forEach(Sprite::update);
    }
}
