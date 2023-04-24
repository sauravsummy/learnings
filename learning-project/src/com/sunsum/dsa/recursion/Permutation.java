package com.sunsum.dsa.recursion;

public class Permutation {

    public static void main(String[] args) {
        printAllPossible("abc","");
    }

    public static void printAllPossible(String str, String result){
        if(str.isEmpty()){
            System.out.println(result);
            return;
        }
        String fc = str.substring(0,1);
        for(int i = 0 ; i <= result.length(); i++){
            String f = result.substring(0,i);
            String l = result.substring(i,result.length());
            printAllPossible(str.substring(1),f+fc+l);
        }

    }
}
