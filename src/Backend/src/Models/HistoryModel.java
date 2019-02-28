package Models;

import java.util.*;

public class HistoryModel {

    private Map<String,Boolean> myHistory;

    public HistoryModel() {
        myHistory = new HashMap<>();
    }

    public void addHistoryEntry(String command, Boolean successful) {
        myHistory.put(command, successful);
    }

    public Map<String,Boolean> getHistory() {
        return Collections.unmodifiableMap(myHistory);
    }
}
