package com.approject.stickhero;

import java.io.*;
import java.util.Vector;

public class Player implements Serializable{
    private int score;
    private String name;
    private static final long serialVersionUID = 1L;
    private static final Vector<Player> players;
    private static final Vector<MainGame> savedGames = new Vector<MainGame>();

    static {
        try {
            players = loadScores();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Player getPlayerScore(String name) {
        for (Player s : players) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        Player newPlayer = new Player(0, name);
        players.add(newPlayer);
        return newPlayer;
    }
    private Player(int score, String name) {
        this.score = score;
        this.name = name;
    }
    public void setScore(int score) {
        System.out.println("Setting score: " + score);
        this.score = score;
    }
    public int getScore() {
        return score;
    }
    public String getName() {
        return name;
    }
    public static void saveScores() throws IOException {
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

    public static Vector<Player> loadScores() throws IOException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream("scoreboard.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Vector<Player> players = (Vector<Player>) in.readObject();
            in.close();
            fileIn.close();

            return players;
        } catch (IOException i) {
            return new Vector<Player>();
        } catch (ClassNotFoundException c) {
            System.out.println("ScoreBoard class not found");
            c.printStackTrace();
            return null;
        }
    }
}