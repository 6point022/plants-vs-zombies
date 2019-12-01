package sample.game;

import javafx.scene.image.ImageView;

public class Cherrybomb extends Plant {
    private Boolean isDetonated;

    public Cherrybomb(ImageView imageView) {
        super(imageView, 150, 20);
        setDetonated(false);
    }

    public void increaseSize(int x) {
        System.out.println("Increasing size");
        getImageView().setFitWidth(getImageView().getFitWidth() + x);
        getImageView().setFitHeight(getImageView().getFitHeight() + x);
    }

    public Boolean getDetonated() {
        return isDetonated;
    }

    public void setDetonated(Boolean detonated) {
        isDetonated = detonated;
    }
}
