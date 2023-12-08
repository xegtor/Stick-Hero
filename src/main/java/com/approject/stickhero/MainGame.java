package com.approject.stickhero;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class MainGame extends Application {
    Stage stage = new Stage();

    private final Random random = new Random();

    private Rectangle stick;
    private Rectangle rectangle1;
    private Rectangle rectangle2;
    private Timeline extendTimelineHeight;
    private Timeline extendTimelineY;
    private boolean rotated = false;
    private Boolean isDead = false;

    private ImageView player;

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
        Pane anchorPane = new AnchorPane();

        ImageView background = new ImageView(new Image(getClass().getResourceAsStream("mountain.jpg")));
        background.setX((double) -392 /2);
        background.setY((double) -645 /2);
        anchorPane.getChildren().add(background);

        rectangle1 = createRandomRectangle();
        rectangle2 = createRandomRectangle();

        player = new ImageView(new Image(getClass().getResourceAsStream("sprite.png")));
        player.setFitHeight(30);
        player.setFitWidth(30);
        player.setX(rectangle1.getX() + rectangle1.getWidth() / 2 - player.getFitWidth() / 2);
        player.setY(rectangle1.getY()-player.getFitHeight());
        anchorPane.getChildren().add(player);

        double distance = random.nextInt(150) + 50;
        rectangle2.setX(rectangle1.getX() + rectangle1.getWidth() + distance);

        anchorPane.getChildren().addAll(rectangle1, rectangle2);

        Text scoreText = new Text("0");
        scoreText.setLayoutX(317);
        scoreText.setLayoutY(42);
        scoreText.setStroke(Color.BLACK);
        scoreText.setFont(new Font(24));

        stick = new Rectangle(4, 0, Color.RED);

        stick.setTranslateX(rectangle1.getX() + rectangle1.getWidth() - 4);
        stick.setTranslateY(rectangle1.getY());

        anchorPane.getChildren().addAll(stick, scoreText);

        anchorPane.setOnMousePressed(this::handleMousePressed);
        anchorPane.setOnMouseReleased(this::handleMouseReleased);

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
        translateTransition.setFromX(player.getX());
        translateTransition.setToX(stick.getHeight());

        if (stick.getHeight()<rectangle2.getX() - rectangle1.getX() - rectangle1.getWidth() || stick.getHeight()>rectangle2.getX()+rectangle2.getWidth() - rectangle1.getX() - rectangle1.getWidth()){
            isDead = true;
        }

        translateTransition.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
