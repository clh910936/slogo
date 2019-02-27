package BackExternal;

import Commands.UserDefinedCommand;
import Models.HistoryModel;
import Models.UserCreatedCommandsModel;
import Parsing.CommandParser;
import Models.TurtleModel;
import Models.VariablesModel;
import java.util.List;
import java.util.Map;

public class Controller implements BackExternalAPI {
    public static final double STARTX = 2000;
    public static final double STARTY = 2000;
    private final VariablesModel myVariablesModel;
    private final HistoryModel myHistoryModel;
    private final TurtleModel myTurtleModel;
    private final CommandParser myCommandParser;
    private final UserCreatedCommandsModel myUserCreatedCommandsModel;

    public Controller() {
        myVariablesModel = new VariablesModel();
        myHistoryModel = new HistoryModel();
        myTurtleModel = new TurtleModel(STARTX, STARTY,false, 0, true);
        myUserCreatedCommandsModel = new UserCreatedCommandsModel();
        myCommandParser = new CommandParser(myVariablesModel, myTurtleModel, myUserCreatedCommandsModel);
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

    public List<UserDefinedCommand> getUserCreatedCommands() {
        return myUserCreatedCommandsModel.getUserCreatedCommands();
    }

}
