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

    private Image image0 = new Image("./images/ms/team_course/課程設定2學員.png");

    public UserListPane(String ctxId, String username, int team) {
        super();
        System.out.println("UserListPane :  username => " + username);

        buttonLabel = new Label();
        buttonLabel.setPrefWidth(155.0);
        buttonLabel.setPrefHeight(33.0);
        buttonLabel.setFont(Main.customFont);
        buttonLabel.setLayoutX(6.0);
        buttonLabel.setLayoutY(119.0);
        buttonLabel.setText(username);

        buttonView = new ImageView();
        buttonView.setFitWidth(155.0);
        buttonView.setFitHeight(165.0);
        buttonView.setImage(image0);

        buttonPane = new Pane();
        if(team != 0){
            buttonPane.setOpacity(0.5);
        }
        buttonPane.setPrefWidth(155.0);
        buttonPane.setPrefHeight(165.0);

        buttonPane.setId(ctxId);

        buttonPane.getChildren().add(buttonView);
        buttonPane.getChildren().add(buttonLabel);
        buttonPane.setVisible(true);
    }


}