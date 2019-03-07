package Parsing;

import BackExternal.IllegalCommandException;

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
     * Returns true if the given text matches the given regular expression pattern
     */
    public static boolean match (String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    /**
     * Returns language's type associated with the given text if one exists
     */
    public static String getRegexSymbol(String rawInput, List<Map.Entry<String, Pattern>> mySymbols) throws IllegalCommandException {
        for (var e : mySymbols) {
            if (match(rawInput, e.getValue())) {
                return e.getKey();
            }
        }
        throw new IllegalCommandException(rawInput + " is not in the file");
    }
}
