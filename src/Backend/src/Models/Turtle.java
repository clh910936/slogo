package Models;

import BackExternal.ITurtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    private List<TurtleStates> listOfStates;

    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed) {
        //myObservers = new ArrayList<>();
        this.nextPointX = nextPointX;
        this.nextPointY = nextPointY;
        this.isPenUp = isPenUp;
        this.headingAngle = headingAngle;
        this.isDisplayed = isDisplayed;
        listOfStates = new ArrayList<>();
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void moveForward(double dist) {
        nextPointX += dist * Math.cos(Math.toRadians(headingAngle));
        nextPointY += dist * Math.sin(Math.toRadians(headingAngle));
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
        //printTurtleStatus();
        //notifyObservers();
    }

    private void printTurtleStatus() {
        System.out.println("Located at: (" + getUpdatedX() + ", " + getUpdatedY() + ")");
        System.out.println("pen?: " + isPenUp);
        System.out.println("angle: " + headingAngle);
        System.out.println("displayed?: +" + isDisplayed);
    }

    public void turnCounterClockwise(double degrees) {
        headingAngle += degrees;
        headingAngle = keepAnglePositive(headingAngle);
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
        //printTurtleStatus();
    }

    public void setHeadingAngle(double degrees) {
        headingAngle = degrees;
        headingAngle = keepAnglePositive(headingAngle);
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        double output = keepAnglePositive(newAngle - headingAngle);
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
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
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void setPenUp () {
        this.isPenUp = true;
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void setPenDown () {
        this.isPenUp = false;
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void setShowTurtle() {
        this.isDisplayed = true;
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public void setHideTurtle() {
        this.isDisplayed = false;
        listOfStates.add(new TurtleStates(this.nextPointX, this.nextPointY, this.isPenUp, this.headingAngle, this.isDisplayed));
    }

    public boolean getIsDisplayed() {
        return isDisplayed;
    }

    public double getUpdatedX() {
        return nextPointX - STARTX;
    }

    public double getUpdatedY() {
        return nextPointY - STARTY;
    }

    public double getHeadingAngle() {
        return headingAngle;
    }

    public boolean getIsPenUp() {
        return isPenUp;
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

}
