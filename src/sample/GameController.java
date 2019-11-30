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
    ArrayList<Zombie> ActiveZombies;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level = new Level(1);
        game = new Game(level);
        ActiveZombies= new ArrayList<Zombie>();
        System.out.println("inited");
        //for testing
        counterLabel.setText(Integer.toString(400));

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

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (level.listOfZombies.size() > 0) {
                Zombie zombie = level.listOfZombies.remove(0);
                System.out.println("Zombie out");
                ActiveZombies.add(zombie);
                zombie.getImageView().setLayoutX(zombie.getPositionX());
                zombie.getImageView().setLayoutY(zombie.getPositionY());
                backyard.getChildren().add(zombie.getImageView());

                //check for plants in row of zombie and activate shooting
                for( Plant p: game.listOfPlants)
                {
                    if(p.getClass()==new Peashooter(new ImageView()).getClass())
                    {
                        System.out.println("yesplant");
                        Peashooter pp= (Peashooter)p;
                        double diffX= zombie.getPositionX()-p.getPositionX();
                        double diffY= Math.abs(zombie.getPositionY()-p.getPositionY());
                        System.out.println(diffX);
                        System.out.println(diffY);
                        if (diffX<700 && diffY<100 && !pp.shoot)
                        {
                            shootpea(p.getPositionX(),p.getPositionY());
                            pp.shoot=true;
                        }

                    }

                }


                KeyFrame kf3 = new KeyFrame(Duration.millis(100), event2 -> {
                    if (zombie.getImageView().getLayoutX() > 160) {
                        zombie.move();
                    }
                });

                Timeline timeline3 = new Timeline(kf3);
                timeline3.setCycleCount(Animation.INDEFINITE);
                timeline3.play();
            }
        }));

        timeline2.setCycleCount(Animation.INDEFINITE);
        timeline2.play();
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

    @FXML
            public ImageView bgimage;

    HashMap<String, Boolean> seedSelected = new HashMap<>();
    HashMap<String, String> plantImagePath = new HashMap<>();

    public void shootpea(double x, double y)
    {
        ImageView pea1= new ImageView();
        pea1.setImage(new Image("/resources/pea.png"));
        backyard.getChildren().add(pea1);
        pea1.setFitHeight(25);
        pea1.setFitWidth(25);
        pea1.setLayoutY(y);
        pea1.setLayoutX(x+10);
        Timeline timeline3;
        KeyFrame kf3 = new KeyFrame(Duration.millis(25), eve -> {
            pea1.setLayoutX(pea1.getLayoutX() + 2);
        });
        timeline3 = new Timeline(kf3);
        timeline3.setCycleCount(Animation.INDEFINITE);
        timeline3.play();

        Timeline timeline;
        KeyFrame kf = new KeyFrame(Duration.seconds(5), event -> {
            ImageView pea= new ImageView();
            pea.setImage(new Image("/resources/pea.png"));
            backyard.getChildren().add(pea);
            pea.setFitHeight(20);
            pea.setFitWidth(20);
            pea.setLayoutY(y);
            pea.setLayoutX(x);
            Timeline timeline2;
            KeyFrame kf2 = new KeyFrame(Duration.millis(25), ev -> {
                pea.setLayoutX(pea.getLayoutX() + 2);
            });
            timeline2 = new Timeline(kf2);
            timeline2.setCycleCount(Animation.INDEFINITE);
            timeline2.play();

        });
        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void dropSunToken() {
        ImageView sun= new ImageView();
        sun.setImage(new Image("/resources/sun.gif"));
        sun.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int temp=Integer.parseInt(counterLabel.getText());
                counterLabel.setText(String.valueOf(temp+25));
                backyard.getChildren().remove(event.getPickResult().getIntersectedNode());
            }
        });

        int RandomY = new Random().nextInt(330) + 20;
        backyard.getChildren().add(sun);
        sun.setFitHeight(40);
        sun.setFitWidth(40);
        sun.setLayoutY(10);
        sun.setLayoutX(new Random().nextInt(622) + 180);
        Timeline timeline;

        KeyFrame kf = new KeyFrame(Duration.millis(45), event -> {
            if(sun.getLayoutY() < RandomY)
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
                tile.setFitHeight(70);
                tile.setFitWidth(45);
                seedSelected.replace(plant, false);
                counterLabel.setText(String.valueOf(Integer.parseInt(counterLabel.getText())-100));
                if (plant.equals("Peashooter")) {
                    Peashooter p=new Peashooter(tile);
                    game.listOfPlants.add(p);

                    // If a zombie already active start shooting
                    for (Zombie z: ActiveZombies){
                        double diffX= z.getPositionX()-p.getPositionX();
                        double diffY= Math.abs(z.getPositionY()-p.getPositionY());
                        if (diffX<600 && diffY<100)
                        {
                            shootpea(p.getPositionX(),p.getPositionY());
                            p.shoot=true;
                        }

                    }
                }
            }
        }
    }

    public void seedClickHandler() throws Exception {
        System.out.println("Here");

        if(Integer.parseInt(counterLabel.getText())>=100) {
            seedSelected.replace("Peashooter", true);
            System.out.println("True done");
        }

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

    public void peashooterHandler(MouseEvent event) throws Exception {
        System.out.println("Clicked peashooter");

        shootpea(event.getPickResult().getIntersectedNode().getLayoutX()+10,event.getPickResult().getIntersectedNode().getLayoutY());

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

    public void plantscheckinrow(){

    }

    public void test(MouseEvent event){
//        System.out.println(event.getX());
//        System.out.println(event.getY());
    }

}
