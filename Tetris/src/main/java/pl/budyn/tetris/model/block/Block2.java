package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class Block2 extends Block {

    private final boolean[][] state = {
            {true, true},
            {true, true}
    };

    protected Block2() {
        addState(state);
    }
}
