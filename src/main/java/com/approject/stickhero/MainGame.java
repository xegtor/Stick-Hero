package com.approject.stickhero;

import javafx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
import java.util.Vector;

public class MainGame extends Application {
    private Game myGame;
    private Score highScore = new Score(0, "Default");
    private int cherryScore = 0;
    private Score currentScore = new Score(0, "Default");
    Stage stage = new Stage();
    Pane root;
    private final Random random = new Random();
    private Rectangle stick;
    private Rectangle rectangle1 = null;
    private Rectangle rectangle2 = null;
    private double distance = 0;
    private Timeline extendTimelineHeight;
    private Timeline extendTimelineY;
    private TranslateTransition translateTransition;
    private int isFlipped = 0;
    private boolean rotated = false;
    private boolean isMoving = false;
    private Boolean cherrySpawn = false;
    private Boolean pause = false;
    private Boolean sceneTransition = false;
    private String map = "mountain.jpg";
    private ImageView player;
    private ImageView cherry;
    private Boolean cherryCollected = false;
    private Boolean isAlive = true;
    private Vector<String> music = new Vector<>();
    private MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) {
        music.add("BackOnTrack.mp3");
        music.add("Electroman.mp3");
        music.add("StereoMadness.mp3");
        music.add("Jumper.mp3");
        music.add("Electrodynamix.mp3");

        int randomMusic = random.nextInt(5);
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(music.get(randomMusic)).toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();

        root = createContent();
        Scene scene = new Scene(root, 392, 650);
        stage.setTitle("Stick Hero Game");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        root.setFocusTraversable(true);
        root.requestFocus();
    }

    private Pane createContent() {
        Pane anchorPane = new AnchorPane();

        ImageView background = new ImageView(new Image(getClass().getResourceAsStream(map)));
        background.setFitHeight(650);
        background.setFitWidth(392);
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

        Label cherryScoreLabel = new Label("Cherries Collected: " + cherryScore);
        cherryScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        cherryScoreLabel.setStyle("-fx-background-color: rgba(255, 255, 255, 0);");
        AnchorPane.setTopAnchor(cherryScoreLabel, 20.0);
        AnchorPane.setLeftAnchor(cherryScoreLabel, 135.0);
        anchorPane.getChildren().add(cherryScoreLabel);

        cherrySpawn = random.nextBoolean();
        if (cherrySpawn){
            cherry = new ImageView(new Image(getClass().getResourceAsStream("cherry.png")));
            cherry.setFitHeight(20);
            cherry.setFitWidth(20);
            cherry.setX(rectangle1.getX() + rectangle1.getWidth()+random.nextInt((int) distance - 30));
            if (random.nextBoolean()) cherry.setY(510);
            else cherry.setY(510 - 30);
            anchorPane.getChildren().add(cherry);
        }

        rotated = false;
        isMoving = false;
        isFlipped = 0;
        sceneTransition = false;
        pause = false;

        anchorPane.setOnMousePressed(this::handleMousePressed);
        anchorPane.setOnMouseReleased(this::handleMouseReleased);
        anchorPane.setOnMouseClicked(this::handleMouseClicked);
        anchorPane.setOnKeyTyped(this::handlePauseResume);
        anchorPane.setFocusTraversable(true);

        return anchorPane;
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
    public void setMap(String newMap) {
        map = newMap;
    }

    private void handleMousePressed(MouseEvent event) {
        if (rotated || isMoving || sceneTransition || pause || !isAlive){
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
    }

    private void handleMouseReleased(MouseEvent event) {
        if (isMoving || sceneTransition || pause || !isAlive){
            return;
        }

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

        translateTransition = new TranslateTransition(Duration.seconds(2), player);
        translateTransition.setByX(stick.getHeight() + 15);
        isMoving = true;
        if (cherrySpawn){
            cherryCollected = false;
            checkCollisions();
        }
        checkPillarCollision();
        translateTransition.play();

        translateTransition.setOnFinished(gameState -> {
            if (stick.getHeight() < rectangle2.getX() - rectangle1.getX() - rectangle1.getWidth() || stick.getHeight() > rectangle2.getX() + rectangle2.getWidth() - rectangle1.getX() - rectangle1.getWidth()){
                try {
                    gameOver();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                currentScore.setScore(currentScore.getScore() + 1);
                if (currentScore.getScore() > highScore.getScore()){
                    highScore = currentScore.clone();
                }
                gameContinue();
            }
        });
    }
    private void checkCollisions() {
        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (player.getBoundsInParent().intersects(cherry.getBoundsInParent()) && !cherryCollected && player.getY()==cherry.getY()) {
                    System.out.println("Cherry detected!");
                    cherryScore++;
                    cherryCollected = true;
                    root.getChildren().remove(cherry);
                }
            }
        };

        timer.start();
    }

    private void checkPillarCollision(){
        javafx.animation.AnimationTimer timer = new javafx.animation.AnimationTimer() {
            @Override
            public void handle(long now) {
                if (player.getBoundsInParent().intersects(rectangle2.getBoundsInParent()) && isAlive && isFlipped%2==0){
                    System.out.println("Pillar detected!");
                    isAlive = false;
                    try {
                        gameOver();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };

        timer.start();
    }

    private void handleMouseClicked(MouseEvent event){
        if (!isMoving || sceneTransition || pause || !isAlive){
            return;
        }
        if (isFlipped==0){
            isFlipped++;
        }
        else if (!(isFlipped%2==0)){
            player.setScaleY(-1);
            player.setY(player.getY()+player.getFitHeight());
            isFlipped++;
        }
        else{
            player.setScaleY(1);
            player.setY(player.getY()-player.getFitHeight());
            isFlipped++;
        }
    }
    private void handlePauseResume(KeyEvent event){
        if (sceneTransition || !isAlive){
            return;
        }
        if (!pause){
            pause();
        }
        else{
            resume();
        }
    }
    private void pause(){
        System.out.println("Paused");
        pause = true;
        if (isMoving) {
            translateTransition.pause();
        }
    }
    private void resume(){
        System.out.println("Resumed");
        pause = false;
        if (isMoving) {
            translateTransition.play();
        }
    }
    public void gameOver() throws IOException {
        mediaPlayer.pause();
        stage.close();
        MediaPlayer mediaPlayer = new MediaPlayer(new javafx.scene.media.Media(getClass().getResource("youDied.mp3").toString()));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("deathScreen.fxml"));
        Parent deathScreen = loader.load();

        DeathScreen deathScreenController = loader.getController();
        deathScreenController.setGame(myGame);

        stage.setScene(new Scene(deathScreen));
        stage.show();

        mediaPlayer.play();
    }
    public void gameContinue(){
        sceneTransition = true;
        TranslateTransition deleteTransition = new TranslateTransition(Duration.seconds(3), rectangle1);
        TranslateTransition moveTransition = new TranslateTransition(Duration.seconds(3), rectangle2);
        TranslateTransition stickTransition = new TranslateTransition(Duration.seconds(3), stick);
        TranslateTransition playerTransition = new TranslateTransition(Duration.seconds(3), player);
        if (cherrySpawn && !cherryCollected){
            TranslateTransition cherryTransition = new TranslateTransition(Duration.seconds(3), cherry);
            cherryTransition.setByX(-distance-rectangle1.getWidth());
            cherryTransition.play();
        }
        deleteTransition.setByX(-distance-rectangle1.getWidth());
        moveTransition.setByX(-distance-rectangle1.getWidth());
        stickTransition.setByX(-distance-rectangle1.getWidth());
        playerTransition.setByX(-distance-rectangle1.getWidth());

        deleteTransition.play();
        moveTransition.play();
        stickTransition.play();
        playerTransition.play();

        moveTransition.setOnFinished(event -> {
            if (!isAlive){
                return;
            }
            root = createContent();
            Scene scene = new Scene(root, 392, 650);
            stage.setScene(scene);
            stage.show();

            root.setFocusTraversable(true);
            root.requestFocus();
        });
    }
    public void revived() throws InterruptedException {
        mediaPlayer.play();
        root = createContent();
        Scene scene = new Scene(root, 392, 650);
        stage.setScene(scene);
        stage.show();

        root.setFocusTraversable(true);
        root.requestFocus();
        isAlive = true;
    }
    public Game getMyGame() {
        return myGame;
    }
    public void setMyGame(Game myGame) {
        this.myGame = myGame;
    }

    public int getCherry() {
        return cherryScore;
    }
    public void setCherry(int cherry) {
        this.cherryScore = cherry;
    }
    public void setHighScore(Score highScore) {
        this.highScore = highScore;
    }
    public Score getHighScore() {
        return highScore;
    }
    public Score getScore() {
        return currentScore;
    }
    public void setScore(Score score) {
        this.currentScore = score;
    }
}
