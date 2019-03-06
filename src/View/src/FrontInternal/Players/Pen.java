package FrontInternal.Players;

import javafx.scene.paint.Color;

public class Pen {
    private Color myColor;
    private int mySize;
    private boolean up;

    private final static Color DEFAULT_COLOR = Color.BLUE;
    private final static int DEFAULT_SIZE = 4;
    public Pen() {
        myColor = DEFAULT_COLOR;
        mySize = DEFAULT_SIZE;
        up = false;
    }

    public Color getColor() {
        return myColor;
    }

    public int getSize() {
        return mySize;
    }

    public void setColor(Color c) {
        myColor = c;
    }

    public void setSize(int size) {
        mySize = size;
    }

    public boolean getPenUp() {
        return up;
    }

    public void setPenUp(boolean true_is_up) {
        up = true_is_up;
    }
}
