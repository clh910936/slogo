package Models;

import API.FrontExternalAPI;

/**
 * @author michaelzhang
 * This model stores the index of the background color
 */
public class BackgroundModel {
    private FrontExternalAPI myFrontEnd;
    int colorIndex;

    /**
     * Constructor for BackgroundModel that initializes color index to zero.
     * @param fe
     */
    public BackgroundModel(FrontExternalAPI fe) {
        myFrontEnd = fe;
        colorIndex = 0;
    }

    /**
     * Changes background to given index
     * @param index
     */
    public void setCurrentBackgroundIndex(int index) {
        this.colorIndex = index;
        myFrontEnd.setBackgroundColor(index);
    }

}
