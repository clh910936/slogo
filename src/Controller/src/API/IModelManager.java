package API;

import java.util.List;
import java.util.Map;

public interface IModelManager {

    Map<String,String> getUserDefinedCommands();
    Map<String,String> getVariables();
    List<String> getHistory();
    void parseCommand(String input, String language);
    boolean getWasSuccessfulHistory(int i);
    void saveCurrentState(String fileName);
    void setStateFromFile(String fileName, String language);
    List<String> getSavedFilesList();
    void changeVariable(String variableName, String value);

}
