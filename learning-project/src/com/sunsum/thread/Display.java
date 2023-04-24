package com.sunsum.thread;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Display {
    public static final ReentrantLock lock = new ReentrantLock();

    public void displayGreetingsMsg(String name) throws InterruptedException {
        if(lock.tryLock(1l,TimeUnit.DAYS)){
            for(int i=0; i <=10; i++){
                String greetingsOfDay = LocalTime.now().isBefore(LocalTime.NOON) && LocalTime.now().isAfter(LocalTime.of(6,0))?"Good Morning: " :
                        LocalTime.now().isBefore(LocalTime.of(18,0)) && LocalTime.now().isAfter(LocalTime.NOON)?"Good Afternoon: ":
                                "Good Evening: ";
                System.out.print(greetingsOfDay);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(" "+name);

            }
            lock.unlock();
        }else {
            System.out.println(Thread.currentThread().getName()+ " unable to perform operation");
        }

    }
}
