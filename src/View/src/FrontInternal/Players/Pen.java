package FrontInternal.Players;

import javafx.scene.paint.Color;

/**
 * Container class to keep track of all pen properties of a turtle
 * @author Feroze
 */
public class Pen {
    private Color myColor;
    private double mySize;
    private boolean up;

    public final static Color DEFAULT_COLOR = Color.BLUE;
    public final static int DEFAULT_R = (int) DEFAULT_COLOR.getRed() * 255;
    public final static int DEFAULT_G = (int) DEFAULT_COLOR.getGreen() * 255;
    public final static int DEFAULT_B = (int) DEFAULT_COLOR.getBlue() * 255;

    public final static int DEFAULT_SIZE = 4;

    /**
     * Creates a pen with default color, size, and status of down
     */
    public Pen() {
        myColor = DEFAULT_COLOR;
        mySize = DEFAULT_SIZE;
        up = false;
    }

    /**
     * @return pen color
     */
    public Color getColor() {
        return myColor;
    }

    /**
     * @return pen size
     */
    public double getSize() {
        return mySize;
    }

    /**
     * Sets the pen color
     * @param c new pen color
     */
    public void setColor(Color c) {
        myColor = c;
    }

    /**
     * Sets the pen size
     * @param size new pen size
     */
    public void setSize(double size) {
        mySize = size;
    }

    /**
     * @return pen up/down status
     */
    public boolean getPenUp() {
        return up;
    }

    /**
     * Sets the pen up/down status
     * @param true_is_up if true, set pen up, if false, set pen down
     */
    public void setPenUp(boolean true_is_up) {
        up = true_is_up;
    }
}
