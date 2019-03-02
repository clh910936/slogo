package BackExternal;

import java.util.List;

public interface ITurtle {

    List<Double> getUpdatedX();
    List<Double> getUpdatedY();
    List<Boolean> getIsPenUp();
    List<Double> getHeadingAngle();
    List<Boolean> getIsDisplayed();

//    1  2  3  4  5  6
//      1  0  1  0  1  0
    //background index
    //pencolor index
    //pensize pixels
    //setshape index
    //setpalette ??? talk to feroze and carrie

    //boolean getReceive();

//    List<Integer> getBackGroundIndex();
//    List<Integer> getPenColorIndex();
//    List<Double> getPenSize();
//    List<Integer> getShapeIndex();


}
