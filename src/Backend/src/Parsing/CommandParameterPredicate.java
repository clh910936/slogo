package Parsing;

import Commands.CommandsGeneral;

import java.util.function.Predicate;

public class CommandParameterPredicate {
    public static final Predicate<CommandsGeneral> needsWordParameter = command -> (command.getCommandName().equals(CommandParser.MAKE_COMMAND)||
            command.getCommandName().equals(CommandParser.IFELSE_COMMAND)||command.getCommandName().equals(CommandParser.IF_COMMAND)||command.getCommandName().equals(CommandParser.REPEAT_COMMAND));

    public static final Predicate<CommandsGeneral> needsVariableParameter = command -> (command.getCommandName().equals(CommandParser.MAKE_VARIABLE_COMMAND));

    public static boolean checkNeedsWordParameter(CommandsGeneral command) {
        return needsWordParameter.test(command);
    }

    public static boolean checkNeedsVariableParameter(CommandsGeneral command) {
        return needsVariableParameter.test(command);
    }
}
