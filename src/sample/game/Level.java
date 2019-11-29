package sample.game;

import java.util.ArrayList;
import java.util.HashMap;

public class Level {
    private int levelNum;
    public ArrayList<String> listOfUnlockedPlants;
    public ArrayList<Zombie> listOfZombies;

    public Level(int levelNum) {
        this.levelNum = levelNum;
        this.listOfUnlockedPlants = new ArrayList<>();
        this.listOfUnlockedPlants.add("Peashooter");
        this.listOfZombies = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 5, 5,758,110));
        }

        for (int i = 0; i < 2; i++) {
            this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 5, 3,758,110));
        }
    }

    public int getLevelNum() {
        return levelNum;
    }
}
