package com.approject.stickhero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class Game extends Application implements Serializable {
    private Stage mainWindow = new Stage();
    private MainGame mainGame = new MainGame();
    private Player player = Player.getPlayerScore("Default");
    public int getCherry() {
        return mainGame.getCherry();
    }
    public void setCherry(int cherry) {
        mainGame.setCherry(cherry);
    }

    @Override
    public void start(Stage stage) throws IOException {
        try{
            Player.loadGame();
        }
        catch (IOException e){
            System.out.println("Can't load game");
        }
        catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("splashTest.fxml"));
        Parent root = loader.load();

        SplashController splashController = loader.getController();
        splashController.setGame(this);

        Scene scene = new Scene(root);
        mainWindow.setTitle("Stick Hero");
        mainWindow.setResizable(false);
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public void launch() {
        mainWindow.close();
        mainGame.setMyGame(this);
        mainGame.setScore(this.player);
        mainGame.start(this.mainWindow);
    }
    public void continueGame() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        mainGame.revived();
    }

    public void setMap(String map){
        mainGame.setMap(map);
    }
    public void setPlayerName(String name) {
        this.player = Player.getPlayerScore(name);
    }
    public void restart() throws IOException {
        start(mainWindow);
    }
    public String getPlayerName() {
        return this.player.getName();
    }
    public void reset() {
        mainGame.resetScore();
    }
    public static void main(String[] args) {
        launch(args);
    }
}