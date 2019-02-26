package Parsing.History;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryModel {

    private List<String> myHistory;

    public HistoryModel() {
        myHistory = new ArrayList<>();
    }

    public void addHistoryEntry(String command) {
        myHistory.add(command);
    }

    public List<String> getVariables() {
        return Collections.unmodifiableList(myHistory);
    }
}
