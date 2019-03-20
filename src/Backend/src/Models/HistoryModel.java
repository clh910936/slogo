package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;


/**
 * @author christinachen
 * This class keeps track of the command input history of the program
 */
public class HistoryModel {

    private List<HistoryEntry> myHistory;
    private final Predicate<Integer> wasSuccessful = index -> (myHistory.get(index).getSuccess());

    public HistoryModel() {
        myHistory = new ArrayList<>();
    }

    /**
     * Allows commands to be added to the history
     * @param command
     * @param successful
     */
    public void addHistoryEntry(String command, Boolean successful) {
        myHistory.add(new HistoryEntry(command,successful));
    }

    /**
     * @return history list
     */
    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        myHistory.forEach(entry -> history.add(entry.getCommandString()));
        return Collections.unmodifiableList(history);
    }

    /**
     * Gets the success of a command from history
     * @param i index of the command in the history list
     * @return boolean
     * @throws IndexOutOfBoundsException
     */
    public boolean getWasSuccessful(int i) throws IndexOutOfBoundsException {
        if(myHistory.size()<i) throw new IndexOutOfBoundsException();
        return wasSuccessful.test(i);
    }
}