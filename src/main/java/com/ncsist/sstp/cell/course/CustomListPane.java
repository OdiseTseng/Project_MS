package com.ncsist.sstp.cell.course;

import com.ncsist.sstp.Main;
import com.ncsist.sstp.vo.Course;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class CustomListPane extends Pane{
//    private HBox content;
//    private Text name;
//    private Text price;

//    @FXML
    private Label buttonLabel;

//    @FXML
    private ImageView buttonView;

//    @FXML
    @Getter
    private Pane buttonPane;

    private Course course;

    private Image image0 = new Image("./images/ms/team_course/課程設定1預設.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定1觸碰.png");
    private Image image2 = new Image("./images/ms/team_course/課程設定1點選.png");

    public CustomListPane(Course course) {
        super();
        System.out.println("CustomListPane :  course => " + course);

        this.course = course;

        buttonLabel = new Label();
        buttonLabel.setPrefWidth(80.0);
        buttonLabel.setPrefHeight(33.0);
        buttonLabel.setFont(Main.customFont);
        buttonLabel.setLayoutX(6.0);
        buttonLabel.setLayoutY(2.5);
        buttonLabel.setText(course.getCourseName());

        buttonView = new ImageView();
        buttonView.setFitWidth(100.0);
        buttonView.setFitHeight(42.5);
        buttonView.setImage(image0);
//        name = new Text();
//        price = new Text();
//        VBox vBox = new VBox(name, price);
//        content = new HBox(new Label("[Graphic]"), vBox);
//        content.setSpacing(10);
        buttonPane = new Pane();
        buttonPane.setPrefWidth(100.0);
        buttonPane.setPrefHeight(42.5);

        buttonPane.getChildren().add(buttonView);
        buttonPane.getChildren().add(buttonLabel);
        buttonPane.setVisible(true);

        buttonLabel.setOnMouseEntered(mouseDragEvent -> {
            buttonView.setImage(image1);
        });

        buttonLabel.setOnMouseExited(mouseDragEvent -> {
            buttonView.setImage(image0);
        });
        buttonLabel.setOnMouseClicked(mouseEvent -> {
            System.out.println("course : " + course);
            buttonView.setImage(image2);
        });
    }

    //    @Override
//    protected void updateItem(Course item, boolean empty) {
//        System.out.println("updateItem ;;; item : " + item + " empty : " + empty);
//        super.updateItem(item, empty);
//        if (item != null && !empty) { // <== test for null item and empty parameter
//            course = item;
////            name.setText(item.getName());
////            price.setText(String.format("%d $", item.getPrice()));
////            setGraphic(content);
//            buttonLabel.setText(item.getCourseName());
////            buttonLabel = new Label(item.getCourseName());
//            buttonView.setImage(image0);
////            setGraphic(buttonView);
//            setGraphic(buttonPane);
//        } else {
//            setGraphic(null);
//        }
//    }


}