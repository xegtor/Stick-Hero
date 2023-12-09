package com.approject.stickhero;

import java.io.*;
import java.util.Vector;

public class Player implements Serializable{
    private int score;
    private String name;
    private MainGame game;
    private static final long serialVersionUID = 1L;
    private static final Vector<Player> players;
    private static final Vector<MainGame> savedGames;

    static {
        try {
            players = loadScores();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    static {
        try {
            savedGames = loadGame();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Player getPlayerScore(String name) {
        for (Player s : players) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        Player newPlayer = new Player(0, name, new MainGame());
        players.add(newPlayer);
        return newPlayer;
    }
    public static MainGame getSavedGame(String name) {
        for (MainGame s : savedGames) {
            if (s.getPlayer().getName().equals(name)) {
                return s;
            }
        }
        MainGame newGame = new MainGame();
        newGame.setPlayer(new Player(0, name, newGame));
        savedGames.add(newGame);
        return newGame;
    }
    public void setGameState(MainGame game){
        this.game = game;
    }
    private Player(int score, String name, MainGame game) {
        this.score = score;
        this.name = name;
        this.game = game;
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
    public static void saveGame(MainGame game) throws IOException {
        try {
            FileOutputStream fileOut = new FileOutputStream("savedGame.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(savedGames);
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
    public static Vector<MainGame> loadGame() throws IOException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream("savedGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Vector<MainGame> saves = (Vector<MainGame>) in.readObject();
            in.close();
            fileIn.close();

            return saves;
        } catch (IOException i) {
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("ScoreBoard class not found");
            c.printStackTrace();
            return null;
        }
    }
}