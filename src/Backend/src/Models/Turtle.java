package Models;

import BackExternal.ITurtle;
import java.util.ArrayList;
import java.util.List;

public class Turtle extends GeneralTurtle implements ITurtle {

    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    private List<TurtleState> listOfStates;

    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed, boolean isClearScreen) {
        super(nextPointX, nextPointY, isPenUp, headingAngle, isDisplayed, isClearScreen);
        listOfStates = new ArrayList<>();
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public void moveForward(double dist) {
        myPointX += dist * Math.cos(Math.toRadians(myHeadingAngle));
        myPointY += dist * Math.sin(Math.toRadians(myHeadingAngle));
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
        printTurtleStatus();
        //notifyObservers();
    }

    private void printTurtleStatus() {
        System.out.println("Located at: (" + getCurrentX() + ", " + getCurrentY() + ")");
        System.out.println("pen?: " + myIsPenUp);
        System.out.println("angle: " + myHeadingAngle);
        System.out.println("displayed?: +" + myIsDisplayed);
    }

    public void turnCounterClockwise(double degrees) {
        myHeadingAngle += degrees;
        myHeadingAngle = keepAnglePositive(myHeadingAngle);
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public void setHeadingAngle(double degrees) {
        myHeadingAngle = degrees;
        myHeadingAngle = keepAnglePositive(myHeadingAngle);
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        double output = keepAnglePositive(newAngle - myHeadingAngle);
        return output;
    }

    public double getAngleToPoint(double x, double y) {
        double rise = x - myPointX;
        double run = y - myPointY;
        return keepAnglePositive(Math.toDegrees(Math.atan(rise / run)));
    }

    public double getDistToPoint(double x, double y) {
        double rise = x - myPointX;
        double run = y - myPointY;
        return Math.sqrt(rise * rise + run * run);
    }

    public void updatePoints(double x, double y) {
        this.myPointX = x;
        this.myPointY = y;
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public void setPenUp () {
        this.myIsPenUp = true;
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public void setPenDown () {
        this.myIsPenUp = false;
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public void setShowTurtle() {
        this.myIsDisplayed = true;
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public void setHideTurtle() {
        this.myIsDisplayed = false;
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public void setClearScreen() {
        listOfStates.add(new TurtleState(this.myPointX, this.myPointY, this.myIsPenUp, this.myHeadingAngle, this.myIsDisplayed, this.myIsClearScreen));
    }

    public List<Double> getUpdatedX() {
        List<Double> listOfX = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfX.add(listOfStates.get(i).getCurrentX() - STARTX);
        }
        return listOfX;
    }

    public List<Double> getUpdatedY() {
        List<Double> listOfY = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfY.add(listOfStates.get(i).getCurrentY() - STARTY);
        }
        return listOfY;
    }

    public List<Double> getHeadingAngle() {
        List<Double> listOfAngles = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfAngles.add(listOfStates.get(i).getCurrentAngle());
        }
        return listOfAngles;
    }

    public List<Boolean> getIsPenUp() {
        List<Boolean> listOfPenUp = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfPenUp.add(listOfStates.get(i).getCurrentIsPenUp());
        }
        return listOfPenUp;
    }

    public List<Boolean> getIsDisplayed() {
        List<Boolean> listOfIsDisplayed = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfIsDisplayed.add(listOfStates.get(i).getCurrentIsDisplayed());
        }
        return listOfIsDisplayed;
    }

    public List<Boolean> getClearScreen() {
        List<Boolean> listOfCS = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfCS.add(listOfStates.get(i).getCurrentIsCS());
        }
        return listOfCS;
    }

    private double keepAnglePositive(double angle) {
        while (angle < 0) {
            angle += 360;
        }
        while (angle > 360) {
            angle -= 360;
        }
        return angle;
    }

//    private void resetModelAfterExecuting() {
//        List<TurtleState> oldStates = new ArrayList<>();
//        oldStates = listOfStates;
//        listOfStates = new ArrayList<>();
//        listOfStates.add(oldStates.get(oldStates.size()-1));
//        System.out.println("STATES IN MODEL: " + listOfStates.size());
//    }

}
