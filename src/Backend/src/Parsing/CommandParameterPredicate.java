package Parsing;

import Commands.CommandsGeneral;

import java.util.Stack;
import java.util.function.Predicate;

public class CommandParameterPredicate {
    public static final Predicate<CommandsGeneral> needsWordParameter = command -> (command.getCommandName().equals(CommandParser.MAKE_COMMAND));

    public static final Predicate<CommandsGeneral> needsVariableParameter = command -> (command.getCommandName().equals(CommandParser.MAKE_VARIABLE_COMMAND));

    public static boolean checkNeedsWordParameter(Stack commandStack) {
        if(commandStack.isEmpty()) return false;
        return needsWordParameter.test((CommandsGeneral) commandStack.peek());
    }

    public static boolean checkNeedsVariableParameter(Stack commandStack) {
        if(commandStack.isEmpty()) return false;
        return needsVariableParameter.test((CommandsGeneral) commandStack.peek());    }
}
