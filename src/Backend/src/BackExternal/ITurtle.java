package BackExternal;

import java.util.List;

public interface ITurtle {

    List<Double> getUpdatedX();
    List<Double> getUpdatedY();
    List<Boolean> getIsPenUp();
    List<Double> getHeadingAngle();
    List<Boolean> getIsDisplayed();

    //background index
    //pencolor index
    //pensize pixels
    //setshape index
    //setpalette ??? talk to feroze and carrie
    //

//    List<Integer> getBackGroundIndex();
//    List<Integer> getPenColorIndex();
//    List<Double> getPenSize();
//    List<Integer> getShapeIndex();


}
