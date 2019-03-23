package Models;

import API.FrontExternalAPI;

/**
 * @author michaelzhang
 * Concrete implementation of GeneralTurtle
 */
public class Turtle extends GeneralTurtle {

    public static final int FULL_CIRCLE_DEGREES = 360;
    public static final int STARTX = 2000;
    public static final int STARTY = 2000;

    /**
     * Constructor - initializes a Turtle with its traits
     * @param nextPointX
     * @param nextPointY
     * @param isPenUp
     * @param headingAngle
     * @param isDisplayed
     * @param isClearScreen
     * @param id
     * @param pcIndex
     * @param ps
     * @param si
     * @param myFrontExternal
     */
    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle,
                  boolean isDisplayed, boolean isClearScreen, int id, int pcIndex,
                  double ps, int si, FrontExternalAPI myFrontExternal) {
        super(nextPointX, nextPointY, isPenUp, headingAngle, isDisplayed, isClearScreen, id, pcIndex, ps, si, myFrontExternal);
    }

    /**
     * Moves a turtle by a given distance
     * @param dist
     */
    public void moveForward(double dist) {
        setMyPointX(getMyPointX() + dist * Math.cos(Math.toRadians(getMyHeadingAngle())));
        setMyPointY(getMyPointY() + dist * Math.sin(Math.toRadians(getMyHeadingAngle())));
        printTurtleStatus();
        getMyFrontExternalAPI().move(getMyPointX() - STARTX, getMyPointY() - STARTY, getMyId());
    }

    /**
     * Turns turtle clockwise by an angle in degrees
     * @param degrees
     */
    public void turnCounterClockwise(double degrees) {
        setMyHeadingAngle(getMyHeadingAngle() + degrees);
        setMyHeadingAngle(keepAnglePositive(getMyHeadingAngle()));
        printTurtleStatus();
        getMyFrontExternalAPI().rotate(getMyHeadingAngle(), getMyId());
    }

    /**
     * Changes the turtle's heading angle
     * @param degrees
     */
    public void setHeadingAngle(double degrees) {
        setMyHeadingAngle(degrees);
        setMyHeadingAngle(keepAnglePositive(getMyHeadingAngle()));
        printTurtleStatus();
        getMyFrontExternalAPI().rotate(getMyHeadingAngle(), getMyId());
    }

    /**
     * Move the turtle to a different location on the board
     * @param x
     * @param y
     */
    public void updatePoints(double x, double y) {
        setMyPointX(x);
        setMyPointY(y);
        getMyFrontExternalAPI().move(getMyPointX() - STARTX, getMyPointY() - STARTY, getMyId());
        printTurtleStatus();
    }

    /**
     * Sets pen up to be true
     */
    public void setPenUp () {
        setMyIsPenUp(true);
        getMyFrontExternalAPI().penUp(true, getMyId());
    }

    /**
     * Sets pen down to be false
     */
    public void setPenDown () {
        setMyIsPenUp(false);
        getMyFrontExternalAPI().penUp(false, getMyId());
    }

    /**
     * Sets show turtle to be true
     */
    public void setShowTurtle() {
        setMyIsDisplayed(true);
        getMyFrontExternalAPI().setDisplayTurtle(true, getMyId());
    }

    /**
     * Sets show turtle to be false
     */
    public void setHideTurtle() {
        setMyIsDisplayed(false);
        getMyFrontExternalAPI().setDisplayTurtle(false, getMyId());
    }

    /**
     * Setter for PenSize
     * @param pixels
     */
    public void setPensize(double pixels) {
        setMyPenSize(pixels);
        getMyFrontExternalAPI().setPenSize(getMyPenSize(), getMyId());
    }

    /**
     * Setter for PenColor
     * @param index
     */
    public void setPenColor(int index) {
        setMyPenColourIndex(index);
        getMyFrontExternalAPI().setPenColor(getMyPenColourIndex(), getMyId());
    }

    /**
     * Setter for ShapeIndex
     * @param index
     */
    public void setShapeIndex(int index) {
        setMyShapeIndex(index);
        getMyFrontExternalAPI().setShape(getMyShapeIndex(), getMyId());
    }

    /**
     * Clears screen
     */
    public void clearScreen() {
        getMyFrontExternalAPI().clearBoard();
    }

    /**
     * Getter for PenColorIndex
     * @return
     */
    public int getPenColorIndex() {
        return getMyPenColourIndex();
    }

    /**
     * Getter for ShapeIndex
     * @return
     */
    public int getShapeIndex() {
        return getMyShapeIndex();
    }

    /**
     * Setter for Palette
     * @param index
     * @param r
     * @param g
     * @param b
     */
    public void setPalette(int index, int r, int g, int b) {
        getMyFrontExternalAPI().setPalette(index, r, g, b);
    }

    //HELPERS

    /**
     * Calculates angle difference between current angle and new angle
     * @param newAngle
     * @return
     */
    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        double output = keepAnglePositive(newAngle - getMyHeadingAngle());
        return output;
    }

    /**
     * Calculates angle that corresponds to point
     * @param x
     * @param y
     * @return
     */
    public double getAngleToPoint(double x, double y) {
        double rise = x - getMyPointX();
        double run = y - getMyPointY();
        return keepAnglePositive(Math.toDegrees(Math.atan(rise / run)));
    }

    /**
     * Calculates distance from current point to next point
     * @param x
     * @param y
     * @return
     */
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
