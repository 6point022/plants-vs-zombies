package sample.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Lawnmower {
    private int posX, posY;
    public Boolean isAlive;
    private ImageView imageView;

    Lawnmower(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.isAlive = true;
        this.imageView = new ImageView();
        imageView.setImage(new Image("/resources/lawn_mower.gif"));
        imageView.setLayoutX(posX);
        imageView.setLayoutY(posY);
    }

    public void move() {
        this.getImageView().setLayoutX(this.getImageView().getLayoutY() + 1);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
