package pl.budyn.tetris.runtime;

import com.badlogic.gdx.Gdx;
import pl.budyn.tetris.Config;

import java.util.logging.Logger;

/**
 * Created by hlibe on 02.09.2016.
 */
public class Log {

    private static Logger log = Logger.getAnonymousLogger();

    public static void info(String tag, String msg){
        if(Config.LOG_INFO) Gdx.app.log(tag, msg);
    }
    public static void err(String tag, String msg){
        if(Config.LOG_ERROR) Gdx.app.error(tag, msg);
    }
    public static void debug(String tag, String msg){
        if(Config.LOG_DEBUG) log.info(tag + msg);
    }
}
