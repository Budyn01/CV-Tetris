package pl.budyn.tetris.model.block;

/**
 * Created by hlibe on 27.08.2016.
 */
public class NullBlock extends Block {
    private final boolean[][] state = {
            {true, false, false, true},
            {true, true, false, true},
            {true, false, true, true},
            {true, false, false, true}
    };

    protected NullBlock() {
        addState(state);
    }
}
