package com.approject.stickhero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to StickHero!");
    }
}