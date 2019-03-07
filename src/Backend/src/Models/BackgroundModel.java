package Models;

import API.FrontExternalAPI;

public class BackgroundModel {
    private FrontExternalAPI myFrontEnd;
    int colorIndex;

    public BackgroundModel(FrontExternalAPI fe) {
        myFrontEnd = fe;
        colorIndex = 0;
    }

    public void setCurrentBackgroundIndex(int index) {
        this.colorIndex = index;
        myFrontEnd.setBackgroundColor(index);
    }

}
