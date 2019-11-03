package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Transition extends Application {
    @FXML
    public ImageView zombieNormal;

    @Override
    public void start(Stage primaryStage) throws Exception {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(5));
        transition.setToX(500);
        transition.setNode(zombieNormal);
        transition.play();

    }
}
