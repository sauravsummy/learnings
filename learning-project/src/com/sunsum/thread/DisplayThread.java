package com.sunsum.thread;

public class DisplayThread extends Thread {

    private String name;
    private Display display;
    public DisplayThread(String name, Display display){
        this.name = name;
        this.display = display;
    }

    @Override
    public void run(){
        try {
            display.displayGreetingsMsg(name);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
