package Models;

import BackExternal.ITurtle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TurtleModel {

    int currentTurtleIndex;
    List<ITurtle> listOfTurtles;

    public TurtleModel(double firstTurtleX, double firstTurtleY, boolean isPenUp, double headingAngle, boolean isDisplayed, int turtleId) {
        listOfTurtles = new ArrayList<>();
        currentTurtleIndex = turtleId;
        //FIXME: maybe a better way to make new turtle
        Turtle t = new Turtle(firstTurtleX, firstTurtleY, isPenUp, headingAngle, isDisplayed, false);
        listOfTurtles.add(t);
    }

    public void addTurtleToList(Turtle t) {
        listOfTurtles.add(t);
    }

    public Turtle getCurrentTurtle() { return (Turtle) listOfTurtles.get(currentTurtleIndex); }

    public List<ITurtle> getListOfTurtles() {
        return Collections.unmodifiableList(listOfTurtles);
    }
}
