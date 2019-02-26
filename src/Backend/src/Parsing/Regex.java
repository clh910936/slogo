package Parsing;

import Exceptions.IllegalCommandException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Collections;
import java.util.AbstractMap;
import java.util.List;
import java.util.regex.Pattern;

public class Regex {

    public static List<Map.Entry<String, Pattern>> addPatterns (String syntax, List<Map.Entry<String, Pattern>> mySymbols) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.add(new AbstractMap.SimpleEntry<>(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
        return mySymbols;
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    private static String getSymbol (String text, List<Map.Entry<String, Pattern>> mySymbols) throws IllegalCommandException {
        for (var e : mySymbols) {
            if (match(text, e.getValue())) {
                return e.getKey();
            }
        }
        throw new IllegalCommandException(text + " is not a valid command");
    }

    /**
     * Returns true if the given text matches the given regular expression pattern
     */
    private static boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    public static String getRegexSymbol(String rawInput, List<Map.Entry<String, Pattern>> mySymbols) {
        String input;
        try {
            input = getSymbol(rawInput, mySymbols);
        }
        catch (IllegalCommandException e){
            throw e;
        }
        return input;
    }
}
