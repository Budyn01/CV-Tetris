package pl.budyn.tetris.model;

/**
 * Created by hlibe on 03.09.2016.
 */
public class Player {

    public static Player getInstance() {
        if(instance == null) {
            instance = new Player();
        }
        return instance;
    }

    private static Player instance = null;
    private String name;
    private int points;
    private int level;

    private Player() {
        this.level = 0;
        this.points = 0;
        this.name = "player";
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getLevel() {
        return level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    public void addPoints(int points) {
        this.points += points;
    }
    public void setLevel(int level) {
        this.level = level;
    }

}
