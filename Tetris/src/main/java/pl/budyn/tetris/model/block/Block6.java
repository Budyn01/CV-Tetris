package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class Block6 extends Block {
    private final boolean[][] state1 = {
            {true, true, false},
            {false, true, true}
    };
    private final boolean[][] state2 = {
            {false, true},
            {true, true},
            {true, false}
    };

    protected Block6() {
        addState(state1);
        addState(state2);
    }
}
