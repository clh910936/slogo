package Models;

import Commands.UserDefinedCommand;

import java.util.*;

public class UserCreatedCommandsModel {

    List<UserDefinedCommand> myUserDefinedCommands;


    public UserCreatedCommandsModel() {
        myUserDefinedCommands = new ArrayList<>();
    }

    public void addUserCreatedCommand(UserDefinedCommand command) {
        myUserDefinedCommands.add(command);
    }

    public List<UserDefinedCommand> getUserCreatedCommands() {
        return Collections.unmodifiableList(myUserDefinedCommands);
    }
}
