package com.smallmao.day0308;

import java.util.LinkedList;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class MyThreadPool extends ThreadGroup {
    private boolean isAlive;        //标志线程池是否开启
    private LinkedList taskQueue;       //线程池中的任务队列
    private int threadID;           //线程池中的线程ID
    private static int threadPoolID;        //线程池ID

    //创建新的线程池,numThreads是池中的线程数
    public MyThreadPool(int numThreads) {
        super("ThreadPool-" + (threadPoolID++));
        //设置为该线程池时的daemon属性为true
        super.setDaemon(true);
        this.isAlive = true;
        this.taskQueue = new LinkedList();
        //启动numThreads个工作线程
        for (int i = 0; i < numThreads; i++) {
            new PooledThread().start();

        }
    }

    //添加新任务
    public synchronized void performTask(Task task) {
        if (!this.isAlive) {
            throw new IllegalStateException();
        }
        if (task != null) {
            this.taskQueue.add(task);
            notify();
        }
    }

    //获取任务

    protected synchronized Task getTask() throws InterruptedException {
        //如果任务列表为空且县城池没有被关闭,则继续等待任务
        while (this.taskQueue.size() == 0) {
            if (!this.isAlive) {
                return null;
            }
            wait();
        }
        //取任务列表第一个任务
        return (Task) this.taskQueue.removeFirst();
    }

    //关闭线程池,所有线程停止,不再执行任务
    public synchronized void close() {
        if (isAlive) {
            this.isAlive = false;
            this.taskQueue.clear();
            this.interrupt();   //中断线程池中的所有线程
        }
    }

    //关闭线程池,等待线程池中所有任务执行完,但是不能接受新的任务
    public void join() {

        //通知其他等待线程 该线程已关闭
        synchronized ((this)) {
            isAlive = false;
            notifyAll();
        }

        Thread[] threads = new Thread[this.activeCount()];
        int count = this.enumerate(threads);

        for (int i = 0; i < count; i++) {
            try {
                threads[i].join();      //等待线程运行结束
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    //内部类,用于执行任务的工作线程
    private class PooledThread extends Thread {
        public PooledThread() {
            super(MyThreadPool.this, "PooledThread-" + threadID++);

        }

        public void run() {
            while (!isInterrupted()) {
                Task task = null;
                try {
                    task = getTask();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (task == null) {
                    return;
                }

                //运行任务,吸收异常
                try {
                    task.perform();
                } catch (Throwable t) {
                    uncaughtException(this, t);
                }
            }
        }
    }
}
