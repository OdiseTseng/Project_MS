package com.ncsist.sstp.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

//import javax.swing.text.View;
//import javax.swing.text.html.ImageView;
import java.io.IOException;

public class MsController {

    @FXML
    private ImageView backImg;

    @FXML
    private ImageView systemDescSelect;
    @FXML
    private ImageView systemDesc;

    @FXML
    private ImageView teamCourseSettingsSelect;
    @FXML
    private ImageView teamCourseSettings;

    @FXML
    private ImageView watchCourseSelect;
    @FXML
    private ImageView watchCourse;

    @FXML
    private ImageView helpCourseSelect;
    @FXML
    private ImageView helpCourse;

    @FXML
    private ImageView studentGradeSelect;
    @FXML
    private ImageView studentGrade;

    private Image tmpImage;
    private int tmpPos = -1;

    private Stage primaryStage; // 添加 Stage 成員變量

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage; //
    }

    @FXML
    private void initialize() {
        // 初始化程式碼，如果需要的話
    }

    @FXML
    private void systemDescAction(MouseEvent event) throws IOException {
        System.out.println("watchCourseAction");

        ImageView clickedView = (ImageView) event.getSource();

        switch (tmpPos) {
            case 1 -> teamCourseSettings.setImage(tmpImage);
            case 2 -> watchCourse.setImage(tmpImage);
            case 3 -> helpCourse.setImage(tmpImage);
        }

        Image image2 = new Image("./images/所有頁面底圖.png");
        backImg.setImage(image2);

        tmpImage = watchCourse.getImage();
        watchCourse.setOpacity(1.0);

        tmpPos = 2;
    }

    @FXML
    private void startTeamCourseAction(MouseEvent event) throws IOException {
        System.out.println("startTeamCourseAction");

        switch (tmpPos) {
            case 1 -> {
                teamCourseSettingsSelect.setOpacity(0.0);
            }
            case 2 -> {
                watchCourse.setImage(tmpImage);
                watchCourse.setOpacity(0.0);
            }
            case 3 -> {
                helpCourse.setImage(tmpImage);
                helpCourse.setOpacity(0.0);
            }
        }

        ImageView clickedView = (ImageView) event.getSource();

        tmpImage = teamCourseSettings.getImage();


        Image image2 = new Image("./images/所有頁面底圖.png");
        backImg.setImage(image2);

//        Image image = new Image("./images/ms/team_course/系統說明選取.png");
//
//        teamCourseSettings.setImage(image);
        teamCourseSettingsSelect.setOpacity(1.0);

        tmpPos = 1;

    }

    @FXML
    private void watchCourseAction(MouseEvent event) throws IOException {
        System.out.println("watchCourseAction");

        ImageView clickedView = (ImageView) event.getSource();

        switch (tmpPos) {
            case 1 -> teamCourseSettings.setImage(tmpImage);
            case 2 -> watchCourse.setImage(tmpImage);
            case 3 -> helpCourse.setImage(tmpImage);
        }

        Image image2 = new Image("./images/所有頁面底圖.png");
        backImg.setImage(image2);

        tmpImage = watchCourse.getImage();
        watchCourse.setOpacity(1.0);

        tmpPos = 2;
    }

    @FXML
    private void helpCourseAction(MouseEvent event) throws IOException {
        System.out.println("helpCourseAction");

        ImageView clickedView = (ImageView) event.getSource();

        switch (tmpPos) {
            case 1 -> teamCourseSettings.setImage(tmpImage);
            case 2 -> watchCourse.setImage(tmpImage);
            case 3 -> helpCourse.setImage(tmpImage);
        }

        Image image2 = new Image("./images/所有頁面底圖.png");
        backImg.setImage(image2);

        tmpImage = helpCourse.getImage();
        helpCourse.setOpacity(1.0);

        tmpPos = 3;
    }



    @FXML
    private void studentGradeAction(MouseEvent event) throws IOException {
        System.out.println("helpCourseAction");

        ImageView clickedView = (ImageView) event.getSource();

        switch (tmpPos) {
            case 1 -> teamCourseSettings.setImage(tmpImage);
            case 2 -> watchCourse.setImage(tmpImage);
            case 3 -> helpCourse.setImage(tmpImage);
        }

        Image image2 = new Image("./images/所有頁面底圖.png");
        backImg.setImage(image2);

        tmpImage = helpCourse.getImage();
        helpCourse.setOpacity(1.0);

        tmpPos = 3;
    }


    @FXML
    private void studentGradeAction(MouseEvent event) throws IOException {
        System.out.println("helpCourseAction");

        ImageView clickedView = (ImageView) event.getSource();

        switch (tmpPos) {
            case 1 -> teamCourseSettings.setImage(tmpImage);
            case 2 -> watchCourse.setImage(tmpImage);
            case 3 -> helpCourse.setImage(tmpImage);
        }

        Image image2 = new Image("./images/所有頁面底圖.png");
        backImg.setImage(image2);

        tmpImage = helpCourse.getImage();
        helpCourse.setOpacity(1.0);

        tmpPos = 3;
    }

}
