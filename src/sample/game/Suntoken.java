package sample.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Suntoken {
    private ImageView imageView;
    private String imagePath =  "/resources/sun.gif";

    static private int width, height;

    public Suntoken(double posX, double posY) {
        imageView = new ImageView();
        imageView.setImage(new Image(imagePath));
        width = 40;
        height = 40;
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        imageView.setLayoutX(posX);
        imageView.setLayoutY(posY);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }





}
