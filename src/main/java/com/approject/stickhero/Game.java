package com.approject.stickhero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Game extends Application {
    private Player player = null;
    private Stage mainWindow = new Stage();
    private MainGame mainGame = new MainGame();

    public int getCherry() {
        return mainGame.getCherry();
    }
    public void setCherry(int cherry) {
        mainGame.setCherry(cherry);
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("splashTest.fxml"));
        Parent root = loader.load();

        SplashController splashController = loader.getController();
        splashController.setGame(this);

        Scene scene = new Scene(root);
        mainWindow.setTitle("Stick Hero");
        mainWindow.setScene(scene);
        mainWindow.show();
    }

    public void launch() {
        mainWindow.close();
        mainGame.setMyGame(this);
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
        this.player = new Player(name);
    }

    public String getPlayerName() {
        return this.player.getName();
    }

    public static void main(String[] args) {
        launch(args);
    }
}