package Models;

public abstract class GeneralTurtle {
//    public static final double STARTX = 2000;
//    public static final double STARTY = 2000;

    protected double myPointX;
    protected double myPointY;
    protected boolean myIsPenUp;
    protected double myHeadingAngle;
    protected boolean myIsDisplayed;
    protected boolean myIsClearScreen;
    protected int myPenColourIndex;
    protected double myPenSize;
    protected int myShapeIndex;


    public GeneralTurtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed, boolean isClearScreen) {
        this.myPointX = nextPointX;
        this.myPointY = nextPointY;
        this.myIsPenUp = isPenUp;
        this.myHeadingAngle = headingAngle;
        this.myIsDisplayed = isDisplayed;
        this.myIsClearScreen = isClearScreen;
    }

    public double getCurrentX() {
        return myPointX;
    }

    public double getCurrentY() {
        return myPointY;
    }

    public boolean getCurrentIsPenUp() {
        return myIsPenUp;
    }

    public double getCurrentAngle() {
        return myHeadingAngle;
    }

    public boolean getCurrentIsDisplayed() {
        return myIsDisplayed;
    }

    public boolean getCurrentIsCS() {
        return myIsClearScreen;
    }
}
