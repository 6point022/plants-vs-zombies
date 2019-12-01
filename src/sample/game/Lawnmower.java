package sample.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;

public class Lawnmower extends Unit implements Serializable {
    private int posX, posY;
    public Boolean isAlive;
    private transient ImageView imageView;

    Lawnmower(int posX, int posY, int rowNum) {
        this.posX = posX;
        this.posY = posY;
        this.isAlive = true;
        this.imageView = new ImageView();
        imageView.setImage(new Image("/resources/lawn_mower.gif"));
        imageView.setLayoutX(posX);
        imageView.setLayoutY(posY);
        setRowNum(rowNum);
    }

    public void move() {
        this.getImageView().setLayoutX(this.getImageView().getLayoutX() + 5);
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
