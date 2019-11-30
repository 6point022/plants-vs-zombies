package sample.game;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sunflower extends Plant {
    public Timeline timeline;

    public Sunflower(ImageView image) {
        super(image, 50, 20);
        getImageView().setTranslateY(-20);
    }
}
