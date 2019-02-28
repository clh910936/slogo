package Models;

import Commands.UserDefinedCommand;

import java.util.*;

public class UserDefinedCommandsModel {

    Map<String,UserDefinedCommand> myUserDefinedCommands;


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
