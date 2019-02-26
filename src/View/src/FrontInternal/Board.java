package FrontInternal;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Board extends Canvas {
    private GraphicsContext gc;
    private int myWidth;
    private int myHeight;

    public Board(int width, int height) {
        gc = getGraphicsContext2D();
        myWidth = width;
        myHeight = height;

        setWidth(myWidth);
        setHeight(myHeight);

        setBackground(Color.BEIGE);

    }

    private void setBackground(Color c) {
        gc.setFill(c);
        gc.fillRect(0, 0, myWidth, myHeight);
    }
}
