package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class Block5 extends Block {

    private final boolean[][] state1 = {
            {false, true, false},
            {true, true, true}
    };
    private final boolean[][] state2 = {
            {false, true},
            {true, true},
            {false, true}
    };
    private final boolean[][] state3 = {
            {true, true, true},
            {false, true, false}
    };
    private final boolean[][] state4 = {
            {true, false},
            {true, true},
            {true, false}
    };

    protected Block5() {
        addState(state1);
        addState(state2);
        addState(state3);
        addState(state4);
    }
}
