package com.ncsist.sstp.cell.course;

import com.ncsist.sstp.Main;
import com.ncsist.sstp.vo.Course;
import com.ncsist.sstp.vo.User;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class UserListPane extends Pane{

//    @FXML
    private Label buttonLabel;

//    @FXML
    private ImageView buttonView;

//    @FXML
    @Getter
    private Pane buttonPane;

    private User user;

    private Image image0 = new Image("./images/ms/team_course/課程設定1預設.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定1觸碰.png");
    private Image image2 = new Image("./images/ms/team_course/課程設定1點選.png");

    public UserListPane(User user) {
        super();
        System.out.println("UserListPane :  user => " + user);

        this.user = user;

        buttonLabel = new Label();
        buttonLabel.setPrefWidth(80.0);
        buttonLabel.setPrefHeight(33.0);
        buttonLabel.setFont(Main.customFont);
        buttonLabel.setLayoutX(6.0);
        buttonLabel.setLayoutY(2.5);
        buttonLabel.setText(user.getStudentBatch() + "");

        buttonView = new ImageView();
        buttonView.setFitWidth(100.0);
        buttonView.setFitHeight(42.5);
        buttonView.setImage(image0);
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
            System.out.println("user : " + user);
            buttonView.setImage(image2);
        });
    }


}