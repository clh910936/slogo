public interface BackendInternal {

    /**
     * The user will be able to add a command with relevant variables and predefined commands that can be called at any other time
     * @param commandName
     * @param input
     */
    public void addCommand(String commandName, String input);

    /**
     * The user can define a variable that can
     * @param var
     */
    public void addVariable(Variable var);

    /**
     *
     * @param var
     * @throws InvalidInputException
     */
    public void removeVariable(Variable var) throws InvalidInputException;

    /**
     *
     * @return
     */
    public Pair getTurtleCoordinates();

    /**
     *
     * @param coordinates
     */
    public void moveTurtleToCoordinates(Pair coordinates);

    /**
     *
     * @return
     */
    public double getTurtleDirection();

    /**
     *
     * @param direction
     */
    public void setTurtleDirection(double direction);

    /**
     *
     */
    public void setPenUp();

    /**
     *
     */
    public void setPenDown();

    /**
     *
     */
    public void showTurtle();

    /**
     *
     */
    public void hideTurtle();

    /**
     *
     */
    public void addCommandToHistory();

    /**
     *
     */
    public void removeCommandFromHistory();

    /**
     *
     */
    public void clearAllHistory();

    /**
     *
     * @param consoleInput
     */
    public void parseCommand(String consoleInput);
}
