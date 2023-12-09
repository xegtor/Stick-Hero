package com.approject.stickhero;

public class Score implements java.io.Serializable{
    private int score;
    MainGame mainGame;
    private final String name;

    public Score(int score, String name, MainGame main) {
        this.score = score;
        this.mainGame = main;
        this.name = name;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }

    public Score clone() {
        return new Score(this.getScore(), this.getName(), this.mainGame);
    }
}
