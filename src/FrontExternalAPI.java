public interface FrontExternalAPI {
    /**
     * part of Board class
     * clears the drawings off of the board
     */
    public void clearBoard();

    /**
     * part of board class
     * changes the background color of the void
     * will have an argument for the color TBD
     */
    public void setBackgroundColor();

    /**
     * part of Console class
     * prints an error to be seen by the user
     */
    public void printError();

}
