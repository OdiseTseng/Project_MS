package com.ncsist.sstp.pane.course;

import com.ncsist.sstp.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class MissionMemberPane extends Pane{

    private Label label;
    private ImageView imageView;
    @Getter
    private Pane pane;

    private Image image0 = new Image("./images/ms/team_course/課程設定1預設.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定1點選.png");
//    private Image image0 = new Image(getClass().getResourceAsStream("./images/ms/team_course/課程設定1預設.png"));
//    private Image image1 = new Image(getClass().getResourceAsStream("./images/ms/team_course/課程設定1點選.png"));


    public MissionMemberPane(String name, String ctxId, String currentCtxId) {
        super();
        System.out.println("MissionTeamPane :  name => " + name + " ; ctxId: " + ctxId);


        label = new Label();
        label.setPrefWidth(80.0);
        label.setPrefHeight(33.0);
        label.setFont(Main.customFont);
        label.setLayoutX(12.0);
        label.setLayoutY(4.0);
        label.setText(name);

        imageView = new ImageView();
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(42.5);

        if(currentCtxId.equals(ctxId)){
            imageView.setImage(image0);
        }else{
            imageView.setImage(image1);
        }

        pane = new Pane();
        pane.setPrefWidth(100.0);
        pane.setPrefHeight(42.5);

        pane.getChildren().add(imageView);
        pane.getChildren().add(label);
        pane.setVisible(true);
        pane.setId(ctxId);

    }



}