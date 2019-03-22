package Models;

import API.FrontExternalAPI;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * @author michaelzhang
 * This abstract class describes the characteristics of a turtle, and provides getters and setters as necessary
 */
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

    private SimpleDoubleProperty xPos;
    private SimpleDoubleProperty yPos;
    private SimpleBooleanProperty PenUp;
    private SimpleDoubleProperty PenThickness;
    private SimpleIntegerProperty R;
    private SimpleIntegerProperty G;
    private SimpleIntegerProperty B;

    /**
     * Constructor for a Turtle, which includes all the parameters each turtle must keep track off
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

    /**
     * Getter for myPointX
     * @return
     */
    public double getMyPointX() {
        return myPointX;
    }

    protected void setMyPointX(double x) {
        myPointX = x;
    }

    /**
     * Getter for myPointY
     * @return
     */
    public double getMyPointY() {
        return myPointY;
    }

    protected void setMyPointY(double y) {
        myPointY = y;
    }

    /**
     * Getter for myIsPenUp
     * @return
     */
    public boolean getMyIsPenUp() {
        return myIsPenUp;
    }

    protected void setMyIsPenUp(boolean isPenUp) {
        this.myIsPenUp = isPenUp;
    }

    /**
     * Getter for myHeadingAngle
     * @return
     */
    public double getMyHeadingAngle() {
        return myHeadingAngle;
    }

    protected void setMyHeadingAngle(double angle) {
        this.myHeadingAngle = angle;
    }

    /**
     * Getter for myIsDisplayed
     * @return
     */
    public boolean getMyIsDisplayed() {
        return myIsDisplayed;
    }

    protected void setMyIsDisplayed(boolean isDisplayed) {
        myIsDisplayed = isDisplayed;
    }

    /**
     * Getter for myId
     * @return
     */
    public int getMyId() {
        return myId;
    }

    /**
     * Getter for myPenColourIndex
     * @return
     */
    public int getMyPenColourIndex() {
        return myPenColourIndex;
    }

    protected void setMyPenColourIndex(int index) {
        myPenColourIndex = index;
    }

    /**
     * Getter for myPenSive
     * @return
     */
    public double getMyPenSize() {
        return myPenSize;
    }

    protected void setMyPenSize(double penSize) {
        myPenSize = penSize;
    }

    /**
     * Getter for myShapeIndex
     * @return
     */
    public int getMyShapeIndex() {
        return myShapeIndex;
    }

    protected void setMyShapeIndex(int index) {
        myShapeIndex = index;
    }

    /**
     * Getter for myFrontExternalAPI
     * @return
     */
    public FrontExternalAPI getMyFrontExternalAPI() {
        return myFrontExternalAPI;
    }

    /**
     * Getter for xPos (JavaFX)
     * @return
     */
    public SimpleDoubleProperty getxPos() {
        return xPos;
    }

    /**
     * Setter for xPos (JavaFX)
     * @param x
     */
    public void setxPos(SimpleDoubleProperty x) {
        xPos = x;
    }

    /**
     * Getter for yPos (JavaFX)
     * @return
     */
    public SimpleDoubleProperty getyPos() {
        return yPos;
    }

    /**
     * Setter for yPos (JavaFX)
     * @param y
     */
    public void setyPos(SimpleDoubleProperty y) {
        yPos = y;
    }

    /**
     * Getter for PenUp (JavaFX)
     * @return
     */
    public SimpleBooleanProperty getPenUp() {
        return PenUp;
    }

    /**
     * Setter for PenUp (JavaFX)
     * @param PenUp
     */
    public void setPenUp(SimpleBooleanProperty PenUp) {
        this.PenUp = PenUp;
    }

    /**
     * Getter for PenThickness (JavaFX)
     * @return
     */
    public SimpleDoubleProperty getPenThickness() {
        return PenThickness;
    }

    /**
     * Setter for PenThickness (JavaFX)
     * @param thickness
     */
    public void setPenThickness(SimpleDoubleProperty thickness) {
        PenThickness = thickness;
    }

    /**
     * Getter for R (JavaFX)
     * @return
     */
    public SimpleIntegerProperty getR() {
        return R;
    }

    /**
     * Setter for R (JavaFX)
     * @param r
     */
    public void setR(SimpleIntegerProperty r) {
        R = r;
    }

    /**
     * Getter for G (JavaFX)
     * @return
     */
    public SimpleIntegerProperty getG() {
        return G;
    }

    /**
     * Setter for G (JavaFX)
     * @param g
     */
    public void setG(SimpleIntegerProperty g) {
        G = g;
    }

    /**
     * Getter for B (JavaFX)
     * @return
     */
    public SimpleIntegerProperty getB() {
        return B;
    }

    /**
     * Setter for B (JavaFX)
     * @param b
     */
    public void setB(SimpleIntegerProperty b) {
        B = b;
    }

}
