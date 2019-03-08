package Parsing;

import Commands.CommandNode;

import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CommandTypePredicate {

    public static final String NEEDS_VARIABLE_FILE = "resources/Commands/needs-variable-parameter";
    public static final String NEEDS_WORD_FILE = "resources/Commands/needs-word-parameter";
    public static final String TURTLE_COMMANDS_FILE = "resources/Commands/turtle-commands";

    private static final Predicate<CommandNode> needsWordParameter = command -> (ResourceBundle.getBundle(NEEDS_WORD_FILE).containsKey(command.getCommandName()));

    private static final Predicate<CommandNode> needsVariableParameter = command -> (ResourceBundle.getBundle(NEEDS_VARIABLE_FILE).containsKey(command.getCommandName()));

    private static final Predicate<CommandNode> isTurtleCommand = command -> (ResourceBundle.getBundle(TURTLE_COMMANDS_FILE).containsKey(command.getCommandName()));

    public static boolean isTurtleCommand(CommandNode input) {
        return isTurtleCommand.test(input);
    }

    public static boolean checkNeedsWordParameter(CommandNode input) {
        return needsWordParameter.test(input);
    }

    public static boolean checkNeedsVariableParameter(CommandNode input) {
        return needsVariableParameter.test(input);    }
}
