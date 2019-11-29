package sample.game;

public abstract class Zombie extends Unit {
    private int speed;

    Zombie(String name, int health, int speed) {
        this.setName(name);
        this.setHealth(health);
        this.setSpeed(speed);
    }

    public void move() {

    }

    public void bite() {

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

class NormalZombie extends Zombie {
    NormalZombie(String name, int health, int speed) {
        super(name, health, speed);
    }
}

class FlagZombie extends Zombie {
    FlagZombie(String name, int health, int speed) {
        super(name, health, speed);
    }
}

class ConeheadZombie extends Zombie{
    ConeheadZombie(String name, int health, int speed) {
        super(name, health, speed);
    }
}


