public interface FrontInternalAPI {

    /**
     * Part of GUI class
     * adds an element to the root of the scene
     * it will take in an object to add to the stage
     */
    public void addElement();

    /**
     * Part of board class
     * clears the drawings
     */
    public void clearBoard();

    /**
     * part of board class
     * changes the background color of the void
     * will have an argument for the color TBD
     */
    public void setBackgroundColor();

    /**
     * part of Console class
     * prints an error to be seen by the user
     * will be used to print errors to user when catching thrown by backend
     */
    public void printError(String s);

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
     * Part of Console class.
     * prints to the console
     */
    public void printToConsole(String s);

    /**
     * Part of the UserDefinedCommandView class
     * updates the user defined variables based on the changed made to the UserDefinedVariables model
     * triggered by backend, using a listener event? (maybe need an external component)
     */
    public void updateUserDefinedVariables();

}
