package BackExternal;

public class IllegalTurtleStateException extends RuntimeException {
    /**
     * Create an exception based on an issue in our code.
     */
    public IllegalTurtleStateException() {
        super("Turtle state has different sized lists!");
    }
}
