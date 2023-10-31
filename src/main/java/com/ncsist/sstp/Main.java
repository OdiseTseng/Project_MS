package com.ncsist.sstp;

import com.ncsist.sstp.controller.LoginController;
import com.ncsist.sstp.server.NettyClientSocket;
import com.ncsist.sstp.utils.text.CommonString;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

//@SpringBootApplication
public class Main extends Application {

    public static Font customFont = null;

    public static void main(String[] args) {

        new Thread(NettyClientSocket::startNettyClient).start();
//        SpringApplication.run(Main.class, args);
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(" start ");
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);

        if(customFont == null){
            InputStream ttfPath = getClass()
                    .getResourceAsStream( "/ttf/TaipeiSansTCBeta-Bold.ttf" );
            customFont = Font.loadFont(ttfPath, 14);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(CommonString.PATH_XML + CommonString.XML_LOGIN));
        Parent root = fxmlLoader.load();

        LoginController loginController = fxmlLoader.getController();
        loginController.setPrimaryStage(primaryStage);


        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(CommonString.TITLE_NAME);
        primaryStage.show();

    }
}