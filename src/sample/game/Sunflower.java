package sample.game;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sunflower extends Plant {
    private ImageView imageView;
    public Timeline timeline;

//    public void loadImage() {
//        imageView = new ImageView();
//        imageView.setImage(new Image("/resources/sunflower.png"));
//    }

    public Sunflower(ImageView image) {
        super(image, 50);
    }
}
