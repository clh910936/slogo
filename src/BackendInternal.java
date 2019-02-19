public interface BackendInternal {

    public void addVariable(Variable var);

    public void removeVariable(Variable var);

    public Pair getTurtleCoordinates();

    public void moveTurtleToCoordinates(Pair coordinates);

    public double getTurtleDirection();

    public void setTurtleDirection(double direction);

    public void setPenUp();

    public void setPenDown();

    public void showTurtle();

    public void hideTurtle();

    public void addCommandToHistory();

    public void removeCommandFromHistory();

    public void clearAllHistory();

    public void parseCommand(String consoleInput);

}
