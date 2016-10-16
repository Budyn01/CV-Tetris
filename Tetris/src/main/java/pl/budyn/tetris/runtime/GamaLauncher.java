package pl.budyn.tetris.runtime;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.budyn.tetris.view.View;

/**
 * Simple Tetris Game
 */
public class GamaLauncher {

    public static void main(String[] args){
        initializeLibGdx();
    }

    private static void initializeLibGdx(){
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "RPG GAME";
        config.width = 320;
        config.height = 640;
        config.fullscreen = false;
        new LwjglApplication(new View(), config);
    }
}
