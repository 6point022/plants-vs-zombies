package sample.game;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private Game game;
    private int suncollected;
    private static final long serialVersionUID=21;
    public void setName(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public int getSuncollected() {
        return suncollected;
    }

    public void setSuncollected(int suncollected) {
        this.suncollected = suncollected;
    }
}
