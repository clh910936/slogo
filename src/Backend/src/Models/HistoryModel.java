package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class HistoryModel {

    private List<HistoryEntry> myHistory;
    private final Predicate<Integer> wasSuccessful = index -> (myHistory.get(index).getSuccess());

    public HistoryModel() {
        myHistory = new ArrayList<>();
    }

    public void addHistoryEntry(String command, Boolean successful) {
        myHistory.add(new HistoryEntry(command,successful));
    }

    public List<String> getHistory() {
        List<String> history = new ArrayList<>();
        myHistory.forEach(entry -> history.add(entry.getCommandString()));
        return Collections.unmodifiableList(history);
    }

    public boolean getWasSuccessful(int i) throws IndexOutOfBoundsException {
        if(myHistory.size()<i) throw new IndexOutOfBoundsException();
        return wasSuccessful.test(i);
    }
}