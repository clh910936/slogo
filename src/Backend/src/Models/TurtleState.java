package Models;

import BackExternal.ITurtle;

public class TurtleState {
    private double x;
    private double y;
    private boolean isPenUp;
    private double angle;
    private boolean getIsDisplayed;
    private boolean isCS;

    public TurtleState(double x, double y, boolean isPenUp, double angle, boolean isDisplayed, boolean isCS) {
        this.x = x;
        this.y = y;
        this.isPenUp = isPenUp;
        this.angle = angle;
        this.getIsDisplayed = isDisplayed;
        this.isCS = isCS;
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

    public boolean getIsCS() {
        return isCS;
    }

}
