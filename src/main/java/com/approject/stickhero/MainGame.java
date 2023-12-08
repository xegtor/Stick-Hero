package com.approject.stickhero;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class MainGame extends Application {
    private Score highScore = new Score(0, "Default");
    private Score currentScore = new Score(0, "Default");
    Stage stage = new Stage();

    private final Random random = new Random();
    private Boolean isFliped = Boolean.FALSE;
    private Rectangle stick;
    private Rectangle rectangle1 = null;
    private Rectangle rectangle2 = null;
    private double distance = 0;
    private Timeline extendTimelineHeight;
    private Timeline extendTimelineY;
    private boolean rotated = false;
    private ImageView player;
    private ImageView cherry;
    private Boolean cherrySpawn = Boolean.FALSE;
    Pane anchorPane;

    @Override
    public void start(Stage primaryStage) {
        Pane root = createContent();
        Scene scene = new Scene(root, 392, 650);
        stage.setTitle("Stick Hero Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Pane createContent() {
        anchorPane = new AnchorPane();

        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("mountain.jpg")));
        background.setX((double) - 392/2);
        background.setY((double) - 650/2);
        anchorPane.getChildren().add(background);

        if (rectangle2==null){
            rectangle1 = createRandomRectangle();
        }
        else{
            rectangle1 = new Rectangle(rectangle1.getX(), 510, rectangle2.getWidth(), 150);
        }
        rectangle2 = createRandomRectangle();

        player = new ImageView(new Image(getClass().getResourceAsStream("sprite.png")));
        player.setFitHeight(30);
        player.setFitWidth(30);
        player.setX(rectangle1.getX() + rectangle1.getWidth() - 30);
        player.setY(rectangle1.getY()-player.getFitHeight());
        anchorPane.getChildren().add(player);

        distance = random.nextInt(150) + 50;
        rectangle2.setX(rectangle1.getX() + rectangle1.getWidth() + distance);

        anchorPane.getChildren().addAll(rectangle1, rectangle2);

        rotated = false;

        stick = new Rectangle(4, 0, Color.RED);
        stick.setTranslateX(rectangle1.getX() + rectangle1.getWidth() - 4);
        stick.setTranslateY(rectangle1.getY());

        anchorPane.getChildren().add(stick);

        Label score = new Label("Score: " + currentScore.getScore());
        score.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        score.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");
        AnchorPane.setTopAnchor(score, 20.0);
        AnchorPane.setRightAnchor(score, 20.0);
        anchorPane.getChildren().add(score);

        Label highScoreLabel = new Label("High Score: " + highScore.getScore());
        highScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        highScoreLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");
        AnchorPane.setTopAnchor(highScoreLabel, 20.0);
        AnchorPane.setLeftAnchor(highScoreLabel, 20.0);
        anchorPane.getChildren().add(highScoreLabel);

        cherrySpawn = random.nextBoolean();
        if (cherrySpawn){
            cherry = new ImageView(new Image(getClass().getResourceAsStream("cherry.png")));
            cherry.setFitHeight(30);
            cherry.setFitWidth(30);
            cherry.setX(rectangle1.getX() + rectangle1.getWidth()+random.nextInt((int) distance - 30));
            if (random.nextBoolean()) cherry.setY(510);
            else cherry.setY(510 - 30);
            anchorPane.getChildren().add(cherry);
        }

        anchorPane.setOnMousePressed(this::handleMousePressed);
        anchorPane.setOnMouseReleased(this::handleMouseReleased);
        anchorPane.setOnMouseClicked(this::handleMouseClick);

        return anchorPane;
    }

    private void handleMouseClick(MouseEvent mouseEvent) {
        if(rotated){
            if (!isFliped) {
                player.setScaleY(-1);
                player.setY(player.getY() + 30);
                isFliped = Boolean.TRUE;
            }else{
                player.setScaleY(1);
                player.setY(player.getY() - 30);
                isFliped = Boolean.FALSE;
            }
        }
    }

    private Rectangle createRandomRectangle() {
        double width = random.nextInt(50) + 25;
        double height = 150.0;
        double x = random.nextInt(50);

        Rectangle rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(510);
        rectangle.setWidth(width);
        rectangle.setHeight(height);

        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeType(StrokeType.INSIDE);

        return rectangle;
    }

    private void handleMousePressed(MouseEvent event) {
        if (rotated){
            return;
        }
        extendTimelineHeight = new Timeline(
                new KeyFrame(Duration.seconds(2), new KeyValue(stick.heightProperty(), 510))
        );
        extendTimelineHeight.play();
        extendTimelineY = new Timeline(
                new KeyFrame(Duration.seconds(2), new KeyValue(stick.yProperty(), -510))
        );
        extendTimelineY.play();
        rotated = false;
    }

    private void handleMouseReleased(MouseEvent event) {
        if (extendTimelineHeight != null && extendTimelineY != null) {
            extendTimelineHeight.stop();
            extendTimelineY.stop();
        }

        if (!rotated) {
            Rotate rotateTransition = new Rotate();
            rotateTransition.setAngle(90);
            rotateTransition.setPivotX(stick.getX());
            rotateTransition.setPivotY(0);
            rotateTransition.setAxis(Rotate.Z_AXIS);
            stick.getTransforms().add(rotateTransition);

            rotated = true;
        }

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(2), player);
        translateTransition.setByX(stick.getHeight() + 15);
        translateTransition.play();

        translateTransition.setOnFinished(gameState -> {
            if (stick.getHeight() < rectangle2.getX() - rectangle1.getX() - rectangle1.getWidth() || stick.getHeight() > rectangle2.getX() + rectangle2.getWidth() - rectangle1.getX() - rectangle1.getWidth()){
                try {
                    gameOver();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                if (cherrySpawn){
                    anchorPane.getChildren().remove(cherry);
                    if (isFliped){
                        try {
                            gameOver();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                currentScore.setScore(currentScore.getScore() + 1);
                if (currentScore.getScore() > highScore.getScore()){
                    highScore = currentScore.clone();
                }
                gameContinue();
            }
        });
    }

    public void gameOver() throws IOException {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("deathScreen.fxml")), 392, 650);
        stage.setScene(scene);
        stage.show();
    }
    public void gameContinue(){
        TranslateTransition deleteTransition = new TranslateTransition(Duration.seconds(3), rectangle1);
        TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(3), rectangle2);
        TranslateTransition stickTransition = new TranslateTransition(Duration.seconds(3), stick);
        TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(3), player);

        deleteTransition.setByX(-distance-rectangle1.getWidth());
        moveTransition.setByX(-distance-rectangle1.getWidth());
        stickTransition.setByX(-distance-rectangle1.getWidth());
        playerTransition.setByX(-distance-rectangle1.getWidth());

        deleteTransition.play();
        moveTransition.play();
        stickTransition.play();
        playerTransition.play();

        moveTransition.setOnFinished(event -> {
            Scene scene = new Scene(createContent(), 392, 650);
            stage.setScene(scene);
            stage.show();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
