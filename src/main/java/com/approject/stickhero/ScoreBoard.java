package com.approject.stickhero;
import java.io.*;
import java.util.Vector;

public class ScoreBoard implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private final Vector<Score> scores = new Vector<>();
    public Score getPlayerScore(String name) {
        for (Score s : scores) {
            if (s.getName().equals(name)) {
                return s;
            }
        }
        Score newScore = new Score(0, name);
        scores.add(newScore);  // add a new score if the score is not found
        return newScore;
    }
    public void serialize() throws IOException{
        try {
            FileOutputStream fileOut = new FileOutputStream("scoreboard.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public ScoreBoard deSerialize() throws IOException, ClassNotFoundException{
        try{
            FileInputStream fileIn = new FileInputStream("scoreboard.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ScoreBoard scoreboard = (ScoreBoard) in.readObject();
            in.close();
            fileIn.close();
            return scoreboard;
        } catch (IOException i) {
            return new ScoreBoard();
        } catch (ClassNotFoundException c) {
            System.out.println("ScoreBoard class not found");
            c.printStackTrace();
            return null;
        }
    }
}
