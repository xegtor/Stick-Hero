package com.approject.stickhero;

public class Player {
    private String name;
    private int position;
    private Boolean isAboveStick;
    private Boolean isJumping;
    private boolean isOnStick;
    private int score;
    private int cherryCount;

    public Player(String name) {
        this.name = name;
        this.position = 0;
        this.isAboveStick = true;
        this.isJumping = false;
        this.isOnStick = false;
    }
    public void move(){};
    public void jump(){};
    public void switchPosition(){};
    public void collectCherry(){};
    public void reviveWithCherries(){};
    public String getName(){
        return this.name;
    }
}
