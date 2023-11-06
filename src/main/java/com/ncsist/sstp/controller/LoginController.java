package com.ncsist.sstp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncsist.sstp.model.NettyDTO;
import com.ncsist.sstp.model.UserDTO;
import com.ncsist.sstp.server.controller.NettyClientMsgController;
import com.ncsist.sstp.utils.func.SHAEncoder;
import com.ncsist.sstp.utils.text.CommonString;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.ncsist.sstp.http.HttpClientPost;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;
    private Stage primaryStage; // 添加 Stage 成員變量

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage; //
    }

    @FXML
    private void initialize() {
        // 初始化程式碼，如果需要的話
    }

    @FXML
    private void handleLoginButtonAction() throws IOException {
        // 當登入按鈕被點擊時執行的程式碼
        String username = usernameField.getText();
        String password = passwordField.getText();
        password = SHAEncoder.getSHA256(password);
        System.out.println("password : " + password);

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setPassword(password);
        userDTO.setCtxId(NettyClientMsgController.getClientCtxId());
        // 創建JSON數據，這只是一個示例，實際上需要根據你的需求創建正確的JSON數據
//        String jsonResponse = HttpClientPost.sendLoginRequest(username, password);
        String jsonResponse = HttpClientPost.sendLoginRequest(userDTO);
        System.out.println("jsonResponse : " + jsonResponse);
        if (jsonResponse != null && !jsonResponse.isEmpty() && jsonResponse.startsWith("{")) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonResponse);
                NettyDTO nettyDTO = objectMapper.readValue(jsonResponse, NettyDTO.class);
                System.out.println("nettyDTO : " + nettyDTO);
                int level = jsonNode.get("level").asInt();

                if(level > 0){
//                    NettyClientHandler.sendMsg("chk");
                }

                //for demo
//                level+=1000;

                if (level == 1003 || level == 1004) {
                    System.out.println("1你是學生!");
                    FXMLLoader esLoader = new FXMLLoader(getClass().getResource(CommonString.PATH_XML + CommonString.XML_ES));
                    Parent esRoot = esLoader.load();
                    Scene esScene = new Scene(esRoot);
                    EsController esController = esLoader.getController();
//                    esController.initializeUserData(jsonNode);

                    primaryStage.setScene(esScene);
                    primaryStage.setTitle("ES");
                } else if (level == 1002) {
                    System.out.println("2你是教官");
                    FXMLLoader msLoader = new FXMLLoader(getClass().getResource(CommonString.PATH_XML + CommonString.XML_MS));
//                    System.out.println("msLoader : " + msLoader);
                    Parent msRoot = msLoader.load();
                    Scene msScene = new Scene(msRoot);

                    MsController msController = msLoader.getController();
//                    msController.setPrimaryStage(primaryStage);
                    primaryStage.setScene(msScene);
                    primaryStage.setTitle("MS");
//                    msController.initializeUserData(jsonNode);
                } else {
                    System.out.println("都不是1跟2");
                }


            } catch (JsonProcessingException e) {
                System.out.println("JsonProcessingException : " + e.getMessage());
//                e.printStackTrace();
            }
        } else {

            System.out.println("Invalid JSON response: " + jsonResponse);
        }

    }
}