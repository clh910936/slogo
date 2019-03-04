package Parsing;

import Commands.CommandNode;

import java.util.Stack;
import java.util.function.Predicate;

public class CommandParameterPredicate {
    public static final Predicate<CommandNode> needsWordParameter = command -> (command.getCommandName().equals(CommandParser.MAKE_COMMAND));

    public static final Predicate<CommandNode> needsVariableParameter = command -> (command.getCommandName().equals(CommandParser.MAKE_VARIABLE_COMMAND));

    public static boolean checkNeedsWordParameter(Stack commandStack) {
        if(commandStack.isEmpty()) return false;
        return needsWordParameter.test((CommandNode) commandStack.peek());
    }

    public static boolean checkNeedsVariableParameter(Stack commandStack) {
        if(commandStack.isEmpty()) return false;
        return needsVariableParameter.test((CommandNode) commandStack.peek());    }
}
