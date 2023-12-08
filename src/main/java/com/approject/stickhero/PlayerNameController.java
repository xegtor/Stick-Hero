// PlayerNameInputController.java
package com.approject.stickhero;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PlayerNameController {

    @FXML
    private TextField playerNameField;
    private String playerName = "NPC";
    private Game myGame;
    private Stage stage;
    public void setStage(Stage stage){
        this.stage = stage;
    }

    @FXML
    private void submitName() {
        playerName = playerNameField.getText().trim();
        boolean hasPlayedBefore = checkIfPlayerExists(playerName);

        if (hasPlayedBefore) {
            displayPlayerStatistics(playerName);
        } else {
            if (!playerName.isEmpty()) {
                myGame.setPlayerName(playerName);
                stage.close();
            } else {
                showAlert("Error", "Please enter at least 1 character.");
            }
        }
    }

    private boolean checkIfPlayerExists(String playerName) {
        return false;
    }

    private void displayPlayerStatistics(String playerName) {
        int highScore = 0;
        String username = "Unknown";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Player Statistics");
        alert.setHeaderText("Welcome back, " + playerName + "!");
        alert.setContentText("Username: " + username + "\nHigh Score: " + highScore);

        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            stage.close();
        });

        alert.getButtonTypes().setAll(ButtonType.CANCEL, ButtonType.OK);
        alert.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        alert.getDialogPane().setContent(playButton);
        alert.showAndWait();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public String getPlayerName() {
        return playerNameField.getText();
    }
    public void setMyGame(Game game){
        this.myGame = game;
    }
}
