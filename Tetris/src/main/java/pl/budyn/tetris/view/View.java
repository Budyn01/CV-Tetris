package pl.budyn.tetris.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import pl.budyn.tetris.Config;
import pl.budyn.tetris.model.Game;
import pl.budyn.tetris.model.KeyEventType;
import pl.budyn.tetris.model.Player;
import pl.budyn.tetris.model.board.Field;
import pl.budyn.tetris.runtime.Log;
import pl.budyn.tetris.service.ResourceManager;
import pl.budyn.tetris.service.TextService;

import javax.xml.crypto.Data;
import java.util.Date;

/**
 * Created by hlibe on 04.09.2016.
 */
public class View extends ApplicationAdapter {

    private ResourceManager resourceManager;
    private TextService textService;
    private TextService.Config config;
    private Batch batch;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Game game;

    private Player player;

    @Override
    public void create() {
        resourceManager = ResourceManager.getInstance();
        textService = TextService.getInstance();
        config = new TextService.Config();
        config.setColor(Color.WHITE);
        config.setFontSize(128);
        batch = new SpriteBatch();
        player = Player.getInstance();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        viewport = new FitViewport(Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT, camera);
        game = new Game();
        game.start();
        Gdx.input.setInputProcessor(new InputAdapter(){
            @Override
            public boolean keyDown(int keycode) {
                game.handleKey(keycode, KeyEventType.DOWN);
                return true;
            }

            @Override
            public boolean keyUp(int keycode) {
                game.handleKey(keycode, KeyEventType.UP);
                return true;
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        for (int i = 0; i < Config.BOARD_WIDTH; i++) {
            for (int j = 0; j < Config.BOARD_HEIGHT; j++) {
                Field field = game.getField(i, j);
                batch.draw(resourceManager.getImageResource(field.getColor()), i * 64, j * 64);
            }
        }
        batch.end();
        textService.renderText(batch, "Points: " + player.getPoints(), Config.WINDOW_WIDTH / 2, 100, config);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.update();
    }
}
