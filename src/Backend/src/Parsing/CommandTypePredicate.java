package Parsing;

import Commands.CommandNode;

import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * @author christinachen
 * This is a utility class used to determine the characteristics of a certain command so that other classes
 * can execute certain characteristic-dependent methods.
 * It assumes that the files used for the class is updated to include all commands that satisfy a certain property.
 * Depends on the CommandNode class as it is used as the parameter for the public methods.
 */

public class CommandTypePredicate {

    public static final String NEEDS_VARIABLE_FILE = "resources/Commands/needs-variable-parameter";
    public static final String NEEDS_WORD_FILE = "resources/Commands/needs-word-parameter";
    public static final String TURTLE_COMMANDS_FILE = "resources/Commands/turtle-commands";

    private static final Predicate<CommandNode> needsWordParameter = command -> (ResourceBundle.getBundle(NEEDS_WORD_FILE).containsKey(command.getCommandName()));

    private static final Predicate<CommandNode> needsVariableParameter = command -> (ResourceBundle.getBundle(NEEDS_VARIABLE_FILE).containsKey(command.getCommandName()));

    private static final Predicate<CommandNode> isTurtleCommand = command -> (ResourceBundle.getBundle(TURTLE_COMMANDS_FILE).containsKey(command.getCommandName()));

    private CommandTypePredicate() {
    }

    /**
     *
     * @param input
     * @return a boolean based on if the input is a turtle command
     */
    public static boolean isTurtleCommand(CommandNode input) {
        return isTurtleCommand.test(input);
    }

    /**
     *
     * @param input
     * @return a boolean based on if the input takes in a word as a parameter
     */
    public static boolean checkNeedsWordParameter(CommandNode input) {
        return needsWordParameter.test(input);
    }

    /**
     *
     * @param input
     * @return a boolean based on if the input takes in a variable as a parameter
     */
    public static boolean checkNeedsVariableParameter(CommandNode input) {
        return needsVariableParameter.test(input);    }
}
