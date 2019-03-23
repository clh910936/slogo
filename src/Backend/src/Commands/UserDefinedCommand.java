package Commands;

import BackExternal.ModelManager;

/**
 * @author christinachen
 * This class defines the command nodes for user defined commands so that the appropriate commands
 * would be executed when this object is used
 */

public class UserDefinedCommand extends CommandNode {
    private String commandName;
    private String commandsToExecute;
    private String[] myVariables;

    public UserDefinedCommand(ModelManager modelManager, String name, String commands, String[] var) {
        super(modelManager
);
        commandName = name;
        commandsToExecute = commands;
        myVariables = var;
    }

    @Override
    public Object executeCommand() throws ClassCastException {
        String commands = commandsToExecute;
        if(commands.length()==0) return 0;
        for (int i = 0; i < myVariables.length; i++) {
            String variable = myVariables[i];
            String param = String.valueOf(getMyParams().get(i));
            commands = commands.replaceAll(variable, param);
        }
        Object ret = getCp().parseCommand(commands);
        clearMyParams();
        clearChildren();
        return ret;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    /**
     *
     * @return commands that are associated to a user defined command name
     */
    public String getCommands() {
        return commandsToExecute;
    }

    /**
     *
     * @return variables necessary for the user defined commands
     */
    public String[] getMyVariables() { return myVariables;}

    /**
     *
     * @return string formatted variables joined by spaces
     */
    public String getVariablesToString() {
        return String.join(" ",myVariables);
    }

    /**
     *
     * @return list to string formatted variables
     */
    public String getVariablesListToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(getVariablesToString());
        sb.append(" ]");
        return sb.toString();
    }

    /**
     *
     * @return list to string formatted commands
     */
    public String getCommandsToString() {
        return "[ " + getCommands() + " ]";
    }

    /**
     *
     * @return string format of a user defined command
     */
    public String toString() {
        return (getCommandName() + "\n" + getVariablesListToString()+ "\n" + getCommandsToString());
    }
    @Override
    public boolean isCommandReadyToExecute() {
        return getChildren().size()==myVariables.length;
    }

}
