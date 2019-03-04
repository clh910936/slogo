package Models;

import BackExternal.ITurtle;
import java.util.ArrayList;
import java.util.List;

public class Turtle extends GeneralTurtle implements ITurtle {

    public static final int FULL_CIRCLE_DEGREES = 360;

    public Turtle(double nextPointX, double nextPointY, boolean isPenUp, double headingAngle, boolean isDisplayed, boolean isClearScreen, int id, int pcIndex, double ps, int si) {
        super(nextPointX, nextPointY, isPenUp, headingAngle, isDisplayed, isClearScreen, id, pcIndex, ps, si);
    }

    public void moveForward(double dist) {
        myPointX += dist * Math.cos(Math.toRadians(myHeadingAngle));
        myPointY += dist * Math.sin(Math.toRadians(myHeadingAngle));
        printTurtleStatus();
        // TODO: call feroze
        //notifyObservers();
    }

    public void turnCounterClockwise(double degrees) {
        myHeadingAngle += degrees;
        myHeadingAngle = keepAnglePositive(myHeadingAngle);
        // TODO: call feroze
    }

    public void setHeadingAngle(double degrees) {
        myHeadingAngle = degrees;
        myHeadingAngle = keepAnglePositive(myHeadingAngle);
        // TODO: call feroze
    }

    public void updatePoints(double x, double y) {
        this.myPointX = x;
        this.myPointY = y;
        printTurtleStatus();
        // TODO: call feroze
    }

    public void setPenUp () {
        this.myIsPenUp = true;
        // TODO: call feroze
    }

    public void setPenDown () {
        this.myIsPenUp = false;
        // TODO: call feroze
    }

    public void setShowTurtle() {
        this.myIsDisplayed = true;
        // TODO: call feroze
    }

    public void setHideTurtle() {
        this.myIsDisplayed = false;
        // TODO: call feroze
    }

//    public List<Double> getUpdatedX() {
//        List<Double> listOfX = new ArrayList<>();
//        for (int i = 0; i < listOfStates.size(); i++) {
//            listOfX.add(listOfStates.get(i).getCurrentX() - STARTX);
//        }
//        return listOfX;
//    }
//
//    public List<Double> getUpdatedY() {
//        List<Double> listOfY = new ArrayList<>();
//        for (int i = 0; i < listOfStates.size(); i++) {
//            listOfY.add(listOfStates.get(i).getCurrentY() - STARTY);
//        }
//        return listOfY;
//    }
//
//    public List<Double> getHeadingAngle() {
//        List<Double> listOfAngles = new ArrayList<>();
//        for (int i = 0; i < listOfStates.size(); i++) {
//            listOfAngles.add(listOfStates.get(i).getCurrentAngle());
//        }
//        return listOfAngles;
//    }
//
//    public List<Boolean> getIsPenUp() {
//        List<Boolean> listOfPenUp = new ArrayList<>();
//        for (int i = 0; i < listOfStates.size(); i++) {
//            listOfPenUp.add(listOfStates.get(i).getCurrentIsPenUp());
//        }
//        return listOfPenUp;
//    }
//
//    public List<Boolean> getIsDisplayed() {
//        List<Boolean> listOfIsDisplayed = new ArrayList<>();
//        for (int i = 0; i < listOfStates.size(); i++) {
//            listOfIsDisplayed.add(listOfStates.get(i).getCurrentIsDisplayed());
//        }
//        return listOfIsDisplayed;
//    }
//
//    public List<Boolean> getClearScreen() {
//        List<Boolean> listOfCS = new ArrayList<>();
//        for (int i = 0; i < listOfStates.size(); i++) {
//            listOfCS.add(listOfStates.get(i).getCurrentIsCS());
//        }
//        return listOfCS;
//    }


    //HELPERS

    public double getDegreesDifference(double newAngle) {
        newAngle = keepAnglePositive(newAngle);
        double output = keepAnglePositive(newAngle - myHeadingAngle);
        return output;
    }

    public double getAngleToPoint(double x, double y) {
        double rise = x - myPointX;
        double run = y - myPointY;
        return keepAnglePositive(Math.toDegrees(Math.atan(rise / run)));
    }

    public double getDistToPoint(double x, double y) {
        double rise = x - myPointX;
        double run = y - myPointY;
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
        System.out.println("Located at: (" + getCurrentX() + ", " + getCurrentY() + ")");
        System.out.println("pen?: " + myIsPenUp);
        System.out.println("angle: " + myHeadingAngle);
        System.out.println("displayed?: +" + myIsDisplayed);
    }
}
