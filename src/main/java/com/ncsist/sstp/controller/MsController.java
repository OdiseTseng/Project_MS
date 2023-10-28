package com.ncsist.sstp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.cell.course.ClassListPane;
import com.ncsist.sstp.cell.course.CourseListPane;
import com.ncsist.sstp.cell.course.UserListPane;
import com.ncsist.sstp.http.HttpClientGetData;
import com.ncsist.sstp.model.TeamDTO;
import com.ncsist.sstp.server.controller.NettyClientMsgController;
import com.ncsist.sstp.server.service.NettyClientTeamService;
import com.ncsist.sstp.utils.text.NettyCode;
import com.ncsist.sstp.vo.Course;
import com.ncsist.sstp.vo.User;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.*;

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
    private ImageView tab;

    @FXML
    private ImageView courseSelect;
    @FXML
    private ImageView teamSelect;
    @FXML
    private ImageView missionSelect;

    @FXML
    private ImageView teamSelect1;
    @FXML
    private ImageView teamSelect2;
    @FXML
    private ImageView teamSelect3;
    @FXML
    private ImageView teamSelect4;

    @FXML
    private ImageView teamSelectPick1;
    @FXML
    private ImageView teamSelectPick2;

    @FXML
    private ImageView teamSelectBack1;
    @FXML
    private ImageView teamSelectBack2;



    @FXML
    private ListView<Course> courseList;

    @FXML
    private GridPane courseGridPane;

    @FXML
    private GridPane classGridPane;

    @FXML
    private GridPane teamSelectPane;

    @FXML
    private GridPane teamSelectedPane1;
    @FXML
    private GridPane teamSelectedPane2;

    @FXML
    private Pane updatePane;

    private Image imageBlank = new Image("./images/ms_blank.png");
    private Image imageCourse1 = new Image("./images/ms/team_course/ms_course.png");
    private Image imageCourse2 = new Image("./images/ms/team_course/ms_team.png");
    private Image imageCourse3 = new Image("./images/ms/team_course/ms_mission.png");
    private Image imageTab1 = new Image("./images/ms/team_course/課程設定選擇課程.png");
    private Image imageTab2 = new Image("./images/ms/team_course/課程設定分組.png");
    private Image imageTab3 = new Image("./images/ms/team_course/課程設定任務設定.png");

    private Image image0 = new Image("./images/ms/team_course/課程設定1預設.png");
    private Image image1 = new Image("./images/ms/team_course/課程設定1觸碰.png");
    private Image image2 = new Image("./images/ms/team_course/課程設定1點選.png");

    private Image tmpImage;
    private int tmpPos = -1;


    private static String selectClassIndex = "";
    private static ImageView lastClassView;

    private static String selectCourseIndex = "";
    private static ImageView lastCourseView;

    private String url = "http://localhost:8080" ;

    private Stage primaryStage; // 添加 Stage 成員變量

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage; //
    }

    @FXML
    private void initialize() {
        teamSelect1.setVisible(false);
        teamSelect2.setVisible(false);
        teamSelectedPane1.setVisible(false);
        teamSelectedPane2.setVisible(false);
        teamSelectPane.setVisible(false);
        teamSelectBack1.setVisible(false);
        teamSelectBack2.setVisible(false);

        classGridPane.setVisible(false);
        courseGridPane.setVisible(false);
        updatePane.setVisible(false);
        // 初始化程式碼，如果需要的話
    }

    @FXML
    private void onTabSelectAction(MouseEvent event) throws IOException{
        System.out.println("onTabSelectAction");
        ImageView clickedView = (ImageView) event.getSource();
        String clickedId = clickedView.getId();

        boolean showCoursePane = false;
        boolean showTeamPane = false;

        System.out.println("clickedId : " + clickedId);
        switch (clickedId){
            case "courseSelect":
                backImg.setImage(imageCourse1);
                tab.setImage(imageTab1);
                showCoursePane = true;
                System.out.println("courseSelect");
                break;
            case "teamSelect":
                backImg.setImage(imageCourse2);
                tab.setImage(imageTab2);
                showTeamPane = true;
                System.out.println("teamSelect");
                break;
            case "missionSelect":
                backImg.setImage(imageCourse3);
                tab.setImage(imageTab3);
                System.out.println("missionSelect");
                break;
        }

        classGridPane.setVisible(showCoursePane);
        courseGridPane.setVisible(showCoursePane);

        teamSelect1.setVisible(showTeamPane);
        teamSelect2.setVisible(showTeamPane);
        teamSelect3.setVisible(showTeamPane);
        teamSelect4.setVisible(showTeamPane);

        teamSelectPick1.setVisible(showTeamPane);
        teamSelectPick2.setVisible(showTeamPane);
        teamSelectedPane1.setVisible(showTeamPane);
        teamSelectedPane2.setVisible(showTeamPane);
    }

    @FXML
    private void onTeamPickAction(MouseEvent event) throws IOException{
        System.out.println("onTeamPickAction");
        teamSelectPane.getChildren().clear();

        ImageView clickedView = (ImageView) event.getSource();
        String clickedId = clickedView.getId();

        switch(clickedId){
            case "teamSelectPick1":
                System.out.println("teamSelectPick1");
//                teamSelectPick1.setVisible(true);
//                teamSelectBack1.setImage(imageBack1);
                teamSelectPane.setLayoutX(580.0);
                teamSelectBack1.setVisible(true);
                break;
            case "teamSelectPick2":
                System.out.println("teamSelectPick2");
//                teamSelectBack1.setImage(imageBack2);
                teamSelectPane.setLayoutX(380.0);
                teamSelectBack2.setVisible(true);
                break;

        }
        EventHandler teamEventHandler = event1 -> {
            System.out.println("teamEventHandler : ");
            EventType eventType = event1.getEventType();
            Pane sourcePane = (Pane) event1.getSource();
            String currentId = sourcePane.getId();
            ImageView sourceView = (ImageView) sourcePane.getChildren().get(0);
//            Label sourceLabel = (Label) sourcePane.getChildren().get(1);
            if(eventType == MouseEvent.MOUSE_CLICKED){
                List<TeamDTO> teamDTOS = NettyClientTeamService.getTeamDTOList();
                System.out.println("mouse clicked");

                for (TeamDTO teamDTO : teamDTOS){

                }

            }
        };

        List<TeamDTO> teamDTOS = NettyClientTeamService.getTeamDTOList();
//        for (TeamDTO teamDTO : teamDTOS){
//            teamSelectPane.add();
//        }

        int index = 0;
        for (int x = 0 ; x < teamDTOS.size(); x++){
            for(int y = 0; y < 7 && index < teamDTOS.size(); y++, index++){
                TeamDTO teamDTO = teamDTOS.get(index);
                String ctxId = teamDTO.getCtxId();
                String name = teamDTO.getName();
                int team = teamDTO.getTeam();
                Pane pane = new UserListPane(ctxId, name, team);
                pane.setOnMouseClicked(teamEventHandler);
                classGridPane.add(pane, x, y);

                if(index == 0){
                    ImageView sourceView = (ImageView) pane.getChildren().get(0);
                    sourceView.setImage(image2);
                    selectClassIndex = pane.getId();
                }
            }
        }

        teamSelectPane.setVisible(true);

    }

    @FXML
    private void onTeamSelectBackAction(MouseEvent event) throws IOException{
        System.out.println("onTeamSelectBackAction");

        EventType eventType = event.getEventType();

        ImageView actionView = (ImageView) event.getSource();
        String actionId = actionView.getId();
        System.out.println("actionId : " + actionId);
        if(eventType == MouseEvent.MOUSE_CLICKED){
            System.out.println("MOUSE_CLICKED");

        }

        if(eventType == MouseEvent.MOUSE_EXITED){
            System.out.println("MOUSE_EXITED");
            actionView.setVisible(false);
            teamSelectPane.setVisible(false);

            updatePane.setVisible(true);

            Task<Void> countdownTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    // 延迟5秒
                    Thread.sleep(2000);
                    return null;
                }
            };

//            countdownTask.setOnSucceeded(event -> {
//                // 在任务完成后执行你的操作
//                updatePane.setVisible(false);
//                // 在这里添加你的操作代码
//            });

            countdownTask.setOnSucceeded(workerStateEvent -> {
                updatePane.setVisible(false);
            });


            // 启动任务
            new Thread(countdownTask).start();

//            Platform.runLater(()->{
//
//                updatePane.setVisible(false);
//            });
        }

    }

    @FXML
    private void onTeamSelectedBackAction(MouseEvent event) throws IOException{
        System.out.println("onTeamSelectedBackAction");

    }


    @FXML
    private void onSystemDescAction(MouseEvent event) throws IOException {
        System.out.println("watchCourseAction");

//        ImageView clickedView = (ImageView) event.getSource();

//        switch (tmpPos) {
//            case 1 -> teamCourseSettings.setImage(tmpImage);
//            case 2 -> watchCourse.setImage(tmpImage);
//            case 3 -> helpCourse.setImage(tmpImage);
//        }
//
//        backImg.setImage(imageBlank);
//
//        tmpImage = watchCourse.getImage();
//        watchCourse.setOpacity(1.0);
//
//        tmpPos = 2;
    }

    @FXML
    private void startTeamCourseAction(MouseEvent event) throws IOException {
        System.out.println("startTeamCourseAction");

        classGridPane.getChildren().clear();
        courseGridPane.getChildren().clear();

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

//        ImageView clickedView = (ImageView) event.getSource();

        tmpImage = teamCourseSettings.getImage();

        tab.setVisible(true);

        backImg.setImage(imageCourse1);

        teamCourseSettingsSelect.setOpacity(1.0);

        tmpPos = 1;


        String urlCourse = url + "/user/getAllUserList";
        String response = HttpClientGetData.sendGetRequest(urlCourse);


        ObjectMapper ob = new ObjectMapper();


        User[] users = ob.readValue(response, User[].class);//array
        HashSet<Long> classSet = new HashSet<>();
        for (User user: users){
            classSet.add(user.getStudentBatch());
        }
        ArrayList<Long> sortClassYearList = new ArrayList<>(classSet);
        sortClassYearList.sort(Collections.reverseOrder());

//        List<Long> sortUserList = Arrays.stream(users).map(User::getStudentBatch).toList();//
//        System.out.println("sortUserList : "+sortUserList);

        EventHandler classEventHandler = event1 -> {
            EventType eventType = event1.getEventType();
            Pane sourcePane = (Pane) event1.getSource();
            String currentId = sourcePane.getId();
            ImageView sourceView = (ImageView) sourcePane.getChildren().get(0);
//            Label sourceLabel = (Label) sourcePane.getChildren().get(1);
            if(eventType == MouseEvent.MOUSE_CLICKED){
                System.out.println("mouse clicked");

                if(!currentId.equals(selectClassIndex)){
                    selectClassIndex = currentId;
                    sourceView.setImage(image2);
                    if(lastClassView != null){
                        lastClassView.setImage(image0);
                    }
                    lastClassView = sourceView;
                }else{
                    selectClassIndex = "";
                    sourceView.setImage(image1);
                    lastClassView = null;
                }


            }else if(eventType == MouseEvent.MOUSE_ENTERED){
                if(!currentId.equals(selectClassIndex)){
                    sourceView.setImage(image1);
                }

            }else if(eventType == MouseEvent.MOUSE_EXITED){
                if(!currentId.equals(selectClassIndex)) {
                    sourceView.setImage(image0);
                }

            }
        };


        int index = 0;
        for (int x = 0 ; x < sortClassYearList.size(); x++){
            for(int y = 0; y < 7 && index < sortClassYearList.size(); y++, index++){
                Pane pane = new ClassListPane(sortClassYearList.get(index)).getPane();
                pane.setOnMouseClicked(classEventHandler);
                pane.setOnMouseEntered(classEventHandler);
                pane.setOnMouseExited(classEventHandler);
                classGridPane.add(pane, x, y);

                if(index == 0){
                    ImageView sourceView = (ImageView) pane.getChildren().get(0);
                    sourceView.setImage(image2);
                    selectClassIndex = pane.getId();
                }
            }
        }

        urlCourse = url + "/course/getAllCourse";
        response = HttpClientGetData.sendGetRequest(urlCourse);

        NettyClientMsgController nettyClientMsgController = NettyClientMsgController.getInstance();
        nettyClientMsgController.sendCMD(NettyCode.TEAM_WAITING_COACH_GET_ALL);

        Course[] courses = ob.readValue(response, Course[].class);//array
        List<String> sourseNameList = Arrays.stream(courses).map(Course::getCourseName).toList();//
        System.out.println("sourseNameList : "+sourseNameList);




        EventHandler courseEventHandler = event1 -> {
            EventType eventType = event1.getEventType();
            Pane sourcePane = (Pane) event1.getSource();
            String currentId = sourcePane.getId();
            ImageView sourceView = (ImageView) sourcePane.getChildren().get(0);
            if(eventType == MouseEvent.MOUSE_CLICKED){
                System.out.println("mouse clicked");

                if(!currentId.equals(selectCourseIndex)){
                    selectCourseIndex = currentId;
                    sourceView.setImage(image2);
                    if(lastCourseView != null){
                        lastCourseView.setImage(image0);
                    }
                    lastCourseView = sourceView;
                }else{
                    selectCourseIndex = "";
                    sourceView.setImage(image1);
                    lastCourseView = null;
                }


            }else if(eventType == MouseEvent.MOUSE_ENTERED){
                if(!currentId.equals(selectCourseIndex)){
                    sourceView.setImage(image1);
                }

            }else if(eventType == MouseEvent.MOUSE_EXITED){
                if(!currentId.equals(selectCourseIndex)) {
                    sourceView.setImage(image0);
                }

            }
        };

        index = 0;
        for (int x = 0 ; x < courses.length; x++){
            for(int y = 0; y < 7 && index < courses.length; y++, index++){
                Pane pane = new CourseListPane(courses[index]).getPane();
                pane.setOnMouseClicked(courseEventHandler);
                pane.setOnMouseEntered(courseEventHandler);
                pane.setOnMouseExited(courseEventHandler);

                courseGridPane.add(pane, x, y);
            }
        }

        classGridPane.setVisible(true);
        courseGridPane.setVisible(true);

    }



    @FXML
    private void watchCourseAction(MouseEvent event) throws IOException {
        System.out.println("watchCourseAction");

//        switch (tmpPos) {
//            case 1 -> teamCourseSettings.setImage(tmpImage);
//            case 2 -> watchCourse.setImage(tmpImage);
//            case 3 -> helpCourse.setImage(tmpImage);
//        }
//
//        backImg.setImage(imageBlank);
//
//        tmpImage = watchCourse.getImage();
//        watchCourse.setOpacity(1.0);
//
//        tmpPos = 2;
    }

    @FXML
    private void helpCourseAction(MouseEvent event) throws IOException {
        System.out.println("helpCourseAction");

//        ImageView clickedView = (ImageView) event.getSource();
//
//        switch (tmpPos) {
//            case 1 -> teamCourseSettings.setImage(tmpImage);
//            case 2 -> watchCourse.setImage(tmpImage);
//            case 3 -> helpCourse.setImage(tmpImage);
//        }
//
//        backImg.setImage(imageBlank);
//
//        tmpImage = helpCourse.getImage();
//        helpCourse.setOpacity(1.0);
//
//        tmpPos = 3;
    }



    @FXML
    private void studentGradeAction(MouseEvent event) throws IOException {
        System.out.println("helpCourseAction");

//        ImageView clickedView = (ImageView) event.getSource();
//
//        switch (tmpPos) {
//            case 1 -> teamCourseSettings.setImage(tmpImage);
//            case 2 -> watchCourse.setImage(tmpImage);
//            case 3 -> helpCourse.setImage(tmpImage);
//        }
//
//        backImg.setImage(imageBlank);
//
//        tmpImage = helpCourse.getImage();
//        helpCourse.setOpacity(1.0);
//
//        tmpPos = 3;
    }

}
