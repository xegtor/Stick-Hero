package com.approject.stickhero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Vector;

public class Game extends Application {
    private Player player = null;
    private final Vector <MyPlatform> platforms = new Vector<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("splashTest.fxml"));
        Parent root = loader.load();

        SplashController splashController = loader.getController();
        splashController.setGame(this);

        Scene scene = new Scene(root);
        stage.setTitle("Stick Hero");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void setPlayerName(String name){
        this.player = new Player(name);
    }
}