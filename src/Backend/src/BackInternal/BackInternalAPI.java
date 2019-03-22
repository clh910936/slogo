package BackInternal;

import BackExternal.IllegalCommandException;
import BackExternal.IllegalParametersException;
import Commands.CommandNode;
import Parsing.ParserTracker;
import java.util.List;

public interface BackInternalAPI {
    /**
     * Gets number of parameters are required for this command
     * @return max number of parameters
     */
    int getNumParamsNeeded();

    /**
     * Adds child to the command
     * @param node
     */
    void addChild(CommandNode node);

    /**
     * Gets list of children
     * @return list of children
     */
    List<CommandNode> getChildren();

    /**
     * Clears list of children
     */
    void clearChildren();

    /**
     * Gets the parameters given to this command
     * @return list of parameters
     */
    List<Object> getMyParams();

    /**
     * Adds a parameter to command
     * @param p
     */
    void addParam(Object p);

    /**
     * Clears list of parameters
     */
    void clearMyParams();

    /**
     * Gets command name
     * @return command name
     */
    String getCommandName();

    /**
     * If command has all its required children, then returns true
     * @return
     */
    boolean isCommandReadyToExecute();

    /**
     * Executes the action for a given command
     * @return always returns a number
     */
    Object executeCommand();

    /**
     * Can be called in order to parse a string command input and will
     * result in the execution of the command(s) through updating of the necessary
     * models (Variables, Turtle, etc)
     * Will throw an IllegalCommandException if the command is invalid
     * @param command
     * @return double that is the return value of a particular command
     * @throws IllegalCommandException
     * @throws IllegalParametersException
     */
    double parseCommand(String command);

    /**
     * Used to get the return values for each individual command that was
     * executed in the previous full command input string.
     * It will return an empty list if no commands have been executed.
     * Used in order to get the input values for loop commands that have list parameters
     * with individual expressions that should all be stored as doubles.
     *
     * Assumes that it will be called after the command associated with the desired
     * return values is parsed
     * @return a list of doubles that represent return values for commands in order
     */
    List<Double> getReturnValues();

    /**
     * Since the same SyntaxHandlerFactory is used for the entire duration of a program, if the language is
     * changed while it is being run, this is called each time a new command is parsed.
     * @param language
     */
    public void changeLanguage(String language);

    /**
     * Handles all of the syntax within a command input
     * Can be called by the CommandParser to fetch the next command node to put in its tree
     * @param parent
     * @param parserTracker
     * @return the CommandNode associated with the current raw input in the parserTracker
     * @throws IllegalCommandException
     */
    public CommandNode getCommandNode(CommandNode parent, ParserTracker parserTracker);




}
