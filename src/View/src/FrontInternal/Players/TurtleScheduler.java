package FrontInternal.Players;

import FrontInternal.Util.Pair;
import java.lang.reflect.Method;
import java.util.*;

/**
 * The TurtleScheduler implements a behavior similar to a thread-scheduler within an operating system. It uses
 * reflection to take in methods and their arguments and adds them to a schedule queue. These methods are then invoked
 * with their arguments when the last method has completed.
 * @author Feroze
 */
public class TurtleScheduler {
    private TurtleView myTurtle;
    private Queue<Pair> mySchedule;
    public TurtleScheduler(TurtleView t) {
        myTurtle = t;
        mySchedule = new LinkedList<>();
    }

    public void addToSchedule(String name, Object... obj) {
        List<Object> args = new ArrayList<>(Arrays.asList(obj));
        mySchedule.add(new Pair(name, args));
    }

    public void update() {
        if (!myTurtle.getBusy() && !mySchedule.isEmpty()) {
            Pair action = mySchedule.remove();
            try {
                for (Method m: myTurtle.getClass().getMethods()) {
                    if(action.x.equals(m.getName())) {
                        m.invoke(myTurtle, action.y.toArray(new Object[action.y.size()]));
                    }
                }
            }
            catch (Exception e) {
                return;
            }
        }
    }
}
