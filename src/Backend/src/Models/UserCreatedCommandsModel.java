package Models;

import BackExternal.IllegalCommandException;
import Commands.UserDefinedCommand;

import java.util.*;

public class UserCreatedCommandsModel {

    List<UserDefinedCommand> listOfUserDefinedCommands;

    // name : string
    // vars : list
    // commands : string


    public UserCreatedCommandsModel() {
        listOfUserDefinedCommands = new ArrayList<>();
    }

    public void addUserCreatedCommand(UserDefinedCommand command) {
        listOfUserDefinedCommands.add(command);
    }

    private Map<String, String> getUserCreatedCommand(String name) throws IllegalCommandException {
        if (myUserCreatedCommands.containsKey(name)) {
            return myUserCreatedCommands.get(name);
        } else {
            throw new IllegalCommandException("Command does not exist");
        }
    }

    public Map<String, Map<String, String>> getUserCreatedCommands() {
        return Collections.unmodifiableMap(myUserCreatedCommands);
    }
}
