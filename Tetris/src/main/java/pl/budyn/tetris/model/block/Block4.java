package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class Block4 extends Block {

    private final boolean[][] state1 = {
            {false, true},
            {false, true},
            {false, true},
            {true, true}
    };
    private final boolean[][] state2 = {
            {true, true, true, true},
            {false, false, false, true}
    };
    private final boolean[][] state3 = {
            {true, true},
            {true, false},
            {true, false},
            {true, false}
    };
    private final boolean[][] state4 = {
            {true, false, false, false},
            {true, true, true, true}
    };

    protected Block4() {
        addState(state1);
        addState(state2);
        addState(state3);
        addState(state4);
    }
}
