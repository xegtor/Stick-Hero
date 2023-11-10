package com.approject.stickhero;

public class Platform {
    private int startPosition;
    private final int width;
    Platform(int startPosition, int width){
        this.startPosition = startPosition;
        this.width = width;
    }
    public int getStartPosition(){return this.startPosition;}
    public int getWidth(){return this.width;}
    public void setStartPosition(int startPosition){this.startPosition = startPosition;}
}
