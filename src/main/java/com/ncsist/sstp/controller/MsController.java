package com.ncsist.sstp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.cell.course.ClassPane;
import com.ncsist.sstp.cell.course.CoursePane;
import com.ncsist.sstp.cell.course.TeamUserPane;
import com.ncsist.sstp.http.HttpClientGetData;
import com.ncsist.sstp.model.TeamDTO;
import com.ncsist.sstp.server.controller.NettyClientMsgController;
import com.ncsist.sstp.server.service.NettyClientTeamService;
import com.ncsist.sstp.utils.text.NettyCode;
import com.ncsist.sstp.vo.Course;
import com.ncsist.sstp.vo.User;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.*;

public class MsController {

    NettyClientMsgController nettyClientMsgController = NettyClientMsgController.getInstance();

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
    private GridPane teamSelectGridPane;

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

    private Image imageTeam0 = new Image("./images/ms/team_course/課程設定2學員.png");
    private Image imageTeam1 = new Image("./images/ms/team_course/課程設定2學員_已選取-removebg-preview.png");

    private Image tmpImage;
    private int tmpPos = -1;


    private static String selectClassIndex = "";
    private static ImageView lastClassView;

    private static String selectCourseIndex = "";
    private static ImageView lastCourseView;

    List<TeamDTO> teamDTOS = null;

    List<TeamDTO> teamDTOS1 = null;
    List<TeamDTO> teamDTOS2 = null;

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
        teamSelectGridPane.setVisible(false);
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

        if(showTeamPane){
            teamDTOS = NettyClientTeamService.getTeamDTOList();
            System.out.println("teamDTOS: " + teamDTOS);

            teamDTOS1 = new ArrayList<>();
            teamDTOS2 = new ArrayList<>();
            for(TeamDTO teamDTO : teamDTOS){
                if(teamDTO.getTeam() == 1){
                    teamDTOS1.add(teamDTO);
                }
                if(teamDTO.getTeam() == 2){
                    teamDTOS2.add(teamDTO);
                }
            }

            int y = 0;
            for(int x= 0; x+y < teamDTOS1.size(); x++){
                TeamDTO teamDTO = teamDTOS1.get(x+y);
                String ctxId = teamDTO.getCtxId();
                String name = teamDTO.getName();
                int team = teamDTO.getTeam();

//                teamSelectedPane1

                Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
//                pane.setOnMouseClicked(teamEventHandler);
                teamSelectedPane1.add(pane, x, y);
            }

            y = 0;
            for(int x= 0; x+y < teamDTOS2.size(); x++){
                TeamDTO teamDTO = teamDTOS2.get(x+y);
                String ctxId = teamDTO.getCtxId();
                String name = teamDTO.getName();
                int team = teamDTO.getTeam();

//                teamSelectedPane2

                Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
//                pane.setOnMouseClicked(teamEventHandler);
                teamSelectedPane2.add(pane, x, y);
            }
        }else{
            teamSelectedPane1.getChildren().clear();
            teamSelectedPane2.getChildren().clear();
        }
    }

    @FXML
    private void onTeamPickAction(MouseEvent event) throws IOException{
        System.out.println("onTeamPickAction");
        teamSelectGridPane.getChildren().clear();

        ImageView clickedView = (ImageView) event.getSource();
        String clickedId = clickedView.getId();

        switch(clickedId){
            case "teamSelectPick1":
                System.out.println("teamSelectPick1");
//                teamSelectPick1.setVisible(true);
//                teamSelectBack1.setImage(imageBack1);
                teamSelectGridPane.setLayoutX(580.0);
                teamSelectBack1.setVisible(true);
                break;
            case "teamSelectPick2":
                System.out.println("teamSelectPick2");
//                teamSelectBack1.setImage(imageBack2);
                teamSelectGridPane.setLayoutX(400.0);
                teamSelectBack2.setVisible(true);
                break;

        }

        //************************************************************
        EventHandler teamEventHandler = event1 -> {
            System.out.println("teamEventHandler : ");
            EventType eventType = event1.getEventType();
            Pane sourceTeamPane = (Pane) event1.getSource();
            String currentId = sourceTeamPane.getId().trim();
            ImageView sourceTeamView = (ImageView) sourceTeamPane.getChildren().get(0);
            Label sourceTeamLabel = (Label) sourceTeamPane.getChildren().get(1);
            if(eventType == MouseEvent.MOUSE_CLICKED){
                teamDTOS = NettyClientTeamService.getTeamDTOList();
//                List<TeamDTO> teamDTOS = NettyClientTeamService.getTeamDTOList();
                System.out.println("mouse clicked");
                
                int currentTeam = 0;
                if(teamSelectBack1.isVisible()){
                    currentTeam = 1;
                    int selectedSize = teamSelectedPane1.getChildren().size();
                    if(selectedSize > 0){
//                        int y = 0;
//                        for(int x = 0; x+y < selectedSize; x++){
//                            System.out.println("x: " + x + " ; y: " + y);
//                            Pane newPane = sourceTeamPane;
//                            teamSelectedPane1.add(sourceTeamPane, x ,y);
//                            if(x == 1){
//                                x = 0;
//                                y = 1;
//                            }
//                        }

                        int y = 0;
                        for(int x= 0; x+y < teamDTOS1.size(); x++){
                            TeamDTO teamDTO = teamDTOS.get(x+y);
                            String ctxId = teamDTO.getCtxId();
                            String name = teamDTO.getName();
//                            int team = teamDTO.getTeam();

                            Pane pane = new TeamUserPane(ctxId, name, 1, true).getPane();
                            teamSelectedPane1.add(pane, x, y);
                            if(x == 1){
                                x = 0;
                                y = 1;
                            }
                        }

                    }else{
                        for(TeamDTO teamDTO : teamDTOS){
                            String ctxId = teamDTO.getCtxId();
                            String name = teamDTO.getName();
//                            int team = teamDTO.getTeam();

                            if(currentId.equals(ctxId)){
                                Pane pane = new TeamUserPane(ctxId, name, 1, true).getPane();
                                teamSelectedPane1.add(pane, 0, 0);
                                break;
                            }
                        }
                    }
                }else if(teamSelectBack2.isVisible()){
                    currentTeam = 2;
                    int selectedSize = teamSelectedPane2.getChildren().size();
                    if(selectedSize > 0){

//                        int y = 0;
//                        for(int x = 0; x+y < selectedSize; x++){
//                            System.out.println("x: " + x + " ; y: " + y);
//                            teamSelectedPane2.add(sourceTeamPane, x ,y);
//                            if(x == 1){
//                                x = 0;
//                                y = 1;
//                            }
//                        }

                        int y = 0;
                        for(int x= 0; x+y < teamDTOS.size(); x++){
                            TeamDTO teamDTO = teamDTOS.get(x+y);
                            String ctxId = teamDTO.getCtxId();
                            String name = teamDTO.getName();
//                            int team = teamDTO.getTeam();

                            Pane pane = new TeamUserPane(ctxId, name, 2, true).getPane();
                            teamSelectedPane2.add(pane, x, y);

                            if(x == 1){
                                x = 0;
                                y = 1;
                            }
                        }

//                        for(TeamDTO teamDTO : teamDTOS2) {
//                            String ctxId = teamDTO.getCtxId();
//                            String name = teamDTO.getName();
////                            int team = teamDTO.getTeam();
//
//                            if (currentId.equals(ctxId)) {
//                                Pane pane = new TeamUserPane(ctxId, name, 2, true).getPane();
//                                teamSelectedPane2.add(pane, 0, 0);
//                            }
//                        }
                    }else{
//                        teamSelectedPane1.add(sourceTeamPane, 0 ,0);

                        for(TeamDTO teamDTO : teamDTOS) {
                            String ctxId = teamDTO.getCtxId();
                            String name = teamDTO.getName();
//                            int team = teamDTO.getTeam();

                            if (currentId.equals(ctxId)) {
                                Pane pane = new TeamUserPane(ctxId, name, 2, true).getPane();
                                teamSelectedPane2.add(pane, 0, 0);
                                break;
                            }
                        }
                    }
                }
//                else if(teamSelectBack3.isVisible()){
//
//                }else if(teamSelectBack4.isVisible()){
//
//                }
                System.out.println("currentTeam : " + currentTeam);
                System.out.println("currentId : " + currentId);

                for (TeamDTO teamDTO : teamDTOS){
                    String ctxId = teamDTO.getCtxId().trim();
                    String name = teamDTO.getName();
                    int team = teamDTO.getTeam();
                    System.out.println("ctxId : " + ctxId);
                    if(currentId.equals(ctxId)){
                        System.out.println("equals : ");
                        if(team != currentTeam){
                            teamDTO.setTeam(currentTeam);
//                            sourceTeamView.setImage(imageTeam1);
//                            sourceTeamView.setOpacity(0.2);
//                            sourceTeamLabel.setOpacity(0.2);
                            sourceTeamPane.setOpacity(0.5);
                        }else{
                            teamDTO.setTeam(0);
//                            sourceTeamView.setImage(imageTeam0);
//                            sourceTeamView.setOpacity(1.0);
//                            sourceTeamLabel.setOpacity(1.0);
                            sourceTeamPane.setOpacity(1.0);
                        }

                    }
                }

            }
        };
        //************************************************************


//        List<TeamDTO> teamDTOS = NettyClientTeamService.getTeamDTOList();
//        for (TeamDTO teamDTO : teamDTOS){
//            teamSelectPane.add();
//        }
        System.out.println("teamDTOS: " + teamDTOS);

        int index = 0;
        for (int x = 0 ; x < teamDTOS.size(); x++){
                TeamDTO teamDTO = teamDTOS.get(index);
                String ctxId = teamDTO.getCtxId();
                String name = teamDTO.getName();
                int team = teamDTO.getTeam();
                Pane pane = new TeamUserPane(ctxId, name, team, false).getPane();
                pane.setOnMouseClicked(teamEventHandler);
                teamSelectGridPane.add(pane, x, 0);

                if(team > 0){
                    pane.setOpacity(0.5);
                }
        }

        teamSelectGridPane.setVisible(true);

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
//            actionView.setVisible(false);
//            teamSelectGridPane.setVisible(false);
//
//            updatePane.setVisible(true);
//
//            Task<Void> countdownTask = new Task<Void>() {
//                @Override
//                protected Void call() throws Exception {
//                    // 延遲兩秒
//                    Thread.sleep(2000);
//                    return null;
//                }
//            };
//
//            countdownTask.setOnSucceeded(workerStateEvent -> {
//                updatePane.setVisible(false);
//            });
//
//
//            // 啟動任務
//            new Thread(countdownTask).start();

        }

    }

    @FXML
    private void onTeamSelectedBackAction(MouseEvent event) throws IOException{
        System.out.println("onTeamSelectedBackAction");

        EventType eventType = event.getEventType();

        if(eventType == MouseEvent.MOUSE_ENTERED){
            if(teamSelectGridPane.isVisible()){
                teamSelectBack1.setVisible(false);
                teamSelectBack2.setVisible(false);
                teamSelectGridPane.setVisible(false);

                updatePane.setVisible(true);

                nettyClientMsgController.sendCMD(NettyCode.TEAM_WAITING_COACH_DISPATCH);

                Task<Void> countdownTask = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        // 延遲兩秒
                        Thread.sleep(2000);
                        return null;
                    }
                };

                countdownTask.setOnSucceeded(workerStateEvent -> {
                    teamSelectedPane1.getChildren().clear();
                    teamSelectedPane2.getChildren().clear();
                    teamDTOS = NettyClientTeamService.getTeamDTOList();
                    System.out.println("teamDTOS: " + teamDTOS);

                    teamDTOS1 = new ArrayList<>();
                    teamDTOS2 = new ArrayList<>();
                    for(TeamDTO teamDTO : teamDTOS){
                        if(teamDTO.getTeam() == 1){
                            teamDTOS1.add(teamDTO);
                        }
                        if(teamDTO.getTeam() == 2){
                            teamDTOS2.add(teamDTO);
                        }
                    }

                    int y = 0;
                    for(int x= 0; x+y < teamDTOS1.size(); x++){
                        TeamDTO teamDTO = teamDTOS1.get(x+y);
                        String ctxId = teamDTO.getCtxId();
                        String name = teamDTO.getName();
                        int team = teamDTO.getTeam();

//                teamSelectedPane1

                        Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
//                pane.setOnMouseClicked(teamEventHandler);
                        teamSelectedPane1.add(pane, x, y);
                    }

                    y = 0;
                    for(int x= 0; x+y < teamDTOS2.size(); x++){
                        TeamDTO teamDTO = teamDTOS2.get(x+y);
                        String ctxId = teamDTO.getCtxId();
                        String name = teamDTO.getName();
                        int team = teamDTO.getTeam();

//                teamSelectedPane2

                        Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
//                pane.setOnMouseClicked(teamEventHandler);
                        teamSelectedPane2.add(pane, x, y);
                    }

                    updatePane.setVisible(false);

                });


                // 啟動任務
                new Thread(countdownTask).start();
            }

        }
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
                Pane pane = new ClassPane(sortClassYearList.get(index)).getPane();
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
                Pane pane = new CoursePane(courses[index]).getPane();
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
