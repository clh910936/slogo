package Models;

import API.FrontExternalAPI;

public class Turtle extends GeneralTurtle {

    public static final int FULL_CIRCLE_DEGREES = 360;
    public static final int STARTX = 2000;
    public static final int STARTY = 2000;

    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle,
                  boolean isDisplayed, boolean isClearScreen, int id, int pcIndex,
                  double ps, int si, FrontExternalAPI myFrontExternal) {
        super(nextPointX, nextPointY, isPenUp, headingAngle, isDisplayed, isClearScreen, id, pcIndex, ps, si, myFrontExternal);
    }

    public void moveForward(double dist) {
        myPointX += dist * Math.cos(Math.toRadians(myHeadingAngle));
        myPointY += dist * Math.sin(Math.toRadians(myHeadingAngle));
        printTurtleStatus();
        myFrontExternalAPI.move(myPointX - STARTX, myPointY - STARTY, myId);
        //notifyObservers();
    }

    public void turnCounterClockwise(double degrees) {
        myHeadingAngle += degrees;
        myHeadingAngle = keepAnglePositive(myHeadingAngle);
        printTurtleStatus();
        myFrontExternalAPI.rotate(myHeadingAngle, myId);
    }

    public void setHeadingAngle(double degrees) {
        myHeadingAngle = degrees;
        myHeadingAngle = keepAnglePositive(myHeadingAngle);
        printTurtleStatus();
        myFrontExternalAPI.rotate(myHeadingAngle, myId);
    }

    public void updatePoints(double x, double y) {
        this.myPointX = x;
        this.myPointY = y;
        myFrontExternalAPI.move(myPointX - STARTX, myPointY - STARTY, myId);
        printTurtleStatus();
    }

    public void setPenUp () {
        this.myIsPenUp = true;
        myFrontExternalAPI.penUp(true, myId);
    }

    public void setPenDown () {
        this.myIsPenUp = false;
        myFrontExternalAPI.penUp(false, myId);
    }

    public void setShowTurtle() {
        this.myIsDisplayed = true;
        myFrontExternalAPI.setDisplayTurtle(true, myId);
    }

    public void setHideTurtle() {
        this.myIsDisplayed = false;
        myFrontExternalAPI.setDisplayTurtle(false, myId);
    }

    public void setPensize(double pixels) {
        this.myPenSize = pixels;
        myFrontExternalAPI.setPenSize(myPenSize, myId);
    }

    public void setPenColor(int index) {
        this.myPenColourIndex = index;
        myFrontExternalAPI.setPenColor(myPenColourIndex, myId);
    }

    public void setShapeIndex(int index) {
        this.myShapeIndex = index;
        myFrontExternalAPI.setShape(myShapeIndex, myId);
    }

    public void clearScreen() {
        myFrontExternalAPI.clearBoard();


    }

    public int getPenColorIndex() {
        return this.myPenColourIndex;
    }

    public int getShapeIndex() {
        return this.myShapeIndex;
    }

    public void setPalette(int index, int r, int g, int b) {
        myFrontExternalAPI.setPalette(index, r, g, b);
    }



    //HELPERS

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

    private double keepAnglePositive(double angle) {
        while (angle < 0) {
            angle += FULL_CIRCLE_DEGREES;
        }
        while (angle > FULL_CIRCLE_DEGREES) {
            angle -= FULL_CIRCLE_DEGREES;
        }
        return angle;
    }

    private void printTurtleStatus() {
        System.out.println("Located at: (" + getCurrentX() + ", " + getCurrentY() + ")");
        System.out.println("pen?: " + myIsPenUp);
        System.out.println("angle: " + myHeadingAngle);
        System.out.println("displayed?: +" + myIsDisplayed);
    }
}
