package com.thread;

/**
 * Created by DongJunJie on 2017-11-2.
 */

/**
 * 任务执行器
 */
public interface TaskExecutor extends Task {

    /**
     * 提交一个任务
     * @param task
     */
    public void submit(Task task);
}