package pl.budyn.tetris.model.board;

import pl.budyn.tetris.Config;

/**
 * Created by hlibe on 02.09.2016.
 */
public class Board {

    // T[x_width][y_height]
    private Field[][] board;
    private Field[][] tempBoard;
    private int width;
    private int height;

    /** Constructor: Creating board to represent mathematical coordinate system
     * @param width Represent X
     * @param height Represent Y
     */
    public Board(int width, int height) {
        board = new Field[width][height];
        this.width = width;
        this.height = height;
        createTab();
    }


    public Field getField(int x, int y){
        y = convert(y);
        if(isOutOfBounds(x, y)){
            //TODO: NULL OBJECT!
            return new Field();
        }
        return board[x][y];
    }
    public void setField(Field field, int x, int y){
        y = convert(y);
        if(!isOutOfBounds(x,y)){
            board[x][y] = field;
        }
    }
    public String toString(){
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append("[");
                if(board[j][i] != null) {
                    builder.append(board[j][i].toString());
                } else {
                    builder.append("NULL");
                }
                builder.append("]");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
    public String toString(boolean simple){
        if(!simple) return toString();
        StringBuilder builder = new StringBuilder("");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                builder.append("[");
                if(board[j][i] != null) {
                    builder.append(board[j][i].getType());
                } else {
                    builder.append("NULL");
                }
                builder.append("]");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public boolean isEmpty(int x, int y){
        if (getField(x, y).getType() != -1) return false;
        return true;
    }
    private boolean isOutOfBounds(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height) {
            System.out.println("Out of bounds! X: " + x  + " Y: " + y);
            return true;
        }
        return false;
    }
    private int convert(int y){
        return (height-1) - y;
    }
    private void createTab(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                board[i][j] = new Field();
            }
        }
    }
    private void createTempTab(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tempBoard[i][j] = new Field();
            }
        }
    }


    private Field[] getRow(int y){
        y = convert(y);
        Field[] row = new Field[width];
        for (int i = 0; i < width; i++) {
            row[i] = board[i][y];
        }
        return row;
    }
    private void setRow(Field[] row, int y){
        y = convert(y);
        System.out.println("Setting: " + y);
        if(row.length > board[0].length) {
            System.out.println("Row is too big!");
        } else {
            for (int i = 0; i < width; i++) {
                board[i][y] = row[i];
            }
        }
    }
    public void deleteRow(int y){
        y = convert(y);
        for (int i = 0; i < width; i++) {
            board[i][y] = new Field();
        }
    }
    public void fallAllAbove(int y){
        for (int i = y; i < (height-1); i++) {
            setRow(getRow(i+1), i);
        }
        deleteRow(height-1);
    }
    private void hide(){
        createTempTab();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(board[i][j].getType() == 2) {
                    tempBoard[i][j] = board[i][j];
                    board[i][j] = new Field();
                }
            }
        }
    }
    private void show(){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if(tempBoard[i][j].getType() == 2) {
                    board[i][j] = tempBoard[i][j];
                }
            }
        }
        createTempTab();
    }
    public int deleteFullRows(){
        int deleted = 0;
        for (int i = 0; i < Config.BOARD_HEIGHT; i++) {
            Field[] temp = getRow(i);
            int sum = 0;
            for (Field f: temp) {
                if(f.getType() != -1) sum++;
            }
            System.out.println("Row sum " + i + ": " + sum);
            if(sum == Config.BOARD_WIDTH){
                deleteRow(i);
                fallAllAbove(i);
                deleted++;
            }
        }
        return deleted;
    }

}
