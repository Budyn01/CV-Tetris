package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class Block3 extends Block {

    private final boolean[][] state1 = {
            {true, false},
            {true, false},
            {true, false},
            {true, true}
    };
    private final boolean[][] state2 = {
            {false, false, false, true},
            {true, true, true, true}
    };
    private final boolean[][] state3 = {
            {true, true},
            {false, true},
            {false, true},
            {false, true}
    };
    private final boolean[][] state4 = {
            {true, true, true, true},
            {true, false, false, false}
    };

    protected Block3() {
        addState(state1);
        addState(state2);
        addState(state3);
        addState(state4);
    }
}

