package sample.game;

import javafx.scene.image.ImageView;

public abstract class Plant extends Unit {
    private long rechargeTime;
    private int cost;
    private ImageView image;

    Plant(ImageView image, int cost) {
        this.image = image;
        this.setPositionX(image.getLayoutX());
        this.setPositionY(image.getLayoutY());
        this.setCost(cost);
    }

    public long getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(long rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}


class Barrier extends Plant{


    Barrier(ImageView image, int cost) {
        super(image, cost);
    }
}
class Wallnut extends Barrier{

    Wallnut(ImageView image, int cost) {
        super(image, cost);
    }
}

class Suntokenproducer extends Plant{

    Suntokenproducer(ImageView image, int cost) {
        super(image, cost);
    }
}
class sunflower extends Suntokenproducer{

    sunflower(ImageView image, int cost) {
        super(image, cost);
    }
}