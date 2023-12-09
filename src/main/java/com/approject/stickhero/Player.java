package com.approject.stickhero;

public class Player implements java.io.Serializable{
    private final String name;

    public Player(String name) {
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
