package pl.budyn.tetris.model.board;

/**
 * Created by hlibe on 02.09.2016.
 */
public class Field {

    private int type;
    private String color;
    private int x;
    private int y;

    public Field(){
        this.type = -1;
        this.x = -1;
        this.y = -1;
        this.color = "texture_black";
    }
    public Field(int id, int x, int y) {
        this.type = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "type:" + this.type + " x:" + this.x + " y:" + this.y + " color: " + this.color;
    }

    public int getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
