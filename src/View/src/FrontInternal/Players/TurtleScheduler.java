package FrontInternal.Players;

import FrontInternal.Util.Pair;

import java.lang.reflect.Method;
import java.util.*;

public class TurtleScheduler {
    private TurtleView myTurtle;
    private Queue<Pair> mySchedule = new LinkedList<>();
    public TurtleScheduler(TurtleView t) {
        myTurtle = t;
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
                e.printStackTrace();
            }
        }
    }
}
