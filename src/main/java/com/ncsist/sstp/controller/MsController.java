package com.ncsist.sstp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.Main;
import com.ncsist.sstp.cell.course.CustomListCell;
import com.ncsist.sstp.cell.course.CustomListPane;
import com.ncsist.sstp.http.HttpClientGetData;
import com.ncsist.sstp.server.controller.NettyClientMsgController;
import com.ncsist.sstp.utils.text.NettyCode;
import com.ncsist.sstp.vo.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

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

    @FXML
    private ListView<Course> courseList;

    @FXML
    private GridPane courseGridPane;

    private Image imageBlank = new Image("./images/ms_blank.png");
    private Image imageCourse = new Image("./images/ms/team_course/ms_course.png");
    private Image tmpImage;
    private int tmpPos = -1;

    private String url = "http://localhost:8080" ;

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

        backImg.setImage(imageBlank);

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

        backImg.setImage(imageCourse);

//        Image image = new Image("./images/ms/team_course/系統說明選取.png");
//
//        teamCourseSettings.setImage(image);
        teamCourseSettingsSelect.setOpacity(1.0);

        tmpPos = 1;

        String urlCourse = url + "/course/getAllCourse";
        String response = HttpClientGetData.sendGetRequest(urlCourse);

//        NettyClientMsgController nettyClientMsgController = new NettyClientMsgController();
        NettyClientMsgController nettyClientMsgController = NettyClientMsgController.getInstance();
        nettyClientMsgController.sendCMD(NettyCode.TEAM_WAITING_COACH_DISPATCH);

        ObjectMapper ob = new ObjectMapper();
        Course[] courses = ob.readValue(response, Course[].class);//array
        List<String> sourseNameList = Arrays.stream(courses).map(Course::getCourseName).toList();//
        System.out.println("sourseNameList : "+sourseNameList);




        ObservableList<Course> data = FXCollections.observableArrayList();
        data.addAll(courses);

        System.out.println("data size: " + data.size());


//        courseList.setItems(data);

//        int x = 0;
//        int y = 0;
        int index = 0;
        for (int x = 0 ; x < courses.length; x++){
            for(int y = 0; y < 7 && index < courses.length; y++, index++){
                Pane pane = new CustomListPane(courses[index]).getButtonPane();
                courseGridPane.add(pane, x, y);
            }


        }


//        courseList.setCellFactory(courseListView -> {
//            System.out.println("call : ");
//            for (Course course2: courseListView.getItems()){
//                System.out.println("course2 : " + course2);
//            }
//            return new CustomListCell();
//        });

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

        backImg.setImage(imageBlank);

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

        backImg.setImage(imageBlank);

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

        backImg.setImage(imageBlank);

        tmpImage = helpCourse.getImage();
        helpCourse.setOpacity(1.0);

        tmpPos = 3;
    }

}
