package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class Block7 extends Block {
    private final boolean[][] state1 = {
            {false, true, true},
            {true, true, false}
    };
    private final boolean[][] state2 = {
            {true, false},
            {true, true},
            {false, true}
    };

    protected Block7() {
        addState(state1);
        addState(state2);
    }
}
