package com.devcolibri.mavenjavafxapp.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class Controller {

    @FXML
    private Button startPlay;


    public Controller() throws IOException {
    }

    @FXML
    void initialize() {
        startPlay.setOnAction(event -> {
            startPlay.setVisible(false);
            startPlay.getScene().getWindow().hide();
            SecondController controller = new SecondController();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/sample2.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Pane root = loader.getRoot();
            controller.initialize(root);
        });
    }


}
