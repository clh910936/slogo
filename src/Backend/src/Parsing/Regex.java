package Parsing;

import BackExternal.IllegalCommandException;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Collections;
import java.util.regex.Pattern;

/**
 * @author christinachen
 * This is a utility class to handle all regex operations
 */

public class Regex {
    private Regex() {
    }

    /**
     * Used to add patterns into a map using the resources file
     * Assumes that the resources file has string mapped to a regex pattern
     * @param syntax file path of file to be read from
     * @param mySymbols
     */
    public static void addPatterns (String syntax, Map<String, Pattern> mySymbols) {
        var resources = ResourceBundle.getBundle(syntax);
        for (var key : Collections.list(resources.getKeys())) {
            var regex = resources.getString(key);
            mySymbols.put(key,
                    Pattern.compile(regex, Pattern.CASE_INSENSITIVE));
        }
    }


    /**
     * @param text
     * @param regex
     * @return boolean based on if the given text matches the given regular expression pattern
     */
    public static boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }


    /**
     *
     * @param rawInput
     * @param mySymbols
     * @return string key that is associated with a given raw input string
     * @throws IllegalCommandException
     */
    public static String getRegexSymbol(String rawInput, Map<String, Pattern> mySymbols) throws IllegalCommandException {
        for (Map.Entry<String,Pattern> e : mySymbols.entrySet()) {
            if (match(rawInput, e.getValue())) {
                return e.getKey();
            }
        }
        throw new IllegalCommandException(rawInput + " is not in the file");
    }
}
