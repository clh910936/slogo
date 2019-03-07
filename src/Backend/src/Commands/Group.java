package Commands;

import BackExternal.IllegalParametersException;
import BackExternal.ModelManager;
import Parsing.CommandParser;

public class Group extends ZeroParamCommand {
    private String[] myParamGroup;
    private CommandNode myCommand;
    private int paramsNeeded;

    public Group(String language, ModelManager modelManager, String[] myList, CommandNode command) {
        super(language, modelManager);
        this.myParamGroup = myList;
        this.myCommand = command;
        paramsNeeded = myCommand.getNumParamsNeeded();
    }

    @Override
    public Object executeCommand() throws ClassCastException,IllegalParametersException {
        if (paramsNeeded == 0 && myParamGroup.length > 1||myParamGroup.length==1) {
            throw new IllegalParametersException();
        }
        String commandToParse;
        if (paramsNeeded == 2) {
            commandToParse = handleTwoParams();
        }
        else {
            commandToParse = handleOtherParams();
        }
        CommandParser commandParser = new CommandParser(myModelManager);
        return commandParser.parseCommand(commandToParse,myLanguage);
    }

    private String handleTwoParams() {
        StringBuilder sb = new StringBuilder();
        int numCommandsNeeded = myParamGroup.length-2;
        String commandString = myParamGroup[0];
        for(int i = 0;i<numCommandsNeeded;i++) {
            sb.append(commandString);
        }
        for(int i = 1 ;i<myParamGroup.length;i++) {
            sb.append(myParamGroup[i]);
        }
        return sb.toString();
    }

    private String handleOtherParams() {
        StringBuilder sb = new StringBuilder();
        String commandString = myParamGroup[0];
        for(int i = 1;i<myParamGroup.length;i++) {
            if((i-1)%paramsNeeded==0) sb.append(commandString);
            sb.append(myParamGroup[i]);
        }
        return sb.toString();

    }
}
