package com.ncsist.sstp.cell.course;

import com.ncsist.sstp.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class MissionTeamPane extends Pane{
//    private HBox content;
//    private Text name;
//    private Text price;

//    @FXML
    private Label label;

//    @FXML
    private ImageView imageView;

//    @FXML
    @Getter
    private Pane pane;

    private Image image0 = new Image("./images/ms/team_course/課程設定1預設.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定1點選.png");

    public MissionTeamPane(int team, int currentTeam) {
        super();
        System.out.println("MissionTeamPane :  team => " + team + " ; currentTeam: " + currentTeam);

        String missionStr = "第";

        switch (team) {
            case 1 -> missionStr += "一";
            case 2 -> missionStr += "二";
            case 3 -> missionStr += "三";
            case 4 -> missionStr += "四";
            case 5 -> missionStr += "五";
            case 6 -> missionStr += "六";
        }

        missionStr += "組";

        label = new Label();
        label.setPrefWidth(80.0);
        label.setPrefHeight(33.0);
        label.setFont(Main.customFont);
        label.setLayoutX(12.0);
        label.setLayoutY(4.0);
        label.setText(missionStr);

        imageView = new ImageView();
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(42.5);

        if(currentTeam == team){
            imageView.setImage(image1);
        }else{
            imageView.setImage(image0);
        }

        pane = new Pane();
        pane.setPrefWidth(100.0);
        pane.setPrefHeight(42.5);

        pane.getChildren().add(imageView);
        pane.getChildren().add(label);
        pane.setVisible(true);
        pane.setId(team + "");

    }



}