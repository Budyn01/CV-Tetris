package pl.budyn.tetris.model;

import com.badlogic.gdx.Input;
import pl.budyn.tetris.Config;
import pl.budyn.tetris.model.block.Block;
import pl.budyn.tetris.model.block.BlockFactory;
import pl.budyn.tetris.model.board.Board;
import pl.budyn.tetris.model.board.Field;
import pl.budyn.tetris.runtime.Log;

/**
 * Created by hlibe on 03.09.2016.
 */
public class Game {

    private static final String TAG = "Game";

    private Task fallTask = null;
    private Task moveLeft = new Task(new Runnable() {
        @Override
        public void run() {
            moveLeftBlock();
        }
    }, 100);
    private Task moveRight = new Task(new Runnable() {
        @Override
        public void run() {
            moveRightBlock();
        }
    }, 100);
    private Task moveDown = new Task(new Runnable() {
        @Override
        public void run() {
            moveDownBlock();
        }
    }, 100);

    private Block block = null;
    private Board board = null;
    private Player player = null;


    public Game() {
        this.board = new Board(Config.BOARD_WIDTH, Config.BOARD_HEIGHT);
        this.player = Player.getInstance();
        createBlock();
    }

    /**
     * Creating a new falling block.
     */
    private void createBlock(){
        int points = board.deleteFullRows();
        player.addPoints(points*points);
        System.out.println("Points added: " + points);
        if(block != null) addBlock();
        block = BlockFactory.getRandomBlock();
        block.setColor(randomColor());
        block.setPosition(Config.BLOCK_START_POSITION_X, Config.BLOCK_START_POSITION_Y);
        Log.debug(TAG, "Block created: " + block.getClass().getSimpleName());
        addBlock();
    }

    /**
     * Add falling block to board.
     */
    private void addBlock(){
        addBlock(1);
    }
    private void addBlock(int type){
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
               if(block.getShape(j, i)) {
                   Field field = new Field(type, j, i);
                   field.setColor(block.getColor());
                   board.setField(field, block.getX()+j, block.getY()+i);
               }
            }
        }
    }

    /**
     * Deleting falling block from board.
     */
    private void deleteBlock(){
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if(block.getShape(j, i)) {
                    board.setField(new Field(), block.getX()+j, block.getY()+i);
                }
            }
        }
    }

    /** Return a random color of block.
     * @return String with texture name.
     */
    private String randomColor(){
        switch((int) Math.floor(Math.random()*5)){
            case 0:
                return "texture_red";
            case 1:
                return "texture_white";
            case 2:
                return "texture_blue";
            case 3:
                return "texture_green";
            case 4:
                return "texture_pink";
            case 5:
                return "texture_yellow";
            default:
                return "texture_black";
        }
    }

    /**
     * Moving falling block by 1 field.
     */
    private synchronized void moveLeftBlock(){
        deleteBlock();
            if (isNoCollision(block.getX() - 1, block.getY())) {
                block.setPosition(block.getX() - 1, block.getY());
            }
            addBlock(2);
    }
    private synchronized void moveRightBlock(){
        deleteBlock();
        if (isNoCollision(block.getX() + 1, block.getY())) {
            block.setPosition(block.getX() + 1, block.getY());
        }
        addBlock(2);
    }
    private synchronized void moveDownBlock(){
        deleteBlock();
        if(isNoCollision(block.getX(), block.getY()-1)){
            block.setPosition(block.getX(), block.getY()-1);
            addBlock(2);
        } else {
            createBlock();
            addBlock();
        }

    }
    private synchronized void moveUpBlock(){
        deleteBlock();
        if (isNoCollision(block.getX(), block.getY() + 1)) {
            block.setPosition(block.getX(), block.getY() + 1);
            addBlock(2);
        } else {
            createBlock();
            addBlock();
        }

    }
    private void rotateBlock(){
        deleteBlock();
        if(isNoCollision(block.getX(), block.getY()+1, true)){
            block.nextState();
        }
        addBlock();
    }

    /**
     *  Checking falling block collision with other objects on position x,y.
     * @param x Position x to check collision of falling block.
     * @param y Position y to check collision of falling block.
     * @return TRUE if space is empty, else returning FALSE
     */
    private boolean isNoCollision(int x, int y){
        System.out.println(x);
        if(x < 0 || x > (Config.BOARD_WIDTH - block.getRealWidth())) return false;
        if(y < (0)) {
            return false;
        }
        for (int i = 0; i < block.getHeight(); i++) {
            for (int j = 0; j < block.getWidth(); j++) {
                if(block.getShape(j, i)) {
                    if(!board.isEmpty(x+j, y+i)) {
                        Log.debug(TAG, "CheckNoCollision: false");
                        return false;
                    }
                }
            }
        }
        Log.debug(TAG, "CheckNoCollision: true");
        return true;
    }
    private boolean isNoCollision(int x, int y, boolean nextState){
        System.out.println(x);
        if(x < 0 || x >= Config.BOARD_WIDTH) return false;
        if(y > (Config.BOARD_HEIGHT - block.getHeight())) {
            return false;
        }
        if(!nextState) return isNoCollision(x,y);
        Block tempBlock = null;
        try {
            tempBlock = block.clone();
            tempBlock.nextState();
            for (int i = 0; i < tempBlock.getHeight(); i++) {
                for (int j = 0; j < tempBlock.getWidth(); j++) {
                    if(tempBlock.getShape(j, i)) {
                        if(!board.isEmpty(x+j, y+i)) {
                            Log.debug(TAG, "CheckNoCollisionNextState: false");
                            return false;
                        }
                    }
                }
            }
            Log.debug(TAG, "CheckNoCollisionNextState: true");
            return true;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return false;
    }

    /** User key input controller
     * @param keycode Key pressed by player
     * @param type Key is pressed(DOWN) or released(UP)
     */
    public void handleKey(int keycode, KeyEventType type){
        if(keycode == Input.Keys.LEFT){
            if( type == KeyEventType.DOWN){
                moveLeft.start();
            } else {
                moveLeft.stop();
            }
        }
        if(keycode == Input.Keys.RIGHT){
            if( type == KeyEventType.DOWN){
                moveRight.start();
            } else {
                moveRight.stop();
            }
        }
        if(keycode == Input.Keys.DOWN){
            if( type == KeyEventType.DOWN){
                moveDown.start();
            } else {
                moveDown.stop();
            }
        }
        if(keycode == Input.Keys.R && type == KeyEventType.DOWN){
            rotateBlock();
        }
    }
    public Field getField(int x, int y){
        return board.getField(x,y);
    }

    /**
     * Start game
     */
    public void start(){
        fallTask = new Task(new Runnable() {
            @Override
            public void run() {
                moveDownBlock();
            }
        }, (int) (1000/Config.FALL_PER_SECOND));
        fallTask.start();
        Log.info(TAG, "Block falling starts");
    }

    /**
     * Stop game
     */
    public void stop(){
        fallTask.stop();
    }


}
