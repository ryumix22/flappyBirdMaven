package com.devcolibri.mavenjavafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String fxmlFile = "/fxml/sample.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        primaryStage.setScene(new Scene(root, 590, 590));
        primaryStage.setTitle("flappyBird");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
