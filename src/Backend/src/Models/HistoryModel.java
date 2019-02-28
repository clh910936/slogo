package Models;

import java.util.*;
import java.util.function.Predicate;

public class HistoryModel {

    private List<HistoryEntry> myHistory;
    public final Predicate<Integer> wasSuccessful = index -> (myHistory.get(index).getSuccess());

    public HistoryModel() {
        myHistory = new ArrayList<>();
    }

    public void addHistoryEntry(String command, Boolean successful) {
        myHistory.add(new HistoryEntry(command,successful));
    }

    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        for (HistoryEntry entry : myHistory) {
            history.add(entry.getCommandString());
        }
        return Collections.unmodifiableList(history);
    }

    public boolean getWasSuccessful(int i) { return wasSuccessful.test(i);}
}
