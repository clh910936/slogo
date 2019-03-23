package FrontInternal.Players;

import FrontInternal.Util.MethodArgPair;
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
    private Queue<MethodArgPair> mySchedule;

    /**
     * The turtle scheduler acts upon a turtle and uses a Queue to enforce FIFO ordering.
     * @param t TurtleView on which to act
     */
    public TurtleScheduler(TurtleView t) {
        myTurtle = t;
        mySchedule = new LinkedList<>();
    }

    /**
     * Adds a method-argument pair to the schedule queue.
     * @param name name of the method
     * @param obj varargs list of parameters for this method
     */
    public void addToSchedule(String name, Object... obj) {
        List<Object> args = new ArrayList<>(Arrays.asList(obj));
        mySchedule.add(new MethodArgPair(name, args));
    }

    /**
     * This method dequeues a method from the schedule and applies it to the turtle.
     * Update is called every MILLISECOND_DELAY.
     */
    public void update() {
        if (!myTurtle.getBusy() && !mySchedule.isEmpty()) {
            MethodArgPair action = mySchedule.remove();
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
