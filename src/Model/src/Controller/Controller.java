package Controller;

import BackExternal.BackExternalAPI;
import Exceptions.IllegalCommandException;
import Exceptions.ParamsExceedLimitException;
import History.HistoryModel;
import Parsing.CommandParser;
import Turtle.TurtleModel;
import Variables.VariablesModel;

import java.util.List;
import java.util.Map;

public class Controller implements BackExternalAPI {
    private final VariablesModel myVariablesModel;
    private final HistoryModel myHistoryModel;
    private final TurtleModel myTurtleModel;
    private final CommandParser myCommandParser;

    public Controller() {
        myVariablesModel = new VariablesModel();

        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel(0,0,false, 0, true);
        myCommandParser = new CommandParser(myVariablesModel, myTurtleModel);
    }

    public void parseCommand(String inputString, String language) throws IllegalCommandException, ParamsExceedLimitException {
        try {
            myCommandParser.parseCommand(inputString, language);
        }
        catch (IllegalCommandException e) {
            myHistoryModel.addHistoryEntry(inputString, false);
            throw e;
        }
        catch (ParamsExceedLimitException e) {
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

    public List<String> getUserCreatedCommands() {

    }

}
