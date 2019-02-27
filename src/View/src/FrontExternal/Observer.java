package FrontExternal;

/**
 * External interface such that a view object can implement the interface
 * and the Model backend can call its abstraction to notify the object
 */
public interface Observer {
    public void update();
}
