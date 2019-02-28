package BackExternal;

/**
 * Implemented by any view that has a corresponding backend model
 */
public interface ViewAPI {
    /**
     * this method needs to be implemented such that the view
     * updates its information based on its backend model
     */
    public void update();
}
