package Models;
import API.FrontExternalAPI;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.ArrayList;



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

    public TurtleModel(FrontExternalAPI frontExternalAPI) {
        myFrontExternalAPI = frontExternalAPI;
        allTurtles = new HashMap<>();

    }

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

    public Map<Integer, Turtle> getAllTurtles() {
        return allTurtles;
    }

    public Turtle getCurrentTurtle() {
        return allTurtles.get((int) currentTurtleIndex);
    }

    public Turtle getTurtle(int turtleId) {
        return allTurtles.get(turtleId);
    }

    public List<Integer> getCurrentActiveTurtles() {
        return Collections.unmodifiableList(currentActiveTurtles);
    }

    public void clearCurrentActiveTurtles() {
        currentActiveTurtles.clear();
    }

    public void setCurrentTurtle(int index) {
        currentTurtleIndex = index;
    }

    public double getCurrentTurtleIndex() {
        return currentTurtleIndex;
    }


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