package sample.game;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Peashooter extends Plant {
    public boolean shoot;
    public Timeline timeline;

    public Peashooter(ImageView imageView) {
        super(imageView, 100, 20);
    }

    public int attack(Zombie zombie) {
        zombie.setHealth(zombie.getHealth() - 1);

        if (zombie.getHealth() <= 0) {
            zombie.getImageView().setImage(new Image("/resources/zombie_normal_dying.gif"));
            System.out.println("Zombie dying...");
            return -1;
        }

        else {
            return 0;
        }
    }
}
