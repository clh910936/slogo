package BackExternal;

import java.util.List;
import java.util.Map;
import Exceptions.IllegalCommandException;
import Exceptions.ParamsExceedLimitException;
import Turtle.TurtleModel;

public interface BackExternalAPI {

    /**
     * Part of the controller. Front-end calls parse() to give unprocessed commands to back-end.
     */
    public void parseCommand(String inputString, String language) throws IllegalCommandException, ParamsExceedLimitException;

    /**
     * When the front-end gets told by TurtleModel that the turtle as been updated, the front-end calls getTurtle()
     * to get the latest turtle with updated parameters
     * @return a turtle object
     */
    public TurtleModel getTurtle();

    /**
     * To update the history view, the front-end calls getHistory() to get the latest history of commands
     * @return a history object
     */
    public List<String> getHistory();

    /**
     * To update variables view, the front-end calls getVariables() to get the latest set of variables
     * @return a variable object
     */
    public Map<String, String> getVariables();

    /**
     * Gets a list of the user defined commands
     * @return
     */
    public List<String> getUserCreatedCommands();

}
