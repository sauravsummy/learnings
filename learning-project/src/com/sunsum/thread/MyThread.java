package com.sunsum.thread;

public class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println();
        for(int i =0; i<10;i++){
            System.out.println("printed by "+Thread.currentThread().getName() + " :"+i);
        }
    }
}
