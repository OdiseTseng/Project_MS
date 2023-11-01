package com.ncsist.sstp.pane.course;

import com.ncsist.sstp.Main;
import com.ncsist.sstp.vo.Unit;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class MissionUnitPane extends Pane{
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

    @Getter
    private  int code;

//    private Image image0 = new Image(getClass().getResourceAsStream("./images/ms/team_course/課程設定1預設.png"));
    private Image image0 = new Image("/images/ms/team_course/課程設定1預設.png");
    private Image image1 = new Image("/images/ms/team_course/課程設定1點選.png");

    public MissionUnitPane(Unit unit, int index, String selectMissionUnitIndex) {
        super();
        System.out.println("MissionUnitPane :  unit => " + unit);

        label = new Label();
        label.setPrefWidth(80.0);
        label.setPrefHeight(33.0);
        label.setFont(Main.customFont);
        label.setLayoutX(6.0);
        label.setLayoutY(2.5);
        label.setText(unit.getUnitName());

        imageView = new ImageView();
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(42.5);
        imageView.setImage(image0);

        pane = new Pane();
        pane.setPrefWidth(100.0);
        pane.setPrefHeight(42.5);

        pane.getChildren().add(imageView);
        pane.getChildren().add(label);
//        selectMissionUnitIndex.equals(unit.getUnitId())
        if(index == 0){
            imageView.setImage(image1);
        }

        pane.setVisible(true);
        pane.setId(unit.getUnitId() + "");

    }



}