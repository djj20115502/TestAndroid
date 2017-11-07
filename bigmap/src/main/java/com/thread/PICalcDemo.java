package com.thread;

/**
 * Created by DongJunJie on 2017-11-2.
 */

public class PICalcDemo {
    public static void main(String[] args) {
        final TaskManager manager = new TaskManager();
        manager.start();
        TaskExecutor executor = manager.getExecutor();
        Task piTask = TaskHelper.createPiTask(executor, 10000);
        executor.submit(piTask);
    }

}

class PICalcTask extends TaskEventEmitter {
    private final int N;

    PICalcTask(TaskExecutor executor, int n) {
        super(executor);
        if (n < 1) throw new IllegalArgumentException("n must be larger than 0");
        this.N = n;
    }

    public int getN() {
        return N;
    }

    @Override
    protected void run() throws Exception {
        emit("next", 1);
    }
}