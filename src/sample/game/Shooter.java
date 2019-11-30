package sample.game;

import javafx.scene.image.ImageView;

public class Shooter extends Plant{
    private int attackvalue;
    public Boolean shoot;

    Shooter(ImageView image, int cost) {
        super(image, cost);
        this.attackvalue = 100;
    }

    public int getAttackvalue() {
        return attackvalue;
    }

    public void setAttackvalue(int attackvalue) {
        this.attackvalue = attackvalue;
    }
    public void shoot()
    {

    }
}