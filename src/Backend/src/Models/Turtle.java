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


    private List<Double> nextPointX = new ArrayList<>();
    private List<Double> nextPointY = new ArrayList<>();
    private List<Boolean> isPenUp = new ArrayList<>();
    private List<Double> headingAngle = new ArrayList<>();
    private List<Boolean> isDisplayed = new ArrayList<>();


    //.size()-1

    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed) {
        //myObservers = new ArrayList<>();
        this.nextPointX.add(nextPointX);
        this.nextPointY.add(nextPointY);
        this.isPenUp.add(isPenUp);
        this.headingAngle.add(headingAngle);
        this.isDisplayed.add(isDisplayed);
    }

    public void moveForward(double dist) {
        nextPointX.add(nextPointX.get(nextPointX.size()-1) + dist * Math.cos(Math.toRadians(headingAngle.get(headingAngle.size()-1))));
        nextPointY.add(nextPointY.get(nextPointY.size()-1) + dist * Math.sin(Math.toRadians(headingAngle.get(headingAngle.size()-1))));
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
        double sum = headingAngle.get(headingAngle.size()-1) + degrees;
        sum = keepAnglePositive(sum);
        headingAngle.add(sum);
        printTurtleStatus();
    }

    public void setHeadingAngle(double degrees) {
        headingAngle.add(keepAnglePositive(degrees));
    }

    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        return keepAnglePositive(newAngle - headingAngle.get(headingAngle.size()-1));
    }

    public double getAngleToPoint(double x, double y) {
        double rise = x - nextPointX.get(nextPointX.size()-1);
        double run = y - nextPointY.get(nextPointY.size()-1);
        return keepAnglePositive(Math.toDegrees(Math.atan(rise / run)));
    }

    public double getDistToPoint(double x, double y) {
        double rise = x - nextPointX.get(nextPointX.size()-1);
        double run = y - nextPointY.get(nextPointY.size()-1);
        return Math.sqrt(rise * rise + run * run);
    }

    public void updatePoints(double x, double y) {
        nextPointX.add(x);
        nextPointY.add(y);
    }

    public void setPenUp () {
        this.isPenUp.add(true);
    }

    public void setPenDown () {
        this.isPenUp.add(false);
    }

    public void setShowTurtle() {
        this.isDisplayed.add(true);
    }

    public void setHideTurtle() {
        this.isDisplayed.add(false);
    }

    public List<Boolean> getIsDisplayed() {
        return isDisplayed;
    }

    public List<Double> getUpdatedX() {
        return getUpdatedPoints(nextPointX, STARTX);
    }

    public List<Double> getUpdatedY() {
        return getUpdatedPoints(nextPointY, STARTY);
    }

    private List<Double> getUpdatedPoints(List<Double> nextPointX, double startx) {
        List<Double> newList = deepCopy(nextPointX);
        for (int i = 0; i < newList.size(); i++) {
            newList.set(i, newList.get(i) - startx);
        }
        return newList;
    }

    public List<Double> getHeadingAngle() {
        return headingAngle;
    }

    public List<Boolean> getIsPenUp() {
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

    private List<Double> deepCopy (List<Double> l) {
        List<Double> newList = new ArrayList<Double>();
        for (Double num : l) {
            newList.add(num);
        }
        return newList;
    }
}
