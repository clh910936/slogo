package BackExternal;

import Commands.UserDefinedCommand;
import java.util.List;
import java.util.Map;

public interface IModelManager {

    Map<String, UserDefinedCommand> getUserDefinedCommands();
    Map<String,String> getVariables();
    Map<String,Boolean> getHistory();
    void parseCommand(String input, String language);
    List<ITurtle> getTurtleList();


}
