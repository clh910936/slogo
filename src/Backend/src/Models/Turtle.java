package Models;

import BackExternal.ITurtle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Turtle implements ITurtle {
    /*
    STATE INFORMATION:
    - NEXT POINT
    - PEN UP/ PEN DOWN
    - ANGLE
    - IS SHOWN/ IS HIDDEN
     */

    public static final double STARTX = 2000;
    public static final double STARTY = 2000;

    private double nextPointX;
    private double nextPointY;
    private boolean isPenUp;
    private double headingAngle;
    private boolean isDisplayed;
    private List<TurtleState> listOfStates;

    //private Queue<TurtleState> listOfStates;


    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed) {
        //myObservers = new ArrayList<>();
        this.nextPointX = nextPointX;
        this.nextPointY = nextPointY;
        this.isPenUp = isPenUp;
        this.headingAngle = headingAngle;
        this.isDisplayed = isDisplayed;
        //listOfStates = new LinkedList<>();
        listOfStates = new ArrayList<>();
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void moveForward(double dist) {
        nextPointX += dist * Math.cos(Math.toRadians(headingAngle));
        nextPointY += dist * Math.sin(Math.toRadians(headingAngle));
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
        printTurtleStatus();
        //notifyObservers();
    }

    private void printTurtleStatus() {
        System.out.println("Located at: (" + getCurrentX() + ", " + getCurrentY() + ")");
        System.out.println("pen?: " + isPenUp);
        System.out.println("angle: " + headingAngle);
        System.out.println("displayed?: +" + isDisplayed);
    }

    public void turnCounterClockwise(double degrees) {
        headingAngle += degrees;
        headingAngle = keepAnglePositive(headingAngle);
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void setHeadingAngle(double degrees) {
        headingAngle = degrees;
        headingAngle = keepAnglePositive(headingAngle);
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        double output = keepAnglePositive(newAngle - headingAngle);
        return output;
    }

    public double getAngleToPoint(double x, double y) {
        double rise = x - nextPointX;
        double run = y - nextPointY;
        return keepAnglePositive(Math.toDegrees(Math.atan(rise / run)));
    }

    public double getDistToPoint(double x, double y) {
        double rise = x - nextPointX;
        double run = y - nextPointY;
        return Math.sqrt(rise * rise + run * run);
    }

    public void updatePoints(double x, double y) {
        this.nextPointX = x;
        this.nextPointY = y;
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void setPenUp () {
        this.isPenUp = true;
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, true, this.headingAngle, this.isDisplayed));
    }

    public void setPenDown () {
        this.isPenUp = false;
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, false, this.headingAngle, this.isDisplayed));
    }

    public void setShowTurtle() {
        this.isDisplayed = true;
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, true));
    }

    public void setHideTurtle() {
        this.isDisplayed = false;
        listOfStates.add(new TurtleState(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, false));
    }

    public double getCurrentX() {
        return nextPointX;
    }

    public double getCurrentY() {
        return nextPointY;
    }

    public double getCurrentHeadingAngle() {
        return headingAngle;
    }

    public boolean getCurrentIsPenUp() {
        return isPenUp;
    }

    public boolean getCurrentIsDisplayed() {
        return isDisplayed;
    }

    public List<Double> getUpdatedX() {
        List<Double> listOfX = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfX.add(listOfStates.get(i).getX() - STARTX);
        }
        return listOfX;
    }

    public List<Double> getUpdatedY() {
        List<Double> listOfY = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfY.add(listOfStates.get(i).getY() - STARTY);
        }
        return listOfY;
    }

    public List<Double> getHeadingAngle() {
        List<Double> listOfAngles = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfAngles.add(listOfStates.get(i).getAngle());
        }
        return listOfAngles;
    }

    public List<Boolean> getIsPenUp() {
        List<Boolean> listOfPenUp = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfPenUp.add(listOfStates.get(i).getIsPenUp());
        }
        return listOfPenUp;
    }

    public List<Boolean> getIsDisplayed() {
        List<Boolean> listOfIsDisplayed = new ArrayList<>();
        for (int i = 0; i < listOfStates.size(); i++) {
            listOfIsDisplayed.add(listOfStates.get(i).getIsDisplayed());
        }
        return listOfIsDisplayed;
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
