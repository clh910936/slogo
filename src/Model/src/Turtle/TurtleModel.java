package Turtle;

public class TurtleModel {
    /*
    STATE INFORMATION:
    - NEXT POINT
    - PEN UP/ PEN DOWN
    - ANGLE
    - IS SHOWN/ IS HIDDEN
     */

    private double nextPointX;
    private double nextPointY;
    private boolean isPenUp;
    private double headingAngle;
    private boolean isDisplayed;

    public TurtleModel(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed) {
        this.nextPointX = nextPointX;
        this.nextPointY = nextPointY;
        this.isPenUp = isPenUp;
        this.headingAngle = headingAngle;
        this.isDisplayed = isDisplayed;
    }

    public void moveForward(double dist) {
        nextPointX += dist * Math.cos(Math.toRadians(headingAngle));
        nextPointY += dist * Math.sin(Math.toRadians(headingAngle));
    }

    public void printTurtleStatus() {
        System.out.println("Located at: (" + nextPointX + ", " + nextPointY + ")");
        System.out.println("pen?: " + isPenUp);
        System.out.println("angle: " + headingAngle);
        System.out.println("displayed?: +" + isDisplayed);
    }





}
