package sample.game;

import java.util.ArrayList;

public class Game {
    private Level currLevel;
    private int totalSunTokens;
//    public ArrayList<Plant> listOfAvailablePlants;
    public ArrayList<Plant> listOfPlants;
    public ArrayList<Zombie> listOfWalkingZombies;

    public Game(Level level) {
        this.currLevel = level;
        this.totalSunTokens = 0;
        this.listOfPlants = new ArrayList<>();
        this.listOfWalkingZombies = new ArrayList<>();
//        this.listOfLawnmower.add(new Lawnmower());
    }
}
