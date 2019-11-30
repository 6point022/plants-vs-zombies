package sample.game;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private int levelNum;
    public ArrayList<String> listOfUnlockedPlants;
    public ArrayList<Zombie> listOfZombies;
    public ArrayList<Lawnmower> listOfLawnmower;

    public Level(int levelNum) {
        this.levelNum = levelNum;
        this.listOfUnlockedPlants = new ArrayList<>();
        this.listOfUnlockedPlants.add("Peashooter");
        this.listOfZombies = new ArrayList<>();
        this.listOfLawnmower = new ArrayList<Lawnmower>();

        for (int i = 0; i < 1; i++) {
            listOfLawnmower.add(new Lawnmower(159, 160));
        }

        for (int i = 0; i < 3; i++) {
            this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 5,758,110));
        }

        for (int i = 0; i < 2; i++) {
            this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 3,758,110));
        }
    }

    public int getLevelNum() {
        return levelNum;
    }
}
