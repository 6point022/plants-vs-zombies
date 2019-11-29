package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.game.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    Level level;
    Game game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level = new Level(1);
        game = new Game(level);

        System.out.println("inited");

        for (String plant: level.listOfUnlockedPlants) {
            seedSelected.put(plant, false);
        }

        plantImagePath.put("Peashooter", "/resources/peashooterplanted.gif");
        plantImagePath.put("Wallnut", "/resources/wallnut.png");
        plantImagePath.put("Cherry Bomb", "/resources/cherrybomb.png");
        plantImagePath.put("Sunflower", "/resources/sunflower.png");

        Timeline timeline;

        KeyFrame kf = new KeyFrame(Duration.seconds(5), event -> {
            dropSunToken();
        });

        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        Thread t = new Thread(() -> {
            System.out.println("inside the thread");

            while(true) {
                Zombie zombie = level.listOfZombies.remove(0);

                zombie.getImageView().setLayoutX(zombie.getPositionX());
                zombie.getImageView().setLayoutY(zombie.getPositionY());

                backyard.getChildren().add(zombie.getImageView());

                KeyFrame kf2 = new KeyFrame(Duration.millis(70), event -> {
                    zombie.getImageView().setLayoutX(zombie.getImageView().getLayoutX() - zombie.getSpeed());

//                    zombie.getImageView().setTranslateX(zombie.getSpeed());
                });

                Timeline timeline2 = new Timeline(kf2);
                timeline2.setCycleCount(Animation.INDEFINITE);
                timeline2.play();

                try {
                    Thread.sleep(zombie.getTimeBeforeNextZombie() * 10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        );

        t.start();
    }

    @FXML
    public AnchorPane backyard;

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
    public Label counterLabel;

    @FXML
    public ImageView tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;

    HashMap<String, Boolean> seedSelected = new HashMap<>();
    HashMap<String, String> plantImagePath = new HashMap<>();

    public void dropSunToken()
    {
        ImageView sun= new ImageView();
        sun.setImage(new Image("/resources/sun.gif"));
        sun.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                int temp=Integer.parseInt(counterLabel.getText());
                counterLabel.setText(String.valueOf(temp+25));
                backyard.getChildren().remove(event.getPickResult().getIntersectedNode());
            }

        });
        int RandomY= new Random().nextInt(330)+20;
        backyard.getChildren().add(sun);
        sun.setFitHeight(40);
        sun.setFitWidth(40);
        sun.setLayoutY(10);
        sun.setLayoutX(new Random().nextInt(622)+180);
        Timeline timeline;

        KeyFrame kf = new KeyFrame(Duration.millis(45), event -> {
            if(sun.getLayoutY()<RandomY)
                sun.setLayoutY(sun.getLayoutY() + 1);
        });

        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

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

//    public void dropSunToken() throws Exception {
//        System.out.println("clicked counter");
//        sunToken.setVisible(true);
//        TranslateTransition transition = new TranslateTransition();
//        transition.setDuration(Duration.seconds(6));
//        transition.setToY(200);
//        transition.setNode(sunToken);
//        transition.play();
//    }

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
