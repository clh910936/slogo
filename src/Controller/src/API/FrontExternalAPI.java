package API;


public interface FrontExternalAPI {
    /**
     * part of Board class
     * clears the drawings off of the board
     */
    public void clearBoard();

    /**
     * part of board class
     * changes the background color of the void
     * will have an argument for the color TBD
     */
    public void setBackgroundColor(int index);

    /**
     * part of TurtleView class.
     * updates the turtle based on changes made to the Turtle
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void penUp(boolean true_is_penup, int turtleId);
    public void rotate(double degrees, int turtleId);
    public void move(double x, double y, int turtleId);
    public void setPenColor(int index, int turtleId);
    public void setPenSize(double pixels, int turtleId);
    public void setShape(int index, int turtleId);
    public void setPalette(int index, int r, int g, int b);
    public void addTurtle(int turtleId);
    /**
     * updates all of the Views
     * ie History, User Defined Commands, etc.
     */
    public void updateViews();
}
