package BackExternal;

import java.util.List;
import java.util.Map;

public interface IModelManager {

    Map<String,String> getUserDefinedCommands();
    Map<String,String> getVariables();
    List<String> getHistory();
    void parseCommand(String input, String language);
    boolean getWasSuccessfulHistory(int i);
    Map<Integer,ITurtle> getTurtleList();
    void saveCurrentState(String fileName);
    void setStateFromFile(String fileName, String language);
    void changeVariable(String variableName, String value);
}
