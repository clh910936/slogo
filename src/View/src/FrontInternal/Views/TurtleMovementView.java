package FrontInternal.Views;


import FrontInternal.Util.Operator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class TurtleMovementView implements ViewAPI {
    Operator myOperator;
    GridPane myGridPane;

    public TurtleMovementView(Operator operator){
        System.out.println("Made it to creating the TurtleMovementView");
        myOperator = operator;
        myGridPane = new GridPane();
        createForwardButton();
    }
    private void createForwardButton(){
        System.out.println("Made it to createForwardButton");
        Image arrow = new Image("/arrow.png");
        ImageView image = new ImageView(arrow);
        Pane temp = new Pane();
        temp.getChildren().add(image);
        myGridPane.add(temp, 1, 0);
    }

    @Override
    public void update() {

    }

    @Override
    public Pane getPane() {
        return myGridPane;
    }
}
