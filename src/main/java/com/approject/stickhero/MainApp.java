package com.approject.stickhero;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader splashLoader = new FXMLLoader(getClass().getResource("main/resources/com/approject/stickhero/splash.fxml"));
            Parent splashRoot = splashLoader.load();

            Scene splashScene = new Scene(splashRoot);
            primaryStage.setScene(splashScene);
            primaryStage.setTitle("Splash Screen");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}