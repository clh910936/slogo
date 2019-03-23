package FrontInternal.Util;

import java.util.List;

/**
 * Keeps a method name and list of parameters, used by `TurtleScheduler.java`.
 * @author Feroze
 */
public class MethodArgPair {
    public final String x;
    public final List y;
    public MethodArgPair(String x, List y) {
        this.x = x;
        this.y = y;
    }
}
