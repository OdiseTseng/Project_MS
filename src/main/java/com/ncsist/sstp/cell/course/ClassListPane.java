package com.ncsist.sstp.cell.course;

import com.ncsist.sstp.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.Getter;

public class ClassListPane extends Pane{

//    @FXML
    private Label buttonLabel;

//    @FXML
    private ImageView buttonView;

//    @FXML
    @Getter
    private Pane pane;

    private long classYear;

    private Image image0 = new Image("./images/ms/team_course/課程設定1預設.png");

    public ClassListPane(long classYear) {
        super();
        System.out.println("ClassListPane :  classYear => " + classYear);

        this.classYear = classYear;

        buttonLabel = new Label();
        buttonLabel.setPrefWidth(80.0);
        buttonLabel.setPrefHeight(33.0);
        buttonLabel.setFont(Main.customFont);
        buttonLabel.setLayoutX(6.0);
        buttonLabel.setLayoutY(2.5);
        buttonLabel.setText(classYear + "");

        buttonView = new ImageView();
        buttonView.setFitWidth(100.0);
        buttonView.setFitHeight(42.5);
        buttonView.setImage(image0);
        pane = new Pane();
        pane.setPrefWidth(100.0);
        pane.setPrefHeight(42.5);

        pane.getChildren().add(buttonView);
        pane.getChildren().add(buttonLabel);
        pane.setVisible(true);
        pane.setId(classYear + "");
    }


}