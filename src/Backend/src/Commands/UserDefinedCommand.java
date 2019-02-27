package Commands;

import BackExternal.IllegalParametersException;
import Models.TurtleModel;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;
import Parsing.CommandParser;
import Parsing.Regex;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class UserDefinedCommand extends CommandsGeneral {
    private String commandName;
    private String commandsToExecute;
    private String[] myVariables;
    CommandParser cp;


    public UserDefinedCommand(String language, TurtleModel turtleModel, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel,
                              String name, String commands, String[] var) {
        super(language, turtleModel, variablesModel, userCreatedCommandsModel);
        commandName = name;
        commandsToExecute = commands;
        myVariables = var;
        cp = new CommandParser(new VariablesModel(), myTurtle, myUserCreatedCommandsModel);
    }

    public boolean isCommandReadyToExecute() {
        return myParams.size()==myVariables.length;
    }

    public double executeCommand() throws ClassCastException {
        String commands = commandsToExecute;
        for (int i = 0; i < myVariables.length; i++) {
            String var = myVariables[i];
            String param = String.valueOf(myParams.get(i));
            commands = commands.replaceAll(var, param);
        }
        return cp.parseCommand(commands, myLanguage);
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    public String[] getVariables() {
        return myVariables;
    }

    public String getCommands() {
        return commandsToExecute;
    }
}
