package Models;

public class TurtleStates {
    private double x;
    private double y;
    private boolean isPenUp;
    private double angle;
    private boolean getIsDisplayed;

    public TurtleStates(double x, double y, boolean isPenUp, double angle, boolean isDisplayed) {
        this.x = x;
        this.y = y;
        this.isPenUp = isPenUp;
        this.angle = angle;
        this.getIsDisplayed = isDisplayed;
    }

}
