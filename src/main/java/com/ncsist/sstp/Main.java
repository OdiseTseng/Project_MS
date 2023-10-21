package com.ncsist.sstp;

import com.ncsist.sstp.controller.LoginController;
import com.ncsist.sstp.utils.text.CommonString;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(" start ");
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);

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