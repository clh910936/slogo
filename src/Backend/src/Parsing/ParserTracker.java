package Parsing;

import BackExternal.IllegalParametersException;
import Commands.CommandNode;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ParserTracker {
    public static final String COMMENT_SYMBOL = "Comment";
    public static final String WHITE_SPACE = "Whitespace";

    private int index;
    private String[] commandInputList;
    private String rawInput;

    public ParserTracker(String command) {
        index = 0;
        makeInputArray(command);
    }

    private void makeInputArray(String command) {
        var resources = ResourceBundle.getBundle(SyntaxHandlerFactory.SYNTAX_FILE);
        commandInputList = command.split(resources.getObject(WHITE_SPACE).toString());
        for(int i = 0 ;i<commandInputList.length;i++) {
            String line = commandInputList[i];
            if(Regex.match(line, Pattern.compile((String) resources.getObject(COMMENT_SYMBOL), Pattern.CASE_INSENSITIVE))) {
                commandInputList[i] = "";
            }
        }
        commandInputList = String.join(" ",commandInputList).split(resources.getObject(WHITE_SPACE).toString());
    }

    public String[] getCommandInputList() {
        return commandInputList;
    }
    public int getIndex() {return index;}

    public void setNextInputString() {
        if(index>=commandInputList.length) {
            throw new IllegalParametersException();
        }
        rawInput = commandInputList[index];
    }

    public void setIndex(int val) {
        this.index=val;
    }

    public String getRawInput() {
        return rawInput;
    }

    protected boolean isDoneParsing() {
        return index==commandInputList.length;
    }


}
