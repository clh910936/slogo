package Models;

import BackExternal.IllegalCommandException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCreatedCommandsModel {

    Map<String, Map<String, String>> myUserCreatedCommands;

    // name : string
    // vars : list
    // commands : string


    public UserCreatedCommandsModel() {
        myUserCreatedCommands = new HashMap<>();
    }

    public void addUserCreatedCommand(String name, String variable, String command) {
        Map<String, String> varAndCommands = new HashMap<>();
        varAndCommands.put(variable, command);
        myUserCreatedCommands.put(name, varAndCommands);
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
