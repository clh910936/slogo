package Models;

import BackExternal.ITurtle;

import java.util.ArrayList;
import java.util.List;

public class TurtleModel {


    List<ITurtle> listOfTurtles;

    public TurtleModel(double firstTurtleX, double firstTurtleY, boolean isPenUp, double headingAngle, boolean isDisplayed) {
        listOfTurtles = new ArrayList<>();
        Turtle t = new Turtle(firstTurtleX, firstTurtleY, isPenUp, headingAngle, isDisplayed);
        listOfTurtles.add(t);
    }

    public List<ITurtle> getListOfTurtles() {
        return listOfTurtles;
    }
}
