package sample.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Level implements Serializable {
    private int levelNum;
    public ArrayList<String> listOfUnlockedPlants;
    public ArrayList<Zombie> listOfZombies;
    public ArrayList<Lawnmower> listOfLawnmower;

    public Level(int levelNum) {
        this.levelNum = levelNum;
        this.listOfUnlockedPlants = new ArrayList<>();
        this.listOfZombies = new ArrayList<>();
        this.listOfLawnmower = new ArrayList<Lawnmower>();

        if (levelNum >= 1) {
            this.listOfUnlockedPlants.add("Peashooter");
            listOfLawnmower.add(new Lawnmower(159, 160));

            if (levelNum == 1) {
                for (int i = 0; i < 5; i++) {
                    this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 5, 758, 160));
                }
            }
        }

        if (levelNum >= 2) {
            this.listOfUnlockedPlants.add("Sunflower");
            listOfLawnmower.add(new Lawnmower(159, 240)); // 160 original Y
            listOfLawnmower.add(new Lawnmower(159, 80)); // 160 original Y

            if (levelNum == 2) {
                this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 5, 758, 150));

                for (int i = 0; i < 2; i++) {
                    this.listOfZombies.add(new NormalZombie("Football Zombie", 10, 3, 5, 758, 220));
                }

                for (int i = 0; i < 2; i++) {
                    this.listOfZombies.add(new FootballZombie("Football Zombie", 10, 3, 5, 758, 80));
                }
            }
        }

        if (levelNum >= 3) {
            this.listOfUnlockedPlants.add("Wallnut");
        }

        for (int i = 0; i < 2; i++) {
            this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 3,758,130));
        }
    }

    public int getLevelNum() {
        return levelNum;
    }
}
