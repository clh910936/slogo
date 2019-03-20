package Parsing;

import BackExternal.IllegalParametersException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

/**
 * @author christinachen
 * This class is used to keep track of the status of the progress of parsing through a
 * given command input string.
 * It is used by the CommandParser class so that it does not have to handle array creation and indexing.
 * It is also used by the SyntaxHandlerFactory class by giving information about the current index and by allowing
 * its index to be changed based on the syntax procedure.
 * For example, if the SyntaxHandlerFactory detects a group start or list start input, it can get the whole
 * group or list parameter from getting information from this class and then setting the final index after it finishes.
 */

public class ParserTracker {
    public static final String COMMENT_SYMBOL = "Comment";
    public static final String NEW_LINE_SYMBOL = "Newline";
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
        command = command.strip();
        commandInputList = command.split(String.valueOf(resources.getObject(NEW_LINE_SYMBOL)));
        for(int i = 0 ;i<commandInputList.length;i++) {
            String line = commandInputList[i];
            if(Regex.match(line, Pattern.compile((String) resources.getObject(COMMENT_SYMBOL), Pattern.CASE_INSENSITIVE))) {
                commandInputList[i] = "";
            }
        }
        commandInputList = (String.join(" ",commandInputList)).split(String.valueOf(resources.getObject(WHITE_SPACE)));
    }

    protected String[] getCommandInputList() {
        return commandInputList;
    }

    protected int getIndex() {return index;}

    protected void setNextInputString() {
        if(index>=commandInputList.length) {
            throw new IllegalParametersException();
        }
        rawInput = commandInputList[index].strip();
    }

    protected void setIndex(int val) {
        this.index=val;
    }

    protected String getRawInput() {
        return rawInput;
    }

    protected boolean isDoneParsing() {
        return index==commandInputList.length;
    }


}
