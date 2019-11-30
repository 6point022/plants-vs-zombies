package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.game.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReference;


public class Level2Controller implements Initializable {

    @FXML
    public AnchorPane backyard;
    public Timeline suntimeline;
    public Timeline zombiestimeline;
    public int counterpeashooter;
    public int counterwin;
    public boolean gameended;
    public boolean counterflag;
    public int counterlost;
    public int countersunflower;
    Level level;
    Game game;
    ArrayList<Plant> tempListOfPlants;
    ArrayList<Zombie> tempListOfWalkingZombies;

    public Boolean checkForCollision(double maxDiffX, double maxDiffY, ImageView i1, ImageView i2) {
        System.out.println("Checking for collision");

        double _diffX = Math.abs(i1.getLayoutX() - i2.getLayoutX());
        double _diffY = Math.abs(i1.getLayoutY() - i2.getLayoutY());

        System.out.println(_diffX + " " + _diffY);

        return _diffX <= maxDiffX && _diffY <= maxDiffY;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        level = new Level(2);
        game = new Game(level);

        for (Lawnmower lawnmower: level.listOfLawnmower) {
            backyard.getChildren().add(lawnmower.getImageView());
        }

        // TODO: Remove the next line
        counterLabel.setText(Integer.toString(400));

        for (String plant: level.listOfUnlockedPlants) {
            seedSelected.put(plant, false);
        }

        plantImagePath.put("Peashooter", "/resources/peashooterplanted.gif");
        plantImagePath.put("Wallnut", "/resources/wallnut.png");
        plantImagePath.put("Cherry Bomb", "/resources/cherrybomb.png");
        plantImagePath.put("Sunflower", "/resources/sun_flower.gif");

        Timeline timeline;

        KeyFrame kf = new KeyFrame(Duration.seconds(10), event -> {
            dropSunToken();
        });

        timeline = new Timeline(kf);
        timeline.setCycleCount(Animation.INDEFINITE);
        suntimeline=timeline;
        timeline.play();


        //*****************Counter timer
        Timeline timeline4;

        KeyFrame kf4 = new KeyFrame(Duration.seconds(1), event -> {
            counterpeashooter++;
            countersunflower++;
            if (counterpeashooter>5)
            {
                peashooterSeed.setOpacity(1);
                peashooterSeed.setDisable(false);
            }
            if(countersunflower>5)
            {
                sunflowerSeed.setOpacity(1);
                sunflowerSeed.setDisable(false);
            }
            if(counterflag && !gameended){
                gameended=true;
                counterlost++;
                if(counterlost>=5)
                {
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Main.window.setTitle("Main menu");
                    Main.window.setScene(new Scene(root, Main.width, Main.height));
                }

            }

            if(game.listOfWalkingZombies.size()==0 && level.listOfZombies.size()==0)
            {
                counterwin++;
                suntimeline.pause();
                zombiestimeline.pause();
                for(Plant p: game.listOfPlants)
                {
                    if(p instanceof Peashooter)
                    {
                        Peashooter x= (Peashooter)p;
                        if(x!=null)
                            x.timeline.pause();
                    }
                }
                for(Zombie z: game.listOfWalkingZombies)
                {
                    z.timeline.pause();
                }
                Label labell= new Label();
                backyard.getChildren().add(labell);
                labell.setText("YOU WIN!!");
                labell.setLayoutX(450);
                labell.setLayoutY(130);
                labell.setFont(new Font(30));
                labell.setTextFill(Color.web("#000000"));
                if(counterwin>=8 && !gameended)
                {
                    gameended=true;
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("chooselevel.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Main.window.setTitle("Choose Level");
                    Main.window.setScene(new Scene(root, Main.width, Main.height));
                }
//                labell.setMinSize(50,50);
            }
        });

        timeline4 = new Timeline(kf4);
//        suntimeline=timeline;
        timeline4.setCycleCount(Animation.INDEFINITE);
        timeline4.play();

        //**************************************************till here

        Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(10), event -> {
            if (level.listOfZombies.size() > 0) {
                Zombie zombie = level.listOfZombies.remove(0);
                System.out.println("Zombie out");
                game.listOfWalkingZombies.add(zombie);

                zombie.getImageView().setLayoutX(zombie.getPositionX());
                zombie.getImageView().setLayoutY(zombie.getPositionY());
                backyard.getChildren().add(zombie.getImageView());

                //check for plants in row of zombie and activate shooting

                tempListOfPlants = new ArrayList<>(game.listOfPlants);

                for (Plant p: tempListOfPlants) {
                    if (p instanceof Peashooter) {//p.getClass()==new Peashooter(new ImageView()).getClass()) {
                        System.out.println("yesplant");
                        Peashooter pp= (Peashooter)p;
                        double diffX= zombie.getPositionX()-p.getPositionX();
                        double diffY= Math.abs(zombie.getPositionY()-p.getPositionY());
                        System.out.println(diffX);
                        System.out.println(diffY);

                        if (diffX < 700 && diffY < 100 && !pp.shoot) {
                            shootpea(p, p.getPositionX(), p.getPositionY());
                            pp.shoot = true;
                        }
                    }
                }

                KeyFrame kf3 = new KeyFrame(Duration.millis(100), event2 -> {
                    boolean flag = false;

                    tempListOfPlants = new ArrayList<Plant>(game.listOfPlants);

                    for (Plant plant: tempListOfPlants) {
                        if (checkForCollision(4, 90, zombie.getImageView(), plant.getImageView())) {
                            flag = true;
                            System.out.println("plant collision");
                            if (zombie.bite(plant) == -1) {
                                // Plant dead

                                System.out.println("biting");
                                game.listOfPlants.remove(plant);
                                backyard.getChildren().remove(plant.getImageView());
                                if (plant instanceof Peashooter) {
                                    Peashooter tt=(Peashooter)plant;
                                    tt.timeline.stop();
                                }

                                else if(plant instanceof Sunflower) {
                                    Sunflower tt = (Sunflower) plant;
                                    tt.timeline.stop();
                                }

                                flag = false;
                            }
                        }
                    }
                    if(zombie.getPositionX()<165)
                    {
                        zombie.timeline.stop();
                        backyard.getChildren().remove(zombie.getImageView());
                        gamelost();
                    }
                    if (zombie.getImageView().getLayoutX() > 160 && !flag) {
                        zombie.move();
                    }

                    if (zombie.getPositionX() < 200) {

                        for (Lawnmower lawnmower: level.listOfLawnmower) {
                            System.out.println("Checking listoflawnmowers");
                            if (lawnmower.isAlive) {
                                System.out.println("lawnmower is alive");

                                if (checkForCollision(40, 40, zombie.getImageView(), lawnmower.getImageView())) {
                                    System.out.println("Colliding with lawnmower");
                                    lawnmower.isAlive = false;

                                    Timeline t4 = new Timeline(new KeyFrame(Duration.millis(10), e2 -> {
                                        lawnmower.move();

                                        tempListOfWalkingZombies = new ArrayList<>(game.listOfWalkingZombies);

                                        for (Zombie z: tempListOfWalkingZombies) {
                                            if (checkForCollision(40, 40, z.getImageView(), lawnmower.getImageView())) {
                                                System.out.println("Colliding with lawnmower2");
                                                z.kill(game);
                                                backyard.getChildren().remove(z.getImageView());
                                            }
                                        }
                                    }));

                                    t4.setCycleCount(Animation.INDEFINITE);
                                    t4.play();
                                }
                            }
                        }


                    }

                });

                Timeline timeline3 = new Timeline(kf3);
                zombie.timeline = timeline3;
                timeline3.setCycleCount(Animation.INDEFINITE);
                timeline3.play();
            }
        }));

        timeline2.setCycleCount(Animation.INDEFINITE);
        zombiestimeline=timeline2;
        timeline2.play();
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
    public ImageView peashooterSeed,sunflowerSeed;

    @FXML
    public Label counterLabel;

    @FXML
    public ImageView tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;

    HashMap<String, Boolean> seedSelected = new HashMap<>();
    HashMap<String, String> plantImagePath = new HashMap<>();

    public void shootpea(Plant plant, double x, double y) {
        ImageView pea1 = new ImageView();
        pea1.setImage(new Image("/resources/pea.png"));
        backyard.getChildren().add(pea1);
        pea1.setFitHeight(20);
        pea1.setFitWidth(20);
        pea1.setLayoutY(y);
        pea1.setLayoutX(x + 10);
        Timeline timeline3;

        KeyFrame kf3 = new KeyFrame(Duration.millis(25), eve -> {
            pea1.setLayoutX(pea1.getLayoutX() + 2);

            for(Zombie zombie: game.listOfWalkingZombies) {
                double diffX = Math.abs(zombie.getPositionX() - pea1.getLayoutX());
                double diffY = Math.abs(zombie.getPositionX() - pea1.getLayoutX());

                if (diffX < 4 && diffY < 100) {
                    System.out.println("Shot");
                    Peashooter peashooter = (Peashooter) plant;

                    if (peashooter.attack(zombie) == -1) {
                        // Zombie dead
                        game.listOfWalkingZombies.remove(zombie);
                        zombie.getImageView().setLayoutX(-1000);
                        zombie.getImageView().setLayoutY(-1000);
                        System.out.println("Layoutchanged");
                        zombie.timeline.stop();
                        backyard.getChildren().remove(zombie.getImageView());
                    }

                    backyard.getChildren().remove(pea1);
                    pea1.setDisable(true);
                }
            }
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
                pea.setLayoutX(pea.getLayoutX() + 3);

                tempListOfWalkingZombies = new ArrayList<Zombie>(game.listOfWalkingZombies);

                for(Zombie zombie: tempListOfWalkingZombies) {
                    double diffX = Math.abs(zombie.getPositionX() - pea.getLayoutX());
                    double diffY = Math.abs(zombie.getPositionX() - pea.getLayoutX());

                    if (diffX < 3 && diffY < 50) {
                        System.out.println("Shot");
                        pea.setLayoutX(1000);
                        Peashooter peashooter = (Peashooter) plant;

                        if (peashooter.attack(zombie) == -1) {
                            // Zombie dead
                            game.listOfWalkingZombies.remove(zombie);
                            zombie.getImageView().setLayoutX(-1000);
                            zombie.timeline.stop();
                            backyard.getChildren().remove(zombie.getImageView());
                        }

                        backyard.getChildren().remove(pea);
                    }
                }
            });

            timeline2 = new Timeline(kf2);
            timeline2.setCycleCount(Animation.INDEFINITE);
            timeline2.play();

        });

        timeline = new Timeline(kf);
        Peashooter temp = (Peashooter) plant;
        temp.timeline=timeline;
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void dropSunToken() {
        ImageView sun = new ImageView();
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

    // Sunflower's sun producer

    public void produceSuntoken(Sunflower flo, double posX, double posY) {
        ImageView tile= flo.getImageView();
        KeyFrame kf = new KeyFrame(Duration.seconds(20), event -> {
            Suntoken suntoken = new Suntoken(posX, posY);
            backyard.getChildren().add(suntoken.getImageView());

            suntoken.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int temp=Integer.parseInt(counterLabel.getText());
                    counterLabel.setText(String.valueOf(temp + 25));
                    backyard.getChildren().remove(event.getPickResult().getIntersectedNode());
                }
            });
        });

        Timeline timeline = new Timeline(kf);
        flo.timeline = timeline;
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    // Handles the planting of plants on the lawn

    public void tileHandler(MouseEvent event) throws Exception {
        String id = event.getPickResult().getIntersectedNode().getId();
        int tileId = Integer.parseInt(Character.toString(id.charAt(4)));
        System.out.println(tileId);
        ImageView tile = (ImageView) event.getPickResult().getIntersectedNode();
        System.out.println(tile);

        for (String plant: level.listOfUnlockedPlants) {
            if (seedSelected.get(plant)) {
                tile.setImage(new Image(plantImagePath.get(plant)));
                System.out.println("Sunflower path --" + plantImagePath.get(plant));
                tile.setFitHeight(70);
                tile.setFitWidth(45);
                seedSelected.replace(plant, false);
                Plant p = null;

                // Peashooter selected

                if (plant.equals("Peashooter")) {
                    p = new Peashooter(tile);
                    game.listOfPlants.add(p);

                    // If a zombie already active start shooting

                    for (Zombie zombie: game.listOfWalkingZombies) {

                        double diffX = Math.abs(zombie.getPositionX()-p.getPositionX());
                        double diffY = Math.abs(zombie.getPositionY()-p.getPositionY());

                        if (diffX < 600 && diffY < 100) {
                            shootpea(p, p.getPositionX(), p.getPositionY());
                            ((Peashooter) p).shoot = true;
                        }
                    }
                }

                // Sunflower selected

                else if (plant.equals("Sunflower")) {
                    p = new Sunflower(tile);
                    game.listOfPlants.add(p);
                    produceSuntoken((Sunflower) p, tile.getLayoutX() + 10, tile.getLayoutY() + 10);
                }

                // TODO: Add for other plants

                counterLabel.setText(String.valueOf(Integer.parseInt(counterLabel.getText()) - p.getCost()));

            }
        }
    }

    // Selecting the plant from the buy menu

    public void seedClickHandler(MouseEvent event) throws Exception {
        String seedId = event.getPickResult().getIntersectedNode().getId();

        if (seedId.equals("peashooterSeed")) {
            if (Integer.parseInt(counterLabel.getText()) >= 100) {
                seedSelected.replace("Peashooter", true);
                // FOR OPACITY PROPOERT
                peashooterSeed.setOpacity(0.5);
                peashooterSeed.setDisable(true);
                counterpeashooter=0;
                //*******************
            }
        }

        if (seedId.equals("sunflowerSeed")) {
            if (Integer.parseInt(counterLabel.getText()) >= 50) {
                seedSelected.replace("Sunflower", true);
                // FOR OPACITY PROPOERT
                sunflowerSeed.setOpacity(0.5);
                sunflowerSeed.setDisable(true);
                countersunflower=0;
                //*******************
            }
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
    }

    public void menuHandler() throws Exception {
        System.out.println("clicked menu");
        menuAlert.toFront();
        menuAlert.setVisible(true);
        menuAlert.setDisable(false);
        gamepause();
    }
    public void gamepause()
    {
        suntimeline.pause();
        zombiestimeline.pause();
        for(Plant p: game.listOfPlants)
        {
            if(p instanceof Peashooter)
            {
                Peashooter x= (Peashooter)p;
                if(x!=null)
                    x.timeline.pause();
            }
        }
        for(Zombie z: game.listOfWalkingZombies)
        {
            z.timeline.pause();
        }

    }

    public void resumeButtonHandler() {
        menuAlert.toBack();
        menuAlert.setVisible(false);
        menuAlert.setDisable(true);
        zombiestimeline.play();
        suntimeline.play();
        for(Plant p: game.listOfPlants)
        {
            if(p instanceof Peashooter)
            {
                Peashooter x= (Peashooter)p;
                x.timeline.play();
            }
        }
        for(Zombie z: game.listOfWalkingZombies)
        {
            z.timeline.play();
        }
    }

    public void ExitbuttonHandler() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Main.window.setTitle("Choose Level");
        Main.window.setScene(new Scene(root, Main.width, Main.height));
    }
    public void peashooterButtonHandler() {
        System.out.println("Clicked peashooter");
    }
    public void restartButton() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("chooselevel.fxml"));

        Main.window.setTitle("Choose Level");
        Main.window.setScene(new Scene(root, Main.width, Main.height));

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

    public void gamelost(){
        gamepause();
        Label labell= new Label();
        backyard.getChildren().add(labell);
        labell.setText("YOU LOOSE!");
        labell.setLayoutX(450);
        labell.setLayoutY(130);
        labell.setFont(new Font(30));
        labell.setTextFill(Color.web("#000000"));
        counterflag=true;

    }

    public void Savegamehandler() throws IOException {

        AtomicReference<String> username= new AtomicReference<>(new String());
        Label labelfirst= new Label();
        labelfirst.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        labelfirst.setText("Enter Your Name");
        Button button= new Button("Submit");
        TextField text= new TextField();
        button.setOnAction(e ->
                {
                    username.set(new String(text.getText()));
                    User saveuser= new User();
                    saveuser.setSuncollected(Integer.parseInt(counterLabel.getText()));
                    saveuser.setName(username.toString());
                    saveuser.setGame(game);

                    ObjectOutputStream out = null;
                    try {
                        out = new ObjectOutputStream (
                                new FileOutputStream(saveuser.getName()));
                        out.writeObject(saveuser);
                        System.out.println("serialized");
                        System.exit(0);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    } finally {
                        try {
                            out.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        VBox layout= new VBox(5);
        layout.setLayoutX(240);
        layout.getChildren().addAll(labelfirst, text, button);
        backyard.getChildren().add(layout);

    }




//    public void test(MouseEvent event){
//        System.out.println(event.getLayoutX());
//        System.out.println(event.getY());
//    }

}
