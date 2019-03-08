package Parsing;

import BackExternal.IllegalParametersException;
import Commands.CommandNode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

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
        System.out.println("COMMAND STRING" + command);
        commandInputList = command.split(String.valueOf(resources.getObject(NEW_LINE_SYMBOL)));
        System.out.println("AFTER FIRST SPLIT" + Arrays.toString(commandInputList));
        for(int i = 0 ;i<commandInputList.length;i++) {
            String line = commandInputList[i];
            if(Regex.match(line, Pattern.compile((String) resources.getObject(COMMENT_SYMBOL), Pattern.CASE_INSENSITIVE))) {
                commandInputList[i] = "";
            }
        }
        System.out.println("AFTER FOR LOOP" + Arrays.toString(commandInputList));
        System.out.println("COMMAND LIST SPLIT" + String.join(" ",commandInputList));
        commandInputList = (String.join(" ",commandInputList)).split(String.valueOf(resources.getObject(WHITE_SPACE)));
        System.out.println("AFTER SECOND SPLIT" + Arrays.toString(commandInputList));

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
