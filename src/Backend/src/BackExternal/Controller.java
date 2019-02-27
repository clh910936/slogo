package BackExternal;

import Models.HistoryModel;
import Models.UserCreatedCommandsModel;
import Parsing.CommandParser;
import Models.TurtleModel;
import Models.VariablesModel;
import java.util.List;
import java.util.Map;

public class Controller implements BackExternalAPI {
    private final VariablesModel myVariablesModel;
    private final HistoryModel myHistoryModel;
    private final TurtleModel myTurtleModel;
    private final CommandParser myCommandParser;
    private final UserCreatedCommandsModel myUserCreatedCommandsModel;

    public Controller() {
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel(0,0,false, 0, true);
        myCommandParser = new CommandParser(myVariablesModel, myTurtleModel);
        myUserCreatedCommandsModel = new UserCreatedCommandsModel();
    }

    public void parseCommand(String inputString, String language) throws IllegalCommandException, IllegalParametersException {
        try {
            myCommandParser.parseCommand(inputString, language);
        }
        catch (IllegalCommandException e) {
            myHistoryModel.addHistoryEntry(inputString, false);
            throw e;
        }
        catch (IllegalParametersException e) {
            myHistoryModel.addHistoryEntry(inputString, false);
            throw e;
        }
        myHistoryModel.addHistoryEntry(inputString, true);
    }

    public Map<String, String> getVariables() {
        return myVariablesModel.getVariables();
    }

    public List<String> getHistory() {
        return myHistoryModel.getHistory();
    }

    public TurtleModel getTurtle() {
        return myTurtleModel;
    }

    public Map<String, Map<String,String>> getUserCreatedCommands() {
        return myUserCreatedCommandsModel.getUserCreatedCommands();
    }

}
