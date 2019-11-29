package sample;

public abstract class Plant extends Unit {
    private long rechargeTime;

    public long getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(long rechargeTime) {
        this.rechargeTime = rechargeTime;
    }
}

class Shooter extends Plant{
    private int attackvalue;

    Shooter(int attackvalue) {
        this.attackvalue = attackvalue;
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

class PeaShooter extends Shooter{
    PeaShooter(int attackvalue)
    {
        super(attackvalue);
    }
}

class Barrier extends Plant{

}
class Wallnut extends Barrier{

}

class Suntokenproducer extends Plant{

}
class sunflower extends Suntokenproducer{

}