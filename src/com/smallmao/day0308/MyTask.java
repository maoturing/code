package com.smallmao.day0308;

/**
 * Created by Administrator on 2017/3/8 0008.
 */
public class MyTask implements Task{
    private int taskID = 0;

    public MyTask(int id){
        this.taskID = id;
    }

    public void perform()throws Exception{
        System.out.println("MyTask" + taskID + ":start");
        Thread.sleep(1000);
        System.out.println("MyTask" + taskID + ":end");
    }

}
