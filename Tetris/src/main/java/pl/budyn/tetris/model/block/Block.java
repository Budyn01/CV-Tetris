package pl.budyn.tetris.model.block;

import pl.budyn.tetris.runtime.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hlibe on 27.08.2016.
 */
public abstract class Block implements Cloneable{

    private static final String TAG = "Block";

    private int x;
    private int y;
    private String color;

    private List<boolean[][]> states = new ArrayList<>();
    private int actualState = 0;


    public boolean[][] nextState(){
        Log.debug(TAG, "Next state");
        actualState++;
        if(!states.isEmpty()){
            if((actualState) >= states.size()){
                actualState = 0;
            }
            return states.get(actualState);
        }
        return null;
    }
    public void addState(boolean[][] state){
        if(state != null) {
            states.add(state);
        }
    }
    public void deleteStates(){
        states.clear();
    }
    public boolean[][] getState(){
        return states.get(actualState);
    }

    public int getX() {
        return x;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Block{" +
                "states=" + states.size() +
                ", actualState=" + actualState +
                '}';
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public int getHeight(){
        return getState().length;
    }
    public int getWidth(){
        return getState()[0].length;
    }
    public boolean getShape(int x, int y){
        y = getHeight() - 1 - y;
        return getState()[y][x];
    }

    @Override
    public Block clone() throws CloneNotSupportedException {
        return (Block) super.clone();
    }
    public int getRealWidth(){
        int max = 0;
        boolean[] temp = new boolean[getWidth()];
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                if(!temp[j]) temp[j] = getState()[i][j];
            }
        };
        for (int i = 0; i < temp.length; i++) {
            if(temp[i]) max++;
            System.out.print(temp[i] + ",");
        };
        return max;
    }

}
