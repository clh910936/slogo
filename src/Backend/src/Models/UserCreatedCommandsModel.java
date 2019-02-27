package Models;

import Commands.UserDefinedCommand;

import java.util.*;

public class UserCreatedCommandsModel {

    Map<String,UserDefinedCommand> myUserDefinedCommands;


    public UserCreatedCommandsModel() {
        myUserDefinedCommands = new HashMap<>();
    }

    public void addUserCreatedCommand(UserDefinedCommand command) {
        myUserDefinedCommands.put(command.getCommandName(),command);
    }

    public Map<String,UserDefinedCommand> getUserCreatedCommands() {
        return Collections.unmodifiableMap(myUserDefinedCommands);
    }
}
