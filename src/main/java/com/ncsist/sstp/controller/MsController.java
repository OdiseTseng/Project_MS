package com.ncsist.sstp.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class MsController {

    private ImageView imageView;
    private Stage primaryStage; // 添加 Stage 成員變量

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage; //
    }

    @FXML
    private void initialize() {
        // 初始化程式碼，如果需要的話
    }

    @FXML
    private void startTeamCourseAction() throws IOException {
        System.out.println("startTeamCourseAction");

    }

    @FXML
    private void watchCourseAction() throws IOException {
        System.out.println("watchCourseAction");

    }

    @FXML
    private void helpCourseAction() throws IOException {
        System.out.println("helpCourseAction");

    }

}
