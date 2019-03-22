package FrontInternal.Util;

/**
 * General class to hold a tuple (in this case, the location of the turtle).
 * @author Feroze
 */
public class Location {
    private double myX;
    private double myY;

    public Location (double x, double y) {
        myX = x;
        myY = y;
    }

    public void setX(double x) {
        myX = x;
    }

    public void setY(double y) {
        myY = y;
    }

    public double getX() {
        return myX;
    }

    public double getY() {
        return myY;
    }
}
