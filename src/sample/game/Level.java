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

        if (levelNum == 1) {
            addUnlockedPlants(1);
            addLawnmowers(1);
            createZombieWaves(1);
        }

        else if (levelNum == 2) {
            addUnlockedPlants(2);
            addLawnmowers(2);
            createZombieWaves(2);
        }

        else if (levelNum == 3) {
            addUnlockedPlants(3);
            addLawnmowers(3);
            createZombieWaves(3);
        }

        else if (levelNum == 4) {
            addUnlockedPlants(4);
            addLawnmowers(4);
            createZombieWaves(4);
        }

        else if (levelNum == 5) {
            addUnlockedPlants(5);
            addLawnmowers(5);
            createZombieWaves(5);
        }
    }

    private void addUnlockedPlants(int levelNum) {
        if (levelNum >= 1) {
            this.listOfUnlockedPlants.add("Peashooter");
        }

        if (levelNum >= 2) {
            this.listOfUnlockedPlants.add("Sunflower");
        }

        if (levelNum >= 3) {
            this.listOfUnlockedPlants.add("Wallnut");
        }

        if (levelNum >= 4) {
            this.listOfUnlockedPlants.add("Cherry Bomb");
        }
    }

    private void addLawnmowers(int levelNum) {
        if (levelNum == 1) {
            listOfLawnmower.add(new Lawnmower(159, 160));
        }

        else if (levelNum == 2) {
            listOfLawnmower.add(new Lawnmower(159, 160));
            listOfLawnmower.add(new Lawnmower(159, 240));
            listOfLawnmower.add(new Lawnmower(159, 80));

        }

        else if (levelNum == 3) {
            listOfLawnmower.add(new Lawnmower(159, 160));
            listOfLawnmower.add(new Lawnmower(159, 240));
            listOfLawnmower.add(new Lawnmower(159, 80));
        }

        else if (levelNum == 4) {
            listOfLawnmower.add(new Lawnmower(159, 160));
            listOfLawnmower.add(new Lawnmower(159, 240));
            listOfLawnmower.add(new Lawnmower(159, 80));
            listOfLawnmower.add(new Lawnmower(159, 310));
            listOfLawnmower.add(new Lawnmower(159, 10));
        }

        else if (levelNum == 5) {
            listOfLawnmower.add(new Lawnmower(159, 30));
            listOfLawnmower.add(new Lawnmower(159, 90));
            listOfLawnmower.add(new Lawnmower(159, 170));
            listOfLawnmower.add(new Lawnmower(159, 240));
            listOfLawnmower.add(new Lawnmower(159, 310));
        }
    }

    private void createZombieWaves(int levelNum) {
        if (levelNum == 1) {
            for (int i = 0; i < 5; i++) {
                this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 5, 758, 160));
            }
        }

        else if (levelNum == 2) {
            this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 5, 758, 150));

            for (int i = 0; i < 2; i++) {
                this.listOfZombies.add(new NormalZombie("Football Zombie", 10, 5, 5, 758, 220));
            }

            for (int i = 0; i < 2; i++) {
                this.listOfZombies.add(new FootballZombie("Football Zombie", 10, 3, 5, 758, 80));
            }
        }

        else if (levelNum == 3) {

        }

        else if (levelNum == 4) {

        }

        else if (levelNum == 5) {
            this.listOfZombies.add(new NormalZombie("Normal Zombie", 10, 2, 5, 758, 150));

            for (int i = 0; i < 1; i++) {
                this.listOfZombies.add(new ConeheadZombie("Conehead Zombie", 30, 3, 5, 758, 300));
            }

            for (int i = 0; i < 1; i++) {
                this.listOfZombies.add(new ConeheadZombie("Conehead Zombie", 30, 3, 5, 758, 150));
            }

            for (int i = 0; i < 2; i++) {
                this.listOfZombies.add(new FootballZombie("Football Zombie", 10, 3, 5, 758, 220));
            }
        }
    }

    public int getLevelNum() {
        return levelNum;
    }
}
