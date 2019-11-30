package sample.game;

import javafx.scene.image.ImageView;

public abstract class Plant extends Unit {
    private long rechargeTime;
    private ImageView image;
    Plant(ImageView image)
    {
        this.image=image;
        this.setPositionX(image.getLayoutX());
        this.setPositionY(image.getLayoutY());
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
}



class Barrier extends Plant{


    Barrier(ImageView image) {
        super(image);
    }
}
class Wallnut extends Barrier{

    Wallnut(ImageView image) {
        super(image);
    }
}

class Suntokenproducer extends Plant{

    Suntokenproducer(ImageView image) {
        super(image);
    }
}
class sunflower extends Suntokenproducer{

    sunflower(ImageView image) {
        super(image);
    }
}