package API;

import java.util.List;
import java.util.Map;

public interface IModelManager {

    List<String> getUserDefinedCommands();
    Map<String,String> getVariables();
    List<String> getHistory();
    void parseCommand(String input, String language);
    boolean getWasSuccessfulHistory(int i);


}
