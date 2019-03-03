package Parsing;

import Commands.CommandsGeneral;

import java.util.Stack;
import java.util.function.Predicate;

public class CommandTypePredicate {
    public static final String MAKE_VARIABLE_COMMAND = "MakeVariable";
    public static final String MAKE_COMMAND = "MakeUserInstruction";

    public static final Predicate<CommandsGeneral> needsWordParameter = command -> (command.getCommandName().equals(MAKE_COMMAND));

    public static final Predicate<CommandsGeneral> needsVariableParameter = command -> (command.getCommandName().equals(MAKE_VARIABLE_COMMAND));

    public static boolean checkNeedsWordParameter(Stack commandStack) {
        if(commandStack.isEmpty()) return false;
        return needsWordParameter.test((CommandsGeneral) commandStack.peek());
    }

    public static boolean checkNeedsVariableParameter(Stack commandStack) {
        if(commandStack.isEmpty()) return false;
        return needsVariableParameter.test((CommandsGeneral) commandStack.peek());    }
}
