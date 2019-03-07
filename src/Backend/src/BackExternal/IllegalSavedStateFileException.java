package BackExternal;

public class IllegalSavedStateFileException extends RuntimeException {
    public IllegalSavedStateFileException() { super("Saved state file could not be read");}
}
