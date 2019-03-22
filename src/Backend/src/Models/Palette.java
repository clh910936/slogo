package Models;

/**
 * @author michaelzhang
 * This describes a palette as a combination of R, G, B.
 */
public class Palette {
    int myRed;
    int myGreen;
    int myBlue;

    /**
     * Constructor that creates a palette given r, g, b.
     * @param r
     * @param g
     * @param b
     */
    public Palette(int r, int g, int b) {
        myRed = r;
        myGreen = g;
        myBlue = b;
    }

    /**
     * Getter for red
     * @return
     */
    public int getMyRed() {
        return myRed;
    }

    /**
     * Getter for green
     * @return
     */
    public int getMyGreen() {
        return myGreen;
    }

    /**
     * Getter for blue
     * @return
     */
    public int getMyBlue() {
        return myBlue;
    }
}
