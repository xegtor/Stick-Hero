package com.approject.stickhero;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PlayerNameController {
    @FXML
    private TextField playerNameField;

    public String getPlayerName() {
        return playerNameField.getText();
    }

    @FXML
    private void submitName() {
        
    }
}
