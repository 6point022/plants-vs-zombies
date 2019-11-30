package sample.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Peashooter extends Plant {
    public boolean shoot;

    public Peashooter(ImageView image) {
        super(image, 100);
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
