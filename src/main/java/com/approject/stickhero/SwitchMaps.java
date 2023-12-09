package com.approject.stickhero;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Vector;

public class SwitchMaps {
    private Game myGame;
    private Vector<String> maps = new Vector<>();
    private int currentMap = 0;

    @FXML
    private ImageView mapSet;
    @FXML
    public void switchRight() throws IOException {
        if (maps.isEmpty()) {
            maps.add("bliss.jpg");
            maps.add("mountain.jpg");
            maps.add("serenity.jpg");
        }
        if (currentMap == 0) {
            currentMap = 2;
        } else {
            currentMap--;
        }

        String imagePath = "/com/approject/stickhero/" + maps.get(currentMap);
        Image newImage = new Image(getClass().getResourceAsStream(imagePath));

        mapSet.setImage(newImage);
    }

    @FXML
    public void switchLeft() throws IOException {
        if (maps.isEmpty()) {
            maps.add("bliss.jpg");
            maps.add("mountain.jpg");
            maps.add("serenity.jpg");
        }
        if (currentMap == 2) {
            currentMap = 0;
        } else {
            currentMap++;
        }

        String imagePath = "/com/approject/stickhero/" + maps.get(currentMap);
        Image newImage = new Image(getClass().getResourceAsStream(imagePath));

        mapSet.setImage(newImage);
    }

    @FXML
    public void start() throws IOException {
        if (maps.isEmpty()) {
            maps.add("bliss.jpg");
        }
        myGame.setMap(maps.get(currentMap));
        myGame.launch();
    }

    public void setGame(Game game) {
        this.myGame = game;
    }
    public Game getGame() {
        return this.myGame;
    }
    public void setMap(){
        myGame.setMap(maps.get(currentMap));
    }
}
