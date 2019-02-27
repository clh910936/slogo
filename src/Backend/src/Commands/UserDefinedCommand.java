package Commands;

import Models.Turtle;
import Models.UserCreatedCommandsModel;
import Models.VariablesModel;
import Parsing.CommandParser;

public class UserDefinedCommand extends CommandsGeneral {
    private String commandName;
    private String commandsToExecute;
    private String[] myVariables;
    private CommandParser cp;


    public UserDefinedCommand(String language, Turtle turtle, VariablesModel variablesModel, UserCreatedCommandsModel userCreatedCommandsModel,
                              String name, String commands, String[] var) {
        super(language, turtle, variablesModel, userCreatedCommandsModel);
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
        if(commands.length()==0) return 0;
        for (int i = 0; i < myVariables.length; i++) {
            String variable = myVariables[i];
            String param = String.valueOf(myParams.get(i));
            commands = commands.replaceAll(variable, param);
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
