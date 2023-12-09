package com.approject.stickhero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import static javafx.application.Platform.exit;

public class Game extends Application implements Serializable {
    private Stage mainWindow = new Stage();
    private MainGame mainGame = null;
    private Player player = null;
    private static boolean passed = true;
    private static boolean showOnce = false;
    @Override
    public void start(Stage stage) throws IOException {
        try{
            Player.loadScores();
        }
        catch (IOException e){
            System.out.println("Can't load game");
        }
        catch (ClassNotFoundException e){
            System.out.println("Class not found");
        }

        if (!showOnce) {
            showOnce = true;
            if (passed) {
                showSuccessAlert();
            } else {
                showFailureAlert("Some tests have failed!");
                exit();
            }
        }
        this.player = Player.getPlayerScore("Default");
        this.mainGame = new MainGame();

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

    public void runMainGame() {
        mainWindow.close();
        mainGame.setMyGame(this);
        mainGame.setPlayer(this.player);
        mainGame.start(this.mainWindow);
    }
    public void continueGame() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        mainGame.revived();
    }
    public int getCherry() {
        return mainGame.getCherry();
    }
    public void setCherry(int cherry) {
        mainGame.setCherry(cherry);
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
    public static void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tests Passed");
        alert.setHeaderText(null);
        alert.setContentText("All tests have passed successfully!");

        alert.showAndWait();
    }

    public static void showFailureAlert(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Test Failed");
        alert.setHeaderText(null);
        alert.setContentText("Error: " + errorMessage);

        alert.showAndWait();
    }

    public String getPlayerName() {
        return this.player.getName();
    }
    public void reset() {
        mainGame.resetScore();
    }
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StickHeroTester.class);
        for (Failure failure : result.getFailures()) {
            passed = false;
        }

        launch(args);
    }
}