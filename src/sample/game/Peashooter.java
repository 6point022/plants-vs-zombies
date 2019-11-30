package sample.game;

import javafx.scene.image.ImageView;

public class Peashooter extends Plant {
    public boolean shoot;

    public Peashooter(ImageView image) {
        super(image, 100);
    }

    public int attack(Zombie zombie) {
        zombie.setHealth(zombie.getHealth() - 1);

        if (zombie.getHealth() <= 0) {
            return -1;
        }

        else {
            return 0;
        }
    }
}
