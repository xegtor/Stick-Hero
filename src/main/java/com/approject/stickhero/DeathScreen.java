package com.approject.stickhero;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class DeathScreen {
    private Game myGame;
    private boolean isReviving = false;
    @FXML
    private void revive(MouseEvent event) throws IOException{
        MediaPlayer mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(getClass().getResource("revive2.mp3").toString()));
        mediaPlayer.play();
        if (isReviving) {
            return;
        }
        if(myGame.getCherry() < 2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("You don't have enough cherry to revive");
            alert.showAndWait();
            return;
        }
        else{
            myGame.setCherry(myGame.getCherry() - 2);
        }
        System.out.println("Reviving");
        try{
            myGame.continueGame();
        }
        catch (InterruptedException e){
            System.out.println("Can't sleep");
        }
        isReviving = true;
    }
    @FXML
    private void home(MouseEvent event) throws IOException{}

    public void setGame(Game game){
        this.myGame = game;
    }
    public Game getGame(){
        return this.myGame;
    }
}