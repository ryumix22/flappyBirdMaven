package com.devcolibri.mavenjavafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.setTitle("flappyBird");
        primaryStage.setX(650);
        primaryStage.setY(170);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
