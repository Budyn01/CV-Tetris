package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class Block1 extends Block {

    private final boolean[][] state1 = {
            {true, false, false, false},
            {true, false, false, false},
            {true, false, false, false},
            {true, false, false, false}
    };
    private final boolean[][] state2 = {
            {false, false, false, false},
            {false, false, false, false},
            {false, false, false, false},
            {true, true, true, true}
    };

    protected Block1() {
        addState(state1);
        addState(state2);
    }
}
