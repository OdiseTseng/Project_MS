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
import com.ncsist.sstp.vo.Course;
import com.ncsist.sstp.vo.Unit;
import com.ncsist.sstp.vo.User;
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
    private ListView<Course> courseList;

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
    
    private Image imageBlank1;
    private Image imageBlank2;
    private Image imageTeam4;

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

    private int selectMissionRoleIndex = 3;

    List<ImageView> imageViewClasses = null;
    List<ImageView> imageViewCourses = null;
    List<ImageView> imageViewTeams = null;
    List<ImageView> imageViewMissionUnits = null;

    List<ImageView> imageViewMissionTeams = null;
    List<ImageView> imageViewMissionMembers = null;

    List<TeamDTO> teamDTOS_ALL = null;

    List<TeamDTO> teamDTOS1 = null;
    List<TeamDTO> teamDTOS2 = null;

    Unit[] units = null;
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

        imageBlank1 = new Image("/images/ms/team_course/課程設定2新增組別.png");
        imageBlank2 = new Image("/images/ms/team_course/課程設定2新增組別處碰.png");

        imageTeam4 = new Image("/images/ms/team_course/課程設定2第四組.png");

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
                            imageView.setImage(image1);
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
                        imageView.setImage(image1);
                    }

                    for(TeamDTO teamDTO : teamDTOS_ALL){
                        String ctxId = teamDTO.getCtxId();
                        int team = teamDTO.getTeam();
                        int role = teamDTO.getRole();
                        if(team == Integer.parseInt(currentMissionId)){
                            RadioButton selectButton = radio1;
                            switch(role){
                                case 2:
                                    selectButton = radio2;
                                    break;
                                case 1:
                                    selectButton = radio3;
                                    break;

                                case 3:
                                default:
                                    break;
                            }
                            toggleGroup.selectToggle(selectButton);
                            break;
                        }
                    }

                    if(!currentMissionId.equals(selectMissionTeamIndex)){
                        selectMissionTeamIndex = currentMissionId;
                        sourceMissionView.setImage(image2);
                        if(lastMissionTeamView != null){
                            lastMissionTeamView.setImage(image0);
                        }
                        lastMissionTeamView = sourceMissionView;
                    }
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
                        imageView.setImage(image1);
                    }

                    for(TeamDTO teamDTO : teamDTOS_ALL){
                        String ctxId = teamDTO.getCtxId();
                        int role = teamDTO.getRole();
                        if(ctxId.equals(currentMissionMemberId)){
                            RadioButton selectButton = radio1;
                            switch(role){
                                case 2:
                                    selectButton = radio2;
                                    break;
                                case 1:
                                    selectButton = radio3;
                                    break;
                                case 3:
                                default:
//                                    selectButton = radio1;
                                    break;
                            }
                            toggleGroup.selectToggle(selectButton);
                        }
                    }

                    if(!currentMissionMemberId.equals(selectMissionMemberIndex)){
                        selectMissionMemberIndex = currentMissionMemberId;
                        sourceMissionMemberView.setImage(image2);
                        if(lastMissionMemberView != null){
                            lastMissionMemberView.setImage(image0);
                        }
                        lastMissionMemberView = sourceMissionMemberView;
                    }

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
                int currentTeam = 0;

                imageViewMissionTeams = new ArrayList<>();

            for(int x = 0; x < 4; x++){
                boolean disablePane = teams < x + 1;
                Pane missionTeamPane = new MissionTeamPane(x, currentTeam, disablePane).getPane();

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

            imageViewMissionMembers = new ArrayList<>();

            for(int x = 0; x < teamDTOS1.size(); x++){
                TeamDTO teamDTO = teamDTOS1.get(x);
                int team = teamDTO.getTeam();
                int role = teamDTO.getRole();

                if(x == 0){
                    selectMissionMemberIndex = teamDTO.getCtxId();
                    if(role == 0){
                        teamDTO.setRole(3);
                    }
                }
//                if(team == 1 && "".equals(selectMissionMemberIndex)){
//                    selectMissionMemberIndex = teamDTO.getCtxId();
//                    if(role == 0){
//                        teamDTO.setRole(3);
//                    }
//                }

                Pane missionMemberPane = new MissionMemberPane(teamDTO.getName(), teamDTO.getCtxId(), selectMissionMemberIndex).getPane();
                missionMemberPane.setOnMouseEntered(missionMemberEventHandler);
                missionMemberPane.setOnMouseClicked(missionMemberEventHandler);
                missionMemberPane.setOnMouseExited(missionMemberEventHandler);

                ImageView sourceView = (ImageView)missionMemberPane.getChildren().get(0);
                imageViewMissionMembers.add(sourceView);


                missionMemberGridPane.add(missionMemberPane, x , 0);
            }

            toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal)->{
                if(newVal != null){
                    RadioButton selectedRadioButton = (RadioButton)newVal;
                    String selectedValue = selectedRadioButton.getText();
                    int total = missionPane.getChildren().size();
                    int selectedIndex = missionPane.getChildren().indexOf(selectedRadioButton);
                    // 1 操作手 / 2 通訊兵 / 3 車長 / 4 指揮 / 5 教官
                    int treatRole = total - selectedIndex -3;

                    System.out.println("total: " + total);
                    System.out.println("Selected Value: " + selectedValue);
                    System.out.println("Selected Index: " + selectedIndex);
                    System.out.println("treatRole: " + treatRole);

                    int teamIndex = Integer.parseInt(selectMissionTeamIndex);
//                    int memberIndex = Integer.parseInt(selectMissionMemberIndex);
                    int firstRole = 3;
                    int secondRole = 2;
                    int thirdRole = 1;

                    switch(treatRole){
                        case 3:
                            firstRole -= treatRole;
                            break;
                        case 2:
                            secondRole -= treatRole;
                            break;
                        case 1:
                            thirdRole -= treatRole;
                            break;
                    }

                    System.out.println("teamDTOS_ALL : " + teamDTOS_ALL.size());


                    for(TeamDTO teamDTO : teamDTOS_ALL){
                        int team = teamDTO.getTeam();
                        int role = teamDTO.getRole();
                        String memberCtxId = teamDTO.getCtxId();
                        System.out.println("memberCtxId: " + memberCtxId + " ; team : " + team + " ; teamIndex : " + teamIndex + " ; role : " + role);

                        if(teamIndex == team){
                            System.out.println("teamIndex: " + teamIndex + " ; team : " + team);
                            System.out.println("selectMissionMemberIndex: " + selectMissionMemberIndex + " ; memberCtxId : " + memberCtxId);

                            if(memberCtxId.equals(selectMissionMemberIndex)){
                                teamDTO.setRole(treatRole);
                            }else if(role == 0){
                                if(firstRole != 0){
                                    teamDTO.setRole(firstRole);
                                    firstRole = 0;
                                }else if(secondRole != 0){
                                    teamDTO.setRole(secondRole);
                                    secondRole = 0;
                                }else if(thirdRole != 0){
                                    teamDTO.setRole(thirdRole);
                                    thirdRole = 0;
                                }
                            }else if(role == treatRole){
                                if(treatRole == 3){
                                    teamDTO.setRole(secondRole);
                                    secondRole = 0;
                                }else if(treatRole == 2){
                                    teamDTO.setRole(thirdRole);
                                    thirdRole = 0;
                                }else{
                                    teamDTO.setRole(firstRole);
                                    firstRole = 0;
                                }
                            }else if(firstRole != 0){
//                                if(treatRole == 2){
//                                    teamDTO.setRole(thirdRole);
//                                    thirdRole = 0;
//                                }else{
                                    teamDTO.setRole(firstRole);
                                    firstRole = 0;
//                                }
                            }else if(secondRole != 0){
//                                if(treatRole == 1){
//                                    teamDTO.setRole(thirdRole);
//                                    thirdRole = 0;
//                                }else{
//                                    teamDTO.setRole(firstRole);
//                                    firstRole = 0;
//                                }
                                    teamDTO.setRole(secondRole);
                                secondRole = 0;

                            }else if(thirdRole != 0){
                                teamDTO.setRole(thirdRole);
                                thirdRole = 0;
                            }
                        }

                    }



                }
                
            });
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

                        if(x == 1){
                            x = 0;
                            y++;
                        }
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

                        if(x == 1){
                            x = 0;
                            y++;
                        }
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
//        tmpPos = 2;
    }

    @FXML
    private void startTeamCourseAction(MouseEvent event) throws IOException {
        System.out.println("startTeamCourseAction");

        courseSelect.setVisible(true);
        teamSelect.setVisible(true);
        missionSelect.setVisible(true);

        classGridPane.getChildren().clear();
        courseGridPane.getChildren().clear();

        tips.setVisible(true);

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

        tabBack.setVisible(true);

        backImg.setImage(imageCourse1);

        teamCourseSettingsSelect.setOpacity(1.0);

        tmpPos = 1;


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
                    imageView.setImage(image1);
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
                    imageView.setImage(image1);
                }

                if(!currentId.equals(selectCourseIndex)){
                    selectCourseIndex = currentId;
                    sourceView.setImage(image2);
                    if(lastCourseView != null){
                        lastCourseView.setImage(image0);
                    }
                    lastCourseView = sourceView;

                    System.out.println("showTeamPane");
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
        System.out.println("studentGradeAction");

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
