package API;

import java.util.List;
import java.util.Map;

public interface IModelManager {

    Map<String,String> getUserDefinedCommands();
    Map<String,String> getVariables();
    List<String> getHistory();
    void parseCommand(String input, String language);
    boolean getSuccessOfHistoryEntry(int index);
    void saveCurrentState(String fileName);
    void setStateFromFile(String fileName, String language);
    List<String> getSavedFilesList();
    void changeVariable(String variableName, String value);
    void addPalette(int index, int r, int g, int b);

}
