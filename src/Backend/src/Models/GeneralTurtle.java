package Models;

import API.FrontExternalAPI;

public abstract class GeneralTurtle {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;

    protected double myPointX;
    protected double myPointY;
    protected boolean myIsPenUp;
    protected double myHeadingAngle;
    protected boolean myIsDisplayed;
    protected boolean myIsClearScreen;
    protected int myId;
    protected int myPenColourIndex;
    protected double myPenSize;
    protected int myShapeIndex;
    protected FrontExternalAPI myFrontExternalAPI;

    public GeneralTurtle(double nextPointX, double nextPointY, boolean isPenUp,
                         double headingAngle, boolean isDisplayed, boolean isClearScreen,
                         int id, int pcIndex, double ps, int si, FrontExternalAPI myFrontExternal) {
        this.myPointX = nextPointX;
        this.myPointY = nextPointY;
        this.myIsPenUp = isPenUp;
        this.myHeadingAngle = headingAngle;
        this.myIsDisplayed = isDisplayed;
        this.myIsClearScreen = isClearScreen;
        this.myId = id;
        this.myPenColourIndex = pcIndex;
        this.myPenSize = ps;
        this.myShapeIndex = si;
        this.myFrontExternalAPI = myFrontExternal;
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
