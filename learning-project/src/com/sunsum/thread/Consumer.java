package com.sunsum.thread;

public class Consumer extends Thread{
    private final Queue queue;
    public Consumer(Queue queue){
        this.queue = queue;
    }

    @Override
    public void run() {
        synchronized (queue){
            try {
                System.out.println("waiting ");
                queue.wait(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(queue.getData());
        }

    }
}
