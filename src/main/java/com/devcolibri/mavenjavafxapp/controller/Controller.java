package com.devcolibri.mavenjavafxapp.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Controller {

    @FXML
    private Button startPlay;

    private static Pane appRoot = new Pane();
    private static Pane gameRoot = new Pane();


    static ArrayList<Wall> walls = new ArrayList<>();
    private Bird bird = new Bird();

    public Controller() throws Exception{
    }

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
        if (bird.velocity.getY() < 5) {
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
    void initialize() {
        startPlay.setOnAction(event -> {
            startPlay.setVisible(false);
            startPlay.getScene().getWindow().hide();
            Stage stage = new Stage();
            Scene scene = new Scene(createContent());
            stage.setTitle("");
            scene.setOnMouseClicked(event1 -> bird.jump());
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
        });
    }


}
