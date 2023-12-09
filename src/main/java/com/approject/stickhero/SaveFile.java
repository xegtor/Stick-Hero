package com.approject.stickhero;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.io.*;
public class SaveFile implements Serializable{
    private String musicName;
    private String map;
    private String playerName;
    private Integer currentScore;
    private Rectangle rectangleFirst;
    private Rectangle rectangleSecond;
    private ImageView cherry;
    private ImageView player;
    private int cherryScore;
    public SaveFile(String musicName, String map, String playerName, int currentScore, Rectangle rectangleFirst, Rectangle rectangleSecond, ImageView cherry, ImageView player, int cherryScore){
        this.musicName = musicName;
        this.map = map;
        this.playerName = playerName;
        this.currentScore = currentScore;
        this.rectangleFirst = rectangleFirst;
        this.rectangleSecond = rectangleSecond;
        this.cherry = cherry;
        this.player = player;
        this.cherryScore = cherryScore;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Rectangle getRectangleFirst() {
        return rectangleFirst;
    }

    public void setRectangleFirst(Rectangle rectangleFirst) {
        this.rectangleFirst = rectangleFirst;
    }

    public Rectangle getRectangleSecond() {
        return rectangleSecond;
    }

    public void setRectangleSecond(Rectangle rectangleSecond) {
        this.rectangleSecond = rectangleSecond;
    }

    public ImageView getCherry() {
        return cherry;
    }

    public void setCherry(ImageView cherry) {
        this.cherry = cherry;
    }

    public ImageView getPlayer() {
        return player;
    }

    public void setPlayer(ImageView player) {
        this.player = player;
    }

    public int getCherryScore() {
        return cherryScore;
    }

    public void setCherryScore(int cherryScore) {
        this.cherryScore = cherryScore;
    }
}
