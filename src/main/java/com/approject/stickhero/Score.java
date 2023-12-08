package com.approject.stickhero;

public class Score {
    private int score;
    private final String name;

    public Score(int score, String name) {
        this.score = score;
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
}
