package BackInternal;

import BackExternal.IllegalCommandException;

public interface BackInternalAPI {

    /**
     * Defined within the UserSetCommandModel
     * The user will be able to add a command with relevant variables and predefined commands that can be called at any other time
     * @param commandName
     * @param input
     */
    public void addCommand(String commandName, String input);

    /**
     * Defined within the VariablesModel
     * The user can define a variable that can be accessed through any other command
     * @param var
     */
    public void addVariable(Object var);

    /**
     * Defined within the VariablesModel
     * Removes a variable from the current state
     * @param var
     * @throws IllegalCommandException if the variable doesn't exist
     */
    public void removeVariable(Object var) throws IllegalCommandException;

    /**
     * Defined within the TurtleModel
     * @return Java Pair object with x coordinate and y coordinate of the turtle
     */
    public Object getTurtleCoordinates();

    /**
     * Defined within the TurtleModel
     * Places the turtle at a certain place on the board
     * @param coordinates
     * @throws if the coordinates are outside the bounds of the dimensions of the board
     */
    public void moveTurtleToCoordinates(Object coordinates) throws IllegalCommandException;

    /**
     * Defined within the TurtleModel
     * Gets the current direction of the turtle on the board
     * @return direction of the turtle
     */
    public double getTurtleDirection();

    /**
     * Defined within the TurtleModel
     * Sets the direction of the turtle to a certain direction
     * Should handle degrees greater than a value of 360
     * @param  direction of the turtle
     */
    public void setTurtleDirection(double direction);

    /**
     * Defined within the TurtleModel
     * Sets the pen up
     */
    public void setPenUp();

    /**
     * Defined within the TurtleModel
     * Sets the pen down
     */
    public void setPenDown();

    /**
     * Defined within the TurtleModel
     * Shows the turtle
     */
    public void showTurtle();

    /**
     * Defined within the TurtleModel
     * Hides the turtle
     */
    public void hideTurtle();

    /**
     * Defined within the HistoryModel
     * Adds a certain input to the history data
     */
    public void addCommandToHistory();

    /**
     * Defined within the HistoryModel
     * Removes a certain input from the history data
     */
    public void removeCommandFromHistory() throws IllegalCommandException;

    /**
     * Defined within the HistoryModel
     * Clears all history
     */
    public void clearAllHistory();

    /**
     * Defined within the controller
     * Parses any input to a command
     * @param consoleInput
     */
    public void parseCommand(String consoleInput) throws IllegalCommandException;
}
