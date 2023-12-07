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
    private final Vector <Cherry> cherries = new Vector<>();

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("splashTest.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Hello!");
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