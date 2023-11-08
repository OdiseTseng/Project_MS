package com.ncsist.sstp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.Main;
import com.ncsist.sstp.http.HttpClientGetData;
import com.ncsist.sstp.model.TeamDTO;
import com.ncsist.sstp.pane.course.*;
import com.ncsist.sstp.server.controller.NettyClientMsgController;
import com.ncsist.sstp.server.service.NettyClientTeamService;
import com.ncsist.sstp.utils.func.DTOParser;
import com.ncsist.sstp.utils.text.NettyCode;
import com.ncsist.sstp.vo.*;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    private ImageView tabBack;

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
    private ImageView missionBack;
    @FXML
    private Label missionLabelTeam;
    @FXML
    private Label missionLabelMember;
    @FXML
    private Label missionLabelUnit;
    @FXML
    private Label missionLabelLeader;

    @FXML
    private ImageView missionIsLeader;

    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private RadioButton radio3;
    
    @FXML
    private TextArea taMission;

    @FXML
    private Label missionLabelContent;

    @FXML
    private ImageView teamSentNext;

    @FXML
    private ImageView tips;

    @FXML
    private ImageView tabGrade;

    @FXML
    private ImageView tabGradeBack1;

    @FXML
    private ImageView tabGradeBack2;

    @FXML
    private ImageView imgGradeClass;

    @FXML
    private ImageView imgGradeCourse;

    @FXML
    private ImageView imgGradeBack;

    @FXML
    private ImageView imgGradeExport;

    @FXML
    private ImageView imgGradePrint;



    @FXML
    private ListView<Course> courseList;

    @FXML
    private ListView<User> gradeList;

    @FXML
    private ListView<Attendance> attendanceList;

    @FXML
    private GridPane courseGridPane;

    @FXML
    private GridPane classGridPane;

    @FXML
    private GridPane teamSelectGridPane;

    @FXML
    private GridPane teamSelectedGridPane1;
    @FXML
    private GridPane teamSelectedGridPane2;

    @FXML
    private Pane missionPane;

    @FXML
    private GridPane missionTeamGridPane;
    @FXML
    private GridPane missionMemberGridPane;
    @FXML
    private GridPane missionUnitGridPane;

    @FXML
    private Pane updatePane;

    @FXML
    private Pane sentPane;

    @FXML
    private Pane gradePane;

    @FXML
    private GridPane gradeYearSelectGridPane;

    @FXML
    private GridPane gradeClassSelectGridPane;

    @FXML
    private TableView<Grade> tableView0;
//    private TableView<Attendance> tableView0;
    
    @FXML
    private TableView<Attendance> tableViewDetail;

    @FXML
    private TableView<Grade> tableViewAll;


    private Image imageBlank;
    private Image imageCourse1;
    private Image imageCourse2;
    private Image imageCourse3;
    private Image imageTab1;
    private Image imageTab2;
    private Image imageTab3;

    private Image image0;
    private Image image1;
    private Image image2;

    private ToggleGroup toggleGroup;

    private Image imageTeamLeader0;
    private Image imageTeamLeader1;


    private Image imageMission1;
    private Image imageMission2;
    private Image imageMission3;

    private Image imageTip1;
    private Image imageTip2;
    private Image imageTip3;
    private Image imageTip4;
    private Image imageTip5;
    private Image imageTip6;
    
    private Image imageBlank1;
    private Image imageBlank2;
    private Image imageTeam4;

    private Image imageGradeBack1;
    private Image imageGradeBack2;

    private Image imageGradeReturn;
    private Image imageGradeReturn2;

    private boolean teamClicked;

    private Image tmpImage;
    private int tmpPos = -1;


    private String selectClassIndex = "";
    private ImageView lastClassView;

    private String selectCourseIndex = "";
    private ImageView lastCourseView;

    private String selectMissionUnitIndex = "";
    private ImageView lastMissionUnitView;

    private String selectMissionTeamIndex = "1";
    private ImageView lastMissionTeamView;

    private String selectMissionMemberIndex = "";
    private ImageView lastMissionMemberView;

    private String selectGradeClassIndex = "";
    private ImageView lastGradeClassView;

    private String selectGradeCourseIndex = "";
    private ImageView lastGradeCourseView;

    private int selectMissionRoleIndex = 3;



    List<ImageView> imageViewClasses = null;
    List<ImageView> imageViewCourses = null;
    List<ImageView> imageViewTeams = null;
    List<ImageView> imageViewMissionUnits = null;

    List<ImageView> imageViewMissionTeams = null;
    List<ImageView> imageViewMissionMembers = null;
    List<ImageView> imageViewGradeClasses = null;
    List<ImageView> imageViewGradeCourses = null;

    List<TeamDTO> teamDTOS_ALL = null;

    List<TeamDTO> teamDTOS1 = null;
    List<TeamDTO> teamDTOS2 = null;


    Course[] gradeCourses = null;

    User[] gradeUsers = null;
    
    Unit[] gradeUnits = null;

    Unit[] units = null;

    Attendance[] attendances = null;

    ObjectMapper ob = new ObjectMapper();

    private String url = "http://localhost:8080" ;

    private Stage primaryStage; // 添加 Stage 成員變量

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage; //
    }

    @FXML
    private void initialize() {
        System.out.println("initialize");
//        URL url2 = getClass().getResource("/images/ms_blank.png");
//        InputStream url8 = getClass().getResourceAsStream("/images/ms_blank.png");
//
//        System.out.println("url2: " + url2);
//        System.out.println("url8: " + url8);

//        imageBlank = new Image(url8);


        imageBlank = new Image("/images/ms_blank.png");
        imageCourse1 = new Image("/images/ms/team_course/ms_course.png");
        imageCourse2 = new Image("/images/ms/team_course/ms_team.png");
        imageCourse3 = new Image("/images/ms/team_course/ms_mission.png");
        imageTab1 = new Image("/images/ms/team_course/課程設定選擇課程.png");
        imageTab2 = new Image("/images/ms/team_course/課程設定分組.png");
        imageTab3 = new Image("/images/ms/team_course/課程設定任務設定.png");

        image0 = new Image("/images/ms/team_course/課程設定1預設.png");
        image1 = new Image("/images/ms/team_course/課程設定1觸碰.png");
        image2 = new Image("/images/ms/team_course/課程設定1點選.png");

        imageTeamLeader0 = new Image("/images/ms/team_course/button/隊長.png");
        imageTeamLeader1 = new Image("/images/ms/team_course/button/隊員.png");


        imageMission1 = new Image("/images/ms/team_course/button/課程設定4送出預設.png");
        imageMission2 = new Image("/images/ms/team_course/button/課程設定4送出觸碰.png");
        imageMission3 = new Image("/images/ms/team_course/button/課程設定4送出點選.png");

        imageTip1 = new Image("/images/ms/team_course/課程設定1使用輔助說明.png");
        imageTip2 = new Image("/images/ms/team_course/課程設定2使用輔助說明.png");
        imageTip3 = new Image("/images/ms/team_course/課程設定3使用輔助說明.png");
        imageTip4 = new Image("/images/ms/team_course/課程設定4使用輔助說明.png");
        imageTip5 = new Image("/images/ms/grade/學員成績1使用輔助.png");

        imageBlank1 = new Image("/images/ms/team_course/課程設定2新增組別.png");
        imageBlank2 = new Image("/images/ms/team_course/課程設定2新增組別處碰.png");

        imageTeam4 = new Image("/images/ms/team_course/課程設定2第四組.png");

        imageGradeBack1 = new Image("/images/ms/grade/學員成績0.png");
        imageGradeBack2 = new Image("/images/ms/grade/學員成績3.png");

        imageGradeReturn = new Image("/images/ms/grade/學員成績2返回.png");
        imageGradeReturn2 = new Image("/images/ms/grade/學員成績2返回觸碰與點選.png");

        teamSelect1.setVisible(false);
        teamSelect2.setVisible(false);
        teamSelectedGridPane1.setVisible(false);
        teamSelectedGridPane2.setVisible(false);
        teamSelectGridPane.setVisible(false);
        teamSelectBack1.setVisible(false);
        teamSelectBack2.setVisible(false);


        System.out.println("teamSelect");

        courseSelect.setVisible(false);
        teamSelect.setVisible(false);
        missionSelect.setVisible(false);


        System.out.println("course tab");

        classGridPane.setVisible(false);
        courseGridPane.setVisible(false);
        updatePane.setVisible(false);


        System.out.println("3 pane");


        toggleGroup = new ToggleGroup();

        radio1.setToggleGroup(toggleGroup);
        radio1.setFont(Main.customFont);
//        radio1.setSelected(true);
        radio2.setToggleGroup(toggleGroup);
        radio2.setFont(Main.customFont);
        radio3.setToggleGroup(toggleGroup);
        radio3.setFont(Main.customFont);

        missionLabelContent.setFont(Main.customFont);
        taMission.setFont(Main.customFont);

        missionIsLeader.setImage(imageTeamLeader1);
        missionLabelTeam.setFont(Main.customFont);
        missionLabelMember.setFont(Main.customFont);
        missionLabelUnit.setFont(Main.customFont);
        missionLabelLeader.setFont(Main.customFont);
        missionBack.setVisible(false);
        missionPane.setVisible(false);
        sentPane.setVisible(false);

        tips.setVisible(false);


        gradePane.setVisible(false);
        gradeYearSelectGridPane.setVisible(false);
        gradeClassSelectGridPane.setVisible(false);
        tableView0.setVisible(false);
        tableViewDetail.setVisible(false);
        tableViewAll.setVisible(false);
        imgGradeBack.setVisible(false);
        imgGradeExport.setVisible(false);
        imgGradePrint.setVisible(false);

        System.out.println("final pane");
        // 初始化程式碼，如果需要的話
    }

    @FXML
    private void onTabSelectAction(MouseEvent event) throws IOException{
        System.out.println("onTabSelectAction");
        ImageView clickedView = (ImageView) event.getSource();
        String clickedId = clickedView.getId();

        boolean showCoursePane = false;
        boolean showTeamPane = false;
        boolean showMissonPane = false;

        System.out.println("clickedId : " + clickedId);

        switch (clickedId){
            case "courseSelect":
                backImg.setImage(imageCourse1);
                tabBack.setImage(imageTab1);
                showCoursePane = true;
                System.out.println("courseSelect");
                tips.setImage(imageTip1);
                break;

            case "teamSelect":
                backImg.setImage(imageCourse2);
                tabBack.setImage(imageTab2);
                showTeamPane = true;
                System.out.println("teamSelect");
                tips.setImage(imageTip2);
                break;

            case "missionSelect":
                backImg.setImage(imageCourse3);
                tabBack.setImage(imageTab3);
                showMissonPane = true;
                System.out.println("missionSelect");

                String urlUnit = url + "/unit/getUnitsBycourseId/" + selectCourseIndex;
                String response = HttpClientGetData.sendGetRequest(urlUnit);

                units = ob.readValue(response, Unit[].class);//array

                tips.setImage(imageTip4);
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
        teamSelectedGridPane1.setVisible(showTeamPane);
        teamSelectedGridPane2.setVisible(showTeamPane);

        missionPane.setVisible(showMissonPane);
        missionTeamGridPane.setVisible(showMissonPane);
        missionMemberGridPane.setVisible(showMissonPane);
        missionUnitGridPane.setVisible(showMissonPane);

        gradePane.setVisible(false);


        teamSelectedGridPane1.getChildren().clear();
        teamSelectedGridPane2.getChildren().clear();

        if(showTeamPane){
            System.out.println("showTeamPane");
            teamDTOS_ALL = NettyClientTeamService.getTeamDTOList();
            System.out.println("teamDTOS: " + teamDTOS_ALL);

            teamDTOS1 = new ArrayList<>();
            teamDTOS2 = new ArrayList<>();
            for(TeamDTO teamDTO : teamDTOS_ALL){
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

                Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
                teamSelectedGridPane1.add(pane, x, y);
            }

            y = 0;
            for(int x= 0; x+y < teamDTOS2.size(); x++){
                TeamDTO teamDTO = teamDTOS2.get(x+y);
                String ctxId = teamDTO.getCtxId();
                String name = teamDTO.getName();
                int team = teamDTO.getTeam();

                Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
                teamSelectedGridPane2.add(pane, x, y);
            }
        }
//        else{
//        }

        if(showMissonPane){
            System.out.println("showMissonPane");


            if(units != null && units.length > 0){
                taMission.clear();
                String unitText = units[0].getUnitSubject();
                taMission.appendText(unitText + " :\n\n");
                unitText = units[0].getDescTitle1();
                taMission.appendText(unitText + " :\n");
                unitText = units[0].getDescContent1();
                taMission.appendText(unitText + "\n\n");
                unitText = units[0].getDescTitle2();
                taMission.appendText(unitText + " :\n");
                unitText = units[0].getDescContent2();
                taMission.appendText(unitText + "\n\n");
                unitText = units[0].getDescTitle3();
                taMission.appendText(unitText + " :\n");
                unitText = units[0].getDescContent3();
                taMission.appendText(unitText + "\n");

                EventHandler missionUnitEventHandler = event1 -> {
                    EventType eventType = event1.getEventType();
                    Pane sourceMissionUnitPane = (Pane) event1.getSource();
                    String currentMissionUnitId = sourceMissionUnitPane.getId();
                    ImageView sourceMissionUnitView = (ImageView) sourceMissionUnitPane.getChildren().get(0);

                    if(eventType == MouseEvent.MOUSE_CLICKED){
                        System.out.println("mouse clicked");

                        System.out.println("currentMissionUnitId :" + currentMissionUnitId);
                        System.out.println("selectMissionUnitIndex :" + selectMissionUnitIndex);

                        for(ImageView imageView : imageViewMissionUnits){
                            imageView.setImage(image0);
                        }

                        String selectedUnitText = "";
                        for(int x = 0; x < units.length; x++){
                            Unit unit = units[x];
                            Long unitId = unit.getUnitId();
                            if(unitId == Long.parseLong(currentMissionUnitId)){
                                taMission.clear();
                                selectedUnitText = unit.getUnitSubject();
                                taMission.appendText(selectedUnitText + " :\n\n");
                                selectedUnitText = unit.getDescTitle1();
                                taMission.appendText(selectedUnitText + " :\n");
                                selectedUnitText = unit.getDescContent1();
                                taMission.appendText(selectedUnitText + "\n\n");
                                selectedUnitText = unit.getDescTitle2();
                                taMission.appendText(selectedUnitText + " :\n");
                                selectedUnitText = unit.getDescContent2();
                                taMission.appendText(selectedUnitText + "\n\n");
                                selectedUnitText = unit.getDescTitle3();
                                taMission.appendText(selectedUnitText + " :\n");
                                selectedUnitText = unit.getDescContent3();
                                taMission.appendText(selectedUnitText + "\n");
                                break;
                            }
                        }

                        if(!currentMissionUnitId.equals(selectMissionUnitIndex)){
                            selectMissionUnitIndex = currentMissionUnitId;
//                            if(lastMissionMemberView != null){
//                            if(lastMissionUnitView != null){
//                                lastMissionUnitView.setImage(image0);
//                            }
                            lastMissionUnitView = sourceMissionUnitView;
//                            taMission.setText(selectedUnitText);
                        }

                        sourceMissionUnitView.setImage(image2);
//                        else{
//                            selectMissionUnitIndex = "";
//                            sourceMissionUnitView.setImage(image1);
//                            lastMissionUnitView = null;
//                        }


                    }else if(eventType == MouseEvent.MOUSE_ENTERED){
                        if(!currentMissionUnitId.equals(selectMissionUnitIndex)){
                            sourceMissionUnitView.setImage(image1);
                        }

                    }else if(eventType == MouseEvent.MOUSE_EXITED){
                        if(!currentMissionUnitId.equals(selectMissionUnitIndex)) {
                            sourceMissionUnitView.setImage(image0);
                        }

                    }
                };

                imageViewMissionUnits = new ArrayList<>();

                int index = 0;
                for(Unit unit: units){
                    Pane missionUnitPane = new MissionUnitPane(unit, index).getPane();
                    missionUnitPane.setOnMouseEntered(missionUnitEventHandler);
                    missionUnitPane.setOnMouseClicked(missionUnitEventHandler);
                    missionUnitPane.setOnMouseExited(missionUnitEventHandler);


                    ImageView sourceView = (ImageView)missionUnitPane.getChildren().get(0);
                    imageViewMissionUnits.add(sourceView);

                    if(index == 0){
                        sourceView.setImage(image2);
                        selectMissionUnitIndex = missionUnitPane.getId();
                    }

                    missionUnitGridPane.add(missionUnitPane, index++ , 0);
                }
            }

            EventHandler missionTeamEventHandler = event1 -> {
                System.out.println("missionTeamEventHandler");
                EventType eventType = event1.getEventType();
                Pane sourceMissionPane = (Pane) event1.getSource();
                String currentMissionId = sourceMissionPane.getId();
                ImageView sourceMissionView = (ImageView) sourceMissionPane.getChildren().get(0);

                System.out.println("currentMissionId :" + currentMissionId);
                System.out.println("selectMissionTeamIndex :" + selectMissionTeamIndex);
                if(eventType == MouseEvent.MOUSE_CLICKED){
                    System.out.println("mouse clicked");

                    for(ImageView imageView : imageViewMissionTeams){
                        imageView.setImage(image0);
                    }

                    for(TeamDTO teamDTO : teamDTOS_ALL){
                        String ctxId = teamDTO.getCtxId();
                        int team = teamDTO.getTeam();
                        int role = teamDTO.getRole();
                        if(team == Integer.parseInt(currentMissionId)){
                            RadioButton selectButton = radio1;
                            switch (role) {
                                case 2 -> selectButton = radio2;
                                case 1 -> selectButton = radio3;
                                default -> {
                                }
                            }
                            toggleGroup.selectToggle(selectButton);
                            selectButton.setSelected(true);
                            break;
                        }
                    }

                    if(!currentMissionId.equals(selectMissionTeamIndex)){
                        selectMissionTeamIndex = currentMissionId;
                        if(lastMissionTeamView != null && lastClassView != sourceMissionView){
                            lastMissionTeamView.setImage(image0);
                        }
                        lastMissionTeamView = sourceMissionView;
                    }
                    sourceMissionView.setImage(image2);
//                    else{
//                        selectMissionTeamIndex = "";
//                        sourceMissionView.setImage(image1);
//                        lastMissionTeamView = null;
//                    }


                }else if(eventType == MouseEvent.MOUSE_ENTERED){
                    if(!currentMissionId.equals(selectMissionTeamIndex)){
                        sourceMissionView.setImage(image1);
                    }

                }else if(eventType == MouseEvent.MOUSE_EXITED){
                    if(!currentMissionId.equals(selectMissionTeamIndex)) {
                        sourceMissionView.setImage(image0);
                    }

                }
            };

            teamDTOS1 = new ArrayList<>();
            teamDTOS2 = new ArrayList<>();
            System.out.println("teamDTOS_ALL : ");
            for(TeamDTO teamDTO : teamDTOS_ALL){
                System.out.println("teamDTO " + teamDTO);
                if(teamDTO.getTeam() == 1){
                    teamDTOS1.add(teamDTO);
                }
                if(teamDTO.getTeam() == 2){
                    teamDTOS2.add(teamDTO);
                }
            }
            System.out.println("teamDTOS1 size : " + teamDTOS1.size());

//            missionPane
            int teams = teamDTOS1.isEmpty() ? 0: 1;
                teams += teamDTOS2.isEmpty() ? 0: 1;
                int currentTeam = 1;

                imageViewMissionTeams = new ArrayList<>();

            for(int x = 0; x < 4; x++){
                boolean disablePane = teams  < x + 1;
                Pane missionTeamPane = new MissionTeamPane(x + 1, currentTeam, disablePane).getPane();

                ImageView sourceView = (ImageView)missionTeamPane.getChildren().get(0);
                imageViewMissionTeams.add(sourceView);


                System.out.println("x : " + x + " ; teams : " + teams +" ; disablePane : " + disablePane );
                if(!disablePane){
                    missionTeamPane.setOnMouseEntered(missionTeamEventHandler);
                    missionTeamPane.setOnMouseClicked(missionTeamEventHandler);
                    missionTeamPane.setOnMouseExited(missionTeamEventHandler);
                }

                missionTeamGridPane.add(missionTeamPane, x, 0);
            }


            //** ----------------------------------------------------------------------------

            EventHandler missionMemberEventHandler = event1 -> {
                System.out.println("missionMemberEventHandler:");
                EventType eventType = event1.getEventType();
                Pane sourceMissionMemberPane = (Pane) event1.getSource();
                String currentMissionMemberId = sourceMissionMemberPane.getId();
                ImageView sourceMissionMemberView = (ImageView) sourceMissionMemberPane.getChildren().get(0);

                if(eventType == MouseEvent.MOUSE_CLICKED){
                    System.out.println("mouse clicked");

                    System.out.println("currentMissionMemberId :" + currentMissionMemberId);
                    System.out.println("selectMissionMemberIndex :" + selectMissionMemberIndex);

                    for(ImageView imageView : imageViewMissionMembers){
                        imageView.setImage(image0);
                    }

                    RadioButton selectButton = radio1;
                    for(TeamDTO teamDTO : teamDTOS_ALL){
                        String ctxId = teamDTO.getCtxId();
                        String name = teamDTO.getName();
                        int role = teamDTO.getRole();
                        System.out.println("ctxId : " + ctxId + " ; currentMissionMemberId : " + currentMissionMemberId + " ; role : " + role + " ; name : " + name);
                        if(ctxId.equals(currentMissionMemberId)){
                            switch (role) {
                                case 2 -> selectButton = radio2;
                                case 1 -> selectButton = radio3;
                                default -> {
//                                    selectButton = radio1;
                                }
                            }

                            System.out.println("change button to role : " + role  + " ;; name: " + name);


                            if(!currentMissionMemberId.equals(selectMissionMemberIndex)){
                                selectMissionMemberIndex = currentMissionMemberId;
                                if(lastMissionMemberView != null && lastMissionMemberView != sourceMissionMemberView){
                                    lastMissionMemberView.setImage(image0);
                                }
                                lastMissionMemberView = sourceMissionMemberView;
                            }
                            
                            toggleGroup.selectToggle(selectButton);
                            selectButton.setSelected(true);
                            break;
                        }
                    }


                    sourceMissionMemberView.setImage(image2);

//                    else{
//                        selectMissionMemberIndex = "";
//                        sourceMissionMemberView.setImage(image1);
//                        lastMissionMemberView = null;
//                    }


                }else if(eventType == MouseEvent.MOUSE_ENTERED){
                    if(!currentMissionMemberId.equals(selectMissionMemberIndex)){
                        sourceMissionMemberView.setImage(image1);
                    }

                }else if(eventType == MouseEvent.MOUSE_EXITED){
                    if(!currentMissionMemberId.equals(selectMissionMemberIndex)) {
                        sourceMissionMemberView.setImage(image0);
                    }

                }
            };

            imageViewMissionMembers = new ArrayList<>();

            for(int x = 0; x < teamDTOS1.size(); x++){
                TeamDTO teamDTO = teamDTOS1.get(x);
                String name = teamDTO.getName();
                String ctxId = teamDTO.getCtxId();
                int team = teamDTO.getTeam();
                int role = teamDTO.getRole();
                System.out.println("check before TeamDTOS1 ::  name = " + name + " ;; ctxId = " + ctxId + " ;; team = " + team + " ;; role : " + role + " ;; x = " + x);

//                if(x == 0){
//                    selectMissionMemberIndex = teamDTO.getCtxId();
//                    if(role == 0){
//                        teamDTO.setRole(3);
//                    }
//                }

                if(x == 0){
                    selectMissionMemberIndex = teamDTO.getCtxId();
                }
//                int y = 0;
                if(role == 0){
                    for(TeamDTO teamDTO1 : teamDTOS_ALL){

                        if(ctxId.equals(teamDTO1.getCtxId())){
//                            teamDTO1.setRole(3 - x);
                            teamDTO.setRole(3 - x);
                            break;
                        }
//                        y++;
                    }
                }

//                System.out.println("check after TeamDTOS1 ::  name = " + name + " ;; ctxId = " + ctxId + " ;; team = " + team + " ;; role : " + teamDTO.getRole() + " ;; x = " + x);
//                System.out.println("teamDTOS_ALL : " + teamDTOS_ALL.get(y).getRole());

//                if(team == 1 && "".equals(selectMissionMemberIndex)){
//                    selectMissionMemberIndex = teamDTO.getCtxId();
//                    if(role == 0){
//                        teamDTO.setRole(3);
//                    }
//                }

                Pane missionMemberPane = new MissionMemberPane(name, ctxId, selectMissionMemberIndex).getPane();
                missionMemberPane.setOnMouseEntered(missionMemberEventHandler);
                missionMemberPane.setOnMouseClicked(missionMemberEventHandler);
                missionMemberPane.setOnMouseExited(missionMemberEventHandler);

                ImageView sourceView = (ImageView)missionMemberPane.getChildren().get(0);
                imageViewMissionMembers.add(sourceView);


                missionMemberGridPane.add(missionMemberPane, x , 0);
            }

            System.out.println("check before click: ");
            for(TeamDTO teamDTO : teamDTOS_ALL){
                System.out.println("teamDTO : " + teamDTO);
            }


            toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal)->{
                System.out.println("toggleGroup ---------------------------------------------------------");
                if(newVal != null){
                    RadioButton selectedRadioButton = (RadioButton)newVal;
                    String selectedValue = selectedRadioButton.getText();
                    int total = missionPane.getChildren().size();
                    int selectedIndex = missionPane.getChildren().indexOf(selectedRadioButton);
                    // 1 操作手 / 2 通訊兵 / 3 車長 / 4 指揮 / 5 教官
                    int treatRole = total - selectedIndex -3;

                    System.out.println("toggleGroup total: " + total);
                    System.out.println("toggleGroup Selected Value: " + selectedValue);
                    System.out.println("toggleGroup Selected Index: " + selectedIndex);
                    System.out.println("toggleGroup treatRole: " + treatRole);

                    int teamIndex = Integer.parseInt(selectMissionTeamIndex);
//                    int memberIndex = Integer.parseInt(selectMissionMemberIndex);
                    int firstRole = 3;
                    int secondRole = 2;
                    int thirdRole = 1;

//                    switch (treatRole) {
//                        case 3 -> firstRole -= treatRole;
//                        case 2 -> secondRole -= treatRole;
//                        case 1 -> thirdRole -= treatRole;
//                    }

                    switch (treatRole) {
                        case 3 -> firstRole = 0;
                        case 2 -> secondRole = 0;
                        case 1 -> thirdRole = 0;
                    }

                    System.out.println("toggleGroup teamDTOS_ALL : " + teamDTOS_ALL.size());

                    int oriRole1 = 0;
                    int oriRole2 = 0;
                    int oriRole3 = 0;

                    //先檢查TEAM的ROLE
                    for(TeamDTO teamDTO : teamDTOS_ALL) {
                        int team = teamDTO.getTeam();
                        int role = teamDTO.getRole();
                        String name = teamDTO.getName();
                        String memberCtxId = teamDTO.getCtxId();
                        System.out.println("toggleGroup memberCtxId: " + memberCtxId + " ; team : " + team + " ; teamIndex : " + teamIndex + " ; role : " + role + " ; name : " + name);

                        if (teamIndex == team) {
                            System.out.println("toggleGroup teamIndex: " + teamIndex + " ; team : " + team + " ; treatRole: " + treatRole);
                            System.out.println("toggleGroup selectMissionMemberIndex: " + selectMissionMemberIndex + " ; memberCtxId : " + memberCtxId);

                            if (memberCtxId.equals(selectMissionMemberIndex)) {
//                                teamDTO.setRole(treatRole);
                                oriRole1 = role;
                            }else if(oriRole2 == 0){
                                oriRole2 = role;
                            }else if(oriRole3 == 0){
                                oriRole3 = role;
                            }
                        }
                    }

                    System.out.println("firstRole : " + firstRole + " ;; secondRole : " + secondRole + " ;; thirdRole : " + thirdRole);
                    System.out.println("oriRole1 : " + oriRole1 + " ;; oriRole2 : " + oriRole2 + " ;;oriRole3 : " + oriRole3);

                    int x = 3;
                    int y = 0;
                    boolean firstStart = false;
                    boolean secondStart = false;
                    boolean thirdStart = false;
                    //再設定TEAM ROLE
                    for(TeamDTO teamDTO : teamDTOS_ALL){
                        int team = teamDTO.getTeam();
                        int role = teamDTO.getRole();
                        String name = teamDTO.getName();
                        String memberCtxId = teamDTO.getCtxId();
                        System.out.println("toggleGroup memberCtxId: " + memberCtxId + " ; team : " + team + " ; teamIndex : " + teamIndex + " ; role : " + role + " ; name : " + name);

                        if(teamIndex == team){
                            System.out.println("toggleGroup teamIndex: " + teamIndex + " ; team : " + team + " ; treatRole: " + treatRole);
                            System.out.println("toggleGroup selectMissionMemberIndex: " + selectMissionMemberIndex + " ; memberCtxId : " + memberCtxId);

                            if(memberCtxId.equals(selectMissionMemberIndex)){
                                teamDTO.setRole(treatRole);
                                y = x;
                            }else{
//                                switch (y) {
//                                    case 0, 1, 2 -> {
//                                        if (treatRole == 3 && role == treatRole) {
//                                            teamDTO.setRole(2);
//                                        } else if (treatRole == 2 && role == treatRole) {
//                                            teamDTO.setRole(1);
//                                        } else if (treatRole == 1) {
//                                            teamDTO.setRole(3);
//                                        }
//                                    }
//                                    default -> {
//                                    }
//                                }
                                if(firstRole > 0){
                                    teamDTO.setRole(firstRole);
                                    firstRole = 0;
                                }else if(secondRole > 0){
                                    teamDTO.setRole(secondRole);
                                    secondRole = 0;
                                }else if(thirdRole > 0){
                                    teamDTO.setRole(thirdRole);
                                    thirdRole = 0;
                                }

//                                if(treatRole == 3){
//                                    switch (x) {
//                                        case 3 -> {
//                                            teamDTO.setRole(2);
//                                        }
//                                        case 2 -> {
//                                            if(y == 1){
//                                                teamDTO.setRole(2);
//                                            }
//                                        }
//                                        case 1 -> {
////                                            teamDTO.setRole(2);
//                                        }
//                                        default -> {
//                                        }
//                                    }
//                                }else if(treatRole == 2){
//                                    switch (x) {
//                                        case 3 -> {
////                                            teamDTO.setRole(3);
//                                        }
//                                        case 2 -> {
//                                            teamDTO.setRole(1);
//                                        }
//                                        case 1 -> {
////                                            teamDTO.setRole(1);
//                                        }
//                                        default -> {
//                                        }
//                                    }
//
//                                }else if(treatRole == 1){
//                                    switch (x) {
//                                        case 3 -> {
////                                            teamDTO.setRole(3);
//                                        }
//                                        case 2 -> {
////                                            teamDTO.setRole(2);
//                                        }
//                                        case 1 -> {
//                                            teamDTO.setRole(3);
//                                        }
//                                        default -> {
//                                        }
//                                    }
//
//                                }


//                                if(oriRole1 != 0 && oriRole1 == treatRole){
//                                    if(firstRole != 0){
//                                        teamDTO.setRole(firstRole);
//                                        oriRole1 = 0;
//                                        firstRole = 0;
//                                    }else if(secondRole != 0){
//                                        teamDTO.setRole(secondRole);
//                                        oriRole1 = 0;
//                                        secondRole = 0;
//                                    }else {
//                                        teamDTO.setRole(thirdRole);
//                                        oriRole1 = 0;
//                                        thirdRole = 0;
//                                    }
//                                }else if(oriRole2 != 0 && oriRole2 == treatRole){
//                                    if(firstRole != 0){
//                                        teamDTO.setRole(firstRole);
//                                        oriRole2 = 0;
//                                        firstRole = 0;
//                                    }else if(secondRole != 0){
//                                        teamDTO.setRole(secondRole);
//                                        oriRole2 = 0;
//                                        secondRole = 0;
//                                    }else {
//                                        teamDTO.setRole(thirdRole);
//                                        oriRole2 = 0;
//                                        thirdRole = 0;
//                                    }
//                                }else if(oriRole3 != 0 && oriRole3 == treatRole){
//                                    if(firstRole != 0){
//                                        teamDTO.setRole(firstRole);
//                                        oriRole3 = 0;
//                                        firstRole = 0;
//                                    }else if(secondRole != 0){
//                                        teamDTO.setRole(secondRole);
//                                        oriRole3 = 0;
//                                        secondRole = 0;
//                                    }else {
//                                        teamDTO.setRole(thirdRole);
//                                        oriRole3 = 0;
//                                        thirdRole = 0;
//                                    }
//                                }
                            }
                            x--;
                        }
                    }
                }
            });

            toggleGroup.selectToggle(radio1);
            radio1.setSelected(true);
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


        tips.setImage(imageTip3);


        //************************************************************
        EventHandler selectTeamEventHandler = event1 -> {
            System.out.println("selectTeamEventHandler : ");
            EventType eventType = event1.getEventType();
            Pane sourceTeamPane = (Pane) event1.getSource();
            String currentCtxId = sourceTeamPane.getId().trim();
            Label sourceTeamSelectedLabel = (Label) sourceTeamPane.getChildren().get(0);
            ImageView sourceTeamView = (ImageView) sourceTeamPane.getChildren().get(1);
            Label sourceTeamLabel = (Label) sourceTeamPane.getChildren().get(2);
            if(eventType == MouseEvent.MOUSE_CLICKED){
                teamDTOS_ALL = NettyClientTeamService.getTeamDTOList();
//                List<TeamDTO> teamDTOS = NettyClientTeamService.getTeamDTOList();
                System.out.println("mouse clicked");


                int currentTeam = 0;
                if(teamSelectBack1.isVisible()){
                    currentTeam = 1;
                    int selectedSize = teamSelectedGridPane1.getChildren().size();
                    if(selectedSize > 0){

                        if(selectedSize < 3){
//                            int x = selectedSize == 2 ? 0 : 1;
//                            int y = selectedSize == 2 ? 1 : 0;

                            for(TeamDTO teamDTO: teamDTOS_ALL){
                                String ctxId = teamDTO.getCtxId();
                                String name = teamDTO.getName();
                                int team = teamDTO.getTeam();

                                if(ctxId.equals(currentCtxId)) {
                                    System.out.println("teamSelectBack1 : check enter");
                                    System.out.println("ctxId : " + ctxId + " ; currentCtxId : " + currentCtxId);
                                    System.out.println("team : " + team + " ; name : " + name + " ; currentTeam : " + currentTeam);
                                    System.out.println("team == currentTeam ? " + (team == currentTeam));

                                    if(team != currentTeam){
//                                        teamDTO.setTeam(currentTeam);
                                        Pane pane = new TeamUserPane(ctxId, name, currentTeam, true).getPane();
//                                        teamSelectedGridPane1.add(pane, x, y);
                                        teamSelectedGridPane1.add(pane, selectedSize, 0);
                                    }else{
                                        ObservableList<Node> nodes = teamSelectedGridPane1.getChildren();
                                        for(int z = 0 ; z < nodes.size(); z++){
                                            Pane selectedPane = (Pane)nodes.get(z);
                                            String selectedCtxId = selectedPane.getId();
                                            if(currentCtxId.equals(selectedCtxId)){
                                                nodes.remove(z);
                                                break;
                                            }
                                        }
                                    }

                                    break;
                                }
                            }

                            System.out.println("teamSelectBack1 : check enter finish");
                        }

                    }else{

                        for(TeamDTO teamDTO : teamDTOS_ALL){
                            String ctxId = teamDTO.getCtxId();
                            String name = teamDTO.getName();
//                            int team = teamDTO.getTeam();

                            if(currentCtxId.equals(ctxId)){
                                Pane pane = new TeamUserPane(ctxId, name, currentTeam, true).getPane();
                                teamSelectedGridPane1.add(pane, 0, 0);
                                break;
                            }
                        }

                    }

                }else if(teamSelectBack2.isVisible()){
                    currentTeam = 2;
                    int selectedSize = teamSelectedGridPane2.getChildren().size();
                    if(selectedSize > 0){

                        if(selectedSize < 3){
//                            int x = selectedSize == 2 ? 0 : 1;
//                            int y = selectedSize == 2 ? 1 : 0;
                            for(TeamDTO teamDTO: teamDTOS_ALL){
                                String ctxId = teamDTO.getCtxId();
                                String name = teamDTO.getName();
                                int team = teamDTO.getTeam();

                                if(ctxId.equals(currentCtxId)) {
                                    if(team != currentTeam){
                                        Pane pane = new TeamUserPane(ctxId, name, currentTeam, true).getPane();
//                                        teamSelectedGridPane2.add(pane, x, y);
                                        teamSelectedGridPane2.add(pane, selectedSize, 0);
                                    }else{
                                        ObservableList<Node> nodes = teamSelectedGridPane2.getChildren();
                                        for(int z = 0 ; z < nodes.size(); z++){
                                            Pane selectedPane = (Pane)nodes.get(z);
                                            String selectedCtxId = selectedPane.getId();
                                            if(currentCtxId.equals(selectedCtxId)){
                                                nodes.remove(z);
                                                break;
                                            }
                                        }
                                    }

                                    break;
                                }
                            }
                        }


                    }else{

                        for(TeamDTO teamDTO : teamDTOS_ALL) {
                            String ctxId = teamDTO.getCtxId();
                            String name = teamDTO.getName();

                            if (currentCtxId.equals(ctxId)) {
                                Pane pane = new TeamUserPane(ctxId, name, currentTeam, true).getPane();
                                teamSelectedGridPane2.add(pane, 0, 0);
                                break;
                            }
                        }

                    }
                }
                System.out.println("currentTeam : " + currentTeam);
                System.out.println("currentId : " + currentCtxId);

                for (TeamDTO teamDTO : teamDTOS_ALL){
                    String ctxId = teamDTO.getCtxId().trim();
                    String name = teamDTO.getName();
                    int team = teamDTO.getTeam();
                    System.out.println("ctxId : " + ctxId);
                    if(currentCtxId.equals(ctxId)){
                        System.out.println("equals : ");
                        if(team != currentTeam){
                            teamDTO.setTeam(currentTeam);
                            sourceTeamPane.setOpacity(0.5);
                            sourceTeamSelectedLabel.setVisible(true);
                        }else{
                            teamDTO.setTeam(0);
                            sourceTeamPane.setOpacity(1.0);
                            sourceTeamSelectedLabel.setVisible(false);
                        }

                    }
                }

            }
        };
        //************************************************************


        System.out.println("teamDTOS_ALL: " + teamDTOS_ALL);

        for (int x = 0; x < teamDTOS_ALL.size(); x++){
                TeamDTO teamDTO = teamDTOS_ALL.get(x);
                String ctxId = teamDTO.getCtxId();
                String name = teamDTO.getName();
                int team = teamDTO.getTeam();
                Pane pane = new TeamUserPane(ctxId, name, team, false).getPane();
                pane.setOnMouseClicked(selectTeamEventHandler);
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

        }

    }

    @FXML
    private void onTeamSelectedBackAction(MouseEvent event) throws IOException{
        System.out.println("onTeamSelectedBackAction");

        EventType eventType = event.getEventType();

        System.out.println("eventType : " + eventType);

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
                    teamSelectedGridPane1.getChildren().clear();
                    teamSelectedGridPane2.getChildren().clear();
                    teamDTOS_ALL = NettyClientTeamService.getTeamDTOList();
                    System.out.println("teamDTOS: " + teamDTOS_ALL);

                    teamDTOS1 = new ArrayList<>();
                    teamDTOS2 = new ArrayList<>();
                    for(TeamDTO teamDTO : teamDTOS_ALL){
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

                        teamSelectedGridPane1.add(pane, x, y);

//                        if(x == 1){
//                            x = 0;
//                            y++;
//                        }
                    }

                    y = 0;
                    for(int x= 0; x+y < teamDTOS2.size(); x++){
                        TeamDTO teamDTO = teamDTOS2.get(x+y);
                        String ctxId = teamDTO.getCtxId();
                        String name = teamDTO.getName();
                        int team = teamDTO.getTeam();

//                teamSelectedPane2

                        Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
                        teamSelectedGridPane2.add(pane, x, y);

//                        if(x == 1){
//                            x = 0;
//                            y++;
//                        }
                    }

                    tips.setImage(imageTip2);
                    updatePane.setVisible(false);

                });


                // 啟動任務
                new Thread(countdownTask).start();
            }

        }
    }
    @FXML
    private void onBlankTeamSelectedBackAction(MouseEvent event) throws IOException{
        System.out.println("onBlankTeamSelectedBackAction");

        EventType eventType = event.getEventType();

        System.out.println("eventType : " + eventType);

        ImageView imageView = (ImageView)event.getSource();

        if(eventType == MouseEvent.MOUSE_CLICKED){
            imageView.setImage(imageTeam4);
            teamClicked = true;
        }else if(eventType == MouseEvent.MOUSE_ENTERED && !teamClicked){
            imageView.setImage(imageBlank2);
        }else if(eventType == MouseEvent.MOUSE_EXITED && !teamClicked){
            imageView.setImage(imageBlank1);
        }
    }

    @FXML
    private void onSelectedMemberAction(MouseEvent event) throws IOException{

    }

    @FXML
    private void onTeamRoleAction(MouseEvent event) throws IOException{
        System.out.println("onTeamRoleAction");
        ImageView imageView = (ImageView)event.getSource();

        int currentRole = 0;

        if(imageView.getImage().equals(imageTeamLeader1)){
            currentRole = 1;
            imageView.setImage(imageTeamLeader0);
        }else if(imageView.getImage().equals(imageTeamLeader0)){
            imageView.setImage(imageTeamLeader1);
        }

        for(TeamDTO teamDTO: teamDTOS_ALL){
            int team = teamDTO.getTeam();
            int role = teamDTO.getRole();
            String ctxId = teamDTO.getCtxId();
            String name = teamDTO.getName();

            if(team == Integer.parseInt(selectMissionTeamIndex)){
                if(ctxId.equals(selectMissionMemberIndex)){
                    teamDTO.setRole(currentRole);
                }
            }
        }
    }

    @FXML
    private void onTeamSelectedSentNextAction(MouseEvent event) throws IOException{
        EventType eventType = event.getEventType();
        ImageView imageView = (ImageView)event.getSource();

        if(eventType == MouseEvent.MOUSE_ENTERED){
            imageView.setImage(imageMission1);
        }else if(eventType == MouseEvent.MOUSE_CLICKED){
            imageView.setImage(imageMission3);

            sentPane.setVisible(true);

            String msgTeamStr = DTOParser.parseDTOsToString(teamDTOS_ALL.toArray());

            nettyClientMsgController.sendMissionMsg(selectClassIndex, selectCourseIndex, selectMissionUnitIndex, msgTeamStr);

            Task<Void> countdownTask = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    // 延遲兩秒
                    Thread.sleep(2000);
                    return null;
                }
            };

            countdownTask.setOnSucceeded(workerStateEvent -> {

                sentPane.setVisible(false);

            });

            // 啟動任務
            new Thread(countdownTask).start();

        }else if(eventType == MouseEvent.MOUSE_EXITED){
            imageView.setImage(imageMission2);
        }

    }

    private void onTeamEventAction() throws IOException{

//        courseEventHandler = event1 -> {
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
        tmpPos = 1;
    }

    @FXML
    private void startTeamCourseAction(MouseEvent event) throws IOException {
        System.out.println("startTeamCourseAction");


        gradeYearSelectGridPane.setVisible(false);
        gradeClassSelectGridPane.setVisible(false);

        courseSelect.setVisible(true);
        teamSelect.setVisible(true);
        missionSelect.setVisible(true);

        classGridPane.getChildren().clear();
        courseGridPane.getChildren().clear();

        tips.setVisible(true);

        tips.setImage(imageTip1);

        studentGradeSelect.setOpacity(0.0);
        tabGrade.setVisible(false);

        tableView0.setVisible(false);
        imgGradeClass.setVisible(false);
        imgGradeCourse.setVisible(false);

//        switch (tmpPos) {
//            case 1 ->{
//
//            }
//
////            case 2 -> {
////                teamCourseSettingsSelect.setOpacity(0.0);
////            }
//
//            case 3 -> {
////                watchCourse.setImage(tmpImage);
//                watchCourse.setOpacity(0.0);
//            }
//
//            case 4 -> {
////                helpCourse.setImage(tmpImage);
//                helpCourse.setOpacity(0.0);
//            }
//
//            case 5 -> {
//                studentGradeSelect.setOpacity(0.0);
//                tabGrade.setVisible(false);
//            }
//        }

//        ImageView clickedView = (ImageView) event.getSource();

        tmpImage = teamCourseSettings.getImage();

        tabBack.setVisible(true);

        backImg.setImage(imageCourse1);

        teamCourseSettingsSelect.setOpacity(1.0);

        tmpPos = 2;


        String urlCourse = url + "/user/getAllUserList";
        String response = HttpClientGetData.sendGetRequest(urlCourse);


        User[] users = ob.readValue(response, User[].class);//array
        HashSet<Long> classSet = new HashSet<>();
        for (User user: users){
            if(user.getStudentBatch() != 0){
                classSet.add(user.getStudentBatch());
            }
        }

        //test
//        for(int x = 0; x < 13; x++){
//            classSet.add(2002013L - x);
//        }

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

                for(ImageView imageView : imageViewClasses){
                    imageView.setImage(image0);
                }

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



        imageViewClasses = new ArrayList<>();

        int index = 0;
        for (int x = 0 ; x < sortClassYearList.size(); x++){
            for(int y = 0; y < 7 && index < sortClassYearList.size(); y++, index++){
                Pane pane = new ClassPane(sortClassYearList.get(index)).getPane();
                pane.setOnMouseClicked(classEventHandler);
                pane.setOnMouseEntered(classEventHandler);
                pane.setOnMouseExited(classEventHandler);
                classGridPane.add(pane, x, y);

                ImageView sourceView = (ImageView) pane.getChildren().get(0);
                imageViewClasses.add(sourceView);
                if(index == 0){
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

                for(ImageView imageView : imageViewCourses){
                    imageView.setImage(image0);
                }

                if(!currentId.equals(selectCourseIndex)){
                    selectCourseIndex = currentId;
                    sourceView.setImage(image2);
                    if(lastCourseView != null){
                        lastCourseView.setImage(image0);
                    }
                    lastCourseView = sourceView;

                    teamDTOS_ALL = NettyClientTeamService.getTeamDTOList();
                    System.out.println("teamDTOS_ALL: " + teamDTOS_ALL);

                    teamDTOS1 = new ArrayList<>();
                    teamDTOS2 = new ArrayList<>();
                    for(TeamDTO teamDTO : teamDTOS_ALL){
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

                        Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
                        teamSelectedGridPane1.add(pane, x, y);
                    }

                    y = 0;
                    for(int x= 0; x+y < teamDTOS2.size(); x++){
                        TeamDTO teamDTO = teamDTOS2.get(x+y);
                        String ctxId = teamDTO.getCtxId();
                        String name = teamDTO.getName();
                        int team = teamDTO.getTeam();

                        Pane pane = new TeamUserPane(ctxId, name, team, true).getPane();
                        teamSelectedGridPane2.add(pane, x, y);
                    }

                    System.out.println("selected showTeamPane");
                    classGridPane.setVisible(false);
                    courseGridPane.setVisible(false);

                    teamSelect1.setVisible(true);
                    teamSelect2.setVisible(true);
                    teamSelect3.setVisible(true);
                    teamSelect4.setVisible(true);

                    teamSelectPick1.setVisible(true);
                    teamSelectPick2.setVisible(true);
                    teamSelectedGridPane1.setVisible(true);
                    teamSelectedGridPane2.setVisible(true);

                    backImg.setImage(imageCourse2);
                    tabBack.setImage(imageTab2);

                    tips.setImage(imageTip2);

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

        imageViewCourses = new ArrayList<>();

        index = 0;
        for (int x = 0 ; x < courses.length; x++){
            for(int y = 0; y < 7 && index < courses.length; y++, index++){
                Pane pane = new CoursePane(courses[index]).getPane();
                pane.setOnMouseClicked(courseEventHandler);
                pane.setOnMouseEntered(courseEventHandler);
                pane.setOnMouseExited(courseEventHandler);

                ImageView sourceView = (ImageView)pane.getChildren().get(0);
                imageViewCourses.add(sourceView);
                
//                if(index == 0){
//                    sourceView.setImage(image2);
//                    selectCourseIndex = pane.getId();
//                }

                courseGridPane.add(pane, x, y);
            }
        }

        //test
//        for (int x = 0 ; x < 28; x++){
//            for(int y = 0; y < 7 && index < 28; y++, index++){
//                Pane pane = new CoursePane(courses[0]).getPane();
//                pane.setOnMouseClicked(courseEventHandler);
//                pane.setOnMouseEntered(courseEventHandler);
//                pane.setOnMouseExited(courseEventHandler);
//
//                courseGridPane.add(pane, x, y);
//            }
//        }

        classGridPane.setVisible(true);
        courseGridPane.setVisible(true);



    }



    @FXML
    private void watchCourseAction(MouseEvent event) throws IOException {
        System.out.println("watchCourseAction");

//        String cmd = "iNetConsole.exe";
//        String cmd = "\"C:\\Program Files (x86)\\SanFong\\EZ-WinMate Teacher\\iNetConsole.exe\"";
        String cmd = "\"C:\\work\\start-WinMate.cmd\"";
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);
//        ProcessBuilder processBuilder = new ProcessBuilder("runas", "/user:yctse", cmd);

//        String[]  startAsAdmin= new String [] {
//                "CMD.EXE",
//                "/C",
//                "RUNAS /profile /user:"
//                        + "administrator"
//                        + " ", "start", "notepad"};
//
//        Process runtimeProcess = Runtime.getRuntime().exec(startAsAdmin);

        try {
            Process process = processBuilder.start();

//            int exitCode = runtimeProcess.waitFor();
            boolean exitCode = process.waitFor(3000, TimeUnit.SECONDS);
            System.out.println("Exit code: " + exitCode);
            //1

            System.out.println("Info: " + process.toString());
            System.out.println("Info: " + process.info().toString());
            //Info: [user: Optional[USER004\yctse], startTime: Optional[2023-11-06T17:04:27.520Z], totalTime: Optional[PT0.015625S]]


        }catch (Exception e){
            System.out.println("Exception : " + e.getMessage());
        }

        tmpPos = 3;
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
        tmpPos = 4;
    }



    @FXML
    private void studentGradeAction(MouseEvent event) throws IOException {
        System.out.println("studentGradeAction");

        tableView0.getColumns().clear();
        tableViewAll.getColumns().clear();

        boolean disabledPane = false;

        classGridPane.setVisible(disabledPane);
        courseGridPane.setVisible(disabledPane);

        teamSelect1.setVisible(disabledPane);
        teamSelect2.setVisible(disabledPane);
        teamSelect3.setVisible(disabledPane);
        teamSelect4.setVisible(disabledPane);

        teamSelectPick1.setVisible(disabledPane);
        teamSelectPick2.setVisible(disabledPane);
        teamSelectedGridPane1.setVisible(disabledPane);
        teamSelectedGridPane2.setVisible(disabledPane);

        missionPane.setVisible(disabledPane);
        missionTeamGridPane.setVisible(disabledPane);
        missionMemberGridPane.setVisible(disabledPane);
        missionUnitGridPane.setVisible(disabledPane);

        courseSelect.setVisible(disabledPane);
        teamSelect.setVisible(disabledPane);
        missionSelect.setVisible(disabledPane);
//        tmpImage = teamCourseSettings.getImage();

        teamCourseSettingsSelect.setOpacity(0.0);

        classGridPane.setVisible(disabledPane);
        courseGridPane.setVisible(disabledPane);

        tabBack.setVisible(disabledPane);

        imgGradeClass.setVisible(!disabledPane);
        imgGradeCourse.setVisible(!disabledPane);

        backImg.setImage(imageCourse3);
        tips.setVisible(!disabledPane);
        tips.setImage(imageTip5);

        studentGradeSelect.setOpacity(1.0);
        gradePane.setVisible(!disabledPane);
        tabGrade.setVisible(!disabledPane);

        gradeYearSelectGridPane.setVisible(!disabledPane);
        gradeClassSelectGridPane.setVisible(!disabledPane);

//        switch (tmpPos) {
//            case 1 ->{
//
//            }
//
//            case 2 -> {
//                teamCourseSettingsSelect.setOpacity(0.0);
//                tabBack.setVisible(false);
//            }
//
//            case 3 -> {
////                watchCourse.setImage(tmpImage);
//                watchCourse.setOpacity(0.0);
//            }
//
//            case 4 -> {
////                helpCourse.setImage(tmpImage);
//                helpCourse.setOpacity(0.0);
//            }
//
////            case 5 -> {
////                studentGradeSelect.setOpacity(1.0);
////                tabGrade.setVisible(true);
////            }
//        }



        String urlCourse = url + "/user/getAllUserList";
        String response = HttpClientGetData.sendGetRequest(urlCourse);


        gradeUsers = ob.readValue(response, User[].class);//array
        HashSet<Long> classSet = new HashSet<>();
        for (User user: gradeUsers){
            if(user.getStudentBatch() != 0){
                classSet.add(user.getStudentBatch());
            }
        }



        //test
//        for(int x = 0; x < 13; x++){
//            classSet.add(2002013L - x);
//        }

        ArrayList<Long> sortClassYearList = new ArrayList<>(classSet);
        sortClassYearList.sort(Collections.reverseOrder());

//        List<Long> sortUserList = Arrays.stream(users).map(User::getStudentBatch).toList();//
//        System.out.println("sortUserList : "+sortUserList);

        EventHandler gradeClassEventHandler = event1 -> {
            EventType eventType = event1.getEventType();
            Pane sourcePane = (Pane) event1.getSource();
            String currentId = sourcePane.getId();
            ImageView sourceView = (ImageView) sourcePane.getChildren().get(0);
//            Label sourceLabel = (Label) sourcePane.getChildren().get(1);
            if(eventType == MouseEvent.MOUSE_CLICKED){
                System.out.println("mouse clicked");

                for(ImageView imageView : imageViewGradeClasses){
                    imageView.setImage(image0);
                }

                if(!currentId.equals(selectGradeClassIndex)){
                    selectGradeClassIndex = currentId;
                    if(lastGradeClassView != null){
                        lastGradeClassView.setImage(image0);
                    }
                    lastGradeClassView = sourceView;
                }
                sourceView.setImage(image2);
//                else{
//                    selectGradeClassIndex = "";
//                    sourceView.setImage(image1);
//                    lastGradeClassView = null;
//                }


            }else if(eventType == MouseEvent.MOUSE_ENTERED){
                if(!currentId.equals(selectGradeClassIndex)){
                    sourceView.setImage(image1);
                }

            }else if(eventType == MouseEvent.MOUSE_EXITED){
                if(!currentId.equals(selectGradeClassIndex)) {
                    sourceView.setImage(image0);
                }

            }
        };



        imageViewGradeClasses = new ArrayList<>();

            for(int y = 0; y < sortClassYearList.size(); y++){
                Pane pane = new ClassPane(sortClassYearList.get(y)).getPane();
                pane.setOnMouseClicked(gradeClassEventHandler);
                pane.setOnMouseEntered(gradeClassEventHandler);
                pane.setOnMouseExited(gradeClassEventHandler);
                gradeYearSelectGridPane.add(pane, 0, y);

                ImageView sourceView = (ImageView) pane.getChildren().get(0);
                imageViewGradeClasses.add(sourceView);
                if(y == 0){
                    sourceView.setImage(image2);
                    selectGradeClassIndex = pane.getId();
                }
            }

//        ObservableList<User> data = FXCollections.observableArrayList();
//        data.addAll(users);
//
//        gradeList.setItems(data);

        urlCourse = url + "/course/getAllCourse";
        response = HttpClientGetData.sendGetRequest(urlCourse);


        gradeCourses = ob.readValue(response, Course[].class);//array
        List<String> sourseNameList = Arrays.stream(gradeCourses).map(Course::getCourseName).toList();//
        System.out.println("sourseNameList : "+sourseNameList);

        EventHandler gradeCourseEventHandler = event1 -> {
            EventType eventType = event1.getEventType();
            Pane sourcePane = (Pane) event1.getSource();
            String currentId = sourcePane.getId();
            ImageView sourceView = (ImageView) sourcePane.getChildren().get(0);
            if(eventType == MouseEvent.MOUSE_CLICKED){
                System.out.println("gradeCourseEventHandler mouse clicked");

                for(ImageView imageView : imageViewGradeCourses){
                    imageView.setImage(image0);
                }

                if(!currentId.equals(selectGradeCourseIndex)){
                    selectGradeCourseIndex = currentId;

                    if(lastGradeCourseView != null){
                        lastGradeCourseView.setImage(image0);
                    }
                    lastGradeCourseView = sourceView;

//                }else{
//                    selectGradeCourseIndex = "";
//                    sourceView.setImage(image0);
//                    lastGradeCourseView = null;
                }
                sourceView.setImage(image2);

//                List<Attendance> findAttendanceList = new ArrayList<>();
                List<Grade> findGradeList = new ArrayList<>();
                System.out.println("currentId : " + currentId);
                for (Attendance attendance : attendances) {
                    System.out.println("attendance.getCourseId() : " + attendance.getCourseId());
                    System.out.println("attendance : " + attendance);
                    if (attendance.getCourseId() == Long.parseLong(currentId)) {
                        Grade grade = new Grade();
                        grade.setCourseId(attendance.getCourseId());
                        grade.setAttendanceId(attendance.getAttendanceId());
                        grade.setTeam(attendance.getTeam());
                        grade.setScore(attendance.getScore());

                        Long studentId = 0L;
                        String userName = "";
                        String name = "";
                        for (User user : gradeUsers) {
                            if (user.getUsername().equals(attendance.getUsername())) {
                                studentId = user.getStudentId();
                                userName = user.getUsername();
                                name = user.getName();
                                break;
                            }
                        }
                        grade.setStudentId(studentId);
                        grade.setUsername(userName);
                        grade.setName(name);

                        String unitName = "";
                        for (Unit unit : gradeUnits) {
                            System.out.println("unitId attendance : " + attendance.getUnitId() + " ; unit : " + unit.getUnitId() );
                            System.out.println("courseId attendance : " + attendance.getCourseId() + " ; unit : " + unit.getCourseId());
                            if (Objects.equals(attendance.getUnitId(), unit.getUnitId()) && Objects.equals(attendance.getCourseId(), unit.getCourseId())) {
                                unitName = unit.getUnitName();
                                break;
                            }
                        }

                        grade.setUnitName(unitName);
                        System.out.println("grade : " + grade);

                        findGradeList.add(grade);
                    }
                }

//                findAttendances = (Attendance[]) Arrays.asList(attendanceList).toArray();
//                ObservableList<Attendance> newAttendanceList = FXCollections.observableArrayList(findAttendanceList);
                ObservableList<Grade> newGradeList = FXCollections.observableArrayList(findGradeList);

//                ObservableList<Attendance> newAttendanceList = FXCollections.observableArrayList(findAttendances);
//                tableView0.setItems(newAttendanceList);
                tableView0.setItems(newGradeList);


                tableViewDetail.setVisible(false);
                imgGradeExport.setVisible(false);
                imgGradePrint.setVisible(false);
                imgGradeBack.setVisible(false);

                tableView0.setVisible(true);


            }else if(eventType == MouseEvent.MOUSE_ENTERED){
                if(!currentId.equals(selectGradeCourseIndex)){
                    sourceView.setImage(image1);
                }

            }else if(eventType == MouseEvent.MOUSE_EXITED){
                if(!currentId.equals(selectGradeCourseIndex)) {
                    sourceView.setImage(image0);
                }

            }
        };

        imageViewGradeCourses = new ArrayList<>();
        for(int y = 0; y < gradeCourses.length; y++){
            Pane pane = new CoursePane(gradeCourses[y]).getPane();
            pane.setOnMouseClicked(gradeCourseEventHandler);
            pane.setOnMouseEntered(gradeCourseEventHandler);
            pane.setOnMouseExited(gradeCourseEventHandler);

            ImageView sourceView = (ImageView)pane.getChildren().get(0);
            imageViewGradeCourses.add(sourceView);

//                if(index == 0){
//                    sourceView.setImage(image2);
//                    selectGradeCourseIndex = pane.getId();
//                }

            gradeClassSelectGridPane.add(pane, 0, y);
        }

        //test
//        for (int x = 0 ; x < 28; x++){
//            for(int y = 0; y < 7 && index < 28; y++, index++){
//                Pane pane = new CoursePane(courses[0]).getPane();
//                pane.setOnMouseClicked(courseEventHandler);
//                pane.setOnMouseEntered(courseEventHandler);
//                pane.setOnMouseExited(courseEventHandler);
//
//                courseGridPane.add(pane, x, y);
//            }
//        }

        gradeYearSelectGridPane.setVisible(true);
        gradeClassSelectGridPane.setVisible(true);
        tableView0.setVisible(true);





        System.out.println("urlAttendance : ");
        String urlAttendance = url + "/attendance/getAllAttendanceList";
        System.out.println(urlAttendance);
        response = HttpClientGetData.sendGetRequest(urlAttendance);
        System.out.println("response : " + response);

        if(response != null && !response.isEmpty()){
            attendances = ob.readValue(response, Attendance[].class);//array
        }

        if(attendances == null || attendances.length == 0){
            Attendance attendance = new Attendance();
            attendance.setRole(1L);
            attendance.setTeam(1L);
            attendance.setUsername("學生1");
            attendance.setContentId(123456789L);
            attendance.setScore(90L);

            Attendance attendance2 = new Attendance();
            attendance2.setRole(1L);
            attendance2.setTeam(2L);
            attendance2.setUsername("學生2");
            attendance2.setContentId(123456789L);
            attendance2.setScore(90L);

            Attendance attendance3 = new Attendance();
            attendance3.setRole(1L);
            attendance3.setTeam(3L);
            attendance3.setUsername("學生3");
            attendance3.setContentId(123456789L);
            attendance3.setScore(90L);

            attendances = new Attendance[]{attendance, attendance2, attendance3};

        }

        String urlUnit = url + "/unit/getAllUnit";
        response = HttpClientGetData.sendGetRequest(urlUnit);

        gradeUnits = ob.readValue(response, Unit[].class);//array

//        TableColumn<Attendance, Long> teamColumn = new TableColumn<>("小組");
        TableColumn<Grade, Long> teamColumn = new TableColumn<>("小組");
        teamColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTeam()));
        teamColumn.setPrefWidth(60.0);
        teamColumn.setMinWidth(60.0);
        teamColumn.setMaxWidth(60.0);
        teamColumn.setStyle("-fx-alignment: CENTER; -fx-background-radius: 10px 0 0 0;"); // 左上倒角

//        TableColumn<Attendance, Long> numberColumn = new TableColumn<>("學號");
//        numberColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getContentId()));
        TableColumn<Grade, Long> studentIdColumn = new TableColumn<>("學號");
        studentIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStudentId()));
        studentIdColumn.setPrefWidth(80.0);
        studentIdColumn.setMinWidth(80.0);
        studentIdColumn.setMaxWidth(80.0);
        studentIdColumn.setStyle("-fx-alignment: CENTER;");

//        TableColumn<Attendance, String> nameColumn = new TableColumn<>("姓名");
        TableColumn<Grade, String> nameColumn = new TableColumn<>("姓名");
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        nameColumn.setPrefWidth(80.0);
        nameColumn.setMinWidth(80.0);
        nameColumn.setMaxWidth(80.0);
        nameColumn.setStyle("-fx-alignment: CENTER;");

//        TableColumn<Attendance, Long> contentColumn = new TableColumn<>("課程內容");
//        contentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRole()));
        TableColumn<Grade, String> unitNameColumn = new TableColumn<>("單元名稱");
        unitNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUnitName()));
        unitNameColumn.setPrefWidth(168.0);
        unitNameColumn.setMinWidth(168.0);
        unitNameColumn.setMaxWidth(168.0);
        unitNameColumn.setStyle("-fx-alignment: CENTER;");

//        TableColumn<Attendance, Long> gradeColumn = new TableColumn<>("成績");
        TableColumn<Grade, Long> scoreColumn = new TableColumn<>("成績");
        scoreColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore()));
        scoreColumn.setPrefWidth(60.0);
        scoreColumn.setMinWidth(60.0);
        scoreColumn.setMaxWidth(60.0);
        scoreColumn.setStyle("-fx-alignment: CENTER; -fx-background-radius: 0 10px 0 0;");   //右上倒角

        tableView0.getColumns().addAll(teamColumn, studentIdColumn, nameColumn, unitNameColumn, scoreColumn);

//        tableView0.getSelectionModel().selectedItemProperty().addListener((observableValue, oldAttendance, newAttendance) -> {
//            System.out.println("oldAttendance : " + oldAttendance);
//            if(newAttendance != null){
//                System.out.println("newAttendance : " + newAttendance);
//            }
//        });

        tableView0.getSelectionModel().selectedItemProperty().addListener((observableValue, oldGrade, newGrade) -> {
            System.out.println("oldGrade : " + oldGrade);
            if(newGrade != null){
                tableView0.setVisible(false);
                tableViewDetail.setItems(null);

                System.out.println("newGrade : " + newGrade);
                Long newCourseId = newGrade.getCourseId();

                List<Attendance> findAttendanceList = new ArrayList<>();
                System.out.println("newCourseId : " + newCourseId);
                for (Attendance attendance : attendances) {
                    System.out.println("attendance.getCourseId() : " + attendance.getCourseId());
                    System.out.println("attendance : " + attendance);
                    if (Objects.equals(attendance.getCourseId(), newCourseId)) {
                        findAttendanceList.add(attendance);
                    }
                }

                ObservableList<Attendance> newAttendanceList = FXCollections.observableArrayList(findAttendanceList);
                tableViewDetail.setItems(newAttendanceList);

                tableViewDetail.setVisible(true);
                imgGradeBack.setVisible(true);
                imgGradeExport.setVisible(true);
                imgGradePrint.setVisible(true);
            }
        });

        tableView0.setFixedCellSize(30);

        tableView0.setStyle("-fx-font-size: 14px; -fx-font-family: TaipeiSansTCBeta-Bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-selection-bar-non-focused: black;");

//        tableView0.getSelectionModel().selectedItemProperty().addListener((observableValue, oldAttendance, newAttendance) -> {
//            System.out.println("oldAttendance : " + oldAttendance);
//            if(newAttendance != null){
//                System.out.println("newAttendance : " + newAttendance);
//            }
//        });
        



        TableColumn<Grade, Long> studentBatchColumn = new TableColumn<>("班級");
        studentBatchColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStudentBatch()));
        studentBatchColumn.setPrefWidth(80.0);
        studentBatchColumn.setMinWidth(80.0);
        studentBatchColumn.setMaxWidth(80.0);
        studentBatchColumn.setStyle("-fx-alignment: CENTER; -fx-background-radius: 10px 0 0 0;"); // 左上倒角

        TableColumn<Grade, String> courseNameColumn = new TableColumn<>("課程名稱");
        courseNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCourseName()));
        courseNameColumn.setPrefWidth(130.0);
        courseNameColumn.setMinWidth(130.0);
        courseNameColumn.setMaxWidth(130.0);
        courseNameColumn.setStyle("-fx-alignment: CENTER;");

        teamColumn = new TableColumn<>("隊伍");
        teamColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTeam()));
        teamColumn.setPrefWidth(60.0);
        teamColumn.setMinWidth(60.0);
        teamColumn.setMaxWidth(60.0);
        teamColumn.setStyle("-fx-alignment: CENTER;");

        studentIdColumn = new TableColumn<>("學號");
        studentIdColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStudentId()));
        studentIdColumn.setPrefWidth(80.0);
        studentIdColumn.setMinWidth(80.0);
        studentIdColumn.setMaxWidth(80.0);
        studentIdColumn.setStyle("-fx-alignment: CENTER;");

//        TableColumn<Attendance, String> nameColumn = new TableColumn<>("姓名");
        nameColumn = new TableColumn<>("姓名");
        nameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getName()));
        nameColumn.setPrefWidth(100.0);
        nameColumn.setMinWidth(100.0);
        nameColumn.setMaxWidth(100.0);
        nameColumn.setStyle("-fx-alignment: CENTER;");

//        TableColumn<Attendance, Long> contentColumn = new TableColumn<>("課程內容");
//        contentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRole()));
        unitNameColumn = new TableColumn<>("單元名稱");
        unitNameColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUnitName()));
        unitNameColumn.setPrefWidth(237.0);
        unitNameColumn.setMinWidth(237.0);
        unitNameColumn.setMaxWidth(237.0);
        unitNameColumn.setStyle("-fx-alignment: CENTER;");

        scoreColumn = new TableColumn<>("成績");
        scoreColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore()));
        scoreColumn.setPrefWidth(60.0);
        scoreColumn.setMinWidth(60.0);
        scoreColumn.setMaxWidth(60.0);
        scoreColumn.setStyle("-fx-alignment: CENTER; -fx-background-radius: 0 10px 0 0;");   //右上倒角

        tableViewAll.getColumns().addAll(studentBatchColumn, courseNameColumn, teamColumn, studentIdColumn, nameColumn, unitNameColumn, scoreColumn);



        tableViewAll.getSelectionModel().selectedItemProperty().addListener((observableValue, oldGrade, newGrade) -> {
            System.out.println("oldGrade : " + oldGrade);
            if(newGrade != null){
                System.out.println("newGrade : " + newGrade);
            }
        });

        tableViewAll.setFixedCellSize(30);

        tableViewAll.setStyle("-fx-font-size: 14px; -fx-font-family: TaipeiSansTCBeta-Bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-selection-bar-non-focused: black;");
        //-fx-selection-bar: black; -fx-selection-bar-non-focused: black; -fx-border-width: 2px;

        
        
        //        tableViewDetail
        TableColumn<Attendance, Long> teamDetailColumn = new TableColumn<>("小組");
        teamDetailColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTeam()));
        teamDetailColumn.setPrefWidth(60.0);
        teamDetailColumn.setMinWidth(60.0);
        teamDetailColumn.setMaxWidth(60.0);
        teamDetailColumn.setStyle("-fx-alignment: CENTER; -fx-background-radius: 10px 0 0 0;"); // 左上倒角

        TableColumn<Attendance, Long> roleDetailColumn = new TableColumn<>("席位");
        roleDetailColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRole()));
        roleDetailColumn.setPrefWidth(60.0);
        roleDetailColumn.setMinWidth(60.0);
        roleDetailColumn.setMaxWidth(60.0);
        roleDetailColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Attendance, Long> courseIdDetailColumn = new TableColumn<>("課程編號");
//        nameColumn = new TableColumn<>("姓名");
        courseIdDetailColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCourseId()));
        courseIdDetailColumn.setPrefWidth(80.0);
        courseIdDetailColumn.setMinWidth(80.0);
        courseIdDetailColumn.setMaxWidth(80.0);
        courseIdDetailColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Attendance, Long> unitIdDetailColumn = new TableColumn<>("單元編號");
//        contentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRole()));
//        unitIdDetailColumn = new TableColumn<>("單元編號");
        unitIdDetailColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUnitId()));
        unitIdDetailColumn.setPrefWidth(80.0);
        unitIdDetailColumn.setMinWidth(80.0);
        unitIdDetailColumn.setMaxWidth(80.0);
        unitIdDetailColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Attendance, Long> contentIdAllColumn = new TableColumn<>("內容編號");
//        contentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRole()));
//        contentIdAllColumn = new TableColumn<>("內容編號");
        contentIdAllColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getUnitId()));
        contentIdAllColumn.setPrefWidth(80.0);
        contentIdAllColumn.setMinWidth(80.0);
        contentIdAllColumn.setMaxWidth(80.0);
        contentIdAllColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Attendance, Date> attendanceDateAllColumn = new TableColumn<>("紀錄日期");
//        contentColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRole()));
//        attendanceDateAllColumn = new TableColumn<>("紀錄日期");
        attendanceDateAllColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAttendanceDate()));
        attendanceDateAllColumn.setPrefWidth(108.0);
        attendanceDateAllColumn.setMinWidth(108.0);
        attendanceDateAllColumn.setMaxWidth(108.0);
        attendanceDateAllColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Attendance, Long> scoreAllColumn = new TableColumn<>("成績");
//        scoreAllColumn = new TableColumn<>("成績");
        scoreAllColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getScore()));
        scoreAllColumn.setPrefWidth(60.0);
        scoreAllColumn.setMinWidth(60.0);
        scoreAllColumn.setMaxWidth(60.0);
        scoreAllColumn.setStyle("-fx-alignment: CENTER; -fx-background-radius: 0 10px 0 0;");   //右上倒角

        tableViewDetail.getColumns().addAll(teamDetailColumn, roleDetailColumn, courseIdDetailColumn, unitIdDetailColumn, contentIdAllColumn, attendanceDateAllColumn, scoreAllColumn);

        tableViewDetail.getSelectionModel().selectedItemProperty().addListener((observableValue, oldGrade, newGrade) -> {
            System.out.println("oldGrade : " + oldGrade);
            if(newGrade != null){
                System.out.println("newGrade : " + newGrade);
            }
        });

        tableViewDetail.setFixedCellSize(30);

        tableViewDetail.setStyle("-fx-font-size: 14px; -fx-font-family: TaipeiSansTCBeta-Bold; -fx-focus-color: transparent; -fx-faint-focus-color: transparent; -fx-selection-bar-non-focused: black;");


        tmpPos = 5;
    }

    @FXML
    private void studentGradeTabAction(MouseEvent event) throws IOException {
        System.out.println("studentGradeTabAction");

        EventType eventType = event.getEventType();
        ImageView clickView = (ImageView)event.getSource();
        String clickId = clickView.getId();

        boolean isVisible = false;
        if(eventType == MouseEvent.MOUSE_CLICKED){
            if(clickId.equals("tabGradeBack2")){
                tabGrade.setImage(imageGradeBack2);

                tableViewAll.setItems(null);

                List<Grade> findFullGradeList = new ArrayList<>();
                for (Attendance attendance : attendances) {
                    System.out.println("attendance.getCourseId() : " + attendance.getCourseId());

                    Grade fullGrade = new Grade();
                    fullGrade.setCourseId(attendance.getCourseId());

                    String courseName = "";
                    for (Course course : gradeCourses) {
                        if (Objects.equals(course.getCourseId(), attendance.getCourseId())) {
                            courseName = course.getCourseName();
                            break;
                        }
                    }
                    fullGrade.setCourseName(courseName);

                    fullGrade.setAttendanceId(attendance.getAttendanceId());
                    fullGrade.setTeam(attendance.getTeam());
                    fullGrade.setScore(attendance.getScore());

                    Long studentId = 0L;
                    Long studentBatch = 0L;
                    String userName = "";
                    String name = "";
                    for (User user : gradeUsers) {
                        if (user.getUsername().equals(attendance.getUsername())) {
                            studentId = user.getStudentId();
                            studentBatch = user.getStudentBatch();
                            userName = user.getUsername();
                            name = user.getName();
                            break;
                        }
                    }
                    fullGrade.setStudentId(studentId);
                    fullGrade.setStudentBatch(studentBatch);
                    fullGrade.setUsername(userName);
                    fullGrade.setName(name);

                    String unitName = "";
                    for (Unit unit : gradeUnits) {
                        if (Objects.equals(attendance.getUnitId(), unit.getUnitId()) && Objects.equals(attendance.getCourseId(), unit.getCourseId())) {
                            unitName = unit.getUnitName();
                            break;
                        }
                    }

                    fullGrade.setUnitName(unitName);

                    findFullGradeList.add(fullGrade);
                }

                ObservableList<Grade> newGradeList = FXCollections.observableArrayList(findFullGradeList);

                tableViewAll.setItems(newGradeList);

            }else{
                tabGrade.setImage(imageGradeBack1);

                isVisible = true;

            }
            imgGradeClass.setVisible(isVisible);
            imgGradeCourse.setVisible(isVisible);
            gradeYearSelectGridPane.setVisible(isVisible);
            gradeClassSelectGridPane.setVisible(isVisible);
            tableView0.setVisible(isVisible);
            tableViewAll.setVisible(!isVisible);
            imgGradeBack.setVisible(!isVisible);
            tableViewDetail.setVisible(!isVisible);
            imgGradeExport.setVisible(!isVisible);
            imgGradePrint.setVisible(!isVisible);

        }

    }

    @FXML
    private void imgGradeBackAction(MouseEvent event) throws IOException {
        System.out.println("imgGradeBackAction");
        EventType eventType = event.getEventType();
        ImageView imageView = (ImageView)event.getSource();

        if(eventType == MouseEvent.MOUSE_CLICKED){
            imageView.setVisible(false);
            tableViewDetail.setVisible(false);
            imgGradeExport.setVisible(false);
            imgGradePrint.setVisible(false);

            tableView0.setVisible(true);
        }else if(eventType == MouseEvent.MOUSE_ENTERED){
            imageView.setImage(imageGradeReturn2);
        }else if(eventType == MouseEvent.MOUSE_EXITED){

            imageView.setImage(imageGradeReturn);
        }
    }

}
