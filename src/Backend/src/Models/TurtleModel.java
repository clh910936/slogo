package Models;
import API.FrontExternalAPI;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;



/**
 * @author michaelzhang & Christina Chen
 * This model stores information about turtles
 */
public class TurtleModel {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    public static final boolean IS_PEN_UP = false;
    public static final double HEADING_ANGLE = 90;
    public static final boolean IS_DISPLAYED = true;
    public static final boolean CLEAR_SCREEN = false;
    public static final int PEN_COLOR_INDEX = 0;
    public static final double PEN_SIZE = 4.0;
    public static final int SHAPE_INDEX = 0;


    private List<Integer> currentActiveTurtles;
    private Map<Integer, Turtle> allTurtles;
    private double currentTurtleIndex;
    private FrontExternalAPI myFrontExternalAPI;

    /**
     * Constructor that initializes TurtleModel
     * @param frontExternalAPI
     */
    public TurtleModel(FrontExternalAPI frontExternalAPI) {
        myFrontExternalAPI = frontExternalAPI;
        allTurtles = new HashMap<>();

    }

    /**
     * Makes list of turtles the currently active ones
     * @param turtleIds
     */
    public void setCurrentActiveTurtles(List<Integer> turtleIds) {
        currentActiveTurtles = turtleIds;
        turtleIds.stream().
                filter(turtleId -> !allTurtles.containsKey(turtleId)).
                forEach(turtleId -> {
                    allTurtles.put(turtleId, new Turtle(STARTX, STARTY, IS_PEN_UP, HEADING_ANGLE,
                IS_DISPLAYED,CLEAR_SCREEN, turtleId, PEN_COLOR_INDEX, PEN_SIZE, SHAPE_INDEX, myFrontExternalAPI));
                    myFrontExternalAPI.addTurtle(turtleId);
                });
    }

    /**
     * Gets all turtles that have been initialized
     * @return map of all turtles
     */
    public Map<Integer, Turtle> getAllTurtles() {
        return allTurtles;
    }

    /**
     * Gets the current turtle as a Turtle object
     * @return index of current turtle
     */
    public Turtle getCurrentTurtle() {
        return allTurtles.get((int) currentTurtleIndex);
    }

    /**
     * Gets the current turtle's id
     * @param turtleId
     * @return id of current turtle
     */
    public Turtle getTurtle(int turtleId) {
        return allTurtles.get(turtleId);
    }

    /**
     * Gets a list of currently active turtle id's
     * @return list of current active turtles
     */
    public List<Integer> getCurrentActiveTurtles() {
        return Collections.unmodifiableList(currentActiveTurtles);
    }

    /**
     * Clears current active turtles
     */
    public void clearCurrentActiveTurtles() {
        currentActiveTurtles.clear();
    }

    /**
     * Setter for current turtle
     * @param index
     */
    public void setCurrentTurtle(int index) {
        currentTurtleIndex = index;
    }

    /**
     * Getter for current turtle
     * @return current turtle index
     */
    public double getCurrentTurtleIndex() {
        return currentTurtleIndex;
    }

    /**
     * Resets models back to only one turtle at center of board
     */
    public void populateBoard() {
        Turtle t = new Turtle(STARTX, STARTY, IS_PEN_UP, HEADING_ANGLE, IS_DISPLAYED,
                CLEAR_SCREEN, 1, PEN_COLOR_INDEX, PEN_SIZE, SHAPE_INDEX, myFrontExternalAPI);
        allTurtles.put(1, t);
        myFrontExternalAPI.addTurtle(1);
        currentActiveTurtles = new ArrayList<>();
        currentActiveTurtles.add(1);
        currentTurtleIndex = 1;
    }
}