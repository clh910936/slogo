import java.awt.*;

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
    public void setBackgroundColor(Paint color);

    /**
     * part of TurtleView class.
     * updates the turtle based on changes made to the TurtleModel
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void updateTurtle();

    /**
     * part of the HistoryView class.
     * updates the history based on the changes made to the HistoryModel
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void updateHistory();

    /**
     * part of the VariableView class.
     * updates the variables based on the changes made to the VariablesModel
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void updateVariables();


    /**
     * Part of the UserDefinedCommandView class
     * updates the user defined commands based on the changed made to the UserDefinedCommands model
     */
    public void updateUserDefinedCommands();
}
