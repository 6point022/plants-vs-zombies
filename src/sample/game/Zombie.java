package sample.game;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.GameController;

public abstract class Zombie extends Unit {
    private int speed;
    private int biteDamage;
    private int timeBeforeNextZombie;
    private ImageView imageView;
    public Timeline timeline;
    static int counter = 0;

    Zombie(String name, int health, int speed, int timeBeforeNextZombie, int posX, int posY) {
        this.setBiteDamage(1);
        this.setTimeBeforeNextZombie(timeBeforeNextZombie);
        this.setName(name);
        this.setHealth(health);
        this.setSpeed(2);
        this.setPositionX(posX);
        this.setPositionY(posY);
        this.setImageView(new ImageView());
        this.getImageView().setImage(new Image("resources/zombie_normal.gif"));

    }

    public void move() {
        this.getImageView().setLayoutX(this.getImageView().getLayoutX() - this.getSpeed());
        this.setPositionX(this.getImageView().getLayoutX());
    }

    public void kill(Game game) {
        game.listOfWalkingZombies.remove(this);
        timeline.stop();
    }

    public int bite(Plant plant) {
        plant.setHealth(plant.getHealth() - this.getBiteDamage());

        if (plant.getHealth() <= 0) {
            return -1;
        }

        else {
            return 0;
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBiteDamage() {
        return biteDamage;
    }

    public void setBiteDamage(int biteDamage) {
        this.biteDamage = biteDamage;
    }

    public int getTimeBeforeNextZombie() {
        return timeBeforeNextZombie;
    }

    public void setTimeBeforeNextZombie(int timeBeforeNextZombie) {
        this.timeBeforeNextZombie = timeBeforeNextZombie;
    }
}

class NormalZombie extends Zombie {
    NormalZombie(String name, int health, int speed, int timeBeforeNextZombie, int posX, int posY) {
        super(name, health, speed, timeBeforeNextZombie, posX, posY);
    }
}

class FlagZombie extends Zombie {
    FlagZombie(String name, int health, int speed, int timeBeforeNextZombie, int posX, int posY) {
        super(name, health, speed, timeBeforeNextZombie, posX, posY);
    }
}

class ConeheadZombie extends Zombie{
    ConeheadZombie(String name, int health, int speed, int timeBeforeNextZombie, int posX, int posY) {
        super(name, health, speed, timeBeforeNextZombie, posX, posY);
    }
}


