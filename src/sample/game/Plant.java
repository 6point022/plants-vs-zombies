package sample.game;

import javafx.scene.image.ImageView;

public abstract class Plant extends Unit {
    private long rechargeTime;
    private int cost;
    private transient ImageView imageView;
    private String id;

    Plant(ImageView image, int cost) {
        this.imageView = image;
        this.setPositionX(image.getLayoutX());
        this.setPositionY(image.getLayoutY());
        this.setCost(cost);
        this.setHealth(20);
        this.setId(image.getId());
    }

    public long getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(long rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setImage(ImageView image) {
        this.imageView = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
