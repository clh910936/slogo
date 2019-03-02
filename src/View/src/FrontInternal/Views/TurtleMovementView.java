package FrontInternal.Views;


import FrontInternal.Util.Operator;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class TurtleMovementView implements ViewAPI {
    private Operator myOperator;
    private GridPane myGridPane;
    private static final int BUTTON_WIDTH = 50;
    private static final int BUTTON_HEIGHT = 50;
    private static final String ARROW_IMAGE = "/arrow.png";
    private static final String RIGHT_ROTATE = "/right_rotate.png";
    private static final String LEFT_ROTATE = "/left_rotate.png";



    public TurtleMovementView(Operator operator){
        myOperator = operator;
        myGridPane = new GridPane();

        Pane forward = createFormatArrow(0, ARROW_IMAGE);
        forward.setOnMouseClicked(e -> forward());
        myGridPane.add(createFormatArrow(0, ARROW_IMAGE), 1, 0);


        myGridPane.add(createFormatArrow(270, ARROW_IMAGE), 0, 1);
        myGridPane.add(createFormatArrow(90, ARROW_IMAGE), 2, 1);
        myGridPane.add(createFormatArrow(180, ARROW_IMAGE), 1, 2);
        myGridPane.add(createFormatArrow(0, RIGHT_ROTATE), 2, 0);
        myGridPane.add(createFormatArrow(0, LEFT_ROTATE), 0, 0);
    }

    private void forward() {

    }

    private Pane createFormatArrow(int rotation, String imageName){
        Image arrow = new Image(imageName);
        ImageView image = new ImageView(arrow);
        image.setFitHeight(BUTTON_HEIGHT);
        image.setFitWidth(BUTTON_WIDTH);
        image.setRotate(rotation);
        Pane temp = new Pane();
        temp.getChildren().add(image);
        return temp;
    }




    @Override
    public void update() {

    }

    @Override
    public Pane getPane() {
        return myGridPane;
    }
}
