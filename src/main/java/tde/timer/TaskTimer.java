package tde.timer;

import java.util.TimerTask;

/**
 * Eine Klasse zum ausfuehren von ITask nach einer bestimmten Zeit
 */
public class TaskTimer extends TimerTask{
    private ITask task;

    public TaskTimer(ITask task){
        this.task = task;
    }

    @Override
    public void run() {
        task.nextTask();
    }
}
