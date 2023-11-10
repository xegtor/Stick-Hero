package com.approject.stickhero;

public class MyPlatform{
    private int startPosition;
    private int width;
    MyPlatform(int startPosition, int width){
        this.startPosition = startPosition;
        this.width = width;
    }
    public int getStartPosition(){return this.startPosition;}
    public int getWidth(){return this.width;}
    public void setStartPosition(int startPosition){this.startPosition = startPosition;}
}
