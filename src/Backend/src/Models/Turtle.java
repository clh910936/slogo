package Models;

import API.FrontExternalAPI;

public class Turtle extends GeneralTurtle {

    public static final int FULL_CIRCLE_DEGREES = 360;
    public static final int STARTX = 2000;
    public static final int STARTY = 2000;

    //private SimpleDoubleProperty xPos;
    //private SimpleDoubleProperty yPos;
    // private SimpleBooleanProperty PenUp;
    // private SimpleDoubleProperty PenThickness;

    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle,
                  boolean isDisplayed, boolean isClearScreen, int id, int pcIndex,
                  double ps, int si, FrontExternalAPI myFrontExternal) {
        super(nextPointX, nextPointY, isPenUp, headingAngle, isDisplayed, isClearScreen, id, pcIndex, ps, si, myFrontExternal);
    }

    public void moveForward(double dist) {
        setMyPointX(getMyPointX() + dist * Math.cos(Math.toRadians(getMyHeadingAngle())));
        setMyPointY(getMyPointY() + dist * Math.sin(Math.toRadians(getMyHeadingAngle())));
        printTurtleStatus();
        getMyFrontExternalAPI().move(getMyPointX() - STARTX, getMyPointY() - STARTY, getMyId());
        //notifyObservers();
    }

    public void turnCounterClockwise(double degrees) {
        setMyHeadingAngle(getMyHeadingAngle() + degrees);
        setMyHeadingAngle(keepAnglePositive(getMyHeadingAngle()));
        printTurtleStatus();
        getMyFrontExternalAPI().rotate(getMyHeadingAngle(), getMyId());
    }

    public void setHeadingAngle(double degrees) {
        setMyHeadingAngle(degrees);
        setMyHeadingAngle(keepAnglePositive(getMyHeadingAngle()));
        printTurtleStatus();
        getMyFrontExternalAPI().rotate(getMyHeadingAngle(), getMyId());
    }

    public void updatePoints(double x, double y) {
        setMyPointX(x);
        setMyPointY(y);
        getMyFrontExternalAPI().move(getMyPointX() - STARTX, getMyPointY() - STARTY, getMyId());
        printTurtleStatus();
    }

    public void setPenUp () {
        setMyIsPenUp(true);
        getMyFrontExternalAPI().penUp(true, getMyId());
    }

    public void setPenDown () {
        setMyIsPenUp(false);
        getMyFrontExternalAPI().penUp(false, getMyId());
    }

    public void setShowTurtle() {
        setMyIsDisplayed(true);
        getMyFrontExternalAPI().setDisplayTurtle(true, getMyId());
    }

    public void setHideTurtle() {
        setMyIsDisplayed(false);
        getMyFrontExternalAPI().setDisplayTurtle(false, getMyId());
    }

    public void setPensize(double pixels) {
        setMyPenSize(pixels);
        getMyFrontExternalAPI().setPenSize(getMyPenSize(), getMyId());
    }

    public void setPenColor(int index) {
        setMyPenColourIndex(index);
        getMyFrontExternalAPI().setPenColor(getMyPenColourIndex(), getMyId());
    }

    public void setShapeIndex(int index) {
        setMyShapeIndex(index);
        getMyFrontExternalAPI().setShape(getMyShapeIndex(), getMyId());
    }

    public void clearScreen() {
        getMyFrontExternalAPI().clearBoard();
    }

    public int getPenColorIndex() {
        return getMyPenColourIndex();
    }

    public int getShapeIndex() {
        return getMyShapeIndex();
    }

    public void setPalette(int index, int r, int g, int b) {
        getMyFrontExternalAPI().setPalette(index, r, g, b);
    }



    //HELPERS

    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        double output = keepAnglePositive(newAngle - getMyHeadingAngle());
        return output;
    }

    public double getAngleToPoint(double x, double y) {
        double rise = x - getMyPointX();
        double run = y - getMyPointY();
        return keepAnglePositive(Math.toDegrees(Math.atan(rise / run)));
    }

    public double getDistToPoint(double x, double y) {
        double rise = x - getMyPointX();
        double run = y - getMyPointY();
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
        System.out.println("Located at: (" + getMyPointX() + ", " + getMyPointY() + ")");
        System.out.println("pen?: " + getMyIsPenUp());
        System.out.println("angle: " + getMyHeadingAngle());
        System.out.println("displayed?: +" + getMyIsDisplayed());
    }
}
