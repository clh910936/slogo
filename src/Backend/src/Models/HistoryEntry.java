package Models;

public class HistoryEntry {
    private String myCommand;
    private boolean successful;

    public HistoryEntry(String command, boolean successful) {
        myCommand = command;
        this.successful = successful;
    }

    public boolean getSuccess() {
        return successful;
    }

    public String getCommandString() {
        return myCommand;
    }
}
