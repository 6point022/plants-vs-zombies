package sample.game;

import java.io.Serializable;

public abstract class Unit implements Serializable {
    private String name;
    private int health;
    private double positionX;
    private double positionY;

    public int getHealth() {
        return health;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public String getName() {
        return name;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

}

