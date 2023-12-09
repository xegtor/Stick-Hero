package com.approject.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class SplashController {
    private Game myGame;
    @FXML
    private void playButtonHandler(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("playerName.fxml"));
        Parent playerNameInputRoot = loader.load();

        PlayerNameController playerNameInputController = loader.getController();

        Stage playerNameInputDialog = new Stage();
        playerNameInputController.setMyGame(myGame);
        playerNameInputController.setStage(playerNameInputDialog);

        playerNameInputDialog.initModality(Modality.APPLICATION_MODAL);
        playerNameInputDialog.setTitle("Enter Your Name");
        playerNameInputDialog.setScene(new Scene(playerNameInputRoot));
        playerNameInputDialog.showAndWait();

        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("switchMaps.fxml"));
        Parent switchMap = loader2.load();

        SwitchMaps switchMapController = loader2.getController();
        switchMapController.setGame(myGame);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(new Scene(switchMap));
        currentStage.show();
    }

    public void setGame(Game game){
        this.myGame = game;
    }

    public Game getGame(){
        return this.myGame;
    }
}
