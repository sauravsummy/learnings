package com.sunsum.general;

import java.util.PriorityQueue;

public class NthHighest {

    //1000,2000,3000,4000,5000,6000,7000,8000,9000;
    public static int getNthHighest(int[]arr , int n){
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int num: arr ){
            if(priorityQueue.size() != n){
                priorityQueue.add(num);
            }else if(priorityQueue.peek() < num){
                priorityQueue.remove();
                priorityQueue.add(num);
            }
        }
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int[]arr = new int[]{5000,2000,1500,8000,4000,2000,5500,4500,9000,10000};
        System.out.println("5th Highest "+getNthHighest(arr,3));
    }
}
