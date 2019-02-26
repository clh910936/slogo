package Models;

import Exceptions.IllegalCommandException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserCreatedCommandsModel {

    Map<String, String> myUserCreatedCommands;

    public UserCreatedCommandsModel() {
        myUserCreatedCommands = new HashMap<>();
    }

    public void addUserCreatedCommand(String variable, String command) {
        myUserCreatedCommands.put(variable, command);
    }

    public String getUserCreatedCommand(String variable) throws IllegalCommandException {
        if (myUserCreatedCommands.containsKey(variable)) {
            return myUserCreatedCommands.get(variable);
        } else {
            throw new IllegalCommandException("Command does not exist");
        }
    }

    public Map<String, String> getUserCreatedCommands() {
        return Collections.unmodifiableMap(myUserCreatedCommands);
    }
}
