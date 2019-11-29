package sample.game;

import java.util.ArrayList;

public class Level {
    private int levelNum;
    public ArrayList<String> listOfUnlockedZombies;
    public ArrayList<String> listOfUnlockedPlants;

    public Level(int levelNum) {
        this.levelNum = levelNum;
        this.listOfUnlockedZombies = new ArrayList<>();
        this.listOfUnlockedPlants = new ArrayList<>();
        this.listOfUnlockedPlants.add("Peashooter");
        this.listOfUnlockedZombies.add("Normal Zombie");
    }

    public int getLevelNum() {
        return levelNum;
    }
}
