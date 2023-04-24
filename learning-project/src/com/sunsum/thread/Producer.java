package com.sunsum.thread;

public class Producer extends Thread{

    private final Queue queue;
    private String data;
    public Producer(Queue queue, String data){
        this.queue = queue;
        this.data = data;
    }

    @Override
    public void run(){
        synchronized (queue){
            queue.setData(data);
            queue.notify();
        }

    }
}
