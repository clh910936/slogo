package Models;

import API.FrontExternalAPI;
import Commands.UserDefinedCommand;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class UserDefinedCommandsModel {

    private Map<String,UserDefinedCommand> myUserDefinedCommands;


    public UserDefinedCommandsModel() {
        myUserDefinedCommands = new HashMap<>();
    }
    public void addUserCreatedCommand(UserDefinedCommand command) {
        myUserDefinedCommands.put(command.getCommandName(),command);
    }

    public Map<String,UserDefinedCommand> getUserCreatedCommands() {
        return Collections.unmodifiableMap(myUserDefinedCommands);
    }

}
