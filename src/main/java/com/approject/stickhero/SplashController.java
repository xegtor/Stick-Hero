// SplashController.java
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

        String playerName = playerNameInputController.getPlayerName();

        FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent helloViewRoot = gameLoader.load();
        Game gameController = gameLoader.getController();
        gameController.setPlayerName(playerName);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(helloViewRoot));
        stage.setTitle("Hello View");
        stage.show();
    }

    public void setGame(Game game){
        this.myGame = game;
    }

    public Game getGame(){
        return this.myGame;
    }
}
