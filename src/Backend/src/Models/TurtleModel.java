package Models;

import java.util.ArrayList;

public class TurtleModel extends Observable{
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

    public TurtleModel(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed) {
        myObservers = new ArrayList<>();
        this.nextPointX = nextPointX;
        this.nextPointY = nextPointY;
        this.isPenUp = isPenUp;
        this.headingAngle = headingAngle;
        this.isDisplayed = isDisplayed;
    }

    public void moveForward(double dist) {
        nextPointX += dist * Math.cos(Math.toRadians(headingAngle));
        nextPointY += dist * Math.sin(Math.toRadians(headingAngle));
        //printTurtleStatus();
        notifyObservers();
    }

    private void printTurtleStatus() {
        System.out.println("Located at: (" + getNextPointX() + ", " + getNextPointY() + ")");
        System.out.println("pen?: " + isPenUp);
        System.out.println("angle: " + headingAngle);
        System.out.println("displayed?: +" + isDisplayed);
    }

    public void turnCounterClockwise(double degrees) {
        headingAngle += degrees;
        headingAngle = keepAnglePositive(headingAngle);
        printTurtleStatus();
    }

    public void setHeadingAngle(double degrees) {
        headingAngle = degrees;
        headingAngle = keepAnglePositive(headingAngle);
    }

    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        return keepAnglePositive(newAngle - headingAngle);
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
    }

    public void setPenUp () {
        this.isPenUp = true;
    }

    public void setPenDown () {
        this.isPenUp = false;
    }

    public void setShowTurtle() {
        this.isDisplayed = true;
    }

    public void setHideTurtle() {
        this.isDisplayed = false;
    }

    public boolean getIsDisplayed() {
        return isDisplayed;
    }

    public double getNextPointX() {
        return nextPointX - STARTX;
    }

    public double getNextPointY() {
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
