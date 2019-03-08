package Commands;

import Parsing.SyntaxHandlerFactory;
import BackExternal.ModelManager;
import Parsing.CommandParser;

public class UserDefinedCommand extends CommandNode {
    private String commandName;
    private String commandsToExecute;
    private String[] myVariables;

    public UserDefinedCommand(SyntaxHandlerFactory syntaxHandlerFactory, ModelManager modelManager,
                              String name, String commands, String[] var) {
        super(syntaxHandlerFactory, modelManager);
        commandName = name;
        commandsToExecute = commands;
        myVariables = var;
    }


    public Object executeCommand() throws ClassCastException {
        String commands = commandsToExecute;
        if(commands.length()==0) return 0;
        for (int i = 0; i < myVariables.length; i++) {
            String variable = myVariables[i];
            String param = String.valueOf(getMyParams().get(i));
            commands = commands.replaceAll(variable, param);
        }
        clearMyParams();
        return getCp().parseCommand(commands);
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getVariables() {
        return myVariables;
    }

    public String getCommands() {
        return commandsToExecute;
    }

    public String getVariablesToString() {
        return String.join(" ",myVariables);
    }


    public String getVariablesListToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(getVariablesToString());
        sb.append(" ]");
        return sb.toString();
    }

    public String getCommandsToString() {
        return "[ " + getCommands() + " ]";
    }

    public String toString() {
        return (getCommandName() + "\n" + getVariablesListToString()+ "\n" + getCommandsToString());
    }
    @Override
    public boolean isCommandReadyToExecute() {
        return getChildren().size()==myVariables.length;
    }

}
