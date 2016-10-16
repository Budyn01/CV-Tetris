import org.junit.Assert;
import org.junit.Test;
import pl.budyn.tetris.model.Game;
import pl.budyn.tetris.model.block.Block;
import pl.budyn.tetris.model.block.BlockFactory;
import pl.budyn.tetris.model.block.NullBlock;

/**
 * Created by hlibe on 06.09.2016.
 */
public class TestGame {

    @Test
    public void testGame(){
        Game game = new Game();
        Assert.assertEquals(game.getField(5,5).getType(), -1);
    }
    @Test
    public void testRealWidth(){
        Block block = BlockFactory.getBlock(1);
        Assert.assertEquals(block.getRealWidth(), 1);
        block = BlockFactory.getBlock(2);
        Assert.assertEquals(block.getRealWidth(), 2);
    }
    @Test
    public void testBlockFactory(){
        Block block;
        for (int i = 0; i < 100; i++) {
            block = BlockFactory.getRandomBlock();
            Assert.assertNotEquals(block.getClass(), NullBlock.class);
        }
        for (int i = 0; i < 100; i++) {
            block = BlockFactory.getRandomBlock();
            Assert.assertNotEquals(block.getClass(), NullBlock.class);
        }
    }
}
