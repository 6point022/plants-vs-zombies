package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController {

    @FXML
    public AnchorPane menuAlert;

    @FXML
    public ImageView zombieNormal;

    @FXML
    public ImageView peashooterPlanted;

    @FXML
    public ImageView pea;

    @FXML
    public ImageView counter;

    @FXML
    public ImageView sunToken;

    public void dropSunToken() throws Exception {
        System.out.println("clicked counter");
        sunToken.setVisible(true);
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(6));
        transition.setToY(200);
        transition.setNode(sunToken);
        transition.play();
    }

//    private class ZombieMove implements EventHandler<ActionEvent> {
//        public void handle(ActionEvent e) {
//
//        }
//    }

    public void zombieNormalHandler() throws Exception {
        System.out.println("clicked zombie");
//        TranslateTransition transition = new TranslateTransition();
//        transition.setDuration(Duration.seconds(6));
//        transition.setToX(-500);
//        transition.setNode(zombieNormal);
//        transition.setCycleCount(Animation.INDEFINITE);
//        transition.play();

        KeyFrame kf = new KeyFrame(Duration.millis(70), event -> {
            zombieNormal.setLayoutX(zombieNormal.getLayoutX() - 1);
            System.out.println(zombieNormal.getLayoutX());

            int diff = Math.abs((int) (zombieNormal.getLayoutX() - pea.getLayoutX()));

            if (diff <= 4) {
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
            }

        });

        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

//        Thread t = new Thread(() -> {
//            while(true) {
//
//                try {
//                    Thread.sleep(100);
//                }
//
//                catch(Exception e) {
//
//                }
//                if (zombieNormal.getTranslateX() == pea.getTranslateX()) {
//                    System.out.println("BOOM!");
//                    break;
//                }
//            }
//            });

//        t.start();
    }

    public void peashooterHandler() throws Exception {
        System.out.println("Clicked peashooter");

        double initPosX = pea.getLayoutX();
        System.out.println(initPosX);

        pea.setVisible(true);

        KeyFrame kf = new KeyFrame(Duration.millis(70), event -> {
            pea.setLayoutX(pea.getLayoutX() + 1);
            System.out.println(pea.getLayoutX());
        });

        Timeline timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

//        TranslateTransition transition = new TranslateTransition();
//
//        transition.setDuration(Duration.seconds(4));
//
//        transition.setToX(600);
//        transition.setCycleCount(Animation.INDEFINITE);
//
//        transition.setNode(pea);
//
//        transition.play();

    }

    public void menuHandler() throws Exception {
        System.out.println("clicked menu");
        menuAlert.setVisible(true);
        menuAlert.setDisable(false);
    }

    public void resumeButtonHandler() {
        menuAlert.setVisible(false);
        menuAlert.setDisable(true);
    }

    public void peashooterButtonHandler() {
        System.out.println("Clicked peashooter");
    }

    public void sunflowerButtonHandler() {
        System.out.println("Clicked sunflower");
    }

    public void wallnutButtonHandler() {
        System.out.println("Clicked wallnut");
    }

    public void cherryBombButtonHandler() {
        System.out.println("Clicked cherry bomb");
    }
}
