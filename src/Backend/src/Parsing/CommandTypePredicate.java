package Parsing;

import Commands.CommandNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class CommandTypePredicate {
    public static final String MAKE_VARIABLE_COMMAND = "MakeVariable";
    public static final String MAKE_COMMAND = "MakeUserInstruction";
    public static final List<String> TURTLE_COMMANDS = new ArrayList<>(
            Arrays.asList(
                    "Forward","Backward","Left","Right","SetHeading","SetTowards","SetPosition","PenDown","PenUp",
                    "ShowTurtle","HideTurtle","Home","ClearScreen", "ID", "XCoordinate", "YCoordinate"
            )
    );

    public static final Predicate<CommandNode> needsWordParameter = command -> (command.getCommandName().equals(MAKE_COMMAND));

    public static final Predicate<CommandNode> needsVariableParameter = command -> (command.getCommandName().equals(MAKE_VARIABLE_COMMAND));

    public static final Predicate<CommandNode> isTurtleCommand = command -> (TURTLE_COMMANDS.contains(command.getCommandName()));

    public static boolean isTurtleCommand(CommandNode input) {
        return isTurtleCommand.test(input);
    }

    public static boolean checkNeedsWordParameter(CommandNode input) {
        return needsWordParameter.test(input);
    }

    public static boolean checkNeedsVariableParameter(CommandNode input) {
        return needsVariableParameter.test(input);    }
}
