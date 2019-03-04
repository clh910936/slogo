package Models;

import BackExternal.ITurtle;

import java.util.ArrayList;
import java.util.List;

public class TurtleModel {

    int currentTurtleIndex;
    List<ITurtle> listOfTurtles;

    public TurtleModel(double firstTurtleX, double firstTurtleY, boolean isPenUp, double headingAngle,
                       boolean isDisplayed, boolean isClearScreen, int turtleId) {
        listOfTurtles = new ArrayList<>();
        currentTurtleIndex = turtleId;
        //FIXME: maybe a better way to make new turtle
        Turtle t = new Turtle(firstTurtleX, firstTurtleY, isPenUp, headingAngle, isDisplayed, isClearScreen);
        listOfTurtles.add(t);
    }

    public void addTurtleToList(Turtle t) {
        listOfTurtles.add(t);
    }

    public int getCurrentTurtleIndex() {
        return currentTurtleIndex;
    }

    public Turtle getCurrentTurtle() { return (Turtle) listOfTurtles.get(currentTurtleIndex); }

    public List<ITurtle> getListOfTurtles() {
        return listOfTurtles;
    }
}
