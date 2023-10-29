package com.ncsist.sstp.cell.course;

import com.ncsist.sstp.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class TeamUserPane extends Pane{

//    @FXML
    private Label buttonLabel;

//    @FXML
    private ImageView buttonView;

//    @FXML
    @Getter
    private Pane pane;

    private Image image0 = new Image("./images/ms/team_course/課程設定2學員.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定2學員_已選取-removebg-preview.png");

    public TeamUserPane(String ctxId, String username, int team, boolean isSmall) {
        super();
        System.out.println("UserListPane :  username => " + username);

        buttonLabel = new Label();
        if(isSmall){
            buttonLabel.setPrefWidth(82.5);
            buttonLabel.setPrefHeight(15.0);
            buttonLabel.setLayoutX(22.5);
            buttonLabel.setLayoutY(60.0);
        }else{
            buttonLabel.setPrefWidth(110.0);
            buttonLabel.setPrefHeight(20.0);
            buttonLabel.setLayoutX(30.0);
            buttonLabel.setLayoutY(80.0);
        }
        buttonLabel.setFont(Main.customFont);
        buttonLabel.setText(username);

        buttonView = new ImageView();
        if(isSmall){
            buttonView.setFitWidth(82.5);
            buttonView.setFitHeight(97.5);
        }else{
            buttonView.setFitWidth(110.0);
            buttonView.setFitHeight(130.0);
        }

        pane = new Pane();
        if(!isSmall && team > 0){
//            buttonView.setImage(image1);
            pane.setOpacity(0.5);
        }
//        else{
//            buttonView.setImage(image0);
//        }

        buttonView.setImage(image0);
        if(isSmall){
            pane.setPrefWidth(82.5);
            pane.setPrefHeight(97.5);
        }else{
            pane.setPrefWidth(110.0);
            pane.setPrefHeight(130.0);
        }

        pane.setId(ctxId);

        pane.getChildren().add(buttonView);
        pane.getChildren().add(buttonLabel);
        pane.setVisible(true);
    }


}