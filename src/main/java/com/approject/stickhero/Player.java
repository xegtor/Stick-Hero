package com.approject.stickhero;

public class Player {
    private int position;
    private Boolean isAboveStick;
    private Boolean isJumping;
    private boolean isOnStick;

    public Player() {
        this.position = 0;
        this.isAboveStick = true;
        this.isJumping = false;
        this.isOnStick = false;
    }
    public void move(){};
    public void jump(){};
    public void switchPosition(){};
    public void collectCherry(){};
}
