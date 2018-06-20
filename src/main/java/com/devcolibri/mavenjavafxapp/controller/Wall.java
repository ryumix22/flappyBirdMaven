package com.devcolibri.mavenjavafxapp.controller;


import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


class Wall extends Pane {
    Wall(int height) {
        Rectangle rectangle = new Rectangle(20, height, Color.BLUE);

        getChildren().add(rectangle);
    }
}
