package FrontInternal;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Board extends Pane {
    private Canvas myCanvas;
    private GraphicsContext gc;
    private int myWidth;
    private int myHeight;
    private TurtleView myTurtle;

    public Board(int width, int height) {
        myWidth = width;
        myHeight = height;
        createCanvas(myWidth, myHeight);
        setBackground(Color.BEIGE);

        myTurtle = new TurtleView();
        myTurtle.place(myWidth / 2, myHeight / 2);
        getChildren().addAll(myCanvas, myTurtle);

    }

    private void createCanvas(int width, int height) {
        myCanvas = new Canvas(width, height);
        gc = myCanvas.getGraphicsContext2D();

    }

    private void setBackground(Color c) {
        gc.setFill(c);
        gc.fillRect(0, 0, myWidth, myHeight);
    }

    public void move() {

    }
}
