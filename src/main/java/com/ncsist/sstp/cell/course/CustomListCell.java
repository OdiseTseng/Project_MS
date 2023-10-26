package com.ncsist.sstp.cell.course;

import com.ncsist.sstp.Main;
import com.ncsist.sstp.vo.Course;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class CustomListCell extends ListCell<Course> {
//    private HBox content;
//    private Text name;
//    private Text price;
    
//    @FXML
    private Label buttonLabel;
    
//    @FXML
    private ImageView buttonView;

//    @FXML
    private Pane buttonPane;

    private Course course;

    private Image image0 = new Image("./images/ms/team_course/課程設定1預設.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定1觸碰.png");
    private Image image2 = new Image("./images/ms/team_course/課程設定1點選.png");

    public CustomListCell() {
        super();

        buttonLabel = new Label();
        buttonLabel.setPrefWidth(128.0);
        buttonLabel.setPrefHeight(43.0);
        buttonLabel.setFont(Main.customFont);
        buttonLabel.setLayoutX(4.0);
        buttonLabel.setLayoutY(1.5);

        buttonView = new ImageView();
        buttonView.setFitWidth(144.0);
        buttonView.setFitHeight(49.0);
//        name = new Text();
//        price = new Text();
//        VBox vBox = new VBox(name, price);
//        content = new HBox(new Label("[Graphic]"), vBox);
//        content.setSpacing(10);
        buttonPane = new Pane();
        buttonPane.setPrefWidth(144.0);
        buttonPane.setPrefHeight(49.0);

        buttonPane.getChildren().add(buttonView);
        buttonPane.getChildren().add(buttonLabel);

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

    @Override
    protected void updateItem(Course item, boolean empty) {
        System.out.println("updateItem ;;; item : " + item + " empty : " + empty);
        super.updateItem(item, empty);
        if (item != null && !empty) { // <== test for null item and empty parameter
            course = item;
//            name.setText(item.getName());
//            price.setText(String.format("%d $", item.getPrice()));
//            setGraphic(content);
            buttonLabel.setText(item.getCourseName());
//            buttonLabel = new Label(item.getCourseName());
            buttonView.setImage(image0);
//            setGraphic(buttonView);
            setGraphic(buttonPane);
        } else {
            setGraphic(null);
        }
    }
}