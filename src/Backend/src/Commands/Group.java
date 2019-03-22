package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Group extends ZeroParamCommand {
    private String[] myParamGroup;
    private CommandNode myCommand;
    private int paramsNeeded;
    private static final String GROUPING_ALLOWED_FILE = "resources/Commands/allow-grouping";

    public Group(ModelManager modelManager
            , String[] myList, CommandNode command) {
        super(modelManager
        );
        this.myParamGroup = myList;
        this.myCommand = command;
        paramsNeeded = myCommand.getNumParamsNeeded();
    }

    @Override
    public Object executeCommand() throws ClassCastException,IllegalParametersException {
        var resources = ResourceBundle.getBundle(GROUPING_ALLOWED_FILE);
        String commandToParse;
        if(resources.containsKey(myCommand.getCommandName())) {
            commandToParse = handleGrouping();
        }
        else {
            commandToParse = handleOther();
        }
        return getCp().parseCommand(commandToParse);
    }

    private String handleGrouping() {
        List<String> groupingList = new ArrayList<>();
        int numCommandsNeeded = myParamGroup.length-2;
        String commandString = myParamGroup[0];
        for(int i = 0;i<numCommandsNeeded;i++) {
            groupingList.add(commandString);
        }
        for(int i = 1 ;i<myParamGroup.length;i++) {
            groupingList.add(myParamGroup[i]);
        }
        return String.join(" ",groupingList);
    }

    private String handleOther() {
        List<String> groupingList = new ArrayList<>();
        String commandString = myParamGroup[0];
        for(int i = 1;i<myParamGroup.length;i++) {
            if((i-1)%paramsNeeded==0) groupingList.add(commandString);
            groupingList.add(myParamGroup[i]);
        }
        return String.join(" ",groupingList);
    }
}
