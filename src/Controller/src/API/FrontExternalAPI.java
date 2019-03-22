package API;

/**
 * This interface is a series of endpoints that are called upon by the backend in order to enact some action on the
 * frontend.
 * @author everyone
 */
public interface FrontExternalAPI {
    /**
     * part of Board class
     * clears the drawings off of the board
     */
    public void clearBoard();

    /**
     * part of board class
     * changes the background color of the void
     */
    public void setBackgroundColor(int index);

    /**
     * part of TurtleView class.
     * updates the turtle based on changes made to the Turtle
     * these are API endpoints used by the backend in response to commands
     */
    public void penUp(boolean true_is_penup, int turtleId);
    public void rotate(double degrees, int turtleId);
    public void move(double x, double y, int turtleId);
    public void setPenColor(int index, int turtleId);
    public void setPenSize(double pixels, int turtleId);
    public void setShape(int index, int turtleId);
    public void setPalette(int index, int r, int g, int b);
    public void addTurtle(int turtleId);
    public void setDisplayTurtle(boolean isDisplayed, int turtleId);

    /**
     * updates all of the Views
     * ie History, User Defined Commands, etc.
     */
    public void updateViews();
}
