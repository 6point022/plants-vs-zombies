package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.game.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    Level level;
    Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("here..");

        level = new Level(1);
        game = new Game(level);

        for (String plant: level.listOfUnlockedPlants) {
            seedSelected.put(plant, false);
        }

        plantImagePath.put("Peashooter", "/resources/peashooterplanted.gif");
        plantImagePath.put("Wallnut", "/resources/wallnut.png");
        plantImagePath.put("Cherry Bomb", "/resources/cherrybomb.png");
        plantImagePath.put("Sunflower", "/resources/sunflower.png");
    }

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

    @FXML
    public ImageView peashooterSeed;

    @FXML
    public ImageView tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;

    HashMap<String, Boolean> seedSelected = new HashMap<>();
    HashMap<String, String> plantImagePath = new HashMap<>();

    public void tileHandler(MouseEvent event) throws Exception {
        String id = event.getPickResult().getIntersectedNode().getId();
        int tileId = Integer.parseInt(Character.toString(id.charAt(4)));
        System.out.println(tileId);
        ImageView tile = (ImageView) event.getPickResult().getIntersectedNode();
        System.out.println(tile);

        for (String plant: level.listOfUnlockedPlants) {
            if (seedSelected.get(plant)) {
                tile.setImage(new Image(plantImagePath.get(plant)));

                if (plant.equals("Peashooter"))
                    game.listOfPlants.add(new Peashooter(tile));
            }
        }


    }

    public void seedClickHandler() throws Exception {
        System.out.println("Here");
        seedSelected.replace("Peashooter", true);

//        peashooterSeedSelected = true;
    }

    public void dropSunToken() throws Exception {
        System.out.println("clicked counter");
        sunToken.setVisible(true);
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(6));
        transition.setToY(200);
        transition.setNode(sunToken);
        transition.play();


    }

    public void zombieNormalHandler() throws Exception {
        System.out.println("clicked zombie");
        Timeline timeline;

        KeyFrame kf = new KeyFrame(Duration.millis(70), event -> {
            zombieNormal.setLayoutX(zombieNormal.getLayoutX() - 1);
            //timeline.stop()
            //setAliveFalse

//            System.out.println(zombieNormal.getLayoutX());

            int diff = Math.abs((int) (zombieNormal.getLayoutX() - pea.getLayoutX()));

            if (diff <= 3) {
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");
                System.out.println("BOOM!");

                pea.setVisible(false);
                System.out.println("Decreasing zombie health");
            }
        });

        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void peashooterHandler() throws Exception {
        System.out.println("Clicked peashooter");

        double initPosX = pea.getLayoutX();
        System.out.println(initPosX);

        pea.setVisible(true);

        KeyFrame kf = new KeyFrame(Duration.millis(70), event -> {
            pea.setLayoutX(pea.getLayoutX() + 1);
//            System.out.println(pea.getLayoutX());
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
