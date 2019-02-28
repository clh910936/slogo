package Commands;

import Models.ModelManager;
import Models.ModelManager;
import Parsing.CommandParser;

import java.util.Arrays;

public class UserDefinedCommand extends CommandsGeneral {
    private String commandName;
    private String commandsToExecute;
    private String[] myVariables;
    private CommandParser cp;

    public UserDefinedCommand(String language, ModelManager modelManager,
                              String name, String commands, String[] var) {
        super(language, modelManager);
        commandName = name;
        commandsToExecute = commands;
        myVariables = var;
        cp = new CommandParser(modelManager);
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
        myParams.clear();
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

    public String toString() {
        return (getCommandName() + "\n\t" + Arrays.toString(getVariables())+ "\n\t[ " + getCommands() + " ]");
    }
}
