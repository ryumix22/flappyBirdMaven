package com.devcolibri.mavenjavafxapp.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SecondController {


    private static Pane appRoot = new Pane();
    private static Pane gameRoot = new Pane();


    static ArrayList<Wall> walls = new ArrayList<>();
    private Bird bird = new Bird();


    private Parent createContent() {

        gameRoot.setPrefSize(590, 590);

        for (int i = 0; i < 100; i++) {
            int enter = (int) (Math.random() * 100 + 50);
            int height = new Random().nextInt(600 - enter);
            Wall wall = new Wall(height);
            wall.setTranslateX(i * 350 + 600);
            wall.setTranslateY(0);
            walls.add(wall);

            Wall wall2 = new Wall(600 - enter - height);
            wall2.setTranslateX(i * 350 + 600);
            wall2.setTranslateY(height + enter);
            walls.add(wall2);

            gameRoot.getChildren().addAll(wall, wall2);

        }
        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot);

        return appRoot;

    }

    private void update() {
        if (bird.velocity.getY() < 4.5) {
            bird.velocity = bird.velocity.add(0, 2);
        }

        bird.moveX((int) bird.velocity.getX());
        bird.moveY((int) bird.velocity.getY());

        bird.translateXProperty().addListener((observable, oldValue, newValue) -> {
            int offset = newValue.intValue();
            if (offset > 200) gameRoot.setLayoutX(-(offset - 200));
        });
    }


    @FXML
    void initialize(Pane root) {
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(root, createContent());
        Stage stage = new Stage();
        stage.setX(650);
        stage.setY(170);
        Scene scene = new Scene(stackPane);
        stage.setTitle("flappyBird");
        scene.setOnMouseClicked(event1 -> {
            bird.jump();
        });
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode.equals(KeyCode.R) &&
                    bird.lose) {
                stage.close();
                appRoot = new Pane();
                gameRoot = new Pane();
                bird = new Bird();
                walls = new ArrayList<>();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/sample.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root1 = loader.getRoot();
                Stage stage1 = new Stage();
                stage1.setTitle("flappyBird");
                stage1.setScene(new Scene(root1));
                stage1.setResizable(false);
                stage1.show();
            }
        });
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
    }
}
