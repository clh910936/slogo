package Models;

public class HistoryEntry {
    private String myCommand;
    private boolean successful;

    public HistoryEntry(String command, boolean successful) {
        myCommand = command;
        this.successful = successful;
    }

    protected boolean getSuccess() {
        return successful;
    }

    protected String getCommandString() {
        return myCommand;
    }
}
