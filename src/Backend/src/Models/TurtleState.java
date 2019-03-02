package Models;

import BackExternal.ITurtle;

public class TurtleState {
    private double x;
    private double y;
    private boolean isPenUp;
    private double angle;
    private boolean getIsDisplayed;

    public TurtleState(double x, double y, boolean isPenUp, double angle, boolean isDisplayed) {
        this.x = x;
        this.y = y;
        this.isPenUp = isPenUp;
        this.angle = angle;
        this.getIsDisplayed = isDisplayed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean getIsPenUp() {
        return isPenUp;
    }

    public double getAngle() {
        return angle;
    }

    public boolean getIsDisplayed() {
        return getIsDisplayed;
    }

}
