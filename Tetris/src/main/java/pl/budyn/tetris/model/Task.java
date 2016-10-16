package pl.budyn.tetris.model;

import pl.budyn.tetris.runtime.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by hlibe on 07.09.2016.
 */
public class Task {
    private Timer timer;
    private int time;
    private Runnable runnable;
    private TimerTask task;
    public Task(Runnable runnable, int time) {
        this.time = time;
        this.runnable = runnable;
    }

    public void stop(){
        if(timer != null) {
            try {
                timer.cancel();
            } catch (IllegalStateException e){
                Log.err("123", "Error");
            }
        }
    }
    public void start(){
        task = new TimerTask() {
            @Override
            public void run() {
                runnable.run();
            }
        };
        timer = new Timer();
        timer.schedule(task, 0, time);
    }
}
