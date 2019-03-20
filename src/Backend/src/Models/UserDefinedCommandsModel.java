package Models;

import Commands.UserDefinedCommand;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author christinachen
 * This class keeps track of all user defined commands
 */

public class UserDefinedCommandsModel {

    private Map<String,UserDefinedCommand> myUserDefinedCommands;

    public UserDefinedCommandsModel() {
        myUserDefinedCommands = new HashMap<>();
    }

    /**
     * Adds a user created command to the model
     * @param command
     */
    public void addUserCreatedCommand(UserDefinedCommand command) {
        myUserDefinedCommands.put(command.getCommandName(),command);
    }

    /**
     * @return all user defined commands
     */
    public Map<String,UserDefinedCommand> getUserCreatedCommands() {
        return Collections.unmodifiableMap(myUserDefinedCommands);
    }

}
