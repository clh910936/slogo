package Models;

import API.FrontExternalAPI;

public abstract class GeneralTurtle {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;

    private double myPointX;
    private double myPointY;
    private boolean myIsPenUp;
    private double myHeadingAngle;
    private boolean myIsDisplayed;
    private boolean myIsClearScreen;
    private int myId;
    private int myPenColourIndex;
    private double myPenSize;
    private int myShapeIndex;
    private FrontExternalAPI myFrontExternalAPI;

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

    public double getMyPointX() {
        return myPointX;
    }

    protected void setMyPointX(double x) {
        myPointX = x;
    }

    public double getMyPointY() {
        return myPointY;
    }

    protected void setMyPointY(double y) {
        myPointY = y;
    }

    public boolean getMyIsPenUp() {
        return myIsPenUp;
    }

    protected void setMyIsPenUp(boolean isPenUp) {
        this.myIsPenUp = isPenUp;
    }

    public double getMyHeadingAngle() {
        return myHeadingAngle;
    }

    protected void setMyHeadingAngle(double angle) {
        this.myHeadingAngle = angle;
    }

    public boolean getMyIsDisplayed() {
        return myIsDisplayed;
    }

    protected void setMyIsDisplayed(boolean isDisplayed) {
        myIsDisplayed = isDisplayed;
    }

    public boolean getMyIsClearScreen() {
        return myIsClearScreen;
    }

    protected void setMyIsClearScreen(boolean isClearScreen) {
        myIsClearScreen = isClearScreen;
    }

    public int getMyId() {
        return myId;
    }

    protected void setMyId(int id) {
        myId = id;
    }

    public int getMyPenColourIndex() {
        return myPenColourIndex;
    }

    protected void setMyPenColourIndex(int index) {
        myPenColourIndex = index;
    }

    public double getMyPenSize() {
        return myPenSize;
    }

    protected void setMyPenSize(double penSize) {
        myPenSize = penSize;
    }

    public int getMyShapeIndex() {
        return myShapeIndex;
    }

    protected void setMyShapeIndex(int index) {
        myShapeIndex = index;
    }

    public FrontExternalAPI getMyFrontExternalAPI() {
        return myFrontExternalAPI;
    }
}
