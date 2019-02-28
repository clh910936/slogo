package Models;

import BackExternal.ITurtle;

import java.util.ArrayList;
import java.util.List;

public class TurtleModel {

    int currentTurtleIndex;
    List<ITurtle> listOfTurtles;

    public TurtleModel(double firstTurtleX, double firstTurtleY, boolean isPenUp, double headingAngle, boolean isDisplayed, int turtleId) {
        listOfTurtles = new ArrayList<>();
        currentTurtleIndex = turtleId;
        Turtle t = new Turtle(firstTurtleX, firstTurtleY, isPenUp, headingAngle, isDisplayed);
        listOfTurtles.add(t);
    }

    public int getCurrentTurtleIndex() {
        return currentTurtleIndex;
    }

    public List<ITurtle> getListOfTurtles() {
        return listOfTurtles;
    }
}
