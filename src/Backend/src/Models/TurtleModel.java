//package Models;
//
//import BackExternal.ITurtle;
//
//import java.util.ArrayList;
//import java.util.ListInput;
//
//public class TurtleModel {
//
//    int currentTurtleIndex;
//    ListInput<ITurtle> listOfTurtles;
//
//    public TurtleModel(double firstTurtleX, double firstTurtleY, boolean isPenUp, double headingAngle, boolean isDisplayed, int turtleId) {
//        listOfTurtles = new ArrayList<>();
//        currentTurtleIndex = turtleId;
//        //FIXME: don't hardcode
//        Turtle t = new Turtle(firstTurtleX, firstTurtleY, isPenUp, headingAngle, isDisplayed, false);
//        listOfTurtles.add(t);
//    }
//
//    public void addTurtleToList(Turtle t) {
//        listOfTurtles.add(t);
//    }
//
//    public int getCurrentTurtleIndex() {
//        return currentTurtleIndex;
//    }
//
//    public Turtle getCurrentTurtle() { return (Turtle) listOfTurtles.get(currentTurtleIndex); }
//
//    public ListInput<ITurtle> getListOfTurtles() {
//        return listOfTurtles;
//    }
//}
package Models;
import API.FrontExternalAPI;

import java.util.*;

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
    private int currentTurtleIndex;
    private FrontExternalAPI myFrontExternalAPI;

    public TurtleModel(FrontExternalAPI frontExternalAPI) {
        myFrontExternalAPI = frontExternalAPI;
        allTurtles = new HashMap<>();
        Turtle t = new Turtle(STARTX, STARTY, IS_PEN_UP, HEADING_ANGLE, IS_DISPLAYED,
                CLEAR_SCREEN, 1, PEN_COLOR_INDEX, PEN_SIZE, SHAPE_INDEX, myFrontExternalAPI);
        allTurtles.put(1, t);
        myFrontExternalAPI.addTurtle(1);
        currentActiveTurtles = new ArrayList<>();
        currentActiveTurtles.add(1);
        currentTurtleIndex = 1;
    }

    public void setCurrentActiveTurtles(List<Integer> turtleIds) {
        currentActiveTurtles = turtleIds;
        turtleIds.stream().
                filter(turtleId -> !allTurtles.containsKey(turtleId)).
                forEach(turtleId -> { allTurtles.put(turtleId, new Turtle(STARTX, STARTY, IS_PEN_UP, HEADING_ANGLE,
                IS_DISPLAYED,CLEAR_SCREEN, turtleId, PEN_COLOR_INDEX, PEN_SIZE, SHAPE_INDEX, myFrontExternalAPI));
                myFrontExternalAPI.addTurtle(turtleId);});
    }

    public Map<Integer, Turtle> getAllTurtles() {
        return allTurtles;
    }

    public Turtle getCurrentTurtle() {
        return allTurtles.get(currentTurtleIndex);
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

    public int getCurrentTurtleIndex() {
        return currentTurtleIndex;
    }



}