package History;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoryModel {

    private List<String> myHistory;

    public HistoryModel() {
        myHistory = new ArrayList<>();
    }

    public void addHistoryEntry(String command, Boolean successful) {
        myHistory.add(command);
    }

    public List<String> getHistory() {
        return Collections.unmodifiableList(myHistory);
    }
}
