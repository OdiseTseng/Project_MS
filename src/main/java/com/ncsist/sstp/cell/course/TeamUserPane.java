package com.ncsist.sstp.cell.course;

import com.ncsist.sstp.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class TeamUserPane extends Pane{

    private Label labelSelected;

    private Label label;

    private ImageView imageView;

    @Getter
    private Pane pane;

    private Image image0 = new Image("./images/ms/team_course/課程設定2學員.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定2學員_已選取-removebg-preview.png");

    public TeamUserPane(String ctxId, String username, int team, boolean isSmall) {
        super();
        System.out.println("TeamUserPane :  username => " + username);

        labelSelected = new Label();
        label = new Label();
        if(isSmall){
            label.setPrefWidth(82.5);
            label.setPrefHeight(15.0);
            label.setLayoutX(22.5);
            label.setLayoutY(60.0);
        }else{
            labelSelected.setPrefWidth(110.0);
            labelSelected.setPrefHeight(20.0);
            labelSelected.setLayoutX(40.0);
            labelSelected.setFont(Main.customFont);
            labelSelected.setText("已選");

            label.setPrefWidth(110.0);
            label.setPrefHeight(20.0);
            label.setLayoutX(30.0);
            label.setLayoutY(90.0);
        }
        label.setFont(Main.customFont);
        label.setText(username);

        imageView = new ImageView();
        if(isSmall){
            imageView.setFitWidth(82.5);
            imageView.setFitHeight(97.5);
        }else{
            imageView.setFitWidth(110.0);
            imageView.setFitHeight(120.0);
            imageView.setLayoutY(10.0);
        }

        pane = new Pane();
        if(!isSmall && team > 0){
//            buttonView.setImage(image1);
            pane.setOpacity(0.5);
        }
        else{
//            buttonView.setImage(image0);
            labelSelected.setVisible(false);
        }

        imageView.setImage(image0);
        if(isSmall){
            pane.setPrefWidth(82.5);
            pane.setPrefHeight(97.5);
        }else{
            pane.setPrefWidth(110.0);
            pane.setPrefHeight(130.0);
        }

        pane.setId(ctxId);

        pane.getChildren().add(labelSelected);
        pane.getChildren().add(imageView);
        pane.getChildren().add(label);
        pane.setVisible(true);
    }


}