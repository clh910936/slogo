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
import BackExternal.ITurtle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurtleModel {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    public static final boolean IS_PEN_UP = false;
    public static final double HEADING_ANGLE = 0;
    public static final boolean IS_DISPLAYED = true;
    public static final boolean CLEAR_SCREEN = false;


    private List<Integer> currentActiveTurtles;
    private Map<Integer, ITurtle> allTurtles;
    private int currentTurtleIndex;

    public TurtleModel() {
        allTurtles = new HashMap<>();
        allTurtles.put(1, new Turtle(STARTX, STARTY, IS_PEN_UP, HEADING_ANGLE, IS_DISPLAYED, CLEAR_SCREEN));
        currentActiveTurtles = new ArrayList<>();
        currentActiveTurtles.add(1);
        currentTurtleIndex = 1;
    }

    public void setCurrentActiveTurtles(List<Integer> turtleIds) {
        currentActiveTurtles = turtleIds;
        for(int turtleId : turtleIds) {
            if (!allTurtles.containsKey(turtleId)) {
                allTurtles.put(turtleId, new Turtle(STARTX, STARTY, IS_PEN_UP, HEADING_ANGLE, IS_DISPLAYED,CLEAR_SCREEN));
            }
        }

    }


    public ITurtle getCurrentTurtle() {
        return allTurtles.get(currentTurtleIndex);
    }

    public Map<Integer, ITurtle> getAllTurtles() {
        return allTurtles;
    }

    public List<Integer> getCurrentActiveTurtles() {
        return currentActiveTurtles;
    }

    public void setCurrentTurtle(int index) {
        currentTurtleIndex = index;
    }
}