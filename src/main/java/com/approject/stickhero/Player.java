package com.approject.stickhero;

import java.io.*;
import java.util.Vector;

public class Player implements java.io.Serializable{
    private int score;
    static MainGame playerSave;
    private final String name;
    private static final long serialVersionUID = 1L;
    private static final Vector<Player> players = new Vector<>();
    public static Player getPlayerScore(String name) {
        for (Player s : players) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        Player newPlayer = new Player(0, name, playerSave);
        players.add(newPlayer);
        return newPlayer;
    }
    private Player(int score, String name, MainGame main) {
        this.score = score;
        this.playerSave = main;
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
    public static void saveGame() throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("scoreboard.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(players);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Vector<Player> loadGame() throws IOException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream("scoreboard.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Vector<Player> scoreboard = (Vector<Player>) in.readObject();
            in.close();
            fileIn.close();
            return scoreboard;
        } catch (IOException i) {
            return new Vector<Player>();
        } catch (ClassNotFoundException c) {
            System.out.println("ScoreBoard class not found");
            c.printStackTrace();
            return null;
        }
    }
}