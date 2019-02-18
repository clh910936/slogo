public interface BackExternalAPI {


    /**
     * Part of the controller. Front-end calls parse() to give unprocessed commands to back-end.
     * @return
     */
    public Object parse();
}
