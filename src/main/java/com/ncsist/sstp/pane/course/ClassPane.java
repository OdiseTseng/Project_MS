package com.ncsist.sstp.pane.course;

import com.ncsist.sstp.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class ClassPane extends Pane{

//    @FXML
    private Label label;

//    @FXML
    private ImageView imageView;

//    @FXML
    @Getter
    private Pane pane;

    private long classYear;

//    private Image image0 = new Image(getClass().getResourceAsStream("./images/ms/team_course/課程設定1預設.png"));
    private Image image0 = new Image("/images/ms/team_course/課程設定1預設.png");

    public ClassPane(long classYear) {
        super();
        System.out.println("ClassListPane :  classYear => " + classYear);

        this.classYear = classYear;

        label = new Label();
        label.setPrefWidth(80.0);
        label.setPrefHeight(33.0);
        label.setFont(Main.customFont);
        label.setLayoutX(6.0);
        label.setLayoutY(2.5);
        label.setText(classYear + "");

        imageView = new ImageView();
        imageView.setFitWidth(100.0);
        imageView.setFitHeight(42.5);
        imageView.setImage(image0);
        pane = new Pane();
        pane.setPrefWidth(100.0);
        pane.setPrefHeight(42.5);

        pane.getChildren().add(imageView);
        pane.getChildren().add(label);
        pane.setVisible(true);
        pane.setId(classYear + "");
    }


}