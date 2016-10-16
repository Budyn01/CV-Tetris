package pl.budyn.tetris.model.block;

import pl.budyn.tetris.model.block.Block;
import pl.budyn.tetris.model.block.Block1;

/**
 * Created by hlibe on 27.08.2016.
 */
public class BlockFactory {

    public static Block getBlock(int id){
        switch(id){
            case 1:
                return new Block1();
            case 2:
                return new Block2();
            case 3:
                return new Block3();
            case 4:
                return new Block4();
            case 5:
                return new Block5();
            case 6:
                return new Block6();
            case 7:
                return new Block7();
            default:
                return new NullBlock();
        }
    }
    public static Block getRandomBlock(){
        return getBlock((int) Math.floor(Math.random()*7)+1);
    }
}
