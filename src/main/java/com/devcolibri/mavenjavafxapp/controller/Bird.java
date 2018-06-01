package com.devcolibri.mavenjavafxapp.controller;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.io.File;
import java.net.MalformedURLException;

class Bird extends Pane {
    Point2D velocity = new Point2D(0,0);
    private Rectangle rectangle;

    Bird() {
        rectangle = new Rectangle(20, 20, Color.RED);
        velocity = new Point2D(0,0);
        setTranslateY(300);
        setTranslateX(100);
        getChildren().addAll(rectangle);

    }

    void moveY(int value) {
        boolean moveDown;
        moveDown = value > 0;
        double moving;
        if (moveDown) {
            moving = 1.0;
        } else {
            moving = -1.0;
        }
        for (int i = 0; i < Math.abs(value); i++) {
            for (Wall w : Controller.walls) {
                if (this.getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (moveDown) {
                        setTranslateY(getTranslateY() - 1);
                    } else {
                        setTranslateY(getTranslateY() + 1);
                    }
                }
            }

            if (getTranslateY() < 0) {
                setTranslateY(0);
            }
            if(getTranslateY() > 580) {
                setTranslateY(580);
            }

            setTranslateY(getTranslateY() + moving);
        }
    }

    void moveX(int value){
        for (int i = 0; i < value; i++) {
            for(Wall w : Controller.walls) {
                if (getBoundsInParent().intersects(w.getBoundsInParent())) {
                    if (getTranslateX() + 20 == w.getTranslateX()) {
                        setTranslateX(getTranslateX() - 1);
                        rectangle.setFill(Color.BLACK);
                    }
                }
            }
            setTranslateX(getTranslateX() + 1);
        }
    }


    void jump() {
        velocity = new Point2D(3, -15);
    }
}
